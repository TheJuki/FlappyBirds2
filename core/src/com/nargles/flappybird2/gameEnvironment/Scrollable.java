package com.nargles.flappybird2.gameEnvironment;

import com.badlogic.gdx.math.Vector2;

/**
 * Scrollable
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected float width;
    protected float height;
    protected boolean isScrolledBack;
    protected boolean isRightGoing;
    private float scrollSpeed;

    /**
     * Constructor
     *
     * @param y           Y position of the object
     * @param x           X position of the object
     * @param width       Width of sprite
     * @param height      Height of sprite
     * @param scrollSpeed Speed of the objects that move
     */
    public Scrollable(float x, float y, float width, float height, float scrollSpeed) {
        position = new Vector2(x, y);
        this.scrollSpeed = scrollSpeed;
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledBack = false;
        isRightGoing = scrollSpeed < 0;
    }

    /**
     * Update position
     *
     * @param delta Update Scalar
     */
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }

    /**
     * Reset position
     *
     * @param newX New X position
     */
    public void reset(float newX) {
        position.x = newX;
        isScrolledBack = false;
    }

    /**
     * Stop movement
     */
    public void stop() {
        velocity.x = 0;
    }

    public boolean isScrolledBack() {
        isScrolledBack = false;

        // If the Scrollable object is no longer visible:
        if (isRightGoing && (position.x + width) < 0) {
            isScrolledBack = true;
        } else if (!isRightGoing && (position.x + width) > (136 * 3)) {
            isScrolledBack = true;
        }

        return isScrolledBack;
    }

    public void setIsScrolledBack(boolean isScrolledBack) {
        this.isScrolledBack = isScrolledBack;
    }

    /**
     * Tail X position
     *
     * @return X position of previous object
     */
    public float getTailX() {
        if (isRightGoing) {
            return position.x + width;
        } else {
            return position.x - width;
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    /**
     * Change velocity X to negative scroll speed
     */
    public void goRight() {
        isRightGoing = true;

        if (scrollSpeed > 0) {
            velocity.x = -scrollSpeed;
        } else {
            velocity.x = scrollSpeed;
        }
    }

    /**
     * Change velocity X to positive scroll speed
     */
    public void goLeft() {
        isRightGoing = false;

        if (scrollSpeed < 0) {
            velocity.x = -scrollSpeed;
        } else {
            velocity.x = scrollSpeed;
        }
    }

    public boolean isRightGoing() {
        return isRightGoing;
    }

    /**
     * Go right on restart
     */
    protected void onRestart() {
        goRight();
    }

    public float getScrollSpeed() {
        return scrollSpeed;
    }
}
