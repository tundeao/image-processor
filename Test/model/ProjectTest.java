package model;

import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

/**
 * Represents tests for the Project Class.
 */
public class ProjectTest {
  private Pixel[][] tp1;
  private Pixel[] tp2;


  /**
   * Tests add layer method to see if project adds blank layer on canvas.
   */
  @Test
  public void testAddLayer() {
    Project p = new Project(200, 200, 255);
    p.addLayer("Layer");
    for (int i = 0; i < p.getHeight(); i++) {
      for (int j = 0; j < p.getWidth(); j++) {
        assertEquals(0, p.getLayers().get(1).getPixels()[i][j].getR());
        assertEquals(0, p.getLayers().get(1).getPixels()[i][j].getG());
        assertEquals(0, p.getLayers().get(1).getPixels()[i][j].getB());
      }
    }
    assertEquals("Layer", p.getNames().get(1));
    assertEquals(2, p.getLayers().size());
  }

  /**
   * Tests exception for add layer method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerException() {
    Project p = new Project(200, 200, 255);
    p.addLayer("Layer");
    p.addLayer("Layer");
  }

  /**
   * Tests set filter method for red component.
   */
  @Test
  public void testSetFilterRedComponent() {
    Project p = new Project(80, 600, 255);
    p.addLayer("Layer 1");
    p.setFilter("Layer 1", "red-component");
    ILayer l = p.getLayer("Layer 1");
    assertEquals(0, l.getPixels()[0][0].getR());
    assertEquals(0, l.getPixels()[0][0].getG());
    assertEquals(0, l.getPixels()[0][0].getB());
  }

  /**
   * Tests set filter method for green component.
   */
  @Test
  public void testSetFilterGreenComponent() {
    Project p = new Project(3, 3, 255);
    p.addLayer("Layer 1");
    p.setFilter("Layer 1", "green-component");
    ILayer l = p.getLayer("Layer 1");
    assertEquals(0, l.getPixels()[0][0].getR());
    assertEquals(0, l.getPixels()[0][0].getG());
    assertEquals(0, l.getPixels()[0][0].getB());
  }

  /**
   * Tests set filter method for blue component.
   */
  @Test
  public void testSetFilterBlueComponent() {
    Project p = new Project(3, 3, 255);
    p.addLayer("Layer 1");
    p.setFilter("Layer 1", "blue-component");
    ILayer l = p.getLayer("Layer 1");
    assertEquals(0, l.getPixels()[0][0].getR());
    assertEquals(0, l.getPixels()[0][0].getG());
    assertEquals(0, l.getPixels()[0][0].getB());
  }

  /**
   * Tests set filter method for brighten intensity.
   */
  @Test
  public void testSetFilterBrightenIntensity() {
    Project p = new Project(3, 3, 255);
    p.addLayer("Layer 1");
    p.setFilter("Layer 1", "brighten-intensity");
    ILayer l = p.getLayer("Layer 1");
    assertEquals(0, l.getPixels()[0][0].getR());
    assertEquals(0, l.getPixels()[0][0].getG());
    assertEquals(0, l.getPixels()[0][0].getB());
    assertEquals(0, l.getPixels()[0][0].getIntensity());
  }

  /**
   * Tests add image to layer method. This test does not work.
   */
  @Test
  public void testAddImageToLayer() {
    Project p = new Project(500, 500, 255);
    p.addLayer("test");
    String path = "jaysontatumm.ppm";
    p.addImageToLayer("test", path, 4, 5);
    ILayer l = p.getLayer("test");
    assertEquals(173, l.getPixels()[4][5].getR());
    assertEquals(179, l.getPixels()[4][5].getG());
    assertEquals(151, l.getPixels()[4][5].getB());
  }

  /**
   * Tests save project method.
   */
  @Test
  public void testSaveProject() throws IOException {
    Project p = new Project(800, 600, 255);
    Layer l1 = new Layer(new Pixel[][]{
            {new Pixel(255, 0, 0), new Pixel(0, 255, 0)},
            {new Pixel(0, 0, 255), new Pixel(255, 255, 0)}
    });

    p.addLayer("Canvas");
    p.setFilter("Canvas", "normal");
    p.saveProject("testSaveProj.ppm");
    Scanner sc = new Scanner(new File("testSaveProj.ppm"));

    assertEquals("C1", sc.nextLine());
    assertEquals(600, sc.nextInt());
    assertEquals(800, sc.nextInt());
    assertEquals(255, sc.nextInt());
    assertEquals("", sc.nextLine());
    assertEquals(" normal", sc.nextLine());
    assertEquals(255, sc.nextInt());
    assertEquals(255, sc.nextInt());
    assertEquals(255, sc.nextInt());

  }

  /**
   * Represents a test for loadProject, test does not work.
   */
  @Test
  public void testLoadProject() throws IOException {

    Project proj = new Project(800, 600, 255);
    Pixel[][] pixels = new Pixel[800][600];
    Layer layer = new Layer(pixels);
    proj.addLayer("layer");

    String path = "projectPath.collage";
    proj.saveProject(path);
    Project projLoad = new Project(path);

    assertEquals(proj.getWidth(), projLoad.getWidth());
    assertEquals(proj.getHeight(), projLoad.getHeight());
    assertEquals(proj.getMax(), projLoad.getMax());

  }

  /**
   * Tests for the Inversion-Blend filter.
   */
  @Test
  public void testSetFilterInversionBlend() {
    Project p = new Project(800, 600, 255);
    p.addLayer("l2");
    p.setFilter("l2", "inversion-blend");
    ILayer l = p.getLayer("l2");

    Pixel[][] endResult = new Pixel[][] {
            {new Pixel(225, 195, 165), new Pixel(155, 135, 145)},
            {new Pixel(55, 75, 20), new Pixel(250, 120, 30)}
    };

    assertEquals(endResult, l.getPixels());
  }

  @Test
  public void testSetFilterBrightenBlend() {
    Project p = new Project(4, 4, 255);
    p.addLayer("layer");
    Pixel pix = new Pixel(125, 153, 142);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        p.getLayers().get(1).getPixels()[i][j] = pix;
      }
    }
    p.setFilter("layer", "brighten-blend");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(255, p.getLayers().get(1).getPixels()[i][j].getR());
        assertEquals(255, p.getLayers().get(1).getPixels()[i][j].getG());
        assertEquals(255, p.getLayers().get(1).getPixels()[i][j].getB());
      }
    }
  }

  @Test
  public void testSetFilterBrightenBlend2() {
    Project p = new Project(4, 4, 255);
    p.addLayer("layer");
    p.addLayer("layer2");
    Pixel pix = new Pixel(164, 153, 142);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        p.getLayers().get(2).getPixels()[i][j] = pix;
      }
    }
    p.setFilter("layer2", "brighten-blend");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(164, p.getLayers().get(2).getPixels()[i][j].getR());
        assertEquals(153, p.getLayers().get(2).getPixels()[i][j].getG());
        assertEquals(142, p.getLayers().get(2).getPixels()[i][j].getB());
      }
    }
  }

  @Test
  public void testSetFilterDarkenBlend() {
    Project p = new Project(4, 4, 255);
    p.addLayer("layer");
    Pixel pix = new Pixel(164, 153, 142);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        p.getLayers().get(1).getPixels()[i][j] = pix;
      }
    }
    p.setFilter("layer", "darken-blend");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(164, p.getLayers().get(1).getPixels()[i][j].getR());
        assertEquals(153, p.getLayers().get(1).getPixels()[i][j].getG());
        assertEquals(142, p.getLayers().get(1).getPixels()[i][j].getB());
      }
    }
  }

  @Test
  public void testSetFilterDarkenBlend2() {
    Project p = new Project(4, 4, 255);
    p.addLayer("layer");
    p.addLayer("layer2");
    Pixel pix = new Pixel(125, 153, 142);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        p.getLayers().get(2).getPixels()[i][j] = pix;
      }
    }
    p.setFilter("layer2", "darken-blend");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(0, p.getLayers().get(2).getPixels()[i][j].getR());
        assertEquals(0, p.getLayers().get(2).getPixels()[i][j].getG());
        assertEquals(0, p.getLayers().get(2).getPixels()[i][j].getB());
      }
    }
  }


}