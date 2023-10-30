package model;

/**
 * Represents an interface that contains all relevant methods for the Pixel class.
 */
public interface IPixel {
  /**
   * Given a string color, sets other rgb values to 0.
   * @param color - string that is either red, green or blue.
   */
  void filter(String color);

  /**
   * Brightens a pixel by given x value.
   * @param x - value the pixel is brightened by.
   */
  void brighten(int x);

  /**
   * Darkens a pixel by given x value.
   * @param x - value the pixel is darkened by.
   */
  void darken(int x);

  /**
   * Getter method for red value.
   */
  int getR();

  /**
   * Getter method for green value.
   */
  int getG();

  /**
   * Getter method for blue value.
   */
  int getB();

  /**
   * Return the max value of all three rgb values.
   */
  int getValue();

  /**
   * Returns the average value of all three rgb values.
   */
  int getIntensity();

  /**
   * Returns the luma value of pixel.
   */
  int getLuma();

  /**
   * Given a pixel and a transparency, creates the new RGB value for the pixel.
   */
  IPixel addPixel(IPixel p, int a);

  /**
   * Inverts the pixel based off the values of the pixel below it.
   * @param p - values of the pixel below.
   */
  void invert(IPixel p);

  /**
   * Darkens the pixel based off the values of the pixel below it.
   * @param p - values of the pixel below.
   */
  void darkenBlend(IPixel p);

  /**
   * Brightens the pixel based off the values of the pixel below it.
   * @param p - values of the pixel below.
   */
  void brightenBlend(IPixel p);
}
