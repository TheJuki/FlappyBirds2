package com.nargles.flappybird2.gameEnvironment.projectiles;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Projectile
 * Copyright 2016 Nargles.
 *
 * @author Taylor Chason (Programmer)
 * @version 1.0
 */
public class Projectile {
    private float speedX;
    private boolean visible;
    private float width;
    private float height;
    private float rotation;
    private boolean isRightGoing;
    private Vector2 position;
    private int type;

    private Circle boundingCircle;

    /**
     * Constructor
     *
     * @param x        Projectile X position
     * @param y        Projectile Y position
     * @param width    Projectile sprite width
     * @param height   Projectile sprite height
     * @param rotation Projectile rotation
     * @param type     Projectile type
     */
    public Projectile(float x, float y, float width, float height, float rotation, boolean isRightGoing, int type) {
        this.width = width;
        this.height = height;
        this.isRightGoing = isRightGoing;
        this.type = type;
        //this.rotation = rotation;
        position = new Vector2(x, y);
        speedX = 3.5f;
        visible = true;
        boundingCircle = new Circle();
    }

    /**
     * Update the x and y position (Moving)
     * If offscreen then hide
     */
    public void update() {

        if (isRightGoing) {
            position.x += speedX;
            if (position.x > (136 * 4)) {
                visible = false;
            }
        } else {
            position.x -= speedX;
            if (position.x < 0) {
                visible = false;
            }
        }

        boundingCircle.set(position.x + 4.8f, position.y + 2.7f, 5f);

    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.position.x = x;
    }

    public void setY(int y) {
        this.position.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public int getType() {
        return type;
    }
}
