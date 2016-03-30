package com.nargles.flappybird2.gameEnvironment.obstacles;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.gameEnvironment.Scrollable;
import com.nargles.flappybird2.gameEnvironment.player.Bird;

import java.util.Random;

public class Pipe extends Scrollable {

	private Rectangle pipeTopUp, pipeTopDown, barUp, barDown;

	private static final int VERTICAL_GAP = 45;
    private static final int PIPE_TOP_WIDTH = 24;
    private static final int PIPE_TOP_HEIGHT = 11;
	private static long seedQualifier = 432282912137141232L;
	private float groundY;

	private boolean isScored = false;

    /**
     * Constructor
     * @param x Pipe X position
     * @param y Pipe Y position
     * @param width Pipe sprite width
     * @param height Pipe sprite height
     * @param scrollSpeed Speed of the Pipe
     * @param groundY Ground's Y position
     */
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

    /**
     * Update the stacked pipes
     * @param delta Update scalar
     */
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

    /**
     * Reset pipe x position and height
     * @param newX New x position
     */
    @Override
	public void reset(float newX) {
		super.reset(newX);
		// Change the height to a random number
		height = new Random(++seedQualifier + System.nanoTime()).nextInt(40) + 15;
		isScored = false;
	}

    /**
     * Restart pipe position
     * @param x x position to move to
     * @param scrollSpeed Movement speed
     */
    public void onRestart(float x, float scrollSpeed) {
		super.onRestart();
		velocity.x = scrollSpeed;
		reset(x);
	}

    /**
     * Determines if a bird and the pipe met
     * @param bird Bird
     * @return if a bird and the pipe met
     */
    public boolean collides(Bird bird) {

        return (position.x < (bird.getX() + bird.getWidth())) &&
                (Intersector.overlaps(bird.getBoundingCircle(), barUp) ||
                        Intersector.overlaps(bird.getBoundingCircle(), barDown) ||
                        Intersector.overlaps(bird.getBoundingCircle(), pipeTopUp) ||
                        Intersector.overlaps(bird.getBoundingCircle(), pipeTopDown));

    }

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}
}
