package com.nargles.flappybird2.gameEnvironment;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	private boolean isScrolledBack;
    private boolean isRightGoing;
    private float scrollSpeed;

    /**
     * Constructor
     * @param y Y position of the object
     * @param x X position of the object
     * @param width Width of sprite
     * @param height Height of sprite
     * @param scrollSpeed Speed of the objects that move
     */
    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
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
     * @param delta Update Scalar
     */
    public void update(float delta) {
		position.add(velocity.cpy().scl(delta));

		// If the Scrollable object is no longer visible:
		if (isRightGoing && (position.x + width) < 0) {
			isScrolledBack = true;
		}
		else if (!isRightGoing && (position.x + width) > (136 * 4)) {
			isScrolledBack = true;
		}
	}

    /**
     * Reset position
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
		return isScrolledBack;
	}

    /**
     * Tail X position
     * @return X position of previous object
     */
    public float getTailX() {
		if(isRightGoing) {
			return position.x + width;
		}
		else
		{
			return position.x - width;
		}
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

    /**
     * Change velocity X to negative scroll speed
     */
    public void goRight()
	{
		isRightGoing = true;
		
		if(scrollSpeed > 0)
		{
			velocity.x = -scrollSpeed;
		}
		else
		{
			velocity.x = scrollSpeed;
		}
	}

    /**
     * Change velocity X to positive scroll speed
     */
	public void goLeft()
	{
		isRightGoing = false;
		
		if(scrollSpeed < 0)
		{
			velocity.x = -scrollSpeed;
		}
		else
		{
			velocity.x = scrollSpeed;
		}
	}
	
	public boolean isRightGoing()
	{
		return isRightGoing;
	}

    /**
     * Go right on restart
     */
    protected void onRestart()
	{
		goRight();
	}

}
