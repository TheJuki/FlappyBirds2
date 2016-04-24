package com.nargles.flappybird2.gameEnvironment;

import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.obstacles.Grass;
import com.nargles.flappybird2.gameEnvironment.obstacles.Pipe;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.gameEnvironment.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

/**
 * Scroll Handler
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class ScrollHandler {

    private Grass frontGrass, backGrass;
    private List<Pipe> pipes;
    private int scrollSpeed;
    private boolean isRightGoing;
    private boolean initialLeftFlip;
    private static final int PIPE_GAP = 50;
    private static final int NUM_PIPES = 6;
    private static final float PIPE_WIDTH = 96;
    private static final float PIPE_HEIGHT = 54;
    private static final float PIPE_START_X = 300;
    private static final float PIPE_START_Y = -10;

    private GameWorld gameWorld;
    private float yPos;

    private int currentEggNest = 0;

    /**
     * Constructor
     *
     * @param gameWorld   Game World
     * @param yPos        Y position of the object
     * @param xPos        X position of the object
     * @param scrollSpeed Speed of the objects that move
     */
    public ScrollHandler(GameWorld gameWorld, float yPos, float xPos, int scrollSpeed) {
        this.gameWorld = gameWorld;
        this.scrollSpeed = scrollSpeed;
        this.yPos = yPos;
        frontGrass = new Grass(0, yPos, 960 , 540 / 2.5f, scrollSpeed);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 960 , 540 / 2.5f, scrollSpeed);

        pipes = new ArrayList<Pipe>();
        // 210 * 2, 0 , 22, 10
        pipes.add(new Pipe(PIPE_START_X, PIPE_START_Y, PIPE_WIDTH, PIPE_HEIGHT, scrollSpeed, yPos, true, 0));

        for (int i = 1; i < NUM_PIPES; i++) {
            if(i == 4)
            {
                pipes.add(new Pipe(pipes.get(i - 1).getTailX() + PIPE_GAP,
                        PIPE_START_Y, PIPE_WIDTH, PIPE_HEIGHT, scrollSpeed, yPos, true, 0));
                ++currentEggNest;
            }
            else
            {
                pipes.add(new Pipe(pipes.get(i - 1).getTailX() + PIPE_GAP,
                        PIPE_START_Y, PIPE_WIDTH, PIPE_HEIGHT, scrollSpeed, yPos, false, 0));
            }
        }

        initialLeftFlip = false;
        isRightGoing = scrollSpeed < 0;
    }

    /**
     * @param delta Update scalar
     */
    public void updateReady(float delta) {

        // Update grass
        frontGrass.update(delta);
        backGrass.update(delta);

        // Reset grass
        if (frontGrass.isScrolledBack()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledBack()) {
            backGrass.reset(frontGrass.getTailX());
        }
    }

    /**
     * Flip direction of ground and pipe movement
     */
    public boolean flip() {
        isRightGoing = !isRightGoing;

        if (!isRightGoing) {
            initialLeftFlip = true;
            frontGrass.goLeft();
            backGrass.goLeft();
            for (Pipe pipe : pipes) {
                pipe.goLeft();
                if (pipe.getNest() != null) {
                    pipe.getNest().goLeft();
                }
            }
        } else {
            initialLeftFlip = false;
            frontGrass.goRight();
            backGrass.goRight();
            for (Pipe pipe : pipes) {
                pipe.goRight();
                if (pipe.getNest() != null) {
                    pipe.getNest().goRight();
                }
            }
        }

        return isRightGoing;
    }

    /**
     * Update ground and pipes x positions
     *
     * @param delta Scalar for Velocity vectors
     */
    public void update(float delta) {

        // Update grass
        frontGrass.update(delta);
        backGrass.update(delta);

        for (Pipe pipe : pipes) {
            pipe.update(delta);
        }

        // Reset Pipes

        if (isRightGoing) {

            /*
            if (pipes.get(0).isScrolledBack()) {
				pipes.get(0).reset(pipes.get(pipes.size() - 1).getTailX() + PIPE_GAP);
			}
			*/

            for (int i = 0; i < pipes.size(); i++) {
                if (pipes.get(i).isScrolledBack() && !pipes.get(i).isPipeCreatedChild()) {
                    if (gameWorld.getDistance()%5 == 4) {
                        if(currentEggNest == 3)
                            currentEggNest = 0;
                        pipes.add(new Pipe(pipes.get(pipes.size() - 1).getTailX() + PIPE_GAP,
                                PIPE_START_Y, PIPE_WIDTH, PIPE_HEIGHT, scrollSpeed, yPos, true, currentEggNest));
                        currentEggNest++;
                    }
                    else
                    {
                        pipes.add(new Pipe(pipes.get(pipes.size() - 1).getTailX() + PIPE_GAP,
                                PIPE_START_Y, PIPE_WIDTH, PIPE_HEIGHT, scrollSpeed, yPos, false, 0));
                    }
                    pipes.get(i).setPipeCreatedChild(true);
                    break;
                }
            }
        } else {

            if (pipes.get(0).isScrolledBack()) {
                if (initialLeftFlip) {
                    flip();
                }

            }
        }

        // Reset grass
        if (frontGrass.isScrolledBack()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledBack()) {
            backGrass.reset(frontGrass.getTailX());

        }
    }

    /**
     * Stop movement of the pipes and ground
     */
    public void stop() {
        frontGrass.stop();
        backGrass.stop();
        for (Pipe pipe : pipes) {
            pipe.stop();
            if (pipe.getNest() != null) {
                pipe.getNest().stop();
            }
        }
    }

    /**
     * Determines if a bird and a pipe met
     * If not then see if score needs to be updated
     *
     * @param bird Bird
     * @return A pipe collided with the bird
     */
    public boolean collides(Bird bird) {

        boolean isCollided = false;

        for (Pipe pipe : pipes) {
            if (!pipe.isScored() && pipe.isRightGoing()
                    && (pipe.getX() + (pipe.getWidth() / 2)) < (bird.getX() + bird.getWidth())) {
                addScore(1);
                gameWorld.addDistance(1);
                pipe.setScored(true);
                AssetLoader.coin.play();
            }
            if (pipe.collides(bird))
                isCollided = true;

            gotNest(pipe, bird);
        }

        return isCollided;
    }

    /**
     * Determines if a bird and a nest met
     *
     * @param pipe Pipe
     * @param bird Bird
     * @return The bird picked up a nest of eggs
     */
    public boolean gotNest(Pipe pipe, Bird bird) {

        boolean didGet = false;

        if (pipe.getNest() != null && pipe.getNest().collides(bird))
            didGet = true;

        return didGet;
    }

    /**
     * Determines if a projectile a pipe met
     *
     * @param projectile Projectile
     * @return if a pipe was destroyed (2) or just hit (1)
     */
    public int destroys(Projectile projectile) {

        int isDestroyed = 0;

        for (Pipe pipe : pipes) {

            isDestroyed = pipe.destroys(projectile);

            if (isDestroyed == 1) {
                break;
            }
            else if (isDestroyed == 2) {
                gameWorld.addPipesDestroyed(1);
                addScore(10);
                break;
            }
        }

        return isDestroyed;
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    /**
     * Revive pipes and ground
     */
    public void onRestart() {
        isRightGoing = true;
        initialLeftFlip = false;
        currentEggNest = 0;
        frontGrass.onRestart(0, scrollSpeed);
        backGrass.onRestart(frontGrass.getTailX(), scrollSpeed);
        pipes.get(0).onRestart(300, scrollSpeed);

        for (int i = 1; i < pipes.size(); i++) {
            pipes.get(i).onRestart(pipes.get(i - 1).getTailX() + PIPE_GAP, scrollSpeed);
        }
    }

    public boolean isRightGoing() {
        return isRightGoing;
    }

}
