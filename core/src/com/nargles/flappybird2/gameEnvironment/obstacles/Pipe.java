package com.nargles.flappybird2.gameEnvironment.obstacles;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.gameEnvironment.Scrollable;
import com.nargles.flappybird2.gameEnvironment.player.Bird;

public class Pipe extends Scrollable {

	private Rectangle pipeTopUp, pipeTopDown, barUp, barDown;

	public static final int VERTICAL_GAP = 45;
	public static final int PIPE_TOP_WIDTH = 24;
	public static final int PIPE_TOP_HEIGHT = 11;
	private static long seedQualifier = 432282912137141232L;
	private float groundY;

	private boolean isScored = false;

	public Pipe(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);

		pipeTopUp = new Rectangle();
		pipeTopDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();
		this.height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));

		pipeTopUp.set(position.x - (PIPE_TOP_WIDTH - width) / 2, position.y + height
				- PIPE_TOP_HEIGHT, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);
		pipeTopDown.set(position.x - (PIPE_TOP_WIDTH - width) / 2, barDown.y,
				PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

	}

	@Override
	public void reset(float newX) {
		super.reset(newX);
		// Change the height to a random number
		height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
		isScored = false;
	}

	public void onRestart(float x, float scrollSpeed) {
		super.onRestart();
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
