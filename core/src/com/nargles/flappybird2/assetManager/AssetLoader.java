package com.nargles.flappybird2.assetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Asset Loader
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class AssetLoader {

    public static Texture texture, logoTexture, gameLogoTexture, blueEggTexture, fireEggTexture, grenadeEggTexture,
            flipButtonLeftTexture, flipButtonRightTexture,
            highScoreButtonUpTexture, highScoreButtonDownTexture,
            controlsButtonUpTexture, controlsButtonDownTexture,
            playButtonUpTexture,playButtonDownTexture,
            playAgainButtonUpTexture,playAgainButtonDownTexture,
            returnButtonUpTexture,returnButtonDownTexture,
            quitButtonUpTexture, quitButtonDownTexture,
            fireButtonUpTexture, fireButtonDownTexture, fireButtonDisabledTexture,
            blueEggNestTexture, fireEggNestTexture, grenadeEggNestTexture,
            backdropTexture, groundTexture, deathScreenTexture,
            highScoreScreenTexture,
            treeTopTexture, treeTrunkTexture;
    public static TextureRegion logo, fbLogo, bg, grass,
            bird, birdDown, birdUp,
            blueEgg, fireEgg, grenadeEgg,
            blueEggNest, fireEggNest, grenadeEggNest,
            birdFlipped, birdDownFlipped, birdUpFlipped,
            pipeUp, pipeDown, barUp, barDown,
            playButtonUp, playButtonDown,
            quitButtonUp, quitButtonDown,
            controlsButtonUp, controlsButtonDown,
            highScoreButtonUp, highScoreButtonDown,
            playAgainButtonUp, playAgainButtonDown,
            returnButtonUp, returnButtonDown,
            flipButtonLeft, flipButtonRight, deathScreen,
            highScoreScreen,
            fireButtonUp, fireButtonDown, fireButtonDisabled,
            ready, gameOver, highScore, retry;
    public static Animation birdAnimation, birdAnimationFlipped;
    public static Sound dead, flap, coin, fall, shoot;
    public static BitmapFont font, shadow, whiteFont, smallShadow, smallWhiteFont;

    /**
     * Load each asset
     * @return if true, each file was found
     */
    public static boolean load() {

        //Nargles Logo
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Game Logo
        gameLogoTexture = new Texture(Gdx.files.internal("data/fb_logo.png"));
        gameLogoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Backdrop/Background
        backdropTexture = new Texture(Gdx.files.internal("data/background.png"));
        backdropTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Grass/Ground
        groundTexture = new Texture(Gdx.files.internal("data/ground.png"));
        groundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Tree Top
        treeTopTexture = new Texture(Gdx.files.internal("data/tree_top.png"));
        treeTopTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        //Tree Trunk
        treeTrunkTexture = new Texture(Gdx.files.internal("data/tree_trunk.png"));
        treeTrunkTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Game over/death screen
        deathScreenTexture = new Texture(Gdx.files.internal("data/DeathScreen.png"));
        deathScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //High score screen
        highScoreScreenTexture = new Texture(Gdx.files.internal("data/HighScoreScreen.png"));
        highScoreScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

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

        //High Scores Button
        highScoreButtonUpTexture = new Texture(Gdx.files.internal("data/HighScores.png"));
        highScoreButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        highScoreButtonDownTexture = new Texture(Gdx.files.internal("data/HighScoresClick.png"));
        highScoreButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Controls Button
        controlsButtonUpTexture = new Texture(Gdx.files.internal("data/Controls.png"));
        controlsButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        controlsButtonDownTexture = new Texture(Gdx.files.internal("data/ControlsClick.png"));
        controlsButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Play Button
        playButtonUpTexture = new Texture(Gdx.files.internal("data/Play.png"));
        playButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playButtonDownTexture = new Texture(Gdx.files.internal("data/PlayClick.png"));
        playButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Quit Button
        quitButtonUpTexture = new Texture(Gdx.files.internal("data/Quit.png"));
        quitButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        quitButtonDownTexture = new Texture(Gdx.files.internal("data/QuitClick.png"));
        quitButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Play Again Button
        playAgainButtonUpTexture = new Texture(Gdx.files.internal("data/PlayAgain.png"));
        playAgainButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playAgainButtonDownTexture = new Texture(Gdx.files.internal("data/PlayAgainClick.png"));
        playAgainButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Return Button
        returnButtonUpTexture = new Texture(Gdx.files.internal("data/Return.png"));
        returnButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        returnButtonDownTexture = new Texture(Gdx.files.internal("data/ReturnClick.png"));
        returnButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

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

        //Fire button
        fireButtonUp = new TextureRegion(fireButtonUpTexture, 0, 0, 960, 540);
        fireButtonUp.flip(false, true);
        fireButtonDown = new TextureRegion(fireButtonDownTexture, 0, 0, 960, 540);
        fireButtonDown.flip(false, true);
        fireButtonDisabled = new TextureRegion(fireButtonDisabledTexture, 0, 0, 960, 540);
        fireButtonDisabled.flip(false, true);

        //High score button
        highScoreButtonUp = new TextureRegion(highScoreButtonUpTexture, 0, 0, 960, 540);
        highScoreButtonUp.flip(false, true);
        highScoreButtonDown = new TextureRegion(highScoreButtonDownTexture, 0, 0, 960, 540);
        highScoreButtonDown.flip(false, true);

        //Controls button
        controlsButtonUp = new TextureRegion(controlsButtonUpTexture, 0, 0, 960, 540);
        controlsButtonUp.flip(false, true);
        controlsButtonDown = new TextureRegion(controlsButtonDownTexture, 0, 0, 960, 540);
        controlsButtonDown.flip(false, true);

        //Play button
        playButtonUp = new TextureRegion(playButtonUpTexture, 0, 0, 960, 540);
        playButtonUp.flip(false, true);
        playButtonDown = new TextureRegion(playButtonDownTexture, 0, 0, 960, 540);
        playButtonDown.flip(false, true);

        //Play Again button
        playAgainButtonUp = new TextureRegion(playAgainButtonUpTexture, 0, 0, 960, 540);
        playAgainButtonUp.flip(false, true);
        playAgainButtonDown = new TextureRegion(playAgainButtonDownTexture, 0, 0, 960, 540);
        playAgainButtonDown.flip(false, true);

        //Return button
        returnButtonUp = new TextureRegion(returnButtonUpTexture, 0, 0, 960, 540);
        returnButtonUp.flip(false, true);
        returnButtonDown = new TextureRegion(returnButtonDownTexture, 0, 0, 960, 540);
        returnButtonDown.flip(false, true);

        //Quit button
        quitButtonUp = new TextureRegion(quitButtonUpTexture, 0, 0, 960, 540);
        quitButtonUp.flip(false, true);
        quitButtonDown = new TextureRegion(quitButtonDownTexture, 0, 0, 960, 540);
        quitButtonDown.flip(false, true);

        //Flip button
        flipButtonLeft = new TextureRegion(flipButtonLeftTexture, 0, 0, 960, 540);
        flipButtonLeft.flip(false, true);
        flipButtonRight = new TextureRegion(flipButtonRightTexture, 0, 0, 960, 540);
        flipButtonRight.flip(false, true);

        ready = new TextureRegion(texture, 59, 83, 34, 7);
        ready.flip(false, true);

        retry = new TextureRegion(texture, 59, 110, 33, 7);
        retry.flip(false, true);

        gameOver = new TextureRegion(texture, 59, 92, 46, 7);
        gameOver.flip(false, true);

        deathScreen = new TextureRegion(deathScreenTexture, 0, 0, 595, 382);
        deathScreen.flip(false, true);

        highScoreScreen = new TextureRegion(highScoreScreenTexture, 0, 0, 960, 540);
        highScoreScreen.flip(false, true);

        highScore = new TextureRegion(texture, 59, 101, 48, 7);
        highScore.flip(false, true);

        fbLogo = new TextureRegion(gameLogoTexture, 0, 0, 960, 540);
        fbLogo.flip(false, true);

        bg = new TextureRegion(backdropTexture, 0, 0, 960, 540);
        bg.flip(false, true);

        grass = new TextureRegion(groundTexture, 0, 0, 960, 540);
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

        TextureRegion[] birds = {birdDown, bird, birdUp};
        TextureRegion[] birdsFlipped = {birdDownFlipped, birdFlipped, birdUpFlipped};
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        birdAnimationFlipped = new Animation(0.06f, birdsFlipped);
        birdAnimationFlipped.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        pipeUp = new TextureRegion(treeTopTexture, 0, 0, 960, 540);
        // Create by flipping existing pipeUp
        pipeDown = new TextureRegion(pipeUp);
        pipeDown.flip(false, true);

        barUp = new TextureRegion(treeTrunkTexture, 0, 0, 960, 540);
        barUp.flip(false, true);
        barDown = new TextureRegion(barUp);
        barDown.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));
        shoot = Gdx.audio.newSound(Gdx.files.internal("data/shoot.wav"));

        font = new BitmapFont(Gdx.files.internal("data/fb2.fnt"));
		font.getData().setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/fb2.fnt"));
		whiteFont.getData().setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/fb2.fnt"));
        shadow.setColor(Color.BLACK);
		shadow.getData().setScale(.28f, -.28f);

/*
        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        font.getData().setScale(.25f, -.25f);

        whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        whiteFont.getData().setScale(.1f, -.1f);

        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);
        */

        smallWhiteFont = new BitmapFont(Gdx.files.internal("data/fb2.fnt"));
        smallWhiteFont.getData().setScale(.15f, -.15f);

        smallShadow = new BitmapFont(Gdx.files.internal("data/fb2.fnt"));
        smallShadow.setColor(Color.BLACK);
        smallShadow.getData().setScale(.25f, -.25f);

        return true;

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