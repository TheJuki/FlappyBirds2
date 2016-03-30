package com.nargles.flappybird2.gameEnvironment;

import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.obstacles.Grass;
import com.nargles.flappybird2.gameEnvironment.obstacles.Pipe;
import com.nargles.flappybird2.gameEnvironment.player.Bird;

import java.util.ArrayList;
import java.util.List;

public class ScrollHandler {

	private Grass frontGrass, backGrass;
	private List<Pipe> pipes;
	private int scrollSpeed;
	private boolean isRightGoing;
	private boolean initialLeftFlip;
	private static final int PIPE_GAP = 49;
    private static final int NUM_PIPES = 6;

	private GameWorld gameWorld;

    /**
     * Constructor
     * @param gameWorld Game World
     * @param yPos Y position of the object
     * @param xPos X position of the object
     * @param scrollSpeed Speed of the objects that move
     */
    public ScrollHandler(GameWorld gameWorld, float yPos, float xPos, int scrollSpeed) {
		this.gameWorld = gameWorld;
		this.scrollSpeed = scrollSpeed;
		frontGrass = new Grass(0, yPos, 143 * 2, 11, scrollSpeed);
		backGrass = new Grass(frontGrass.getTailX(), yPos, 143 * 2, 11, scrollSpeed);

		pipes = new ArrayList<Pipe>();
		// 210 * 2, 0 , 22, 10
		pipes.add(new Pipe(300, 0, 22, 10, scrollSpeed, yPos));

		for (int i = 1; i < NUM_PIPES; i++) {
			pipes.add(new Pipe(pipes.get(i - 1).getTailX() + PIPE_GAP, 0, 22, 10, scrollSpeed, yPos));
		}

		initialLeftFlip = false;
		isRightGoing = scrollSpeed < 0;
	}

    /**
     * @param delta Update scalar
     */
    public void updateReady(float delta) {

		// Update grass
		frontGrass.update(delta);
		backGrass.update(delta);

		// Reset grass
		if (frontGrass.isScrolledBack()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledBack()) {
			backGrass.reset(frontGrass.getTailX());
		}
	}

    /**
     * Flip direction of ground and pipe movement
     */
    public void flip() {
		isRightGoing = !isRightGoing;

		if (!isRightGoing) {
			initialLeftFlip = true;
			frontGrass.goLeft();
			backGrass.goLeft();
			for (Pipe pipe : pipes) {
				pipe.goLeft();
			}
		} else {
			initialLeftFlip = false;
			onRestart();
		}
	}

    /**
     * Update ground and pipes x positions
     * @param delta Scalar for Velocity vectors
     */
	public void update(float delta) {

		// Update grass
		frontGrass.update(delta);
		backGrass.update(delta);

		for (Pipe pipe : pipes) {
			pipe.update(delta);
		}

		// Reset Pipes

		if (isRightGoing) {
			if (pipes.get(0).isScrolledBack()) {
				pipes.get(0).reset(pipes.get(NUM_PIPES - 1).getTailX() + PIPE_GAP);
			}

			for (int i = 1; i < NUM_PIPES; i++) {
				if (pipes.get(i).isScrolledBack()) {
					pipes.get(i).reset(pipes.get(i - 1).getTailX() + PIPE_GAP);
				}
			}
		} else {
			
			if (pipes.get(0).isScrolledBack()) {
				if(initialLeftFlip) {
					pipes.get(0).reset(-30);
					//initialLeftFlip = false;
				}
				else
				{
					pipes.get(NUM_PIPES-1).reset(pipes.get(0).getTailX() - PIPE_GAP);
				}
			}

			for (int i = 1; i < NUM_PIPES; i++) {
				if (pipes.get(i).isScrolledBack()) {
					pipes.get(i).reset(pipes.get(i - 1).getTailX() - PIPE_GAP);
				}
			}
		}

		// Reset grass
		if (frontGrass.isScrolledBack()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledBack()) {
			backGrass.reset(frontGrass.getTailX());

		}
	}

    /**
     * Stop movement of the pipes and ground
     */
    public void stop() {
		frontGrass.stop();
		backGrass.stop();
		for (Pipe pipe : pipes) {
			pipe.stop();
		}
	}

    /**
     * Determines if a bird and a pipe met
     * If not then see if score needs to be updated
     * @param bird Bird
     * @return A pipe collided with the bird
     */
    public boolean collides(Bird bird) {

		boolean isCollided = false;

		for (Pipe pipe : pipes) {
			if (!pipe.isScored() && pipe.isRightGoing()
					&& (pipe.getX() + (pipe.getWidth() / 2)) < (bird.getX() + bird.getWidth())) {
				addScore(1);
				pipe.setScored(true);
				AssetLoader.coin.play();
			}
			if (pipe.collides(bird))
				isCollided = true;
		}

		return isCollided;
	}

	private void addScore(int increment) {
		gameWorld.addScore(increment);
	}

	public Grass getFrontGrass() {
		return frontGrass;
	}

	public Grass getBackGrass() {
		return backGrass;
	}

	public List<Pipe> getPipes() {
		return pipes;
	}

    /**
     * Revive pipes and ground
     */
    public void onRestart() {
		isRightGoing = true;
		initialLeftFlip = false;
		frontGrass.onRestart(0, scrollSpeed);
		backGrass.onRestart(frontGrass.getTailX(), scrollSpeed);
		pipes.get(0).onRestart(300, scrollSpeed);

		for (int i = 1; i < NUM_PIPES; i++) {
			pipes.get(i).onRestart(pipes.get(i - 1).getTailX() + PIPE_GAP, scrollSpeed);
		}
	}

	public boolean isRightGoing() {
		return isRightGoing;
	}

}
