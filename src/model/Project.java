package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Represents a project, made up of multiple layers. Each project has a height, width, max value a
 * list of layers and its names and the last filter used in the project.
 */
public class Project implements IProject {
  private int height;
  private int width;
  private int max;
  private List<ILayer> layers;
  private List<String> names;
  private String filter;

  /**
   * Represents constructor for the project class.
   *
   * @param height - represents height of project.
   * @param width  - represents width of project.
   * @param max    - represents max value of rgb values in project.
   */
  public Project(int height, int width, int max) {
    if (height < 0 || width < 0 || max < 0) {
      throw new IllegalArgumentException("Unable to create a project with negative parameters");
    }
    this.height = height;
    this.width = width;
    this.max = max;
    this.layers = new ArrayList<ILayer>();
    this.names = new ArrayList<String>();
    Pixel[][] p = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        p[i][j] = new Pixel(255, 255, 255);
      }
    }
    Layer base = new Layer(p);
    layers.add(base);
    names.add("canvas");
    filter = "normal";
  }

  /**
   * Represents constructor for the project class. This constructor is used for loadProject.
   *
   * @param filepath - represents the filepath of a given collage project.
   */
  public Project(String filepath) {
    try {
      loadProject(filepath);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to find file.");
    }
  }

  /**
   * Adds a new blank layer to the project.
   *
   * @param name - represents the name of the new layer.
   */
  public void addLayer(String name) {
    this.addLayer(name, 255);
  }

  /**
   * Adds a new blank layer to the project.
   *
   * @param name - represents the name of the new layer.
   */
  public void addLayer(String name, int a) {
    for (int i = 0; i < names.size(); i++) {
      if (name.equals(names.get(i))) {
        throw new IllegalArgumentException("Another Layer already has this name.");
      }
    }
    Pixel[][] p = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        p[i][j] = new Pixel(255, 255, 255, 0);
      }
    }
    Layer base = new Layer(p, a);
    layers.add(base);
    names.add(name);
  }

  /**
   * Represents a getter method for the height field.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Represents a getter method for the width field.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Represents a getter method for the max field.
   */
  public int getMax() {
    return max;
  }

  /**
   * Represents a getter method for the list of layers.
   */
  public List<ILayer> getLayers() {
    return this.layers;
  }

  /**
   * Represents a getter method for the list of layer names.
   */
  public List<String> getNames() {
    return this.names;
  }

  /**
   * Saves an image with all updated filters.
   */
  public void saveImage(String path) throws IOException {
    if (path.substring(path.indexOf(".")).equals(".jpg")
            || path.substring(path.indexOf(".")).equals(".jpeg")) {
      BufferedImage b = this.saveImageHelp();
      File f = new File(path);
      ImageIO.write(b, "JPEG", f);
    } else if (path.substring(path.indexOf(".")).equals(".png")) {
      BufferedImage b = this.saveImageHelp();
      File f = new File(path);
      ImageIO.write(b, "PNG", f);
    } else {
      FileWriter fileW = new FileWriter(path);
      ILayer image = compressLayers();
      IPixel[][] pixels = image.getPixels();

      fileW.write("P3\n");
      fileW.write(width + " " + height + "\n");
      fileW.write(max + "\n");

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          IPixel p = pixels[i][j];

          fileW.write(p.getR() + "  " + p.getG() + "  " + p.getB() + "  ");
        }
        fileW.write("\n");
      }
      fileW.close();
    }
  }

  /**
   * Saves the project given a correct file path.
   */
  public void saveProject(String path) throws IOException {
    FileWriter fileW = new FileWriter(path);

    fileW.write("C1\n");
    fileW.write(this.getWidth() + " " + this.getHeight() + "\n");
    fileW.write(this.getMax() + "\n");

    for (int i = 0; i < this.layers.size(); i++) {
      ILayer l = this.layers.get(i);
      String lName = this.names.get(i);
      String filterName = this.filter;
      int alpha = l.getA();


      fileW.write(lName + " " + filterName + " " + alpha + "\n");

      IPixel[][] pixels = l.getPixels();
      for (int j = 0; j < this.getHeight(); j++) {
        for (int k = 0; k < this.getWidth(); k++) {
          IPixel px = pixels[j][k];
          fileW.write(px.getR() + "  " + px.getG() + "  " + px.getB() + "  ");
        }
        fileW.write("\n");
      }
    }
    fileW.close();
  }

  /**
   * Loads project given correct file path.
   */
  public void loadProject(String path) throws IOException {
    this.layers = new ArrayList<ILayer>();
    this.names = new ArrayList<String>();
    File file = new File(path);
    Scanner sc = new Scanner(file);
    String name = sc.nextLine();
    int width = sc.nextInt();
    this.width = width;
    int height = sc.nextInt();
    this.height = height;
    int max = sc.nextInt();
    this.max = max;
    Project loadedProj = new Project(height, width, max);

    while (sc.hasNext()) {

      String layerName = sc.next();
      String filterName = sc.next();
      int a = sc.nextInt();


      Pixel[][] pixels = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = Integer.parseInt(sc.next());
          int g = Integer.parseInt(sc.next());
          int b = Integer.parseInt(sc.next());

          pixels[i][j] = new Pixel(r, g, b);
        }
      }

      Layer layer = new Layer(pixels, a);
      names.add(layerName);
      layers.add(layer);


    }

    sc.close();
  }

  /**
   * Places a given image on a given layer offset by a specified position.
   *
   * @param layerName - the name of the layer that the image is being placed onto.
   * @param imageName - the name of the image that is being placed as (x, y) on the layer.
   * @param x         - the x-coordinate of the position where the image is being placed.
   * @param y         - the y-coordinate of the position where the image is being placed.
   */
  public void addImageToLayer(String layerName, String imageName, int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid x or y value");
    }

    if (imageName.substring(imageName.indexOf(".")).equals(".jpg")
            || imageName.substring(imageName.indexOf(".")).equals(".jpeg")
            || imageName.substring(imageName.indexOf(".")).equals(".png")) {
      ILayer l = this.getLayer(layerName);
      Pixel[][] p;
      try {
        p = this.loadImage(imageName);
      } catch (IOException e) {
        p = new Pixel[0][0];
      }
      for (int i = 0; i < p[0].length; i++) {
        for (int j = 0; j < p.length; j++) {
          if (i + x < width && j + y < height) {
            l.getPixels()[j + y][i + x] = p[j][i];
          }
        }
      }
      int a = 0;
      for (int i = 0; i < names.size(); i++) {
        a = i;
      }
      layers.set(a, l);
    } else {
      Pixel[][] p = ImageUtil.readPPM(imageName);
      ILayer l = this.getLayer(layerName);
      if (p.length < 1) {
        return;
      }
      for (int i = 0; i < p[0].length; i++) {
        for (int j = 0; j < p.length; j++) {
          if (i + x < width && j + y < height) {
            l.getPixels()[j + y][i + x] = p[j][i];
          }
        }
      }
      int a = 0;
      for (int i = 0; i < names.size(); i++) {
        a = i;
      }
      layers.set(a, l);
    }
  }

  /**
   * Sets filter to a layer given the name of a layer and the name of the filter.
   *
   * @param name - represents the name of a layer.
   * @param flt  - represents the name of a filter.
   */
  public void setFilter(String name, String flt) {
    int x = 0;
    for (int i = 0; i < names.size(); i++) {
      if (name.equals(names.get(i))) {
        x = i;
      }
    }
    if (flt.equals("red-component")) {
      layers.get(x).filter("red");
      filter = "red-component";
    } else if (flt.equals("green-component")) {
      layers.get(x).filter("green");
      filter = "green-component";
    } else if (flt.equals("blue-component")) {
      layers.get(x).filter("blue");
      filter = "blue-component";
    } else if (flt.equals("brighten-value")) {
      layers.get(x).brightenValue();
      filter = "brighten-value";
    } else if (flt.equals("brighten-luma")) {
      layers.get(x).brightenLuma();
      filter = "brighten-luma";
    } else if (flt.equals("brighten-intensity")) {
      layers.get(x).brightenIntensity();
      filter = "brighten-intensity";
    } else if (flt.equals("darken-value")) {
      layers.get(x).darkenValue();
      filter = "darken-value";
    } else if (flt.equals("darken-luma")) {
      layers.get(x).darkenLuma();
      filter = "darken-luma";
    } else if (flt.equals("darken-intensity")) {
      layers.get(x).darkenIntensity();
      filter = "darken-intensity";
    } else if (flt.equals("inversion-blend")) {
      if (x < 1) {
        throw new IllegalStateException();
      }
      layers.get(x).inversionDiff(layers.get(x - 1));
      filter = "inversion-blend";
    } else if (flt.equals("darken-blend")) {
      if (x < 1) {
        throw new IllegalStateException();
      }
      layers.get(x).blendDark(layers.get(x - 1));
      filter = "darken-blend";
    } else if (flt.equals("brighten-blend")) {
      if (x < 1) {
        throw new IllegalStateException();
      }
      layers.get(x).blendBright(layers.get(x - 1));
      filter = "brighten-blend";
    }
  }

  /**
   * Getter method to retrieve the index of a given layer from a list of Layers.
   *
   * @param layerName - the name of the layer that.
   * @return the index of the given layer in the list of Layers.
   */
  public ILayer getLayer(String layerName) {
    int index = this.names.indexOf(layerName);

    if (index < 0) {
      throw new IllegalArgumentException("Layer "
              + layerName + "was not found in the list of Layers");
    }
    return this.layers.get(index);
  }

  /**
   * Compresses all the layers in the project into a single layer.
   * This is used for save image method.
   */
  public ILayer compressLayers() {
    if (layers.size() == 0) {
      throw new IllegalStateException("No layers in list");
    } else if (layers.size() == 1) {
      return this.layers.get(0);
    } else {
      ILayer l = layers.get(0).addLayer(layers.get(1), 255);
      for (int i = 1; i < layers.size() - 1; i++) {
        l = layers.get(i).addLayer(layers.get(i + 1), layers.get(i + 1).getA());
      }
      return l;
    }
  }

  /**
   * Returns a list of all the layer names in the project.
   */
  public String[] getLayerNames() {
    String[] strs = new String[this.names.size()];
    for (int i = 0; i < strs.length; i++) {
      strs[i] = this.names.get(i);
    }
    return strs;
  }

  /**
   * Loads a JPG or PNG image from a given filepath.
   *
   * @param path - the file path of the image being loaded.
   * @throws IOException when the file path is invalid or the image cannot be loaded.
   */
  public Pixel[][] loadImage(String path) throws IOException {
    BufferedImage img = ImageIO.read(new File(path));
    Pixel[][] pixels = new Pixel[img.getHeight()][img.getWidth()];

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int rgb = img.getRGB(j, i);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        pixels[i][j] = new Pixel(red, green, blue);
      }
    }
    return pixels;
  }

  /**
   * Creates a new project with a given height, width, and max value.
   *
   * @param height - the height of the new project being created.
   * @param width  - the width of the new project being created.
   * @return the new project.
   */
  public Project newProject(int height, int width, int max) {
    return new Project(height, width, max);
  }

  private BufferedImage saveImageHelp() {
    ILayer l = this.compressLayers();
    BufferedImage b = new BufferedImage(l.getPixels()[0].length,
            l.getPixels().length, BufferedImage.TYPE_INT_RGB);
    IPixel[][] pixels = l.getPixels();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        int rgb = 65536 * pixels[i][j].getR() + 256 * pixels[i][j].getG() + pixels[i][j].getB();
        b.setRGB(j, i, rgb);
      }
    }
    return b;
  }
}
