package view;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import model.ILayer;
import model.IPixel;
import model.IProject;


/**
 * Represents the Graphical User Interface that allows a user to view the project that they
 * are editing.
 */
public class GUIView {
  private String layerName;
  private IProject project;
  private JFrame frame;
  private JScrollPane imgScrollPane;
  private JLabel imageLabel;
  private JLabel loadProjDisplay;
  private JComboBox<String> comboBox;

  /**
   * Represents the view of a Graphical User Interface, showing the user what is
   * currently being worked on.
   *
   * @param p - the project that the user is currently working on.
   */
  public GUIView(IProject p) {

    JPanel mainPanel;
    JScrollPane mainScrollPane;
    JButton addLayerBt;
    JButton setFilterBt;
    JButton saveImgBt;
    JButton saveProjBt;
    JButton loadProjBt;
    JButton newProjBt;
    JButton addImgToLayerBt;
    JPanel btPanel;

    this.project = p;
    this.frame = new JFrame();
    this.frame.setTitle("Collage Project");
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane();
    mainScrollPane.setViewportView(mainPanel);
    this.frame.add(mainScrollPane);

    // Layer List
    String[] layer = this.project.getLayerNames();
    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBorder(BorderFactory.createTitledBorder("Selected Layer:"));
    comboboxPanel.setLayout(new FlowLayout());
    comboBox = new JComboBox<String>();
    comboBox.setActionCommand("Layer Selection");
    comboBox.addActionListener(this::actionPerformed);
    for (int i = 0; i < layer.length; i++) {
      comboBox.addItem(layer[i]);
    }
    comboboxPanel.add(comboBox);


    // Scrolling Image Frame
    this.imageLabel = new JLabel();
    this.imageLabel.setHorizontalAlignment(JLabel.CENTER);
    this.imgScrollPane = new JScrollPane(imageLabel);
    imgScrollPane.setPreferredSize(new Dimension(500, 500));
    imgScrollPane.setBorder(BorderFactory.createTitledBorder("Project View:"));
    mainPanel.add(imgScrollPane, BorderLayout.CENTER);
    mainPanel.add(comboboxPanel);

    // Buttons Panel
    btPanel = new JPanel(new FlowLayout());

    newProjBt = new JButton("New Project");
    newProjBt.setActionCommand("New Project");
    newProjBt.addActionListener(this::actionPerformed);

    addLayerBt = new JButton("Add Layer");
    addLayerBt.setActionCommand("Add Layer");
    addLayerBt.addActionListener(this::actionPerformed);

    addImgToLayerBt = new JButton("Add Image to Layer");
    addImgToLayerBt.setActionCommand("Add Image");
    addImgToLayerBt.addActionListener((this::actionPerformed));

    // Load Project Button
    loadProjBt = new JButton("Load Project");
    loadProjBt.setActionCommand("Open File");
    loadProjBt.addActionListener(this::actionPerformed);
    this.loadProjDisplay = new JLabel("File path will appear here");
    btPanel.add(loadProjDisplay);

    // Save Project Button
    saveProjBt = new JButton("Save Project");
    saveProjBt.setActionCommand("Save Project");
    saveProjBt.addActionListener(this::actionPerformed);

    // Save Image Button
    saveImgBt = new JButton("Save Image");
    saveImgBt.setActionCommand("Save Image");
    saveImgBt.addActionListener(this::actionPerformed);
    btPanel.add(saveImgBt);

    setFilterBt = new JButton("Set Filter");
    setFilterBt.setActionCommand("Set Filter");
    setFilterBt.addActionListener(this::actionPerformed);

    btPanel.add(newProjBt);
    btPanel.add(loadProjBt);
    btPanel.add(addLayerBt);
    btPanel.add(addImgToLayerBt);
    btPanel.add(saveImgBt);
    btPanel.add(saveProjBt);
    btPanel.add(setFilterBt);
    mainPanel.add(btPanel);

    frame.getContentPane().add(mainScrollPane, BorderLayout.CENTER);

  }


  /**
   * Getter method to return the current frame in the GUI.
   *
   * @return the current frame to allow the user to view what they are working on.
   */
  public JFrame getFrame() {
    return this.frame;
  }

  private BufferedImage getCompImg() {
    ILayer l = this.project.compressLayers();
    BufferedImage b = new BufferedImage(l.getPixels()[0].length,
            l.getPixels().length, BufferedImage.TYPE_INT_RGB);
    IPixel[][] pixels = l.getPixels();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        int rgb = 65536 * pixels[i][j].getR() + 256 * pixels[i][j].getG() + pixels[i][j].getB();
        b.setRGB(j, i, rgb);
      }
    }
    return b;
  }

  private void updateGui() {
    ILayer mergedLayer = project.compressLayers();
    int w = project.getWidth();
    int h = project.getHeight();
    BufferedImage newGui = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

    IPixel[][] pixels = mergedLayer.getPixels();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        int rgb = 65536 * pixels[i][j].getR() + 256 * pixels[i][j].getG() + pixels[i][j].getB();
        newGui.setRGB(j, i, rgb);
      }
    }
    ImageIcon imageIcon = new ImageIcon(newGui);
    imageLabel.setIcon(imageIcon);
    imgScrollPane.setViewportView(imageLabel);
  }


  /**
   * Method to handle events that are brought about by user interaction with the GUI.
   *
   * @param arg - the event that is being handled by the action performed method.
   */
  public void actionPerformed(ActionEvent arg) {
    int retvalue;
    JFileChooser fchooser;
    switch (arg.getActionCommand()) {
      case "Open File":
        fchooser = new JFileChooser(".");
        retvalue = fchooser.showOpenDialog(this.frame);
        if (retvalue == 0) {
          File f = fchooser.getSelectedFile();
          this.loadProjDisplay.setText("Loaded file path: \n" + f.getAbsolutePath());
          try {
            project.loadProject(f.getAbsolutePath());
            BufferedImage img = getCompImg();
            ImageIcon icon = new ImageIcon(img);
            imageLabel.setIcon(icon);
            imageLabel.revalidate();
            imgScrollPane.repaint();
          } catch (IOException e) {
            throw new IllegalStateException("Unable to find project.");
          }
        }
        break;
      case "Save Project":
        fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Collage",
                new String[]{"collage"});
        fchooser.setFileFilter(filter);
        retvalue = fchooser.showSaveDialog(this.frame);
        if (retvalue == 0) {
          File f = fchooser.getSelectedFile();
          try {
            project.saveProject(f.getAbsolutePath());
          } catch (IOException e) {
            throw new IllegalStateException("Unable to save to given file path.");
          }
        }
        break;
      case "Save Image":
        fchooser = new JFileChooser(".");
        FileNameExtensionFilter fillter = new FileNameExtensionFilter("PPM, JPEG, or PNG",
                new String[]{"ppm", "jpeg", "jpg", "png"});
        fchooser.setFileFilter(fillter);
        retvalue = fchooser.showSaveDialog(this.frame);
        if (retvalue == 0) {
          File f = fchooser.getSelectedFile();
          try {
            project.saveImage(f.getAbsolutePath());
          } catch (IOException e) {
            throw new IllegalStateException("Unable to save to given file path.");
          }
        }
        break;
      case "Add Layer":
        layerName = JOptionPane.showInputDialog("Enter layer name:");
        if (project == null) {
          JOptionPane.showMessageDialog(this.frame,
                  "Please create a project before adding a layer!",
                  "No project found", JOptionPane.ERROR_MESSAGE);
        } else if ((layerName == null || layerName.equals("canvas") || layerName.equals(""))) {
          JOptionPane.showMessageDialog(this.frame, "Please enter valid layer name",
                  "Invalid Layer Name", JOptionPane.ERROR_MESSAGE);
        } else {
          int alpha = Integer.parseInt(JOptionPane.showInputDialog("Enter alpha value (0-255):"));
          String alp = Integer.toString(alpha);
          if (alpha < 0 || alpha > 255 || alp.equals("")) {
            JOptionPane.showMessageDialog(this.frame,
                    "Please enter a valid alpha value within the bounds!",
                    "Invalid alpha value", JOptionPane.ERROR_MESSAGE);
          } else {
            project.addLayer(layerName, alpha);
            comboBox.addItem(layerName);
            updateGui();
          }
        }
        break;
      case "Add Image":
        fchooser = new JFileChooser(".");
        FileNameExtensionFilter filllter = new FileNameExtensionFilter("PPM, JPEG, or PNG",
                new String[]{"ppm", "jpeg", "jpg", "png"});
        fchooser.setFileFilter(filllter);
        int returnValue = fchooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION && !layerName.equals("canvas")) {
          File image = fchooser.getSelectedFile();
          String imagePath = image.getAbsolutePath();


          int x = Integer.parseInt(JOptionPane.showInputDialog("Enter an x value:"));
          int y = Integer.parseInt(JOptionPane.showInputDialog("Enter a y value:"));
          if (layerName == null) {
            // Do nothing
          } else {
            project.addImageToLayer(layerName, imagePath, x, y);
            updateGui();
          }
        } else {
          JOptionPane.showMessageDialog(this.frame, "Please create or select a layer",
                  "Invalid Layer Selection", JOptionPane.ERROR_MESSAGE);
        }
        break;
      case "New Project":
        int height = Integer.parseInt(JOptionPane.showInputDialog("Enter project height:"));
        int width = Integer.parseInt(JOptionPane.showInputDialog("Enter project width:"));
        IProject p = this.project.newProject(height, width, 255);
        this.project = p;
        updateGui();
        break;
      case "Set Filter":
        String[] filters = {"red-component", "blue-component", "green-component",
          "brighten-value", "darken-value", "brighten-luma", "darken-luma",
          "brighten-intensity", "darken-intensity", "brighten-blend", "darken-blend",
          "inversion-blend"};
        var filterName = JOptionPane.showOptionDialog(null, "Choose a Filter",
                "Filter Selection",
                0, 0, null, filters, filters[0]);
        if (filterName == 0) {
          project.setFilter(layerName, "red-component");
        }
        if (filterName == 1) {
          project.setFilter(layerName, "blue-component");
        }
        if (filterName == 2) {
          project.setFilter(layerName, "green-component");
        }
        if (filterName == 3) {
          project.setFilter(layerName, "brighten-value");
        }
        if (filterName == 4) {
          project.setFilter(layerName, "darken-value");
        }
        if (filterName == 5) {
          project.setFilter(layerName, "brighten-luma");
        }
        if (filterName == 6) {
          project.setFilter(layerName, "darken-luma");
        }
        if (filterName == 7) {
          project.setFilter(layerName, "brighten-intensity");
        }
        if (filterName == 8) {
          project.setFilter(layerName, "darken-intensity");
        }
        if (filterName == 9) {
          project.setFilter(layerName, "brighten-blend");
        }
        if (filterName == 10) {
          project.setFilter(layerName, "darken-blend");
        }
        if (filterName == 11) {
          project.setFilter(layerName, "inversion-blend");
        }
        updateGui();
        break;
      case "Layer Selection":
        if (arg.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) arg.getSource();
          layerName = (String) box.getSelectedItem();
        }
        break;
      default:
        //do nothing
    }
  }
}
