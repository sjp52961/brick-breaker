package cs1302.omega;

import cs1302.game.Game;
import cs1302.omega.Ball;

import java.util.Random;
import java.util.logging.Level;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.geometry.BoundingBox;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import java.lang.NullPointerException;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * This is a game of Brick Breaker. There is a
 * {@code ball} that bounces around and breaks the bricks.
 * To avoid the ball touching the ground, the player must
 * use the {@code paddle}. This paddle can move left and right
 * using the arrow keys. If the ball touches the ground, the player loses
 * a life. Each player starts with three lives.
 */
public class OmegaGame extends Game {
    private Random rng;       // random number generator
    private Rectangle paddle; // paddle used to deflect the ball
    protected Ball ball;      // ball used to break bricks
    private Rectangle brick;  // brick used to build wall
    protected ArrayList<Rectangle> walls;
    private int rows = 4;
    private int cols = 9;
    private int score = 0;
    private int lives = 3;
    private Label label;
    private Button playAgain;

    /**
     * Construct a {@code OmegaGame} object.
     * @param width scene width
     * @param height scene height
     */
    public OmegaGame(int width, int height) {
        super(width, height, 60);               // call parent constructor
        setLogLevel(Level.INFO);                // enable logging
        this.rng = new Random();                // random number generator
        this.paddle = new Rectangle(120, 20);   // paddle used to deflect the ball
        this.ball = new Ball(this);
        this.walls = new ArrayList();
        this.label = new Label("Lives: 3");
        this.playAgain = new Button("Play Again");
    } // OmegaGame

    /** {@inheritDoc} */
    @Override
    protected void init() {
        // add to main container
        getChildren().addAll(label, paddle, ball);
        // setup paddle
        paddle.setX(0);
        paddle.setY(420);
        //setup ball
        ball.setCenterX(40);
        ball.setCenterY(400);
        //setup walls
        for (int i = 0; i < cols; i++) {
            for (int j = 1; j < rows + 1; j++) {
                int x = i * 70;
                int y = j * 30;
                brick = new Rectangle(x, y, 60, 20);
                brick.setFill(Color.BLUE);
                getChildren().add(brick);
                walls.add(brick);
            } // for
        } // for
    } // init

    /**
     * Returns the paddle.
     *
     * @return paddle
     */
    public Rectangle getPaddle() {
        return paddle;
    } //getPaddle

    /**
     * Returns the walls.
     *
     * @return walls
     */
    public ArrayList<Rectangle> getWalls() {
        return walls;
    } // getWalls

    /**
     * Lowers the number of lives. When the player
     * reaches zero lives, it allows them to start
     * the game again.
     */
    public void lowerLives() {
        lives--;
        System.out.println(lives);

        if (lives == 2) {
            getChildren().remove(label);
            this.label = new Label("Lives: 2");
            getChildren().add(label);
        } // if

        if (lives == 1) {
            getChildren().remove(label);
            this.label = new Label("Lives: 1");
            getChildren().add(label);
        } // if

        if (lives == 0) {
            getChildren().removeAll(label);
            this.label = new Label("Lives: 0. Game Over!");
            getChildren().addAll(label, playAgain);
            playAgain.setLayoutX(280);
            playAgain.setLayoutY(180);
            stop();

            EventHandler<ActionEvent> playGame = event -> {
                getChildren().remove(playAgain);
                lives = 3;
                getChildren().remove(label);
                this.label = new Label("Lives: 3");
                getChildren().add(label);
                play();
            };

            playAgain.setOnAction(playGame);
        } // if
    } // getLives

    /**
     * Removes a brick from the {@code walls} when the ball
     * intersects it.
     *
     * @param i the brick within the wall
     */
    protected void removeBrick(int i) {
        walls.get(i).setTranslateX(1000);
        this.getChildren().remove(walls.get(i));
        if (walls.isEmpty()) {
            getChildren().remove(label);
            this.label = new Label("You Won!");
            getChildren().add(label);
            stop();
        } //
    } // removeBrick

    /** {@inheritDoc} */
    @Override
    protected void update() {
        // update paddle position
        isKeyPressed(KeyCode.LEFT, () -> paddle.setX(paddle.getX() - 10.0));
        isKeyPressed(KeyCode.RIGHT, () -> paddle.setX(paddle.getX() + 10.0));
        ball.update();
    } // update

} // OmegaGame
