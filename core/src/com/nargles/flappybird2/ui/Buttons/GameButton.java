package com.nargles.flappybird2.ui.Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.assetManager.AssetLoader;

public class GameButton {

	private float x, y, width, height;
	private String name;

	private TextureRegion buttonUp;
	private TextureRegion buttonDown;

	private Rectangle bounds;

	private boolean isPressed = false;

    /**
     * Constructor
     * @param name Tag of Button
     * @param x X position of Button
     * @param y Y position of Button
     * @param width Button width
     * @param height Button height
     * @param buttonUp Button Unpressed texture
     * @param buttonDown Button Pressed texture
     */
    public GameButton(String name, float x, float y, float width, float height,
                      TextureRegion buttonUp, TextureRegion buttonDown) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;

		bounds = new Rectangle(x, y, width, height);

	}

    /**
     * Draw button
     * @param batcher SpriteBatch
     */
    public void draw(SpriteBatch batcher) {
		if (isPressed) {
			batcher.draw(buttonDown, x, y, width, height);
		} else {
			batcher.draw(buttonUp, x, y, width, height);
		}
	}

    /**
     * Button is Pressed Down
     * @param screenX Tap X position
     * @param screenY Tap Y position
     * @return Button is tapped
     */
    public boolean isTouchDown(int screenX, int screenY) {

		if (bounds.contains(screenX, screenY)) {
			isPressed = true;
			return true;
		}

		return false;
	}

    /**
     * Button is Released
     * @param screenX Tap X position
     * @param screenY Tap Y position
     * @return Button is released within its bounds
     */
	public boolean isTouchUp(int screenX, int screenY) {

		if (bounds.contains(screenX, screenY) && isPressed) {
			isPressed = false;
			AssetLoader.flap.play();
			return true;
		}

		isPressed = false;
		return false;
	}

	public String getName() {
		return name;
	}


}
