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

/**
 * Later.
 */
public class OmegaGame extends Game {

    //BALL
    private Game game; // game containing this sprite
    private double dx; // change in x per update
    private double dy; // change in y per update

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
    //private String text = "Score: " + score.toString() + " Lives: " + lives.toString();

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
        this.label = new Label("Score: 0, Lives: 3");
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
        System.out.println(walls);

        /*
        for (int x = 0; x < 570; x += 70) {
            brick = new Rectangle(x, 120.0, 60.0, 20.0);
            brick.setFill(Color.BLUE);
            int i = x/70;
            walls.add(brick);
            getChildren().add(walls.get(i));
        } // for
        */
//paddle.setOnMouseClicked(event -> handleClickPlayer(event));
    } // init

    /**
     * Returns the paddle.
     *
     * @return the paddle
     */
    public Rectangle getPaddle() {
        return paddle;
    } //getPaddle

    /**
     * Returns the brick.
     */
    public Rectangle getBrick() {
        return brick;
    } // getBrick

    /**
     * Returns the walls.
     *
     * @return walls
     */
    public ArrayList<Rectangle> getWalls() {
        return walls;
    } // getWalls

    /**
     * Later.
     */
    protected void removeBrick(int i) {
/*
        for (int i = 0; i < walls.size(); i++) {
            if (ball.getBoundsInParent().intersects(walls.get(i).getBoundsInParent())) {
                walls.remove(walls.get(i));
                this.getChildren().remove(walls.get(i));
                score += 1;
            } // if
        } // for
        //System.out.println(score);
        */
        //System.out.println("hello");

                //brick = new Rectangle(x, 120.0, 60.0, 20.0);
                //this.getChildren().remove(brick);
        walls.get(i).setTranslateX(1000);
        this.getChildren().remove(walls.get(i));
        score++;
        System.out.println(score);
    } // removeBrick

    /** {@inheritDoc} */
    @Override
    protected void update() {
        // update paddle position
        isKeyPressed(KeyCode.LEFT, () -> paddle.setX(paddle.getX() - 10.0));
        isKeyPressed(KeyCode.RIGHT, () -> paddle.setX(paddle.getX() + 10.0));

        // <--------------------------------------------------------------------
        // try adding the code to make the player move up and down!
        //isKeyPressed(KeyCode.UP, () -> player.setY(player.getY() - 10.0));
        //isKeyPressed(KeyCode.DOWN, () -> player.setY(player.getY() + 10.0));
        // <--------------------------------------------------------
        ball.update();
        //removeBrick();
    } // update

    /**
     * Move the player rectangle to a random position.
     * @param event associated mouse event
     */
    private void handleClickPlayer(MouseEvent event) {
        logger.info(event.toString());
        paddle.setX(rng.nextDouble() * (getWidth() - paddle.getWidth()));
        paddle.setY(rng.nextDouble() * (getHeight() - paddle.getHeight()));
    } // handleClickPlayer

} // OmegaGame
