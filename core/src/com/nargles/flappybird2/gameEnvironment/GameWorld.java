package com.nargles.flappybird2.gameEnvironment;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.FlappyBirds2;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.scoreManager.FlappyBird2Database;

public class GameWorld {

	private FlappyBird2Database db;
	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle ground;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameRenderer renderer;

	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSED
	}

	public GameWorld(FlappyBirds2 game, int midPointY, int midPointX) {
		db = game.getDatabase();
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		bird = new Bird(33 * 2, midPointY - 5, 17, 12);

		scroller = new ScrollHandler(this, (midPointY) * 3, midPointX, 59);
		ground = new Rectangle(0, (float) ((midPointY) * 3), midPointX * 2, 11);
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		bird.update(delta, scroller.isRightGoing());
		scroller.update(delta);

		if (scroller.collides(bird) && bird.isAlive()) {
			scroller.stop();
			bird.die();
			AssetLoader.dead.play();
			renderer.prepareTransition(255, 255, 255, .3f);

			AssetLoader.fall.play();
		}

		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {

			if (bird.isAlive()) {
				AssetLoader.dead.play();
				renderer.prepareTransition(255, 255, 255, .3f);

				bird.die();
			}

			scroller.stop();
			bird.decelerate();
			currentState = GameState.GAMEOVER;
			if (score > 0) {
				db.addHighscore(score);
			}
			currentState = GameState.HIGHSCORE;

		}
	}

	public Bird getBird() {
		return bird;

	}

	public int getMidPointY() {
		return midPointY;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}
	
	public void pause() {
		currentState = GameState.PAUSED;
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void highSore() {
		currentState = GameState.HIGHSCORE;
	}

	public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}
	
	public boolean isPaused() {
		return currentState == GameState.PAUSED;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

	public FlappyBird2Database getDatabase() {
		return db;
	}

}
