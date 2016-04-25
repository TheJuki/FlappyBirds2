/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

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

                // Verify that the database loaded
                assertTrue (game.getDatabase() != null);
                // Verify that each asset can be loaded
                assertTrue (AssetLoader.load());
                // Verify that a texture is not null
                assertTrue (AssetLoader.bird != null);
                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

        Gdx.app.exit();
	}

    /**
     * Test adding and getting high scores to the database.
     */
    @Test
    public void testDatabase() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

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

        Gdx.app.exit();
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
                GameScreen gameScreen = new GameScreen(game);

                // Verify that the world is not null
                assertTrue(gameScreen.getWorld() != null);

                // Verify that the render is not null
                assertTrue(gameScreen.getRenderer() != null);

                waitForThread = false;
            }
        });

        while(waitForThread) {
            try {
                Thread.sleep(10);
            } catch(Exception e ) {
            }
        }

        Gdx.app.exit();

    }
}
