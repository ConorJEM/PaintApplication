package drawingMain;

import model.vectorModel;
import viewing.view;
import controller.vectorController;

/**
 * Initialises the program, making a new model, controller and view.
 */
public class drawingMain {

    public static void main(String[] args)  {

        vectorModel model = new vectorModel();
        vectorController controller = new vectorController(model);
        new view(model, controller);

    }

}
