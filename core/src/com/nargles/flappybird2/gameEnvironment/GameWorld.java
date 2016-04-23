package com.nargles.flappybird2.gameEnvironment;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nargles.flappybird2.FlappyBird2;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.gameEnvironment.projectiles.Projectile;
import com.nargles.flappybird2.scoreManager.FlappyBird2Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Game World
 * Copyright 2016 Nargles.
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
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

    /**
     * The current game state
     */
    private enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSED
	}

    /**
     * Constructor
     * @param game FlappyBirds2 created initially
     * @param midPointY Middle of game screen vertically
     * @param midPointX Middle of game screen horizontally
     */
    public GameWorld(FlappyBird2 game, int midPointY, int midPointX) {
		db = game.getDatabase();
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		bird = new Bird(33 * 2, midPointY - 5, 17, 12);

		scroller = new ScrollHandler(this, (midPointY) * 3, midPointX, -59);
		ground = new Rectangle(0, (float) ((midPointY) * 3), midPointX * 2, 11);
	}

    /**
     * @param delta Updates scalars
     */
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

    /**
     * Update Bird and Scroller at Ready state
     * @param delta Update scalars
     */
    private void updateReady(float delta) {
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

    /**
     *  Update Bird and Scroller at Running state
     * @param delta Update scalars
     */
    private void updateRunning(float delta) {
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
        else if (bird.isAlive())
        {
            List<Integer> projectilesToRemove = new ArrayList<Integer>();
            for (int i = 0; i < bird.getProjectiles().size(); i++) {
                Projectile p = bird.getProjectiles().get(i);
                if (scroller.collides(p)) {
                    p.setVisible(false);
                    addScore(10);
                    projectilesToRemove.add(i);
                }
            }

            for(int i: projectilesToRemove)
            {
                bird.getProjectiles().remove(i);
            }
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
				db.addHighScore(score);
			}
			currentState = GameState.HIGHSCORE;

		}
	}

	public Bird getBird() { return bird; }

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

    /**
     * Restart Game
     */
    public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
        renderer.onRestart();
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

    public FlappyBird2Database getDb() {
        return db;
    }

}
