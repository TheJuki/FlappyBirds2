package com.nargles.flappybird2.gameEnvironment;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.obstacles.Grass;
import com.nargles.flappybird2.gameEnvironment.obstacles.Pipe;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.gameEnvironment.projectiles.Projectile;
import com.nargles.flappybird2.ui.Buttons.SimpleButton;
import com.nargles.flappybird2.ui.InputHandler;
import com.nargles.flappybird2.ui.TweenAccessors.Value;
import com.nargles.flappybird2.ui.TweenAccessors.ValueAccessor;

import java.util.List;

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
	private List<Pipe> pipes;

	// Game Assets
    private Texture bullet1, bullet2, bullet3;

	private TextureRegion bg, grass, birdMid, birdMidFlipped, pipeUp, pipeDown, bar, ready,
			fbLogo, gameOver, highScore, scoreboard, retry;
	private Animation birdAnimation, birdAnimationFlipped;

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
		pipes = scroller.getPipes();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdAnimationFlipped = AssetLoader.birdAnimationFlipped;
		birdMid = AssetLoader.bird;
		birdMidFlipped = AssetLoader.birdFlipped;
		pipeUp = AssetLoader.pipeUp;
		pipeDown = AssetLoader.pipeDown;
		bar = AssetLoader.bar;
		ready = AssetLoader.ready;
		fbLogo = AssetLoader.fbLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		retry = AssetLoader.retry;
        bullet1 = AssetLoader.bullet1;
        bullet2 = AssetLoader.bullet2;
        bullet3 = AssetLoader.bullet3;
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawPipeTops() {

		for(Pipe pipe: pipes)
		{
			batcher.draw(pipeUp, pipe.getX() - 1,
					pipe.getY() + pipe.getHeight() - 14, 24, 14);
			batcher.draw(pipeDown, pipe.getX() - 1,
					pipe.getY() + pipe.getHeight() + 45, 24, 14);
		}
	}

	private void drawPipes() {
		
		for(Pipe pipe: pipes)
		{
			batcher.draw(bar, pipe.getX(), pipe.getY(), pipe.getWidth(),
					pipe.getHeight());
			batcher.draw(bar, pipe.getX(), pipe.getY() + pipe.getHeight() + 45,
					pipe.getWidth(), midPointY + (midPointY * 3) - (pipe.getHeight() + 45));
		}
	}

	private void drawBirdCentered(float runTime) {
		batcher.draw(birdAnimation.getKeyFrame(runTime), 59 * 2, (bird.getY() - 15) * 2,
				bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
	}

	private void drawBird(float runTime) {

		if (bird.shouldntFlap() && myWorld.getScroller().isRightGoing()) {
			batcher.draw(birdMid, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

		} else if(bird.shouldntFlap() && !myWorld.getScroller().isRightGoing()) {
			batcher.draw(birdMidFlipped, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
		} else if(myWorld.getScroller().isRightGoing()) {
			batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
					bird.getY(), bird.getWidth() / 2.0f,
					bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
					1, 1, bird.getRotation());
		} else if(!myWorld.getScroller().isRightGoing()) {
			batcher.draw(birdAnimationFlipped.getKeyFrame(runTime), bird.getX(),
					bird.getY(), bird.getWidth() / 2.0f,
					bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
					1, 1, bird.getRotation());
		}

        for (int i = 0; i < bird.getProjectiles().size(); i++) {
            Projectile p = bird.getProjectiles().get(i);
            if (p.isVisible()) {
                p.update();
                batcher.draw(bullet1, p.getX(), p.getY(), p.getWidth(),
                        p.getHeight());
            } else {
                bird.getProjectiles().remove(i);
            }
        }

	}

	private void drawMenuUI(boolean showLogo) {
		if(showLogo) {
			batcher.draw(fbLogo, (float) midPointX - 70 , (midPointY - 20) /2 ,
				fbLogo.getRegionWidth() / 1.2f, fbLogo.getRegionHeight() / 1.2f);
		}

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, 22 * 4, midPointY - 00, 97, 37);

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
				(float) ((104 - (2 * length)) * 1.7), midPointY + 10);

		int length2 = ("" + myWorld.getDatabase().getHighestHighscore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getDatabase().getHighestHighscore(),
				(float) ((104 - (2.5f * length2)) * 1.7), midPointY + 27);

	}

	private void drawRetry() {
		//batcher.draw(retry, bird.getWidth() / 2.0f, (midPointY) * 2, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, bird.getX() - 25, (midPointY) * 2, 68, 14);
	}

	private void drawGameOver() {
		//batcher.draw(gameOver, 24, (midPointY - 50) * 2, 92, 14);
	}

	private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				midPointX - (3 * length), midPointY - 22);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
				midPointX - (3 * length), midPointY - 23);
	}

	private void drawHighScore() {
		//batcher.draw(highScore, midPointX, (midPointY - 20) * 2, 96, 14);
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		

		batcher.begin();
		batcher.disableBlending();

		batcher.draw(bg, -10,(midPointY - 38), 143 * 2, (float) (43 * 2.8));

		drawPipes();

		batcher.enableBlending();
		drawPipeTops();
		
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

		if (myWorld.isRunning() || myWorld.isPaused()) {
			drawBird(runTime);
			drawScore();
		} else if (myWorld.isReady()) {
			drawBird(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawBirdCentered(runTime);
			drawMenuUI(true);
		} else if (myWorld.isGameOver()) {
			drawScoreboard();
			drawBird(runTime);
			drawGameOver();
			drawRetry();
		} else if (myWorld.isHighScore()) {
			drawScoreboard();
			drawBird(runTime);
			drawHighScore();
			drawMenuUI(false);
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
