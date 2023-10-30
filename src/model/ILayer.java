package model;

/**
 * Represents an interface that contains all relevant methods for the Layer class.
 */
public interface ILayer {
  /**
   * Given a color red, green, or blue, the layer is filtered to.
   * display only the given color's value.
   * @param color - String that is either red, green or blue.
   */
  void filter(String color);

  /**
   * Given a value x, return the layer with the pixels brightened by max rgb value.
   */
  void brightenValue();

  /**
   * Given a value x, return the layer with the pixels brightened by Luma value.
   */
  void brightenLuma();

  /**
   * Given a value x, return the layer with the pixels brightened by Intensity value.
   */
  void brightenIntensity();

  /**
   * Given a value x, return the layer with the pixels darkened by max rgb value.
   */
  void darkenValue();

  /**
   * Given a value x, return the layer with the pixels darkened by the Luma value.
   */
  void darkenLuma();

  /**
   * Given a value x, return the layer with the pixels darkened by the Intensity value.
   */
  void darkenIntensity();

  /**
   * Getter method to get the pixels in a 2D-Array of pixels.
   */
  IPixel[][] getPixels();

  /**
   * Adds a given layer on top of the current layer with a specified alpha value.
   * @param l - the layer that is being added on top of the current layer
   * @param a - the alpha value of the current layer
   * @return a new layer representing the blend of both layers
   */
  ILayer addLayer(ILayer l, int a);

  /**
   * Inverts the current layer's values based on the background/layer below it.
   * @param bg - the layer below the current layer
   */
  void inversionDiff(ILayer bg);

  /**
   * Darkens the current layer by using the lightness values of the layer below it.
   * @param bg - the layer below the current layer.
   */
  void blendDark(ILayer bg);

  /**
   * Brightens the current layer by using the lightness values of the layer below it.
   * @param bg - the layer below the current layer.
   */
  void blendBright(ILayer bg);

  /**
   * Getter method to return the alpha value of the Layer.
   * @return the alpha value as an integer.
   */
  int getA();

}
