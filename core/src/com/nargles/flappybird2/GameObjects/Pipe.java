package com.nargles.flappybird2.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

	private Rectangle pipeTopUp, pipeTopDown, barUp, barDown;

	public static final int VERTICAL_GAP = 45;
	public static final int PIPE_TOP_WIDTH = 24;
	public static final int PIPE_TOP_HEIGHT = 11;
	private static long seedQualifier = 432282912137141232L;
	private float groundY;

	private boolean isScored = false;

	// When Pipe's constructor is invoked, invoke the super (Scrollable)
	// constructor
	public Pipe(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		pipeTopUp = new Rectangle();
		pipeTopDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();
		this.height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		// The set() method allows you to set the top left corner's x, y
		// coordinates,
		// along with the width and height of the rectangle

		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));

		// Our skull width is 24. The bar is only 22 pixels wide. So the skull
		// must be shifted by 1 pixel to the left (so that the skull is centered
		// with respect to its bar).

		// This shift is equivalent to: (SKULL_WIDTH - width) / 2
		pipeTopUp.set(position.x - (PIPE_TOP_WIDTH - width) / 2, position.y + height
				- PIPE_TOP_HEIGHT, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);
		pipeTopDown.set(position.x - (PIPE_TOP_WIDTH - width) / 2, barDown.y,
				PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
		isScored = false;
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}

	public Rectangle getPipeTopUp() {
		return pipeTopUp;
	}

	public Rectangle getPipeTopDown() {
		return pipeTopDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}

	public boolean collides(Bird bird) {
		
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), barDown)
					|| Intersector.overlaps(bird.getBoundingCircle(), pipeTopUp) || Intersector
						.overlaps(bird.getBoundingCircle(), pipeTopDown));
		}
		
		return false;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}
}
