package model;

/**
 * This represents a layer. Each Layer has a 2d array of pixels
 * and an integer that represents transparency value of the layer.
 */
public class Layer implements ILayer {
  private Pixel[][] pixels;
  private int a;

  /**
   * Represents constructor for Layer.
   * @param p - 2-dimensionsal array of pixels.
   */
  public Layer(Pixel[][] p) {
    this.pixels = p;
    this.a = 255;
  }

  public Layer(Pixel[][] p, int a) {
    this.pixels = p;
    this.a = a;
  }

  /**
   * Given a color red, green, or blue, the layer is filtered to.
   * display only the given color's value.
   * @param color - String that is either red, green or blue.
   */
  public void filter(String color) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].filter(color);
      }
    }
  }

  /**
   * Given a value x, return the layer with the pixels brightened by max rgb value.
   */
  public void brightenValue() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].brighten(pixels[i][j].getValue());
      }
    }
  }

  /**
   * Given a value x, return the layer with the pixels brightened by Luma value.
   */
  public void brightenLuma() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].brighten(pixels[i][j].getLuma());
      }
    }
  }

  /**
   * Given a value x, return the layer with the pixels brightened by Intensity value.
   */
  public void brightenIntensity() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].brighten(pixels[i][j].getIntensity());
      }
    }
  }


  /**
   * Given a value x, return the layer with the pixels darkened by max rgb value.
   */

  public void darkenValue() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].darken(pixels[i][j].getValue());
      }
    }
  }

  /**
   * Given a value x, return the layer with the pixels darkened by the Luma value.
   */

  public void darkenLuma() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].darken(pixels[i][j].getLuma());
      }
    }
  }

  /**
   * Given a value x, return the layer with the pixels darkened by the Intensity value.
   */

  public void darkenIntensity() {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].darken(pixels[i][j].getIntensity());
      }
    }
  }

  /**
   * Getter method to get the pixels in a 2D-Array of pixels.
   */
  public Pixel[][] getPixels() {
    return pixels;
  }

  /**
   * Adds a given layer on top of the current layer with a specified alpha value.
   * @param l - the layer that is being added on top of the current layer
   * @param a - the alpha value of the current layer
   * @return a new layer representing the blend of both layers
   */
  public Layer addLayer(ILayer l, int a) {
    Layer ret = new Layer(new Pixel[l.getPixels().length][l.getPixels()[0].length]);
    for (int i = 0; i < l.getPixels().length; i++) {
      for (int j = 0; j < l.getPixels()[i].length; j++) {
        ret.pixels[i][j] = (Pixel) this.pixels[i][j].addPixel(l.getPixels()[i][j], a);
      }
    }
    return ret;
  }

  /**
   * Inverts the current layer's values based on the background/layer below it.
   * @param bg - the layer below the current layer
   */
  public void inversionDiff(ILayer bg) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {

        pixels[i][j].invert(bg.getPixels()[i][j]);
      }
    }
  }

  /**
   * Darkens the current layer by using the lightness values of the layer below it.
   * @param bg - the layer below the current layer.
   */
  public void blendDark(ILayer bg) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].darkenBlend(bg.getPixels()[i][j]);
      }
    }
  }

  /**
   * Brightens the current layer by using the lightness values of the layer below it.
   * @param bg - the layer below the current layer.
   */
  public void blendBright(ILayer bg) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].brightenBlend(bg.getPixels()[i][j]);
      }
    }
  }


  /**
   * Getter method to return the alpha value of the Layer.
   * @return the alpha value as an integer.
   */
  public int getA() {
    return this.a;
  }
}


