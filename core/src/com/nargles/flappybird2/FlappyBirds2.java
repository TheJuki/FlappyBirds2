package com.nargles.flappybird2;

import com.badlogic.gdx.Game;
import com.nargles.flappybird2.ui.Screens.SplashScreen;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.scoreManager.FlappyBird2Database;

public class FlappyBirds2 extends Game {
	
	private FlappyBird2Database db;

	@Override
	public void create() {
		db = new FlappyBird2Database();
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}
	
	public FlappyBird2Database getDatabase()
	{
		return db;
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
		db.close();
	}

}