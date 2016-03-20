package com.nargles.flappybird2.gameEnvironment;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
	
	
	// Protected is similar to private, but allows inheritance by subclasses.
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledBack;
	protected boolean isRightGoing;

	public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(scrollSpeed, 0);
		this.width = width;
		this.height = height;
		isScrolledBack = false;
		isRightGoing = scrollSpeed < 0;
	}

	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));

		// If the Scrollable object is no longer visible:
		if (isRightGoing && (position.x + width) < 0) {
			isScrolledBack = true;
		}
		else if (!isRightGoing && (position.x + width) > 136 * 3) {
			isScrolledBack = true;
		}
	}

	// Reset: Should Override in subclass for more specific behavior.
	public void reset(float newX) {
		position.x = newX;
		isScrolledBack = false;
	}

	public void stop() {
		velocity.x = 0;
	}
	
	public boolean isScrolledBack() {
		return isScrolledBack;
	}
	
	public float getTailX() {
		return position.x + width;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void goRight()
	{
		isRightGoing = true;
		velocity.x = -59;
	}
	
	public void goLeft()
	{
		isRightGoing = false;
		velocity.x = 59;
	}
	
	public boolean isRightGoing()
	{
		return isRightGoing;
	}

}
