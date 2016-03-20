package com.nargles.flappybird2.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nargles.flappybird2.FlappyBirds2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1080 / 3;
		config.width = 1920 / 3;
		config.title = "Flappy Bird II";
		config.addIcon("data/icon_128.png", FileType.Internal);
		config.addIcon("data/icon_64.png", FileType.Internal);
		config.addIcon("data/icon_32.png", FileType.Internal);
		config.addIcon("data/icon_16.png", FileType.Internal);
		new LwjglApplication(new FlappyBirds2(), config);
	}
}
