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
            playButtonUpTexture, playButtonDownTexture,
            playAgainButtonUpTexture, playAgainButtonDownTexture,
            returnButtonUpTexture, returnButtonDownTexture,
            nextButtonUpTexture, nextButtonDownTexture,
            quitButtonUpTexture, quitButtonDownTexture,
            fireButtonUpTexture, fireButtonDownTexture, fireButtonDisabledTexture,
            blueEggNestTexture, fireEggNestTexture, grenadeEggNestTexture,
            backdropTexture, groundTexture, deathScreenTexture,
            blueBird1Texture, blueBird2Texture, blueBird3Texture,
            fireBird1Texture, fireBird2Texture, fireBird3Texture,
            grenadeBird1Texture, grenadeBird2Texture, grenadeBird3Texture,
            highScoreScreenTexture, controls1ScreenTexture, controls2ScreenTexture,
            treeTop1Texture, treeTop2Texture, treeTop3Texture,
            treeTrunk1Texture, treeTrunk2Texture, treeTrunk3Texture;
    public static TextureRegion logo, fbLogo, bg, grass,
            blueBird1, blueBird2, blueBird3,
            fireBird1, fireBird2, fireBird3,
            grenadeBird1, grenadeBird2, grenadeBird3,
            blueEgg, fireEgg, grenadeEgg,
            blueEggNest, fireEggNest, grenadeEggNest,
            blueBird1Flipped, blueBird2Flipped, blueBird3Flipped,
            fireBird1Flipped, fireBird2Flipped, fireBird3Flipped,
            grenadeBird1Flipped, grenadeBird2Flipped, grenadeBird3Flipped,
            treeTop1Up, treeTop1Down, treeTop2Up, treeTop2Down, treeTop3Up, treeTop3Down,
            bar1Up, bar1Down, bar2Up, bar2Down, bar3Up, bar3Down,
            playButtonUp, playButtonDown,
            quitButtonUp, quitButtonDown,
            controlsButtonUp, controlsButtonDown,
            highScoreButtonUp, highScoreButtonDown,
            playAgainButtonUp, playAgainButtonDown,
            returnButtonUp, returnButtonDown,
            nextButtonUp, nextButtonDown,
            flipButtonLeft, flipButtonRight, deathScreen,
            highScoreScreen, controls1Screen, controls2Screen,
            fireButtonUp, fireButtonDown, fireButtonDisabled,
            ready;
    public static Animation blueBirdAnimation, blueBirdAnimationFlipped,
            fireBirdAnimation, fireBirdAnimationFlipped,
            grenadeBirdAnimation, grenadeBirdAnimationFlipped;
    public static Sound dead, flap, coin, fall, shoot;
    public static BitmapFont font, shadow, whiteFont, smallShadow, smallWhiteFont;

    /**
     * Load each asset
     *
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

        //Blue Birds
        blueBird1Texture = new Texture(Gdx.files.internal("data/BlueBird1.png"));
        blueBird1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blueBird2Texture = new Texture(Gdx.files.internal("data/BlueBird2.png"));
        blueBird2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blueBird3Texture = new Texture(Gdx.files.internal("data/BlueBird3.png"));
        blueBird3Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Red Birds
        fireBird1Texture = new Texture(Gdx.files.internal("data/RedBird1.png"));
        fireBird1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fireBird2Texture = new Texture(Gdx.files.internal("data/RedBird2.png"));
        fireBird2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        fireBird3Texture = new Texture(Gdx.files.internal("data/RedBird3.png"));
        fireBird3Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Green Birds
        grenadeBird1Texture = new Texture(Gdx.files.internal("data/GreenBird1.png"));
        grenadeBird1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        grenadeBird2Texture = new Texture(Gdx.files.internal("data/GreenBird2.png"));
        grenadeBird2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        grenadeBird3Texture = new Texture(Gdx.files.internal("data/GreenBird3.png"));
        grenadeBird3Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Grass/Ground
        groundTexture = new Texture(Gdx.files.internal("data/ground.png"));
        groundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Tree Top
        treeTop1Texture = new Texture(Gdx.files.internal("data/TreeTop1.png"));
        treeTop1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        treeTop2Texture = new Texture(Gdx.files.internal("data/TreeTop2.png"));
        treeTop2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        treeTop3Texture = new Texture(Gdx.files.internal("data/TreeTop3.png"));
        treeTop3Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        //Tree Trunk
        treeTrunk1Texture = new Texture(Gdx.files.internal("data/tree1.png"));
        treeTrunk1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        treeTrunk2Texture = new Texture(Gdx.files.internal("data/tree3.png"));
        treeTrunk2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        treeTrunk3Texture = new Texture(Gdx.files.internal("data/tree4.png"));
        treeTrunk3Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        //Game over/death screen
        deathScreenTexture = new Texture(Gdx.files.internal("data/DeathScreen.png"));
        deathScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //High score screen
        highScoreScreenTexture = new Texture(Gdx.files.internal("data/HighScoreScreen.png"));
        highScoreScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Controls Page 1 screen
        controls1ScreenTexture = new Texture(Gdx.files.internal("data/HowToPlay1.png"));
        controls1ScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Controls Page 2 screen
        controls2ScreenTexture = new Texture(Gdx.files.internal("data/HowToPlay2.png"));
        controls2ScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

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

        //Next Button
        nextButtonUpTexture = new Texture(Gdx.files.internal("data/Next.png"));
        nextButtonUpTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        nextButtonDownTexture = new Texture(Gdx.files.internal("data/NextClick.png"));
        nextButtonDownTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Nargles Logo
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        //Texture
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

        //Next button
        nextButtonUp = new TextureRegion(nextButtonUpTexture, 0, 0, 661, 284);
        nextButtonUp.flip(false, true);
        nextButtonDown = new TextureRegion(nextButtonDownTexture, 0, 0, 661, 284);
        nextButtonDown.flip(false, true);

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

        //Gameover screen
        deathScreen = new TextureRegion(deathScreenTexture, 0, 0, 595, 382);
        deathScreen.flip(false, true);

        //Highscore screen
        highScoreScreen = new TextureRegion(highScoreScreenTexture, 0, 0, 960, 540);
        highScoreScreen.flip(false, true);

        //Controls 1 screen
        controls1Screen = new TextureRegion(controls1ScreenTexture, 0, 0, 712, 620);
        controls1Screen.flip(false, true);

        //Controls 2 screen
        controls2Screen = new TextureRegion(controls2ScreenTexture, 0, 0, 712, 620);
        controls2Screen.flip(false, true);

        //Flappybird 2 logo
        fbLogo = new TextureRegion(gameLogoTexture, 0, 0, 960, 540);
        fbLogo.flip(false, true);

        //Background
        bg = new TextureRegion(backdropTexture, 0, 0, 960, 540);
        bg.flip(false, true);

        //Grass
        grass = new TextureRegion(groundTexture, 0, 0, 960, 540);
        grass.flip(false, true);

        /* Blue Bird */

        //Down
        blueBird3 = new TextureRegion(blueBird3Texture, 0, 0, 960, 540);
        blueBird3.flip(false, true);
        //Normal
        blueBird1 = new TextureRegion(blueBird1Texture, 0, 0, 960, 540);
        blueBird1.flip(false, true);
        //Up
        blueBird2 = new TextureRegion(blueBird2Texture, 0, 0, 960, 540);
        blueBird2.flip(false, true);
        //Down Flipped
        blueBird3Flipped = new TextureRegion(blueBird3);
        blueBird3Flipped.flip(true, false);
        //Normal Flipped
        blueBird1Flipped = new TextureRegion(blueBird1);
        blueBird1Flipped.flip(true, false);
        //Up Flipped
        blueBird2Flipped = new TextureRegion(blueBird2);
        blueBird2Flipped.flip(true, false);
        TextureRegion[] blueBirds = {blueBird3, blueBird1, blueBird2};
        TextureRegion[] blueBirdsFlipped = {blueBird3Flipped, blueBird1Flipped, blueBird2Flipped};
        blueBirdAnimation = new Animation(0.06f, blueBirds);
        blueBirdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        blueBirdAnimationFlipped = new Animation(0.06f, blueBirdsFlipped);
        blueBirdAnimationFlipped.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

         /* Fire/Red Bird */

        //Down
        fireBird3 = new TextureRegion(fireBird3Texture, 0, 0, 960, 540);
        fireBird3.flip(false, true);
        //Normal
        fireBird1 = new TextureRegion(fireBird1Texture, 0, 0, 960, 540);
        fireBird1.flip(false, true);
        //Up
        fireBird2 = new TextureRegion(fireBird2Texture, 0, 0, 960, 540);
        fireBird2.flip(false, true);
        //Down Flipped
        fireBird3Flipped = new TextureRegion(fireBird3);
        fireBird3Flipped.flip(true, false);
        //Normal Flipped
        fireBird1Flipped = new TextureRegion(fireBird1);
        fireBird1Flipped.flip(true, false);
        //Up Flipped
        fireBird2Flipped = new TextureRegion(fireBird2);
        fireBird2Flipped.flip(true, false);
        TextureRegion[] fireBirds = {fireBird3, fireBird1, fireBird2};
        TextureRegion[] fireBirdsFlipped = {fireBird3Flipped, fireBird1Flipped, fireBird2Flipped};
        fireBirdAnimation = new Animation(0.06f, fireBirds);
        fireBirdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        fireBirdAnimationFlipped = new Animation(0.06f, fireBirdsFlipped);
        fireBirdAnimationFlipped.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

         /* Grenade/Green Bird */

        //Down
        grenadeBird3 = new TextureRegion(grenadeBird3Texture, 0, 0, 960, 540);
        grenadeBird3.flip(false, true);
        //Normal
        grenadeBird1 = new TextureRegion(grenadeBird1Texture, 0, 0, 960, 540);
        grenadeBird1.flip(false, true);
        //Up
        grenadeBird2 = new TextureRegion(grenadeBird2Texture, 0, 0, 960, 540);
        grenadeBird2.flip(false, true);
        //Down Flipped
        grenadeBird3Flipped = new TextureRegion(grenadeBird3);
        grenadeBird3Flipped.flip(true, false);
        //Normal Flipped
        grenadeBird1Flipped = new TextureRegion(grenadeBird1);
        grenadeBird1Flipped.flip(true, false);
        //Up Flipped
        grenadeBird2Flipped = new TextureRegion(grenadeBird2);
        grenadeBird2Flipped.flip(true, false);
        TextureRegion[] grenadeBirds = {grenadeBird3, grenadeBird1, grenadeBird2};
        TextureRegion[] grenadeBirdsFlipped = {grenadeBird3Flipped, grenadeBird1Flipped, grenadeBird2Flipped};
        grenadeBirdAnimation = new Animation(0.06f, grenadeBirds);
        grenadeBirdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        grenadeBirdAnimationFlipped = new Animation(0.06f, grenadeBirdsFlipped);
        grenadeBirdAnimationFlipped.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        treeTop1Up = new TextureRegion(treeTop1Texture, 0, 0, 960, 540);
        treeTop1Up.flip(false, true);
        treeTop1Down = new TextureRegion(treeTop1Up);
        treeTop1Down.flip(false, true);

        treeTop2Up = new TextureRegion(treeTop2Texture, 0, 0, 960, 540);
        treeTop2Up.flip(false, true);
        treeTop2Down = new TextureRegion(treeTop2Up);
        treeTop2Down.flip(false, true);

        treeTop3Up = new TextureRegion(treeTop3Texture, 0, 0, 960, 540);
        treeTop3Up.flip(false, true);
        treeTop3Down = new TextureRegion(treeTop3Up);
        treeTop3Down.flip(false, true);


        bar1Up = new TextureRegion(treeTrunk1Texture, 0, 0, 960, 540);
        bar1Up.flip(false, true);
        bar1Down = new TextureRegion(bar1Up);
        bar1Down.flip(false, true);

        bar2Up = new TextureRegion(treeTrunk2Texture, 0, 0, 960, 540);
        bar2Up.flip(false, true);
        bar2Down = new TextureRegion(bar2Up);
        bar2Down.flip(false, true);

        bar3Up = new TextureRegion(treeTrunk3Texture, 0, 0, 960, 540);
        bar3Up.flip(false, true);
        bar3Down = new TextureRegion(bar3Up);
        bar3Down.flip(false, true);

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