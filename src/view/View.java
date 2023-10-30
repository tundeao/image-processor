package view;

import java.io.IOException;

/**
 * Represents an interface view.
 */
public interface View {
  void renderMessage(String message) throws IOException;
}
