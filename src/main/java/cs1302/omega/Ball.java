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
 * Creates a ball that changes directions
 * when it hits the bricks, paddle, or game
 * boundaries.
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
        this.setRadius(10);
        this.setFill(Color.RED);
        this.game = game;
        this.dx = 2; // each update, add 2 to x (to start)
        this.dy = 2; // each update, add 2 to y (to start)
    } // Ball

    /**
     * Update the position of the ball.
     */
    public void update() {
        Bounds ballBounds = getBoundsInParent();
        Bounds gameBounds = game.getGameBounds();
        if (ballBounds.getMaxX() > gameBounds.getMaxX()) {
            dx *= -1.0;
        } else if (ballBounds.getMinX() < gameBounds.getMinX()) {
            dx *= -1.0;
        } else if (ballBounds.getMaxY() > gameBounds.getMaxY()) {
            game.lowerLives();
            dy *= -1.0;
        } else if (ballBounds.getMinY() < gameBounds.getMinY()) {
            dy *= -1.0;
        } else if (ballBounds.intersects(game.getPaddle().getBoundsInParent())) {
            dy *= -1.0;
        } // if

        for (int i = game.getWalls().size() - 1; i >= 0; i--) {
            if (ballBounds.intersects(game.getWalls().get(i).getBoundsInParent())) {
                dy *= -1.0;
                dx *= -1.0;
                game.removeBrick(i);
            } // if
        } // for

        setTranslateX(getTranslateX() + dx);
        setTranslateY(getTranslateY() + dy);
    } // update

} // Ball
