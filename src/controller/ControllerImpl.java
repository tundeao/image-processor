package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.NoSuchElementException;

import model.IProject;
import view.View;


/**
 * Represents the Controller for the Collage project.
 */
public class ControllerImpl implements IController {

  protected IProject p;
  protected View v;
  protected Readable in;
  private boolean newOrLoadedProj;

  /**
   * Represents constructor for ControllerImpl.
   * @param p - represents a Project.
   * @param v - represents a View.
   * @param in - represents a Readable.
   */
  public ControllerImpl(IProject p, View v, Readable in) throws IllegalArgumentException {
    if (p == null || v == null || in == null) {
      throw new IllegalArgumentException("A parameter is null");
    }
    this.p = p;
    this.v = v;
    this.in = in;
    this.newOrLoadedProj = false;
  }

  /**
   * Collage Project is the main method in controller that allows the user
   * to interact with the model.
   * @throws IllegalStateException - throws exception whenever error occurs in collageProject.
   */
  public void collageProject() throws IllegalStateException {
    Scanner sc = new Scanner(this.in);
    String nxt = "";
    while (true) {
      try {
        nxt = sc.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException();
      }
      saveOrLoadProject(nxt, sc);
      if (this.newOrLoadedProj) {
        break;
      }
      if (nxt.equals("quit") || nxt.equals("q")) {
        return;
      }
    }
    while (true) {
      nxt = sc.next();
      saveOrLoadProject(nxt, sc);
      if (nxt.equals("add-layer")) {
        String layerName = sc.next();
        try {
          int layerTransparency = sc.nextInt();
          p.addLayer(layerName, layerTransparency);
        } catch (NoSuchElementException e) {
          p.addLayer(layerName);
        }
      }
      else if (nxt.equals("add-image-to-layer")) {
        String layerName = sc.next();
        String imageName = sc.next();
        int x = sc.nextInt();
        int y = sc.nextInt();
        p.addImageToLayer(layerName, imageName, x, y);
      }
      else if (nxt.equals("set-filter")) {
        String layerName = sc.next();
        String filterName = sc.next();
        p.setFilter(layerName, filterName);
      }
      else if (nxt.equals("save-project")) {
        String filePath = sc.next();
        try {
          p.saveProject(filePath);
        } catch (IOException e) {
          writeMessage("Unable to save project to given file path.");
        }
      }
      else if (nxt.equals("save-image")) {
        String filePath = sc.next();
        try {
          p.saveImage(filePath);
        } catch (IOException e) {
          writeMessage("Unable to save image to given file path.");
        }
      }
      else if (nxt.equals("quit") || nxt.equals("q")) {
        return;
      }
    }
  }

  /**
   * Writes a message to the user, does this by using the renderMessage method in the view.
   * @param message - is the message that is displayed to the user.
   */
  public void writeMessage(String message) {
    try {
      this.v.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Loads or saves a project to a given file path.
   * @param nxt - the next integer in the file (new project).
   * @param sc - a scanner that finds the file path (save/load project).
   */
  private void saveOrLoadProject(String nxt, Scanner sc) {
    if (nxt.equals("new-project")) {
      try {
        int x = sc.nextInt();
        int y = sc.nextInt();
        p = p.newProject(x, y, 255);
        this.newOrLoadedProj = true;
      } catch (NoSuchElementException e) {
        writeMessage("Unable to create a new project, please try again.");
      }
    }
    else if (nxt.equals("load-project")) {
      try {
        String path = sc.next();
        p.loadProject(path);
        this.newOrLoadedProj = true;
      } catch (NoSuchElementException e) {
        writeMessage("Unable to load project, please try again. " + e.getMessage());
      } catch (IOException e) {
        writeMessage("No file is found.");
      }
    }
  }

  /**
   * Executes the script file when it is called on.
   * @param filePath - the path to the script file being executed.
   * @throws IllegalStateException if the file cannot be found or read.
   */
  public void executeScript(String filePath) throws IllegalStateException {
    try (Reader reader = new FileReader(filePath);
         BufferedReader bufferedReader = new BufferedReader(reader)) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] commandParts = line.split(" ");
        String command = commandParts[0];
        switch (command) {
          case "new-project":
            int x = Integer.parseInt(commandParts[1]);
            int y = Integer.parseInt(commandParts[2]);
            p = p.newProject(x, y, 255);
            break;
          case "load-project":
            String path = commandParts[1];
            p.loadProject(path);
            break;
          case "add-layer":
            String layerName = commandParts[1];
            try {
              int layerTransparency = Integer.parseInt(commandParts[2]);
              p.addLayer(layerName, layerTransparency);
            } catch (IndexOutOfBoundsException e) {
              p.addLayer(layerName);
            }
            break;
          case "add-image-to-layer":
            String layerName2 = commandParts[1];
            String imageName = commandParts[2];
            int x2 = Integer.parseInt(commandParts[3]);
            int y2 = Integer.parseInt(commandParts[4]);
            p.addImageToLayer(layerName2, imageName, x2, y2);
            break;
          case "set-filter":
            String layerName3 = commandParts[1];
            String filterName = commandParts[2];
            p.setFilter(layerName3, filterName);
            break;
          case "save-project":
            String filePath2 = commandParts[1];
            try {
              p.saveProject(filePath2);
            } catch (IOException e) {
              writeMessage("Unable to save project to given file path.");
            }
            break;
          case "save-image":
            String filePath3 = commandParts[1];
            try {
              p.saveImage(filePath3);
            } catch (IOException e) {
              writeMessage("Unable to save image to given file path.");
            }
            break;
          case "quit":
          case "q":
            return;
          default:
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading script file: " + e.getMessage());
    }
  }
}
