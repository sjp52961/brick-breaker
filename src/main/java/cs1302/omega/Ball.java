package cs1302.omega;

import cs1302.game.Game;
import cs1302.omega.OmegaGame;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.EventHandler;

/**
 * Later.
 */
public class Ball extends Circle {

    private OmegaGame game; // game containing this sprite
    private double dx; // change in x per update
    private double dy; // change in y per update

    /**
     * Construct an {@code Ball} object.
     * @param game parent game
     */
    public Ball(OmegaGame game) {
        //super("file:resources/sprites/cat_idle.gif"); // call parent constructor
        //this.setPreserveRatio(true);
        this.setRadius(10);
        this.setFill(Color.RED);
        this.game = game;
        this.dx = 2; // each update, add 2 to x (to start)
        this.dy = 2; // each update, add 0 to y (to start)
    } // Ball

    /**
     * Update the position of the ball.
     */
    public void update() {
        Bounds ballBounds = getBoundsInParent();
        Bounds gameBounds = game.getGameBounds();
        if (ballBounds.getMaxX() > gameBounds.getMaxX()) {
            dx *= -1.0;      // change x direction
        } else if (ballBounds.getMinX() < gameBounds.getMinX()) {
            dx *= -1.0;      // change x direction
        } else if (ballBounds.getMaxY() > gameBounds.getMaxY()) {
            dy = 0;
            dx = 0;
            /*
            EventHandler<ActionEvent> alertEvent = event -> {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setWidth(100.0);
                alert.setHeight(200.0);
                alert.setResizable(false);
                alert.setContentText("GAME OVER");
                alert.showAndWait();
            }; // alertEvent
            */
        } else if (ballBounds.getMinY() < gameBounds.getMinY()) {
            dy *= -1.0;// change y direction
        } else if (ballBounds.intersects(game.getPaddle().getBoundsInParent())) {
            dy *= -1.0;
            //System.out.println("HELLO");
        } // if

        for (int i = game.getWalls().size() - 1; i >= 0; i--) {
            if (ballBounds.intersects(game.getWalls().get(i).getBoundsInParent())) {
                //game.getWalls().remove(game.getWalls().get(i));
                //OmegaGame.getChildren().remove(game.getWalls().get(i));
                dy *= -1.0;
                dx *= -1.0;
                game.removeBrick(i);
                //setTranslateX(getTranslateX() - 10);
                //setTranslateY(getTranslateY() - 10);
            } // if
        } // for

        /*
        for (int x = 0; x < 570; x += 70) {
            if (ballBounds.intersects(gam) {
                dy *= -1.0;
                game.removeBrick(x/70);
                //game.getWalls().remove(game.getWalls().get(i));
                //OmegaGame.getChildren().remove(game.getWalls().get(i));
            } // if
        } // for
        */

        setTranslateX(getTranslateX() + dx);   // move this ball
        setTranslateY(getTranslateY() + dy);
    } // update

} // Ball
