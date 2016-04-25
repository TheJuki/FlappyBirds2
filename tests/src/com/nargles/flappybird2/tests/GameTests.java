package com.nargles.flappybird2.tests;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.nargles.flappybird2.FlappyBird2;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.scoreManager.FlappyBird2Database;
import com.nargles.flappybird2.ui.Screens.GameScreen;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.nargles.flappybird2.assetManager.AssetLoader.bird;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Game Tests
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class GameTests {

    private static FlappyBird2 game;
    private static HeadlessApplication app;
    private boolean waitForThread = true;

    @BeforeClass
    public static void initGdx() {
        Gdx.gl = mock(GL20.class);
        game = new FlappyBird2();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 1080 / 3;
        config.width = 1920 / 3;
        config.title = "Flappy Bird II";
        config.addIcon("data/icon_128.png", Files.FileType.Internal);
        config.addIcon("data/icon_64.png", Files.FileType.Internal);
        config.addIcon("data/icon_32.png", Files.FileType.Internal);
        config.addIcon("data/icon_16.png", Files.FileType.Internal);
        new LwjglApplication(game, config);
    }

    /**
     * Test that after a new game runs that the database is
     * not null and that textures were loaded and set.
     */
    @Test
	public void testGame() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                waitForThread = true;

                // Verify that the database loaded
                assertTrue (game.getDatabase() != null);
                // Verify that each asset can be loaded
                assertTrue (AssetLoader.load());
                // Verify that a texture is not null
                assertTrue (bird != null);
                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

	}

    /**
     * Test adding and getting high scores to the database.
     */
    @Test
    public void testDatabase() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                waitForThread = true;

                FlappyBird2Database db = new FlappyBird2Database();

                // Verify that the database loaded
                assertTrue (db != null);

                // Add two high scores
                db.addHighScore(10);
                db.addHighScore(5);

                List<Integer> highScoreList = db.getAllHighScores();

                // Verify that the high scores are in the database
                assertTrue (highScoreList != null && highScoreList.size() >= 2);

                System.out.print("High Scores: \n");
                for (int highScore : highScoreList)
                {
                    System.out.print(highScore + "\n");
                }

                // Close the database
                db.close();

                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

    }

    /**
     * Test a new game after hitting play or
     * play again.
     */
    @Test
    public void testPlay() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                waitForThread = true;

                // Start a new game screen
                GameScreen gameScreen = new GameScreen(game);

                // Verify that the world is not null
                assertTrue(gameScreen.getWorld() != null);

                // Verify that the render is not null
                assertTrue(gameScreen.getRenderer() != null);

                // Verify that the scroller is not null
                assertTrue(gameScreen.getWorld().getScroller() != null);

                // Verify that the back grass is not null
                assertTrue(gameScreen.getWorld().getScroller().getBackGrass() != null);

                // Verify that the front grass is not null
                assertTrue(gameScreen.getWorld().getScroller().getFrontGrass() != null);

                // Verify that at least 6 pipes are ready
                assertTrue(gameScreen.getWorld().getScroller().getPipes() != null &&
                        gameScreen.getWorld().getScroller().getPipes().size() >= 6);

                // Verify that the bird is not null
                assertTrue(gameScreen.getWorld().getBird() != null);

                // Verify that the first state is the menu
                assertTrue(gameScreen.getWorld().isMenu());

                // Verify that the following variables are 0
                assertTrue(gameScreen.getWorld().getPipesDestroyed() == 0);
                assertTrue(gameScreen.getWorld().getDistance() == 0);
                assertTrue(gameScreen.getWorld().getScore() == 0);

                // Hit Play
                gameScreen.getWorld().ready();

                // Verify that the state is ready
                assertTrue(gameScreen.getWorld().isReady());

                // Verify that the bird has no ammo
                assertTrue(gameScreen.getWorld().getBird().isOutOfAmmo());
                assertTrue((gameScreen.getWorld().getBird().getNumBlueEggs() +
                        gameScreen.getWorld().getBird().getNumFireEggs() +
                        gameScreen.getWorld().getBird().getNumGrenadeEggs()) == 0);

                // Tap screen
                gameScreen.getWorld().start();
                gameScreen.getWorld().getBird().onClick();

                // Add data to record variables
                gameScreen.getWorld().addDistance(3);
                gameScreen.getWorld().addPipesDestroyed(5);
                gameScreen.getWorld().addScore(99);

                //Add ammo to bird
                gameScreen.getWorld().getBird().addNumBlueEggs(5);
                gameScreen.getWorld().getBird().addNumFireEggs(10);
                gameScreen.getWorld().getBird().addNumGrenadeEggs(15);

                // Verify that the bird has ammo
                assertTrue(!gameScreen.getWorld().getBird().isOutOfAmmo());

                // The bird dies...
                gameScreen.getWorld().getBird().die();

                gameScreen.getWorld().getScroller().stop();
                gameScreen.getWorld().getBird().decelerate();
                gameScreen.getWorld().gameOver();
                if (gameScreen.getWorld().getScore() > 0) {
                    game.getDatabase().addHighScore(gameScreen.getWorld().getScore());
                }

                List<Integer> highScoreList = game.getDatabase().getAllHighScores();

                // Verify that the high scores are in the database
                assertTrue (highScoreList != null && highScoreList.size() > 0);

                System.out.print("High Scores: \n");
                for (int highScore : highScoreList)
                {
                    System.out.print(highScore + "\n");
                }

                // Hit Play Again
                gameScreen.getWorld().restart();

                // Verify that the state is ready
                assertTrue(gameScreen.getWorld().isReady());

                // Verify that the following variables are 0
                assertTrue(gameScreen.getWorld().getPipesDestroyed() == 0);
                assertTrue(gameScreen.getWorld().getDistance() == 0);
                assertTrue(gameScreen.getWorld().getScore() == 0);

                // Verify that the bird has no ammo
                assertTrue(gameScreen.getWorld().getBird().isOutOfAmmo());

                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

    }

    /**
     * Test that fire behaves normally
     */
    @Test
    public void testFire() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                waitForThread = true;

                // Start a new game screen
                GameScreen gameScreen = new GameScreen(game);

                // Verify that projectiles array is initialized
                assertTrue(gameScreen.getWorld().getBird().getProjectiles() != null);

                // Verify that the bird has no ammo
                assertTrue(gameScreen.getWorld().getBird().isOutOfAmmo());

                gameScreen.getWorld().getBird().shoot();

                // Verify that no projectiles were added
                assertTrue(gameScreen.getWorld().getBird().getProjectiles().size() == 0);


                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

    }

    /**
     * Test that flip behaves normally
     */
    @Test
    public void testFlip() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                waitForThread = true;

                // Start a new game screen
                GameScreen gameScreen = new GameScreen(game);

                // Tap screen
                gameScreen.getWorld().start();

                // Verify that the game is moving right initially
                assertTrue(gameScreen.getWorld().getScroller().isRightGoing());
                assertTrue(gameScreen.getWorld().getBird().isRightGoing());
                assertTrue(gameScreen.getWorld().getScroller().getBackGrass().isRightGoing());
                assertTrue(gameScreen.getWorld().getScroller().getFrontGrass().isRightGoing());
                assertTrue(gameScreen.getWorld().getScroller().getPipes().get(0).isRightGoing());

                gameScreen.getWorld().getScroller().flip();

                // Verify that the game is moving left after a flip
                assertTrue(!gameScreen.getWorld().getScroller().isRightGoing());
                //assertTrue(!gameScreen.getWorld().getBird().isRightGoing());
                assertTrue(!gameScreen.getWorld().getScroller().getBackGrass().isRightGoing());
                assertTrue(!gameScreen.getWorld().getScroller().getFrontGrass().isRightGoing());
                assertTrue(!gameScreen.getWorld().getScroller().getPipes().get(0).isRightGoing());

                gameScreen.getWorld().getScroller().flip();

                // Verify that the game is moving right after a second flip
                assertTrue(gameScreen.getWorld().getScroller().isRightGoing());
                assertTrue(gameScreen.getWorld().getBird().isRightGoing());
                assertTrue(gameScreen.getWorld().getScroller().getBackGrass().isRightGoing());
                assertTrue(gameScreen.getWorld().getScroller().getFrontGrass().isRightGoing());
                assertTrue(gameScreen.getWorld().getScroller().getPipes().get(0).isRightGoing());

                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

    }
}
