package com.nargles.flappybird2.gameEnvironment.projectiles;

/**
 * Created by Taylor Chason on 3/24/16.
 */
public class Projectile {
    private float x, y, speedX;
    private boolean visible;
    private int width;
    private float height;

    public Projectile(float startX, float startY, int width, int height){
        this.width = width;
        this.height = height;
        x = startX;
        y = startY;
        speedX = 7;
        visible = true;
    }

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

}
