package com.nargles.flappybird2.gameEnvironment.obstacles;

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

    private Rectangle pipeTopUp, pipeTopDown, barUp, barDown;

    private static final int VERTICAL_GAP = 45;
    private static final int PIPE_TOP_WIDTH = 24;
    private static final int PIPE_TOP_HEIGHT = 11;
    private static long seedQualifier = 432282912137141232L;
    private float groundY;

    private boolean barTopVisible;
    private boolean barBottomVisible;

    private Nest nest;

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

        pipeTopUp = new Rectangle();
        pipeTopDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        this.height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
        this.groundY = groundY;

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

        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));

        pipeTopUp.set(position.x - (PIPE_TOP_WIDTH - width) / 2, position.y + height
                - PIPE_TOP_HEIGHT, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);
        pipeTopDown.set(position.x - (PIPE_TOP_WIDTH - width) / 2, barDown.y,
                PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

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
        nest = null;
        reset(x);
    }

    /**
     * Determines if a bird and the pipe met
     *
     * @param bird Bird
     * @return if a bird and the pipe met
     */
    public boolean collides(Bird bird) {

        return ((1 == 2) && position.x < (bird.getX() + bird.getWidth())) &&
                ((Intersector.overlaps(bird.getBoundingCircle(), barUp) && barTopVisible) ||
                        (Intersector.overlaps(bird.getBoundingCircle(), barDown) && barBottomVisible) ||
                        (Intersector.overlaps(bird.getBoundingCircle(), pipeTopUp) && barTopVisible) ||
                        (Intersector.overlaps(bird.getBoundingCircle(), pipeTopDown) && barBottomVisible));

    }

    /**
     * Determines if a projectile and the pipe met
     *
     * @param projectile Projectile
     * @return if a projectile and the pipe met
     */
    public boolean collides(Projectile projectile) {

        boolean collided = false;

        if ((position.x < (projectile.getX() + projectile.getWidth())) && barTopVisible &&
                (Intersector.overlaps(projectile.getBoundingCircle(), barUp) ||
                        Intersector.overlaps(projectile.getBoundingCircle(), pipeTopUp))) {
            collided = true;
            barTopVisible = false;
        } else if ((position.x < (projectile.getX() + projectile.getWidth())) && barBottomVisible &&
                (Intersector.overlaps(projectile.getBoundingCircle(), barDown) ||
                        Intersector.overlaps(projectile.getBoundingCircle(), pipeTopDown))) {
            collided = true;
            barBottomVisible = false;
        }

        return collided;

    }

    /**
     * Add nest to pipe
     */
    private void addNest(int ammoType) {
        this.nest = new Nest(position.x - 0.5f, getY() + getHeight() + 30, 96 / 4, 54 / 4, getScrollSpeed(), ammoType);
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
}
