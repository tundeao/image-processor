package model;

import java.io.IOException;

/**
 * Represents an interface that contains all relevant methods for the Project class.
 */
public interface IProject {
  /**
   * Adds a new blank layer to the project.
   * @param name - represents the name of the new layer.
   */
  void addLayer(String name);

  /**
   * Adds a new blank layer to the project with a given alpha value.
   * @param name - represents the name of the new layer.
   * @param a - represents the alpha value (from 0-255) of the new layer.
   */
  void addLayer(String name, int a);

  /**
   * Getter method for the height of the project.
   * @return the height of the project.
   */
  int getHeight();

  /**
   * Getter method to get the width of the project.
   * @return the width of the project.
   */
  int getWidth();

  /**
   * Getter method to get the max value of the project.
   * @return the max value of the project.
   */
  int getMax();

  /**
   * Method to save the image to the given path.
   * @param path - the path that the user wishes to save the image to.
   * @throws IOException when the path cannot be accessed or found.
   */
  void saveImage(String path) throws IOException;

  /**
   * Method to save the project to the given path.
   * @param path - the path that the user wishes to save the project to.
   * @throws IOException when the path cannot be accessed or found.
   */
  void saveProject(String path) throws IOException;

  /**
   * Method to load a project from the given path.
   * @param path - the path that the user wishes to load the project from.
   * @throws IOException when the path cannot be accessed or found.
   */
  void loadProject(String path) throws IOException;

  /**
   * Method to add a given image to the given layer, offset by a certain x and y value.
   * @param layerName - the name of the layer that the image is being added to.
   * @param imageName - the name of the image that is being added to the given layer.
   * @param x - the x value for offsetting the image.
   * @param y - the y value for offsetting the image.
   */
  void addImageToLayer(String layerName, String imageName, int x, int y);

  /**
   * Method to add a filter to a given layer.
   * @param name - the name of the layer that the filter is being added to.
   * @param flt - the name of the filter that is being applied to the layer.
   */
  void setFilter(String name, String flt);

  /**
   * Getter method to retrieve the index of a given layer from a list of Layers.
   * @param layerName - the name of the layer that.
   * @return the index of the given layer in the list of Layers.
   */
  ILayer getLayer(String layerName);

  /**
   * Compresses all the layers in the project into a single layer.
   * This is used for save image method.
   */
  ILayer compressLayers();

  /**
   * Returns a list of all the layer names in the project.
   */
  String[] getLayerNames();

  /**
   * Creates a new project with a given height, width, and max value.
   * @param height - the height of the new project being created.
   * @param width - the width of the new project being created.
   * @return the new project.
   */
  IProject newProject(int height, int width, int max);


}
