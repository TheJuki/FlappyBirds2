package com.nargles.flappybird2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nargles.flappybird2.FlappyBirds2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1080 / 3;
		config.width = 1920 / 3;
		new LwjglApplication(new FlappyBirds2(), config);
	}
}
