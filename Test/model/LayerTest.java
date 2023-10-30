package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Represents the tests for the Layer class.
 */
public class LayerTest {


  private Pixel[][] testPixels = new Pixel[2][2];

  @Before
  public void instantiate() {
    testPixels[0][0] = new Pixel(30, 60, 90);
    testPixels[0][1] = new Pixel(100, 120, 110);
    testPixels[1][0] = new Pixel(200, 180, 235);
    testPixels[1][1] = new Pixel(250, 120, 30);
  }

  /**
   * First set of tests for the Filter method.
   * Tests for the green-component filter.
   */
  @Test
  public void testFilter() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.filter("green");

    assertEquals(0, testPixels[0][0].getR());
    assertEquals(60, testPixels[0][0].getG());
    assertEquals(0, testPixels[0][0].getB());

    assertEquals(0, testPixels[0][1].getR());
    assertEquals(120, testPixels[0][1].getG());
    assertEquals(0, testPixels[0][1].getB());

    assertEquals(0, testPixels[1][0].getR());
    assertEquals(180, testPixels[1][0].getG());
    assertEquals(0, testPixels[1][0].getB());

    assertEquals(0, testPixels[1][1].getR());
    assertEquals(120, testPixels[1][1].getG());
    assertEquals(0, testPixels[1][1].getB());
  }

  /**
   * Second set of tests for the Filter method.
   * Tests for the red-component filter.
   */
  @Test
  public void testFilter2() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.filter("red");

    assertEquals(30, testPixels[0][0].getR());
    assertEquals(0, testPixels[0][0].getG());
    assertEquals(0, testPixels[0][0].getB());

    assertEquals(100, testPixels[0][1].getR());
    assertEquals(0, testPixels[0][1].getG());
    assertEquals(0, testPixels[0][1].getB());

    assertEquals(200, testPixels[1][0].getR());
    assertEquals(0, testPixels[1][0].getG());
    assertEquals(0, testPixels[1][0].getB());

    assertEquals(250, testPixels[1][1].getR());
    assertEquals(0, testPixels[1][1].getG());
    assertEquals(0, testPixels[1][1].getB());
  }

  /**
   * Third set of tests for the Filter method.
   * Tests for the blue-component filter.
   */
  @Test
  public void testFilter3() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.filter("blue");

    assertEquals(0, testPixels[0][0].getR());
    assertEquals(0, testPixels[0][0].getG());
    assertEquals(90, testPixels[0][0].getB());

    assertEquals(0, testPixels[0][1].getR());
    assertEquals(0, testPixels[0][1].getG());
    assertEquals(110, testPixels[0][1].getB());

    assertEquals(0, testPixels[1][0].getR());
    assertEquals(0, testPixels[1][0].getG());
    assertEquals(235, testPixels[1][0].getB());

    assertEquals(0, testPixels[1][1].getR());
    assertEquals(0, testPixels[1][1].getG());
    assertEquals(30, testPixels[1][1].getB());
  }

  /**
   * Tests for the BrightenValue method.
   */
  @Test
  public void testBrightenValue() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.brightenValue();

    assertEquals(120, testPixels[0][0].getR());
    assertEquals(150, testPixels[0][0].getG());
    assertEquals(180, testPixels[0][0].getB());

    assertEquals(220, testPixels[0][1].getR());
    assertEquals(240, testPixels[0][1].getG());
    assertEquals(230, testPixels[0][1].getB());

    assertEquals(255, testPixels[1][0].getR());
    assertEquals(255, testPixels[1][0].getG());
    assertEquals(255, testPixels[1][0].getB());

    assertEquals(255, testPixels[1][1].getR());
    assertEquals(255, testPixels[1][1].getG());
    assertEquals(255, testPixels[1][1].getB());
  }

  /**
   * Tests for the BrightenLuma method.
   */
  @Test
  public void testBrightenLuma() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.brightenLuma();


    assertEquals(85, testPixels[0][0].getR());
    assertEquals(115, testPixels[0][0].getG());
    assertEquals(145, testPixels[0][0].getB());

    assertEquals(215, testPixels[0][1].getR());
    assertEquals(235, testPixels[0][1].getG());
    assertEquals(225, testPixels[0][1].getB());

    assertEquals(255, testPixels[1][0].getR());
    assertEquals(255, testPixels[1][0].getG());
    assertEquals(255, testPixels[1][0].getB());

    assertEquals(255, testPixels[1][1].getR());
    assertEquals(255, testPixels[1][1].getG());
    assertEquals(171, testPixels[1][1].getB());
  }

  /**
   * Tests for the BrightenIntensity method.
   */
  @Test
  public void testBrightenIntensity() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.brightenIntensity();


    assertEquals(90, testPixels[0][0].getR());
    assertEquals(120, testPixels[0][0].getG());
    assertEquals(150, testPixels[0][0].getB());

    assertEquals(210, testPixels[0][1].getR());
    assertEquals(230, testPixels[0][1].getG());
    assertEquals(220, testPixels[0][1].getB());

    assertEquals(255, testPixels[1][0].getR());
    assertEquals(255, testPixels[1][0].getG());
    assertEquals(255, testPixels[1][0].getB());

    assertEquals(255, testPixels[1][1].getR());
    assertEquals(253, testPixels[1][1].getG());
    assertEquals(163, testPixels[1][1].getB());
  }

  /**
   * Tests for the DarkenValue method.
   */
  @Test
  public void testDarkenValue() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.darkenValue();

    assertEquals(0, testPixels[0][0].getR());
    assertEquals(0, testPixels[0][0].getG());
    assertEquals(0, testPixels[0][0].getB());

    assertEquals(0, testPixels[0][1].getR());
    assertEquals(0, testPixels[0][1].getG());
    assertEquals(0, testPixels[0][1].getB());

    assertEquals(0, testPixels[1][0].getR());
    assertEquals(0, testPixels[1][0].getG());
    assertEquals(0, testPixels[1][0].getB());

    assertEquals(0, testPixels[1][1].getR());
    assertEquals(0, testPixels[1][1].getG());
    assertEquals(0, testPixels[1][1].getB());
  }

  /**
   * Tests for the DarkenLuma method.
   */
  @Test
  public void testDarkenLuma() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.darkenLuma();

    // 55
    assertEquals(0, testPixels[0][0].getR());
    assertEquals(5, testPixels[0][0].getG());
    assertEquals(35, testPixels[0][0].getB());

    // 115
    assertEquals(0, testPixels[0][1].getR());
    assertEquals(5, testPixels[0][1].getG());
    assertEquals(0, testPixels[0][1].getB());

    // 188
    assertEquals(12, testPixels[1][0].getR());
    assertEquals(0, testPixels[1][0].getG());
    assertEquals(47, testPixels[1][0].getB());

    // 141
    assertEquals(109, testPixels[1][1].getR());
    assertEquals(0, testPixels[1][1].getG());
    assertEquals(0, testPixels[1][1].getB());
  }

  /**
   * Tests for the DarkenIntensity method.
   */
  @Test
  public void testDarkenIntensity() {
    instantiate();
    Layer l = new Layer(testPixels);
    l.darkenIntensity();

    // 60
    assertEquals(0, testPixels[0][0].getR());
    assertEquals(0, testPixels[0][0].getG());
    assertEquals(30, testPixels[0][0].getB());

    // 110
    assertEquals(0, testPixels[0][1].getR());
    assertEquals(10, testPixels[0][1].getG());
    assertEquals(0, testPixels[0][1].getB());

    // 205
    assertEquals(0, testPixels[1][0].getR());
    assertEquals(0, testPixels[1][0].getG());
    assertEquals(30, testPixels[1][0].getB());

    // 133
    assertEquals(117, testPixels[1][1].getR());
    assertEquals(0, testPixels[1][1].getG());
    assertEquals(0, testPixels[1][1].getB());
  }

  /**
   * Tests for the addLayer method.
   */
  @Test
  public void testAddLayer() {
    Pixel white = new Pixel(255, 255, 255);
    Pixel black = new Pixel(0, 0, 0);
    Pixel grey = new Pixel(128, 128, 128);
    Pixel[][] whitePixels = new Pixel[2][2];
    Pixel[][] blackPixels = new Pixel[2][2];
    Pixel[][] greyPixels = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        whitePixels[i][j] = white;
        blackPixels[i][j] = black;
        greyPixels[i][j] = grey;
      }
    }
    Layer whiteLayer = new Layer(whitePixels);
    Layer blackLayer = new Layer(blackPixels);
    Layer greyLayer = blackLayer.addLayer(whiteLayer, 128);
    for (int i = 0; i < whiteLayer.getPixels().length; i++) {
      for (int j = 0; j < whiteLayer.getPixels()[i].length; j++) {
        assertEquals(128, greyLayer.getPixels()[i][j].getR());
        assertEquals(128, greyLayer.getPixels()[i][j].getG());
        assertEquals(128, greyLayer.getPixels()[i][j].getB());
      }
    }
  }

  /**
   * Tests for the inversionDiff method.
   */
  @Test
  public void testInversionDiff() {
    instantiate();
    Layer layer = new Layer(testPixels);
    Pixel[][] bg = new Pixel[][]{
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)},
            {new Pixel(255, 255, 255), new Pixel(0, 0, 0)}};
    Layer bgLayer = new Layer(bg);

    layer.inversionDiff(bgLayer);
    Pixel[][] finalPx = new Pixel[][]{
            {new Pixel(225, 195, 165), new Pixel(155, 135, 145)},
            {new Pixel(55, 75, 20), new Pixel(250, 120, 30)}
    };
    assertEquals(finalPx, layer.getPixels());
  }

  /**
   * Tests for the darkenBlend method.
   */
  @Test
  public void testDarkenBlend() {
    Pixel black = new Pixel(0, 0, 0);
    Pixel rand = new Pixel(134, 124, 112);
    Pixel[][] blackPixels = new Pixel[2][2];
    Pixel[][] randomPixels = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blackPixels[i][j] = black;
        randomPixels[i][j] = rand;
      }
    }
    Layer blackLayer = new Layer(blackPixels);
    Layer randomLayer = new Layer(randomPixels);
    randomLayer.blendDark(blackLayer);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(0, randomLayer.getPixels()[i][j].getR());
        assertEquals(0, randomLayer.getPixels()[i][j].getG());
        assertEquals(0, randomLayer.getPixels()[i][j].getB());
      }
    }
  }

  /**
   * More tests for the darkenBlend method.
   */
  @Test
  public void testDarkenBlend2() {
    Pixel white = new Pixel(255, 255, 255);
    Pixel rand = new Pixel(134, 124, 112);
    Pixel[][] whitePixels = new Pixel[2][2];
    Pixel[][] randomPixels = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        whitePixels[i][j] = white;
        randomPixels[i][j] = rand;
      }
    }
    Layer whiteLayer = new Layer(whitePixels);
    Layer randomLayer = new Layer(randomPixels);
    randomLayer.blendDark(whiteLayer);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(134, randomLayer.getPixels()[i][j].getR());
        assertEquals(124, randomLayer.getPixels()[i][j].getG());
        assertEquals(112, randomLayer.getPixels()[i][j].getB());
      }
    }
  }

  /**
   * Tests for the brightenBlend method.
   */
  @Test
  public void testBrightenBlend() {
    Pixel white = new Pixel(255, 255, 255);
    Pixel rand = new Pixel(134, 124, 112);
    Pixel[][] whitePixels = new Pixel[2][2];
    Pixel[][] randomPixels = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        whitePixels[i][j] = white;
        randomPixels[i][j] = rand;
      }
    }
    Layer whiteLayer = new Layer(whitePixels);
    Layer randomLayer = new Layer(randomPixels);
    randomLayer.blendBright(whiteLayer);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(255, randomLayer.getPixels()[i][j].getR());
        assertEquals(255, randomLayer.getPixels()[i][j].getG());
        assertEquals(255, randomLayer.getPixels()[i][j].getB());
      }
    }
  }

  /**
   * More tests for the brightenBlend method.
   */
  @Test
  public void testBrightenBlend2() {
    Pixel black = new Pixel(0, 0, 0);
    Pixel rand = new Pixel(134, 124, 112);
    Pixel[][] blackPixels = new Pixel[2][2];
    Pixel[][] randomPixels = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blackPixels[i][j] = black;
        randomPixels[i][j] = rand;
      }
    }
    Layer blackLayer = new Layer(blackPixels);
    Layer randomLayer = new Layer(randomPixels);
    randomLayer.blendBright(blackLayer);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(134, randomLayer.getPixels()[i][j].getR());
        assertEquals(124, randomLayer.getPixels()[i][j].getG());
        assertEquals(112, randomLayer.getPixels()[i][j].getB());
      }
    }
  }
}