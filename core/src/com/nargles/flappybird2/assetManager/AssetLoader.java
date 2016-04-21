package com.nargles.flappybird2.assetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Asset Loader
 * Copyright 2016 Nargles.
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class AssetLoader {

	public static Texture texture, logoTexture, blueEggTexture, fireEggTexture, grenadeEggTexture, flipButtonLeftTexture, flipButtonRightTexture,
    fireButtonUpTexture, fireButtonDownTexture, fireButtonDisabledTexture,
    blueEggNestTexture, fireEggNestTexture, grenadeEggNestTexture;
	public static TextureRegion logo, fbLogo, bg, grass,
	bird, birdDown, birdUp,
    blueEgg, fireEgg, grenadeEgg,
            blueEggNest, fireEggNest, grenadeEggNest,
	birdFlipped, birdDownFlipped, birdUpFlipped,
	pipeUp, pipeDown, bar,
			playButtonUp, playButtonDown,
			quitButtonUp, quitButtonDown,
            flipButtonLeft, flipButtonRight,
            fireButtonUp, fireButtonDown, fireButtonDisabled,
			ready, gameOver, highScore, scoreboard, star, noStar, retry;
	public static Animation birdAnimation, birdAnimationFlipped;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, shadow, whiteFont;

    /**
     * Load each asset
     */
    public static void load() {

        //Logo
		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Eggs
        blueEggTexture = new Texture(Gdx.files.internal("data/blue_egg.png"));
        blueEggTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fireEggTexture = new Texture(Gdx.files.internal("data/fire_egg.png"));
        fireEggTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        grenadeEggTexture = new Texture(Gdx.files.internal("data/grenade_egg.png"));
        grenadeEggTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Nests
        blueEggNestTexture = new Texture(Gdx.files.internal("data/blue_egg_nest.png"));
        blueEggNestTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fireEggNestTexture = new Texture(Gdx.files.internal("data/fire_egg_nest.png"));
        fireEggNestTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        grenadeEggNestTexture = new Texture(Gdx.files.internal("data/grenade_egg_nest.png"));
        grenadeEggNestTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Flip Button
        flipButtonLeftTexture = new Texture(Gdx.files.internal("data/left_flip.png"));
        flipButtonLeftTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        flipButtonRightTexture = new Texture(Gdx.files.internal("data/right_flip.png"));
        flipButtonRightTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Fire Button
        fireButtonUpTexture = new Texture(Gdx.files.internal("data/unpressed_fire.png"));
        fireButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fireButtonDownTexture = new Texture(Gdx.files.internal("data/pressed_fire.png"));
        fireButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fireButtonDisabledTexture = new Texture(Gdx.files.internal("data/disabled_fire.png"));
        fireButtonDisabledTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        //Egg
        blueEgg = new TextureRegion(blueEggTexture, 0, 0, 960, 540);
        blueEgg.flip(false, true);
        fireEgg = new TextureRegion(fireEggTexture, 0, 0, 960, 540);
        fireEgg.flip(false, true);
        grenadeEgg = new TextureRegion(grenadeEggTexture, 0, 0, 960, 540);
        grenadeEgg.flip(false, true);

        //Nests
        blueEggNest = new TextureRegion(blueEggNestTexture, 0, 0, 960, 540);
        blueEggNest.flip(false, true);
        fireEggNest = new TextureRegion(fireEggNestTexture, 0, 0, 960, 540);
        fireEggNest.flip(false, true);
        grenadeEggNest = new TextureRegion(grenadeEggNestTexture, 0, 0, 960, 540);
        grenadeEggNest.flip(false, true);

        //Fire
        fireButtonUp = new TextureRegion(fireButtonUpTexture, 0, 0, 960, 540);
        fireButtonUp.flip(false, true);
        fireButtonDown = new TextureRegion(fireButtonDownTexture, 0, 0, 960, 540);
        fireButtonDown.flip(false, true);
        fireButtonDisabled = new TextureRegion(fireButtonDisabledTexture, 0, 0, 960, 540);
        fireButtonDisabled.flip(false, true);

        //Flip
        flipButtonLeft = new TextureRegion(flipButtonLeftTexture, 0, 0, 960, 540);
        flipButtonLeft.flip(false, true);
        flipButtonRight = new TextureRegion(flipButtonRightTexture, 0, 0, 960, 540);
        flipButtonRight.flip(false, true);

        //Play
		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);
		
		//Quit
		quitButtonUp = new TextureRegion(texture, 0, 101, 29, 16);
		quitButtonDown = new TextureRegion(texture, 29, 101, 29, 16);
		quitButtonUp.flip(false, true);
		quitButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 59, 83, 34, 7);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(texture, 59, 92, 46, 7);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 152, 70, 10, 10);
		noStar = new TextureRegion(texture, 165, 70, 10, 10);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore.flip(false, true);

		fbLogo = new TextureRegion(texture, 0, 55, 160, 24);
		fbLogo.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 153, 0, 17, 12);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		birdUp.flip(false, true);
		
		birdDownFlipped = new TextureRegion(texture, 136, 0, 17, 12);
		birdDownFlipped.flip(true, true);

		birdFlipped = new TextureRegion(texture, 153, 0, 17, 12);
		birdFlipped.flip(true, true);

		birdUpFlipped = new TextureRegion(texture, 170, 0, 17, 12);
		birdUpFlipped.flip(true, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		TextureRegion[] birdsFlipped = { birdDownFlipped, birdFlipped, birdUpFlipped };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		birdAnimationFlipped = new Animation(0.06f, birdsFlipped);
		birdAnimationFlipped.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		pipeUp = new TextureRegion(texture, 192, 0, 24, 14);
		// Create by flipping existing skullUp
		pipeDown = new TextureRegion(pipeUp);
		pipeDown.flip(false, true);

		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

		font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		font.getData().setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.getData().setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.getData().setScale(.25f, -.25f);

	}

    /**
     * Dispose of assets
     */
    public static void dispose() {

		// Dispose texture
		texture.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin.dispose();

        //Dispose fonts
		font.dispose();
		shadow.dispose();
	}

}