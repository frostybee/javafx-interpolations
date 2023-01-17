package org.frostybee.animation.ui;

import org.frostybee.animation.controllers.MainAppController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A JavaFX application that showcases various animation interpolations.
 *
 * @author frostybee.
 */
public class MainApp extends Application {

    private final String FXML_LAYOUT_FILE = "/fxml/MainApp_layout.fxml";
    MainAppController fxmlController;

    @Override
    public void start(Stage stage) throws Exception {
        // Load the scene graph from FXML.
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_LAYOUT_FILE));
        fxmlController = new MainAppController();
        loader.setController(fxmlController);
        Pane root = loader.load();
        // Set the scene graph to the scene.
        Scene scene = new Scene(root, 900, 700);
        // Load the scene into stage (window)
        stage.setScene(scene);
        stage.setTitle("JavaFX Animation Interpolation");
        // Resize the stage so the size matches the scene
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        fxmlController.stopAnimation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
