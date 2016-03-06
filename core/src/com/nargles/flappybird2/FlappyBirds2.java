package com.nargles.flappybird2;

import com.badlogic.gdx.Game;
import com.nargles.flappybird2.Screens.SplashScreen;
import com.nargles.flappybird2.ZBHelpers.AssetLoader;

public class FlappyBirds2 extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}