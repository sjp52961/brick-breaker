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
 * This is a game of Brick Breaker. There is a wall of bricks and a ball
 * that bounces around and if it hits a brick,
 * the brick will break and be removed from the wall.
 * To avoid the ball touching the ground, the player must
 * use the paddle. This paddle can move left and right
 * using the arrow keys. If the ball hits the paddle,
 * it will reflect off of it. If the ball touches the ground, then the player
 * loses a life. Each player starts with three lives. If the player loses all
 * three lives, then the game is over. If the player clears all of the bricks
 * without losing three lives, then they win the game.
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
