package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Represents the tests for the Pixel class.
 */
public class PixelTest {
  /**
   * Tests invalid construction of a pixel with r value > 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction() {
    Pixel p = new Pixel(256, 255, 255, 255);
  }

  /**
   * Tests invalid construction of a pixel with g value > 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction2() {
    Pixel p = new Pixel(255, 256, 255, 255);
  }

  /**
   * Tests invalid construction of a pixel with g value > 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction3() {
    Pixel p = new Pixel(255, 255, 256, 255);
  }

  /**
   * Tests invalid construction of a pixel with a value > 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction4() {
    Pixel p = new Pixel(255, 255, 255, 256);
  }

  /**
   * Tests invalid construction of a pixel with r value < 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction5() {
    Pixel p = new Pixel(-1, 255, 255, 255);
  }

  /**
   * Tests invalid construction of a pixel with g value < 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction6() {
    Pixel p = new Pixel(255, -1, 255, 255);
  }

  /**
   * Tests invalid construction of a pixel with b value < 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction7() {
    Pixel p = new Pixel(255, 255, -1, 255);
  }

  /**
   * Tests invalid construction of a pixel with a value < 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidConstruction8() {
    Pixel p = new Pixel(255, 255, 255, -1);
  }

  /**
   * Tests for the GetRGB method.
   */
  @Test
  public void testGetRGB() {
    Pixel p = new Pixel(123, 135, 255);
    assertEquals(123, p.getR());
    assertEquals(135, p.getG());
    assertEquals(255, p.getB());
  }

  /**
   * First set of tests for the Filter method.
   * Tests the red-component filter on a given pixel.
   */
  @Test
  public void testFilter() {
    Pixel p = new Pixel(235, 135, 43);
    p.filter("red");
    assertEquals(235, p.getR());
    assertEquals(0, p.getG());
    assertEquals(0, p.getB());
  }

  /**
   * Second set of tests for the Filter method.
   * Tests the green-component filter on a given pixel.
   */
  @Test
  public void testFilter2() {
    Pixel p = new Pixel(235, 135, 43);
    p.filter("green");
    assertEquals(0, p.getR());
    assertEquals(135, p.getG());
    assertEquals(0, p.getB());
  }

  /**
   * Third set of tests for the Filter method.
   * Tests the blue-component filter on a given pixel.
   */
  @Test
  public void testFilter3() {
    Pixel p = new Pixel(235, 135, 43);
    p.filter("blue");
    assertEquals(0, p.getR());
    assertEquals(0, p.getG());
    assertEquals(43, p.getB());
  }

  /**
   * First set of tests for the Brighten method.
   * Brightens the pixel by a factor of 23.
   */
  @Test
  public void testBrighten() {
    Pixel p = new Pixel(214, 134, 132);
    p.brighten(23);
    assertEquals(237, p.getR());
    assertEquals(157, p.getG());
    assertEquals(155, p.getB());
  }

  /**
   * Second set of tests for the Brighten method.
   * Brightens the pixel by a factor of 100, maxing out the red value.
   */
  @Test
  public void testBrighten2() {
    Pixel p = new Pixel(214, 134, 132);
    p.brighten(100);
    assertEquals(255, p.getR());
    assertEquals(234, p.getG());
    assertEquals(232, p.getB());
  }

  /**
   * Third set of tests for the Brighten method.
   * Brightens the pixel by a factor of 100, maxing out the green value.
   */
  @Test
  public void testBrighten3() {
    Pixel p = new Pixel(114, 234, 132);
    p.brighten(100);
    assertEquals(214, p.getR());
    assertEquals(255, p.getG());
    assertEquals(232, p.getB());
  }

  /**
   * Fourth set of tests for the Brighten method.
   * Brightens the pixel by a factor of 100, maxing out the blue value.
   */
  @Test
  public void testBrighten4() {
    Pixel p = new Pixel(114, 134, 232);
    p.brighten(100);
    assertEquals(214, p.getR());
    assertEquals(234, p.getG());
    assertEquals(255, p.getB());
  }

  /**
   * First set of tests for the Darken method.
   * Darkens the pixel by a factor of 100.
   */
  @Test
  public void testDarken() {
    Pixel p = new Pixel(114, 134, 232);
    p.darken(100);
    assertEquals(14, p.getR());
    assertEquals(34, p.getG());
    assertEquals(132, p.getB());
  }

  /**
   * Second set of tests for the Darken method.
   * Darkens the pixel, making sure the red value does not become less than 0.
   */
  @Test
  public void testDarken2() {
    Pixel p = new Pixel(14, 134, 132);
    p.darken(100);
    assertEquals(0, p.getR());
    assertEquals(34, p.getG());
    assertEquals(32, p.getB());
  }

  /**
   * Third set of tests for the Darken method.
   * Darkens the pixel, making sure the green value does not become less than 0.
   */
  @Test
  public void testDarken3() {
    Pixel p = new Pixel(114, 34, 132);
    p.darken(100);
    assertEquals(14, p.getR());
    assertEquals(0, p.getG());
    assertEquals(32, p.getB());
  }

  /**
   * Fourth set of tests for the Darken method.
   * Darkens the pixel, making sure the blue value does not become less than 0.
   */
  @Test
  public void testDarken4() {
    Pixel p = new Pixel(114, 134, 32);
    p.darken(100);
    assertEquals(14, p.getR());
    assertEquals(34, p.getG());
    assertEquals(0, p.getB());
  }

  /**
   * Tests for the GetIntensity method.
   */
  @Test
  public void testGetIntensity() {
    Pixel p = new Pixel(163, 124, 134);
    int x = 163 + 124 + 134;
    x = x / 3;
    assertEquals(x, p.getIntensity());
  }

  /**
   * Tests for the GetLuma method.
   */
  @Test
  public void testGetLuma() {
    Pixel p = new Pixel(163, 124, 134);
    int x = (int) (0.2126 * 163 + 0.7152 * 124 + 0.0722 * 134);
    assertEquals(x, p.getLuma());
  }

  /**
   * Tests for the addPixel method.
   */
  @Test
  public void testAddPixel() {
    Pixel p = new Pixel(0, 0, 0);
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel finalp = (Pixel) p.addPixel(p1, 128);
    assertEquals(finalp.getR(), 128);
    assertEquals(finalp.getG(), 128);
    assertEquals(finalp.getB(), 128);
  }

  /**
   * Tests for the invert method.
   */
  @Test
  public void testInvert() {
    Pixel bg = new Pixel(255, 255, 255);
    Pixel p = new Pixel(128, 128, 128);
    p.invert(bg);
    assertEquals(127, p.getR());
    assertEquals(127, p.getG());
    assertEquals(127, p.getB());
  }

  /**
   * Tests for the darkenBlend method.
   */
  @Test
  public void testDarkenBlend() {
    Pixel white = new Pixel(255, 255, 255);
    Pixel p = new Pixel(137, 125, 114);
    p.darkenBlend(white);
    assertEquals(137, p.getR());
    assertEquals(125, p.getG());
    assertEquals(114, p.getB());
  }

  /**
   * More tests for the darkenBlend method.
   */
  @Test
  public void testDarkenBlend2() {
    Pixel black = new Pixel(0, 0, 0);
    Pixel p = new Pixel(137, 125, 114);
    p.darkenBlend(black);
    assertEquals(0, p.getR());
    assertEquals(0, p.getR());
    assertEquals(0, p.getB());
  }

  /**
   * Tests for the brightenBlend method.
   */
  @Test
  public void testBrightenBlend() {
    Pixel black = new Pixel(0, 0, 0);
    Pixel p = new Pixel(137, 125, 114);
    p.brightenBlend(black);
    assertEquals(137, p.getR());
    assertEquals(125, p.getG());
    assertEquals(114, p.getB());
  }

  /**
   * More tests for the brightenBlend method.
   */
  @Test
  public void testBrightenBlend2() {
    Pixel white = new Pixel(255, 255, 255);
    Pixel p = new Pixel(137, 125, 114);
    p.brightenBlend(white);
    assertEquals(255, p.getR());
    assertEquals(255, p.getG());
    assertEquals(255, p.getB());
  }
}