package com.nargles.flappybird2.gameEnvironment;

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
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.obstacles.Grass;
import com.nargles.flappybird2.gameEnvironment.obstacles.Pipe;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.gameEnvironment.projectiles.Projectile;
import com.nargles.flappybird2.ui.Buttons.GameButton;
import com.nargles.flappybird2.ui.InputHandler;
import com.nargles.flappybird2.ui.TweenAccessors.Value;
import com.nargles.flappybird2.ui.TweenAccessors.ValueAccessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Game Renderer
 * Copyright 2016 Nargles.
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
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
    private TextureRegion blueEgg, fireEgg, grenadeEgg, blueEggNest, fireEggNest, grenadeEggNest;


	private TextureRegion bg, grass, birdMid, birdMidFlipped, pipeUp, pipeDown, bar, ready,
			fbLogo, gameOver, highScore, scoreboard, retry;
	private Animation birdAnimation, birdAnimationFlipped;

	// Tween 
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<GameButton> menuButtons;
    private List<GameButton> inGameButtons;
	private Color transitionColor;

    /**
     * Constructor
     * @param world Game World
     * @param gameHeight Height of game screen
     * @param midPointY Middle of game screen vertically
     * @param midPointX Middle of game screen horizontally
     */
    public GameRenderer(GameWorld world, int gameHeight, int midPointX , int midPointY) {
		myWorld = world;

		this.midPointY = midPointY;
		this.midPointX = midPointX;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();
        this.inGameButtons = ((InputHandler) Gdx.input.getInputProcessor())
                .getInGameButtons();

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

    /**
     * Get player and obstacles
     */
    private void initGameObjects() {
		bird = myWorld.getBird();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipes = scroller.getPipes();
	}

    /**
     * Get assets
     */
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
        blueEgg = AssetLoader.blueEgg;
        fireEgg = AssetLoader.fireEgg;
        grenadeEgg = AssetLoader.grenadeEgg;
        blueEggNest = AssetLoader.blueEggNest;
        fireEggNest = AssetLoader.fireEggNest;
        grenadeEggNest = AssetLoader.grenadeEggNest;
	}

    /**
     * Resets objects on a new game
     */
    public void onRestart() {
        for(GameButton btn : inGameButtons) {
            if (btn.getName().equals("flip"))
            {
                btn.setTextures(AssetLoader.flipButtonRight, AssetLoader.flipButtonRight, true);
                break;
            }
        }
    }

    /**
     * Draw grass
     */
    private void drawGrass() {

		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

    /**
     * Draw top portion for all pipes (Up and down)
     */
    private void drawPipeTops() {

		for(Pipe pipe: pipes)
		{
            if(pipe.isBarTopVisible()) {
                batcher.draw(pipeUp, pipe.getX() - 1,
                        pipe.getY() + pipe.getHeight() - 14, 24, 14);
            }
            if(pipe.isBarBottomVisible()) {
                batcher.draw(pipeDown, pipe.getX() - 1,
                        pipe.getY() + pipe.getHeight() + 45, 24, 14);
            }

            if(pipe.getNest() != null)
            {
                if(pipe.getNest().isNestVisible()) {
                    switch(pipe.getNest().getAmmoType())
                    {
                        case 0:
                            batcher.draw(blueEggNest, pipe.getNest().getX(), pipe.getNest().getY(), pipe.getNest().getWidth(),
                                    pipe.getNest().getHeight(), pipe.getNest().getWidth(),
                                    pipe.getNest().getHeight(), 1, 1, 1.0f);
                            break;
                        case 1:
                            batcher.draw(fireEggNest, pipe.getNest().getX(), pipe.getNest().getY(), pipe.getNest().getWidth(),
                                    pipe.getNest().getHeight(), pipe.getNest().getWidth(),
                                    pipe.getNest().getHeight(), 1, 1, 1.0f);
                            break;
                        case 2:
                            batcher.draw(grenadeEggNest, pipe.getNest().getX(), pipe.getNest().getY(), pipe.getNest().getWidth(),
                                    pipe.getNest().getHeight(), pipe.getNest().getWidth(),
                                    pipe.getNest().getHeight(), 1, 1, 1.0f);
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    pipe.setNest(null);
                }
            }
		}
	}

    /**
     * Draw all pipes (Up and down)
     */
	private void drawPipes() {
		
		for(Pipe pipe: pipes)
		{
            if(pipe.isBarTopVisible()) {
                batcher.draw(bar, pipe.getX(), pipe.getY(), pipe.getWidth(),
                        pipe.getHeight());
            }
            if(pipe.isBarBottomVisible()) {
                batcher.draw(bar, pipe.getX(), pipe.getY() + pipe.getHeight() + 45,
                        pipe.getWidth(), midPointY + (midPointY * 3) - (pipe.getHeight() + 45));
            }
		}
	}

    /**
     * Draw Bird in center of game screen
     * @param runTime Update animation frame
     */
    private void drawBirdCentered(float runTime) {
		batcher.draw(birdAnimation.getKeyFrame(runTime), 59 * 2, (bird.getY() - 15) * 2,
				bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
	}

    /**
     * Draw bird (Handle flip)
     * Draw bird's fired projectiles
     * @param runTime Update animation frame
     */
    private void drawBird(float runTime) {

		if (bird.shouldNotFlap() && myWorld.getScroller().isRightGoing()) {
			batcher.draw(birdMid, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

		} else if(bird.shouldNotFlap() && !myWorld.getScroller().isRightGoing()) {
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

        List<Integer> projectilesToRemove = new ArrayList<Integer>();
        for (int i = 0; i < bird.getProjectiles().size(); i++) {
            Projectile p = bird.getProjectiles().get(i);
            if (p.isVisible()) {
                p.update();

                switch(p.getType())
                {
                    case 0:
                        batcher.draw(blueEgg, p.getX(), p.getY(), p.getWidth(),
                                p.getHeight(), p.getWidth(),
                                p.getHeight(), 1, 1, p.getRotation());
                        break;
                    case 1:
                        batcher.draw(fireEgg, p.getX(), p.getY(), p.getWidth(),
                                p.getHeight(), p.getWidth(),
                                p.getHeight(), 1, 1, p.getRotation());
                        break;
                    case 2:
                        batcher.draw(grenadeEgg, p.getX(), p.getY(), p.getWidth(),
                                p.getHeight(), p.getWidth(),
                                p.getHeight(), 1, 1, p.getRotation());
                        break;
                    default:
                        break;
                }

            } else {
                projectilesToRemove.add(i);
            }
        }

        for(int i: projectilesToRemove)
        {
            bird.getProjectiles().remove(i);
        }

	}

    /**
     * Draw menu buttons
     * @param showLogo Main Menu displays the game logo
     */
    private void drawMenuUI(boolean showLogo) {
		if(showLogo) {
			batcher.draw(fbLogo, (float) (midPointY) * 3 , midPointX * 2 ,
				fbLogo.getRegionWidth(), fbLogo.getRegionHeight());
		}

		for (GameButton button : menuButtons) {
			button.draw(batcher);
		}

	}

    /**
     * Draw HighScore and final score on board
     */
    private void drawScoreboard() {
		batcher.draw(scoreboard, 22 * 4, midPointY, 97, 37);

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

		int length2 = ("" + myWorld.getDb().getHighestHighScore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getDb().getHighestHighScore(),
				(float) ((104 - (2.5f * length2)) * 1.7), midPointY + 27);

	}

    /**
     * Draw Ready text sprite
     */
    private void drawReady() {
		batcher.draw(ready, bird.getX() - 25, (midPointY) * 2, 68, 14);
	}

    /**
     * Draw current score during game play
     */
    private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				midPointX - (3 * length), midPointY - 22);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
				midPointX - (3 * length), midPointY - 23);
	}

    /**
     * Draw fire and flip buttons during game play
     */
    private void drawInGameButtons() {
        for (GameButton button : inGameButtons) {
            button.draw(batcher);
        }
    }

    /**
     * Render screen and changes to objects
     * @param delta Update Transition
     * @param runTime Update animation frame
     */
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
            drawInGameButtons();
		} else if (myWorld.isReady()) {
			drawBird(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawBirdCentered(runTime);
			drawMenuUI(true);
		} else if (myWorld.isGameOver()) {
			drawScoreboard();
			drawBird(runTime);
			//drawGameOver();
			//drawRetry();
		} else if (myWorld.isHighScore()) {
			drawScoreboard();
			drawBird(runTime);
			//drawHighScore();
			drawMenuUI(false);
		}

		drawGrass();

		batcher.end();
		drawTransition(delta);
	}

    /**
     * Get Tween ready
     * @param r R
     * @param g G
     * @param b B
     * @param duration Duration of transition
     */
    public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

    /**
     * Draw Flash
     * @param delta Update Transition
     */
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
