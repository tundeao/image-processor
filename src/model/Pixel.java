package model;

/**
 * This class represents the metadata of a pixel (its position and its color).
 */
public class Pixel implements IPixel {
  private int r;
  private int g;
  private int b;

  /**
   * Represents the constructor of a pixel.
   * @param r - Red value of pixel.
   * @param g - Green value of pixel.
   * @param b - Blue value of pixel.
   * @param a - Alpha value of pixel.
   */
  public Pixel(int r, int g, int b, int a) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || a < 0 || a > 255) {
      throw new IllegalArgumentException("Unable to create pixel.");
    }
    this.r = r * a / 255;
    this.g = g * a / 255;
    this.b = b * a / 255;
  }

  /**
   * Represents the constructor of a pixel.
   * @param r - Red value of pixel.
   * @param g - Green value of pixel.
   * @param b - Blue value of pixel.
   */
  public Pixel(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Unable to create pixel.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Given a string color, sets other rgb values to 0.
   * @param color - string that is either red, green or blue.
   */
  public void filter(String color) {
    if (color.equals("red")) {
      this.g = 0;
      this.b = 0;
    }
    else if (color.equals("green")) {
      this.r = 0;
      this.b = 0;
    }
    else if (color.equals("blue")) {
      this.r = 0;
      this.g = 0;
    }
  }

  /**
   * Brightens a pixel by given x value.
   * @param x - value the pixel is brightened by.
   */
  public void brighten(int x) {
    if (x + this.r < 256) {
      this.r = r + x;
    }
    else {
      this.r = 255;
    }
    if (x + this.g < 256) {
      this.g = g + x;
    }
    else {
      this.g = 255;
    }
    if (x + this.b < 256) {
      this.b = b + x;
    }
    else {
      this.b = 255;
    }
  }

  /**
   * Darkens a pixel by given x value.
   * @param x - value the pixel is darkened by.
   */
  public void darken(int x) {
    if (this.r - x > -1) {
      this.r = r - x;
    }
    else {
      this.r = 0;
    }
    if (this.g - x > -1) {
      this.g = g - x;
    }
    else {
      this.g = 0;
    }
    if (this.b - x > -1) {
      this.b = b - x;
    }
    else {
      this.b = 0;
    }
  }

  /**
   * Getter method for red value.
   */
  public int getR() {
    return this.r;
  }

  /**
   * Getter method for green value.
   */
  public int getG() {
    return this.g;
  }

  /**
   * Getter method for blue value.
   */
  public int getB() {
    return this.b;
  }

  /**
   * Return the max value of all three rgb values.
   */
  public int getValue() {
    return Math.max(this.r, Math.max(this.g, this.b));
  }

  /**
   * Returns the average value of all three rgb values.
   */
  public int getIntensity() {
    return (this.r + this.g + this.b) / 3;
  }

  /**
   * Returns the luma value of pixel.
   */
  public int getLuma() {
    return (int) (0.2126 * this.r + 0.7152 * this.g + 0.0722 * this.b);
  }

  /**
   * Given a pixel and a transparency, creates the new RGB value for the pixel.
   */
  public IPixel addPixel(IPixel p, int a) {
    double alpha = (double) a / 255;
    int rvalue = (int) ((p.getR() * alpha) + (this.getR() * (1 - alpha)));
    int gvalue = (int) ((p.getG() * alpha) + (this.getG() * (1 - alpha)));
    int bvalue = (int) ((p.getB() * alpha) + (this.getB() * (1 - alpha)));
    return new Pixel(rvalue, gvalue, bvalue);
  }

  /**
   * Inverts the pixel based off the values of the pixel below it.
   * @param p - values of the pixel below.
   */
  public void invert(IPixel p) {
    this.r = Math.abs(r - p.getR());
    this.g = Math.abs(g - p.getG());
    this.b = Math.abs(b - p.getB());
  }

  /**
   * Darkens the pixel based off the values of the pixel below it.
   * @param p - values of the pixel below.
   */
  public void darkenBlend(IPixel p) {
    double[] hslp = RepresentationConverter.convertRGBtoHSL(p.getR(), p.getG(), p.getB());
    double lp = hslp[2];
    double[] hslthis = RepresentationConverter.convertRGBtoHSL(this.r, this.g, this.b);
    double h = hslthis[0];
    double s = hslthis[1];
    double lthis = hslthis[2];
    double l = lthis * lp;
    int[] rgb = RepresentationConverter.convertHSLtoRGB(h, s, l);
    this.r = rgb[0];
    this.g = rgb[1];
    this.b = rgb[2];
  }

  /**
   * Brightens the pixel based off the values of the pixel below it.
   * @param p - values of the pixel below.
   */
  public void brightenBlend(IPixel p) {
    double[] hslp = RepresentationConverter.convertRGBtoHSL(p.getR(), p.getG(), p.getB());
    double lp = hslp[2];
    double[] hslthis = RepresentationConverter.convertRGBtoHSL(this.r, this.g, this.b);
    double h = hslthis[0];
    double s = hslthis[1];
    double lthis = hslthis[2];
    double l = 1 - ((1 - lthis) * (1 - lp));
    int[] rgb = RepresentationConverter.convertHSLtoRGB(h, s, l);
    this.r = rgb[0];
    this.g = rgb[1];
    this.b = rgb[2];
  }

}



