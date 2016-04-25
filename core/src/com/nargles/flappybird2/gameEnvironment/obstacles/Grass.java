package com.nargles.flappybird2.gameEnvironment.obstacles;

import com.nargles.flappybird2.gameEnvironment.Scrollable;

/**
 * Grass
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class Grass extends Scrollable {

    /**
     * Constructor
     *
     * @param x           Grass X position
     * @param y           Grass Y position
     * @param width       Grass sprite width
     * @param height      Grass sprite height
     * @param scrollSpeed Speed of the Grass
     */
    public Grass(float x, float y, float width, float height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    /**
     * Restart grass position
     *
     * @param x           New X position
     * @param scrollSpeed Speed of the Grass
     */
    public void onRestart(float x, float scrollSpeed) {
        super.onRestart();
        position.x = x;
        velocity.x = scrollSpeed;
    }

    @Override
    public boolean isScrolledBack() {
        isScrolledBack = false;

        // If the Scrollable object is no longer visible:
        if (isRightGoing && (position.x + width) < 0) {
            isScrolledBack = true;
        } else if (!isRightGoing && position.x > (136 * 3)) {
            isScrolledBack = true;
        }

        return isScrolledBack;
    }

}
