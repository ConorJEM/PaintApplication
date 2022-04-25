1. Running the program.
	
	To simply run the application, download the P4-Drawing.jar file, go into your console, navigate to the file and run 'java -jar P4-Drawing.jar'
	
	To run the project and tests, After compiling in your IDE or from the console, run 'java drawingMain.drawingMain' to open the program.
	All tests are written in JUnit 4, the same style as with the animal Chess tests. I ran the tests from intelliJ by loading the project directory within 
	intelliJ, marking the src directory as the sources root, and marking the test directory as the test root, then running the 3 individual test classes.
![image](https://user-images.githubusercontent.com/75184002/165093480-db1155a4-48f9-4252-ac8a-ce2aae6f4e78.png)

2. FEATURES

IMPLEMENTED FEATURES:

	(ALL BASIC REQUIREMENTS IMPLEMENTED)
 
		-DRAWING STRAIGHT LINES
		-DRAWING RECTANGLES
		-DRAWING ELLIPSES
		-DRAWING DIAGONAL CROSSES
		-UNDO/REDO
			To use: The undo and redo buttons are located on the button panel at the top of the screen.

		-DIFFERENT COLOURS
			To use: 6 Colours are available, you can change the colour selection by clicking one of the colour buttons at the bottom of the screen.
			Click the 'Fill Mode' button at the top of the screen to toggle the fill mode, allowing shapes to be filled instead of drawn.


	(ADVANCED REQUIREMENTS IMPLEMENTED)
	
		-ADD SEVERAL MORE SHAPES: TRIANGLES, PARALLELOGRAM, HEXAGON AND RANDOM (A RANDOM PATH MOVING BETWEEN 3 to 10 POINTS AT RANDOM LOCATIONS)
			To use: Buttons for every shape implemented are provided at the top of the frame. The random shape is just for fun and sometimes makes nice shapes.

		-ADD ASPECT RATIO LOCK FOR ELLIPSE (TO MAKE CIRCLES) AND RECTANGLE (TO MAKE SQUARES) 
			To use: Click Edit in the top left -> Click aspect mode to toggle aspect mode (Default off).
			I tried to write this with a key listener but had many issues as the JFrame kept losing focus thus it wouldnt pick up any keystrokes, so I used a button.
		
		-SAVING AND LOADING FILES: 
			To use: 
			SAVING: Click 'file' at the top left and click the 'save' button to open a prompt to save drawings. Save as .txt files! 
			LOADING: Click 'file' at the top left and click the 'load' button to open a prompt to load drawings. After drawings are loaded, the shapelist AND redoStack are loaded.

		-DRAGGING OF SHAPES:
			Not sure if this is an advanced or basic requirement, shapes are drawn before the mouse is released.

	
3. How my design works;

A (very rough) class diagram is attactched in this project folder named ClassDiagram.png

I used a MVC design to make the paint application. The model (from class vectorModel) updates its listeners by way of an interface 'Listener' which asks for
an update method.
The view implements this interface by repainting everytime there is an update. This is done the same way as the frog example from week 7.

The JPANEL within Viewing.view has 3 JPanels on its content pane.
The view will always go through the controller if it wants to CHANGE any values within the model. The view will directly call the models getter methods if 
it is not going to change anything within the model. This is the same as the calculator example from week 7. I could have made the getter methods go through 
the controller, but I thought this is unneccesary.


vectorModel stores the users current Colour Selection, Shape Selection, and fill Mode (true or false) and aspectMode (true or false). It also stores the list of all Shapes drawn by the user.
vectorModel also has a stack of Shapes for all shapes that were 'un done' to enable redo functionality called the redoStack. The way I have written this means that if a user undoes a shape,
then draws some more shapes, pressing redo will actually undo the shape that was last undone. I decided I liked the model working this way, but it could be changed so that
the redoStack clears after any new shape is created.
