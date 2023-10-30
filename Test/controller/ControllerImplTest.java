package controller;

import org.junit.Test;

import java.io.StringReader;

import model.Project;
import view.ViewImpl;


/**
 * Represents test class for ControllerImpl.
 */
public class ControllerImplTest {




  /**
   * Tests invalid construction of ControllerImpl with null readable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction() {
    StringBuilder sb = new StringBuilder("");
    Project p = new Project(500, 500, 255);
    ViewImpl v = new ViewImpl(p, sb);
    ControllerImpl c = new ControllerImpl(p, v, null);
  }

  /**
   * Tests invalid construction of ControllerImpl with null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction2() {
    Project p = new Project(500, 500, 255);
    StringReader s = new StringReader("");
    ControllerImpl c = new ControllerImpl(p, null, s);
  }

  /**
   * Tests invalid construction of ControllerImpl with null project.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction3() {
    StringBuilder sb = new StringBuilder("");
    Project p = new Project(500, 500, 255);
    ViewImpl v = new ViewImpl(p, sb);
    StringReader s = new StringReader("");
    ControllerImpl c = new ControllerImpl(null, v, s);
  }
}