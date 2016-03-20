package com.nargles.flappybird2.gameEnvironment;

import java.util.ArrayList;
import java.util.List;

import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.obstacles.Grass;
import com.nargles.flappybird2.gameEnvironment.obstacles.Pipe;
import com.nargles.flappybird2.gameEnvironment.player.Bird;

public class ScrollHandler {

	private Grass frontGrass, backGrass;
	private List<Pipe> pipes;
	private int scrollSpeed;
	public static final int PIPE_GAP = 49;
	public static final int NUM_PIPES = 6;

	private GameWorld gameWorld;

	public ScrollHandler(GameWorld gameWorld, float yPos, float xPos, int scrollSpeed) {
		this.gameWorld = gameWorld;
		this.scrollSpeed = scrollSpeed;
		frontGrass = new Grass(0, yPos, 143 * 2, 11, scrollSpeed);
		backGrass = new Grass(frontGrass.getTailX(), yPos, 143 * 2, 11,
				scrollSpeed);

		pipes = new ArrayList<Pipe>();
		pipes.add(new Pipe(210 * 2, 0, 22, 10, scrollSpeed, yPos));
		
		for(int i = 1; i < NUM_PIPES; i++)
		{
			pipes.add(new Pipe(pipes.get(i - 1).getTailX() + PIPE_GAP, 0, 22, 10, scrollSpeed,
					yPos));
		}
	}

	public void updateReady(float delta) {

		frontGrass.update(delta);
		backGrass.update(delta);

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());

		}

	}

	public void update(float delta) {
		// Update our objects
		frontGrass.update(delta);
		backGrass.update(delta);
		
		for(Pipe pipe: pipes)
		{
			pipe.update(delta);
		}

		// Check if any of the pipes are scrolled left,
		// and reset accordingly
		if (pipes.get(0).isScrolledLeft()) {
			pipes.get(0).reset(pipes.get(NUM_PIPES - 1).getTailX() + PIPE_GAP);
		}

		for(int i = 1; i < NUM_PIPES; i++)
		{
			if (pipes.get(i).isScrolledLeft()) {
				pipes.get(i).reset(pipes.get(i - 1).getTailX() + PIPE_GAP);
			}
		}

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());

		}
	}

	public void stop() {
		frontGrass.stop();
		backGrass.stop();
		for(Pipe pipe: pipes)
		{
			pipe.stop();
		}
	}

	public boolean collides(Bird bird) {

		boolean isCollided = false;
		
		for(Pipe pipe: pipes)
		{
			if (!pipe.isScored()
					&& pipe.getX() + (pipe.getWidth() / 2) < bird.getX()
							+ bird.getWidth()) {
				addScore(1);
				pipe.setScored(true);
				AssetLoader.coin.play();
			}
			if(pipe.collides(bird))
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

	public void onRestart() {
		frontGrass.onRestart(0, scrollSpeed);
		backGrass.onRestart(frontGrass.getTailX(), scrollSpeed);
		pipes.get(0).onRestart(210 * 2, scrollSpeed);
		
		for(int i = 1; i < NUM_PIPES; i++)
		{
			pipes.get(i).onRestart(pipes.get(i - 1).getTailX() + PIPE_GAP, scrollSpeed);
		}
	}

}
