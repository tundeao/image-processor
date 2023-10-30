package model;

/**
 * Represents a Position. Has two fields, an x value and a y value.
 */
public class Position {
  private int x;
  private int y;

  /**
   * Constructor for position.
   * @param x - represents x coordinate.
   * @param y - represents y coordinate.
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter method for the x value of a position.
   * @return the x value.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Getter method for the y value of a position.
   * @return the y value.
   */
  public int getY() {
    return this.y;
  }
}
