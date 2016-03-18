package com.nargles.flappybird2.GameWorld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.nargles.flappybird2.GameObjects.Bird;
import com.nargles.flappybird2.GameObjects.Grass;
import com.nargles.flappybird2.GameObjects.Pipe;
import com.nargles.flappybird2.GameObjects.ScrollHandler;
import com.nargles.flappybird2.TweenAccessors.Value;
import com.nargles.flappybird2.TweenAccessors.ValueAccessor;
import com.nargles.flappybird2.ZBHelpers.AssetLoader;
import com.nargles.flappybird2.ZBHelpers.InputHandler;
import com.nargles.flappybird2.ui.SimpleButton;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int midPointX;

	// Game Objects
	private Bird bird;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3, pipe4, pipe5, pipe6;

	// Game Assets
	private TextureRegion bg, grass, birdMid, pipeUp, pipeDown, bar, ready,
			fbLogo, gameOver, highScore, scoreboard, retry;
	private Animation birdAnimation;

	// Tween 
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons;
	private Color transitionColor;

	public GameRenderer(GameWorld world, int gameHeight, int midPointX , int midPointY) {
		myWorld = world;

		this.midPointY = midPointY;
		this.midPointX = midPointX;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136 * 2, gameHeight * 2);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();

		transitionColor = new Color();
		prepareTransition(255, 255, 255, .5f);
	}

	private void initGameObjects() {
		bird = myWorld.getBird();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
		pipe4 = scroller.getPipe4();
		pipe5 = scroller.getPipe5();
		pipe6 = scroller.getPipe6();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdMid = AssetLoader.bird;
		pipeUp = AssetLoader.pipeUp;
		pipeDown = AssetLoader.pipeDown;
		bar = AssetLoader.bar;
		ready = AssetLoader.ready;
		fbLogo = AssetLoader.fbLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		retry = AssetLoader.retry;
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawSkulls() {

		batcher.draw(pipeUp, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(pipeDown, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

		batcher.draw(pipeUp, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(pipeDown, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

		batcher.draw(pipeUp, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
		batcher.draw(pipeDown, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
		
		batcher.draw(pipeUp, pipe4.getX() - 1,
				pipe4.getY() + pipe4.getHeight() - 14, 24, 14);
		batcher.draw(pipeDown, pipe4.getX() - 1,
				pipe4.getY() + pipe4.getHeight() + 45, 24, 14);
		
		batcher.draw(pipeUp, pipe5.getX() - 1,
				pipe5.getY() + pipe5.getHeight() - 14, 24, 14);
		batcher.draw(pipeDown, pipe5.getX() - 1,
				pipe5.getY() + pipe5.getHeight() + 45, 24, 14);
		
		batcher.draw(pipeUp, pipe6.getX() - 1,
				pipe6.getY() + pipe6.getHeight() - 14, 24, 14);
		batcher.draw(pipeDown, pipe6.getX() - 1,
				pipe6.getY() + pipe6.getHeight() + 45, 24, 14);
	}

	private void drawPipes() {
		batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
				pipe1.getHeight());
		batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
				pipe1.getWidth(), midPointY + (midPointY * 3) - (pipe1.getHeight() + 45));

		batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
				pipe2.getHeight());
		batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
				pipe2.getWidth(), midPointY + (midPointY * 3) - (pipe2.getHeight() + 45));

		batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
				pipe3.getHeight());
		batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
				pipe3.getWidth(), midPointY + (midPointY * 3) - (pipe3.getHeight() + 45));
		
		batcher.draw(bar, pipe4.getX(), pipe4.getY(), pipe4.getWidth(),
				pipe4.getHeight());
		batcher.draw(bar, pipe4.getX(), pipe4.getY() + pipe4.getHeight() + 45,
				pipe4.getWidth(), midPointY + (midPointY * 3) - (pipe4.getHeight() + 45));
		
		batcher.draw(bar, pipe5.getX(), pipe5.getY(), pipe5.getWidth(),
				pipe5.getHeight());
		batcher.draw(bar, pipe5.getX(), pipe5.getY() + pipe5.getHeight() + 45,
				pipe5.getWidth(), midPointY + (midPointY * 3) - (pipe5.getHeight() + 45));
		
		batcher.draw(bar, pipe6.getX(), pipe6.getY(), pipe6.getWidth(),
				pipe6.getHeight());
		batcher.draw(bar, pipe6.getX(), pipe6.getY() + pipe6.getHeight() + 45,
				pipe6.getWidth(), midPointY + (midPointY * 3) - (pipe6.getHeight() + 45));
	}

	private void drawBirdCentered(float runTime) {
		batcher.draw(birdAnimation.getKeyFrame(runTime), 59 * 2, (bird.getY() - 15) * 2,
				bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
	}

	private void drawBird(float runTime) {

		if (bird.shouldntFlap()) {
			batcher.draw(birdMid, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

		} else {
			batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
					bird.getY(), bird.getWidth() / 2.0f,
					bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
					1, 1, bird.getRotation());
		}

	}

	private void drawMenuUI() {
		batcher.draw(fbLogo, (float) midPointX - 70 , (midPointY - 20) /2 ,
				fbLogo.getRegionWidth() / 1.2f, fbLogo.getRegionHeight() / 1.2f);

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, 22 * 4, midPointY - 30, 97, 37);

		/*
		if (myWorld.getScore() > 2) {
			batcher.draw(star, 73 * 3, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 17) {
			batcher.draw(star, 61 * 3, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 50) {
			batcher.draw(star, 49 * 3, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 80) {
			batcher.draw(star, 37 * 3, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 120) {
			batcher.draw(star, 25 * 3, midPointY - 15, 10, 10);
		}
*/
		int length = ("" + myWorld.getScore()).length();

		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(),
				(float) ((104 - (2 * length)) * 1.7), midPointY - 20);

		int length2 = ("" + AssetLoader.getHighScore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
				(float) ((104 - (2.5f * length2)) * 1.7), midPointY - 3);

	}

	private void drawRetry() {
		batcher.draw(retry, 36 * 3, (midPointY) * 2, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, 36 * 3, (midPointY) * 2, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, 24, (midPointY - 50) * 2, 92, 14);
	}

	private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				midPointX - (3 * length), midPointY - 22);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
				midPointX - (3 * length), midPointY - 23);
	}

	private void drawHighScore() {
		batcher.draw(highScore, midPointX, (midPointY - 20) * 2, 96, 14);
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		

		batcher.begin();
		batcher.disableBlending();

		batcher.draw(bg, -10,(midPointY - 38), 143 * 2, (float) (43 * 2.8));

		drawPipes();

		batcher.enableBlending();
		drawSkulls();
		
		batcher.end();
		
		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		//shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		//shapeRenderer.rect(0, 0, 136 * 2, (midPointY + (midPointY * 3)) * 2);

		
		// Draw Grass
		//shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		//shapeRenderer.rect(0, (midPointY + 66) / 2, 136 * 2, 11 / 2);

		// Draw Dirt
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY * 3, 136 * 2, (midPointY));
		
		shapeRenderer.end();
		
		batcher.begin();
		batcher.enableBlending();

		if (myWorld.isRunning()) {
			drawBird(runTime);
			drawScore();
		} else if (myWorld.isReady()) {
			drawBird(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawBirdCentered(runTime);
			drawMenuUI();
		} else if (myWorld.isGameOver()) {
			drawScoreboard();
			drawBird(runTime);
			drawGameOver();
			drawRetry();
		} else if (myWorld.isHighScore()) {
			drawScoreboard();
			drawBird(runTime);
			drawHighScore();
			drawRetry();
		}

		drawGrass();

		batcher.end();
		drawTransition(delta);
		
		

	}

	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g,
					transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, 136 * 2, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);

		}
	}

}
