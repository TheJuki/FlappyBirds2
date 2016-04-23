package com.nargles.flappybird2.ui.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nargles.flappybird2.FlappyBird2;
import com.nargles.flappybird2.gameEnvironment.GameRenderer;
import com.nargles.flappybird2.gameEnvironment.GameWorld;
import com.nargles.flappybird2.ui.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

    /**
     * Constructor
     * Setup the screen size, world, and renderer
     * @param game FlappyBirds2
     */
    public GameScreen(FlappyBird2 game) {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		int midPointY = (int) (gameHeight / 2);
		int midPointX = (int) gameWidth;

		world = new GameWorld(game, midPointY, midPointX);
		Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
		renderer = new GameRenderer(world, (int) gameHeight, midPointX, midPointY);
		world.setRenderer(renderer);
	}

    /**
     * Render screen
     * @param delta Update scalar
     */
    @Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
