package cs1302.omega;

import cs1302.game.DemoGame;
import cs1302.omega.OmegaGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        Image bannerImage = new Image("file:resources/brickbreakerbanner.jpeg");
        ImageView banner = new ImageView(bannerImage);
        banner.setPreserveRatio(false);
        banner.setFitWidth(620);
        banner.setFitHeight(200);
        Button start = new Button("Start");
        OmegaGame game = new OmegaGame(620, 440);

        // some labels to display information
        Label notice = new Label("Welcome to Brick Breaker!");
        Label instructions
            = new Label("Move the paddle left/right with arrow keys; " +
            "don't let the ball hit the ground!");

        // setup scene
        VBox root = new VBox(banner, notice, instructions, game);
        Scene scene = new Scene(root);
/*
        EventHandler<ActionEvent> startGame = event -> {
            root.getChildren().add(game);
        };
        start.setOnAction(startGame);
*/
        // setup stage
        stage.setTitle("Brick Breaker");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

        // play the game
        game.play();

    } // start

} // OmegaApp
