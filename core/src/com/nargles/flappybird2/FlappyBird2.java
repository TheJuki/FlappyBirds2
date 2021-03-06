package com.nargles.flappybird2;

import com.badlogic.gdx.Game;
import com.nargles.flappybird2.ui.Screens.SplashScreen;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.scoreManager.FlappyBird2Database;

/**
 * Flappy Bird 2 Game
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class FlappyBird2 extends Game {

    private FlappyBird2Database db;

    @Override
    public void create() {
        db = new FlappyBird2Database();
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    public FlappyBird2Database getDatabase() {
        return db;
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
        db.close();
    }

}