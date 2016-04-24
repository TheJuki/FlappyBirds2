package com.nargles.flappybird2.gameEnvironment.obstacles;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.gameEnvironment.Scrollable;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.gameEnvironment.projectiles.Projectile;

import java.util.Random;

/**
 * Pipe
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class Pipe extends Scrollable {

    private Rectangle barUp, barDown;
    private Circle pipeTopUp, pipeTopDown;

    private static final int VERTICAL_GAP = 45;
    private static final float PIPE_TOP_WIDTH = 96 / 1.5f;
    private static final float PIPE_TOP_HEIGHT = 54 / 1.5f;
    private static long seedQualifier = 432282912137141232L;
    private float groundY;

    private boolean barTopVisible;
    private boolean barBottomVisible;

    private Nest nest;

    private float topHealth;
    private float bottomHealth;

    private boolean isScored = false;

    private boolean pipeCreatedChild = false;

    /**
     * Constructor
     *
     * @param x           Pipe X position
     * @param y           Pipe Y position
     * @param width       Pipe sprite width
     * @param height      Pipe sprite height
     * @param scrollSpeed Speed of the Pipe
     * @param groundY     Ground's Y position
     * @param addNest     if true, add a nest to the pipe
     * @param nestType    The nest type to add
     */
    public Pipe(float x, float y, float width, float height, float scrollSpeed,
                float groundY, boolean addNest, int nestType) {
        super(x, y, width, height, scrollSpeed);

        pipeTopUp = new Circle();
        pipeTopDown = new Circle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        this.height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
        this.groundY = groundY;
        topHealth = 3.0f;
        bottomHealth = 3.0f;
        barTopVisible = true;
        barBottomVisible = true;

        if (addNest) {
            addNest(nestType);
        }
    }

    /**
     * Update the stacked pipes
     *
     * @param delta Update scalar
     */
    @Override
    public void update(float delta) {
        super.update(delta);

        if (nest != null) {
            nest.update(delta);
        }

        barUp.set(position.x + 44, position.y, width / 8.5f, height);
        barDown.set(position.x + 44, position.y + height + VERTICAL_GAP, width / 8.5f,
                groundY - (position.y + height + VERTICAL_GAP));

        pipeTopUp.set(position.x - (PIPE_TOP_WIDTH - width / 2) / 2, position.y + height
                - PIPE_TOP_HEIGHT, 20);
        pipeTopDown.set(position.x - (PIPE_TOP_WIDTH - width / 2) / 2, barDown.y,
                15);

        pipeTopUp.set((position.x + 58) - (PIPE_TOP_WIDTH - width / 2) / 2, position.y - 5 + height, 14);

        pipeTopDown.set((position.x + 58) - (PIPE_TOP_WIDTH - width / 2) / 2, barDown.y + 12,
                14);


    }

    /**
     * Reset pipe x position and height
     *
     * @param newX New x position
     */
    @Override
    public void reset(float newX) {
        super.reset(newX);
        // Change the height to a random number
        height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
        barTopVisible = true;
        barBottomVisible = true;
        isScored = false;
        topHealth = 3.0f;
        bottomHealth = 3.0f;
        if (nest != null)
            nest.resetNest(position.x + 38f, getY() + getHeight() + 30);
    }

    /**
     * Restart pipe position
     *
     * @param x           x position to move to
     * @param scrollSpeed Movement speed
     */
    public void onRestart(float x, float scrollSpeed) {
        super.onRestart();
        velocity.x = scrollSpeed;
        barTopVisible = true;
        barBottomVisible = true;
        if (nest != null) {
            nest.onRestart(x, scrollSpeed);
        }
        reset(x);
    }

    /**
     * Determines if a bird and the pipe met
     *
     * @param bird Bird
     * @return if a bird and the pipe met
     */
    public boolean collides(Bird bird) {
//TODO remove easy mode
        return ((1 == 1) && position.x < (bird.getX() + bird.getWidth())) &&
                ((Intersector.overlaps(bird.getBoundingCircle(), barUp) && barTopVisible) ||
                        (Intersector.overlaps(bird.getBoundingCircle(), barDown) && barBottomVisible) ||
                        (Intersector.overlaps(bird.getBoundingCircle(), pipeTopUp) && barTopVisible) ||
                        (Intersector.overlaps(bird.getBoundingCircle(), pipeTopDown) && barBottomVisible));

    }

    /**
     * Determines if a projectile and the pipe met
     *
     * @param projectile Projectile
     * @return if a pipe was destroyed (2) or just hit (1)
     */
    public int destroys(Projectile projectile) {

        int destroyed = 0;

        if ((position.x < (projectile.getX() + projectile.getWidth())) && barTopVisible &&
                (Intersector.overlaps(projectile.getBoundingCircle(), barUp) ||
                        Intersector.overlaps(projectile.getBoundingCircle(), pipeTopUp))) {

            switch (projectile.getType()) {
                case 0:
                    topHealth -= 1;
                    break;
                case 1:
                    topHealth -= 1.5;
                    break;
                case 2:
                    topHealth -= 3;
                    break;
                default:
                    break;
            }

            destroyed = 1;

            if (topHealth <= 0) {
                barTopVisible = false;
                destroyed = 2;
            }

        } else if ((position.x < (projectile.getX() + projectile.getWidth())) && barBottomVisible &&
                (Intersector.overlaps(projectile.getBoundingCircle(), barDown) ||
                        Intersector.overlaps(projectile.getBoundingCircle(), pipeTopDown))) {

            switch (projectile.getType()) {
                case 0:
                    bottomHealth -= 1;
                    break;
                case 1:
                    bottomHealth -= 1.5;
                    break;
                case 2:
                    bottomHealth -= 3;
                    break;
                default:
                    break;
            }

            destroyed = 1;

            if (bottomHealth <= 0) {
                barBottomVisible = false;
                destroyed = 2;
            }
        }

        return destroyed;

    }

    /**
     * Add nest to pipe
     */
    private void addNest(int ammoType) {
        this.nest = new Nest(position.x + 38f, getY() + getHeight() + 30, 96 / 4, 54 / 4, getScrollSpeed(), ammoType);
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }

    public boolean isBarTopVisible() {
        return barTopVisible;
    }

    public boolean isBarBottomVisible() {
        return barBottomVisible;
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }

    public boolean isPipeCreatedChild() {
        return pipeCreatedChild;
    }

    public void setPipeCreatedChild(boolean pipeCreatedChild) {
        this.pipeCreatedChild = pipeCreatedChild;
    }

    public Circle getPipeTopUp() {
        return pipeTopUp;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Circle getPipeTopDown() {
        return pipeTopDown;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
}
