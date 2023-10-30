package view;

import java.io.IOException;

import model.IProject;

/**
 * Represents an implementation of the view interface.
 */
public class ViewImpl implements View {
  private IProject p;
  private Appendable out;

  /**
   * Represents a consrtuctor for the ViewImpl.
   * @param p - represents a Project.
   * @param out - represents an Appendable.
   */
  public ViewImpl(IProject p, Appendable out) {
    this.p = p;
    this.out = out;
  }

  /**
   * Renders a given message to the appendable field.
   * @param message - the message that is added to the appendable.
   */
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }

  /**
   * Getter method for project for view.
   */
  public IProject getProject() {
    return this.p;
  }
}
