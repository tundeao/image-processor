The goal of this project was to create a program that could read, create, and edit collage projects
using multiple images, filters, and effects.
- Currently, users can create new projects or load old ones to continue editing, add images
  (PPMs, JPEGs, or PNGs) to their projects, and change the opacity of each image. You can do this
  through the graphical user interface (GUI) to view the project as you edit it, or through the text
  user interface using predefined commands to view a final product.

Requirements/Dependencies:
- Java 11 or higher JRE
- JUnit 4 for running tests

Instructions on how to use the program in its entirety are given in the USEME file, which can be
found in this project (titled USEME.txt). This will give you instructions on how to launch and use
the textUI and the GUI, along with the commands needed to create your project.

A Pixel represents a pixel in an image, with r, g and b values as fields. The pixel has two
constructors one with r,g,b,a values where the 'a' represents transparency and the other with just the
r,g,b values. The constructors throw an exception whenever there is a value less than 0 or greater
than 255. The Pixel has a filter method where it filters the pixel to only show the color given in
the method’s parameter. Another method that this class contains is the brighten method which
increases each r,g,b value by a given x value. The darken method decreases each r,g,b value by a
given x value. Finally, this class has getter methods for each of the fields and also to calculate
the max value, the intensity and the luma of the Pixel.


A Position represents a position in a 2-dimensional space by using an x and y integer as fields. The
Position has a single constructor containing the integer values of x and y, and there are currently
no additional methods in the class.


A Layer represents a layer in a collage project, with a 2-dimensional array of pixels as fields. The
Layer has a single constructor with a 2-dimensional array of pixels as its parameter. The Layer also
has a filter method that filters each pixel in the 2d array by a given color. This class also has
three brighten methods, one for the max value, another for the luma value and the final for the
intensity value. These brighten methods brighten each pixel in the 2d array by its specific max,
luma and intensity values. There are also three darken methods for the max value, the luma value and
the intensity value which do the exact opposite of the brighten methods. Finally this class has a
getter method for the 2d array of pixels.


A Project represents a digital image project that consists of multiple layers. The Project has a
single constructor with integers representing the height, width, and maximum value of the RGB values
of the project, as well as a list of Layer to represent the layers in the Project, followed by a
list of Names, which is a list of Strings that represents the names of the Layers that make up the
Project. The addLayer method adds a new transparent and blank layer with the given name to the
project. The methods getHeight, getWidth, and getMax are all methods to return the mentioned value
of the project. The finalImage method returns a Layer that represents the final image created in a
project with all layers combined. The saveImage method saves the finalImage layer as a file to the
given path. The saveProject saves the Project to the given file path, while the loadProject method
retrieves and loads a saved Project from the given file path. The addImageToLayer method adds a
given image to a given layer, offset by a given x-y position. The setFilter method applies a filter
to the layer it is given, and the getLayer method returns the Layer that corresponds to the given
Layer name.


The View is an interface that represents a GUI (graphical user interface) view. The only method in
View is the renderMessage method, which displays a message in the view. If there is an error in
displaying said message, the interface will throw an IOException to alert the user.


A ViewImpl implements the View interface and represents a GUI view for a given Project. It has a
field that represents the given Project, and an out field that represents an Appendable object
acting as the view’s output location. There is a single constructor for ViewImpl that initializes
the p and out fields with the given Project and Appendable object. The renderMessage method displays
the given method in a view by appending it to the out field, which throws an IOException if there is
an error with appending the message. The getProject method is a getter method that returns the
Project that the view is dealing with.


An IController represents an interface of a Controller. This interface has one method which allows
the user to interact with the project by giving commands such as new-project height width or
add-layer layer-name. This interface has no purpose other than to be implemented by the
ControllerImpl class.


A ControllerImpl represents the main Controller which allows the user to interact with the project.
This class has fields for a Project, a View and a Readable. The class has a singular constructor
which is given a Project, a View and a Readable which throws an exception whenever any of  the
parameters are null. There are many commands that the controller gives the user access to.
new-project height width will create a new project with the given height and width values.
load-project path will load a saved project given its absolute path. These first two commands are
only able to work as a first command and cannot be used after already loading or creating a new
project. The command quit or q will stop the method from running completely and can be used at any
time, as the first command or even later and loses all unsaved work. The following commands can only
be used after loading or creating a new project. add-layer layer-name adds a layer and gives the
layer the name layer-name. add-image-to-layer layer-name image-path x y adds the image at the given
image-path to the layer with the given layer-name to the offsetting x and y values. set-filter
layer-name filter-name filters the given filter-name to the layer with the given layer-name.
save-image file-path saves the image to the given file-path. save-project file-path saves the entire
project to the given file-path.


Image Citation:
Cox, Kevin C. “CelticsBlog: Player of the Week #19: Jayson Tatum.” Celticsblog.com, 15 Mar. 2023, 
https://www.celticsblog.com/2023/3/15/23638453/celticsblog-player-of-the-week-19-jayson-tatum-boston-celtics-portland-trail-blazers-atlanta-hawks. Accessed 17 Mar. 2023.