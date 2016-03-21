package com.nargles.flappybird2.gameEnvironment.obstacles;

import com.nargles.flappybird2.gameEnvironment.Scrollable;

public class Grass extends Scrollable {

	public Grass(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}

	public void onRestart(float x, float scrollSpeed) {
		super.onRestart();
		position.x = x;
		velocity.x = scrollSpeed;
	}

}
