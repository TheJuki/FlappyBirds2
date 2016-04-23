package com.nargles.flappybird2.gameEnvironment.obstacles;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.gameEnvironment.Scrollable;
import com.nargles.flappybird2.gameEnvironment.player.Bird;

/**
 * Nest
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class Nest extends Scrollable {

    private Rectangle nestRectangle;

    private boolean nestVisible;
    private int ammoType;

    /**
     * Constructor
     *
     * @param x           Nest X position
     * @param y           Nest Y position
     * @param width       Nest sprite width
     * @param height      Nest sprite height
     * @param scrollSpeed Speed of the Nest
     */
    public Nest(float x, float y, float width, float height, float scrollSpeed, int ammoType) {
        super(x, y, width, height, scrollSpeed);

        nestRectangle = new Rectangle();
        nestVisible = true;
        this.ammoType = ammoType;
    }

    /**
     * Update the nest position
     *
     * @param delta Update scalar
     */
    @Override
    public void update(float delta) {
        super.update(delta);

        nestRectangle.set(position.x, position.y, width, height);

    }

    /**
     * Determines if a bird and the nest met
     *
     * @param bird Bird
     * @return if a bird and the nest met
     */
    public boolean collides(Bird bird) {

        boolean collided = false;

        if ((position.x < (bird.getX() + bird.getWidth())) &&
                ((Intersector.overlaps(bird.getBoundingCircle(), nestRectangle) && nestVisible))) {
            collided = true;
            nestVisible = false;
            switch (ammoType) {
                case 0:
                    bird.addNumBlueEggs(5);
                    break;
                case 1:
                    bird.addNumFireEggs(5);
                    break;
                case 2:
                    bird.addNumGrenadeEggs(5);
                    break;
                default:
                    break;
            }
        }

        return collided;
    }

    public boolean isNestVisible() {
        return nestVisible;
    }

    public int getAmmoType() {
        return ammoType;
    }
}
