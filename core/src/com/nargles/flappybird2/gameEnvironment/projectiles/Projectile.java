package com.nargles.flappybird2.gameEnvironment.projectiles;

/**
 * Projectile
 * Copyright 2016 Nargles.
 * @author Taylor Chason (Programmer)
 * @version 1.0
 */
public class Projectile {
    private float x, y, speedX;
    private boolean visible;
    private int width;
    private float height;
    private float rotation;

    /**
     * Constructor
     * @param startX Player X position
     * @param startY Player Y position
     * @param width Projectile sprite width
     * @param height Projectile sprite height
     * @param rotation Projectile rotation
     */
    public Projectile(float startX, float startY, int width, int height, float rotation){
        this.width = width;
        this.height = height;
        //this.rotation = rotation;
        x = startX;
        y = startY;
        speedX = 7;
        visible = true;
    }

    /**
     * Update the x and y position (Moving)
     * If offscreen then hide
     */
    public void update(){
        x += speedX;
        if (x > 800){
            visible = false;
        }

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

}
