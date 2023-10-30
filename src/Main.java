import java.io.InputStreamReader;

import controller.ControllerImpl;
import model.Project;
import view.View;
import view.ViewImpl;

import javax.swing.SwingUtilities;

import view.GUIView;

/**
 * Represents a Main class for the collage project.
 */
public class Main {

  /**
   * Allows user to run the collage project in either text or GUI form.
   *
   * @param args - represents array of Strings.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      SwingUtilities.invokeLater(() -> {
        // Create a new project
        Project project = new Project(800, 600, 255);

        // Create an instance of GUIView
        GUIView guiView = new GUIView(project);

        // Set the JFrame to be visible
        guiView.getFrame().setVisible(true);
      });
    } else if (args.length == 1 && args[0].equals("-text")) {
      Project p = new Project(500, 500, 255);
      View v = new ViewImpl(p, System.out);
      ControllerImpl c = new ControllerImpl(p, v, new InputStreamReader(System.in));
      c.collageProject();
    } else if (args.length == 2 && args[0].equals("-file")) {
      Project p = new Project(500, 500, 255);
      View v = new ViewImpl(p, System.out);
      ControllerImpl controller = new ControllerImpl(p, v, new InputStreamReader(System.in));
      controller.executeScript("colllllllllage/code/Script.txt");
    } else {
      System.out.println("Invalid arguments. Please enter one of the following lines:");
      System.out.println("java -jar Program.jar -file path-of-script-file");
      System.out.println("java -jar Program.jar -text");
      System.out.println("java -jar Program.jar");
    }
  }
}




