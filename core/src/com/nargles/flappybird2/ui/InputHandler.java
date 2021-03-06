package com.nargles.flappybird2.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.GameWorld;
import com.nargles.flappybird2.gameEnvironment.player.Bird;
import com.nargles.flappybird2.ui.Buttons.GameButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Input Handler
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class InputHandler implements InputProcessor {
    private Bird myBird;
    private GameWorld myWorld;

    private List<GameButton> menuButtons;
    private List<GameButton> gameOverButtons;
    private List<GameButton> inGameButtons;
    private List<GameButton> controlsScreenButtons;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
        this.myWorld = myWorld;
        myBird = myWorld.getBird();

        int midPointY = myWorld.getMidPointY();
        int midPointX = myWorld.getMidPointX();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<GameButton>();
        inGameButtons = new ArrayList<GameButton>();
        gameOverButtons = new ArrayList<GameButton>();
        controlsScreenButtons = new ArrayList<GameButton>();


        // Add Next button
        controlsScreenButtons.add(new GameButton("next", (midPointX + 5), (midPointY * 2.9f) + 5,
                96 / 1.3f, 54 / 1.5f, AssetLoader.nextButtonUp, AssetLoader.nextButtonDown, false));

        // Add Return button
        controlsScreenButtons.add(new GameButton("return", (midPointX - 55), (midPointY * 2.9f) + 5,
                96 / 1.3f, 54 / 1.3f, AssetLoader.returnButtonUp, AssetLoader.returnButtonDown, false));

        // Add Play Again button
        gameOverButtons.add(new GameButton("playagain", (midPointX + 5), (midPointY * 2.1f) + 5,
                96 / 1.3f, 54 / 1.3f, AssetLoader.playAgainButtonUp, AssetLoader.playAgainButtonDown, false));

        // Add Return button
        gameOverButtons.add(new GameButton("return", (midPointX - 55), (midPointY * 2.1f) + 5,
                96 / 1.3f, 54 / 1.3f, AssetLoader.returnButtonUp, AssetLoader.returnButtonDown, false));

        // Add Play button
        menuButtons.add(new GameButton("play", (midPointX + 20), (midPointY * 2.1f),
                96 / 1.3f, 54 / 1.3f, AssetLoader.playButtonUp, AssetLoader.playButtonDown, false));

        // Add Quit button
        menuButtons.add(new GameButton("quit", (midPointX - 70), (midPointY * 2.1f),
                96 / 1.3f, 54 / 1.3f, AssetLoader.quitButtonUp, AssetLoader.quitButtonDown, false));

        // Add Controls button
        menuButtons.add(new GameButton("controls", (136 - 120), (midPointY * 1.1f),
                96 / 1.3f, 54 / 1.3f, AssetLoader.controlsButtonUp, AssetLoader.controlsButtonDown, false));

        // Add High Scores button
        menuButtons.add(new GameButton("highscore", (136 + 70), (midPointY * 1.1f),
                96 / 1.3f, 54 / 1.3f, AssetLoader.highScoreButtonUp, AssetLoader.highScoreButtonDown, false));

        // Add Fire button
        inGameButtons.add(new GameButton("fire", (136 - 130), (midPointY * 3.5f) - 9,
                96 / 2f, 54 / 2f, AssetLoader.fireButtonDisabled, AssetLoader.fireButtonDisabled, false));

        // Add Flip button
        inGameButtons.add(new GameButton("flip", (136 - 30), (midPointY * 3.5f) - 9,
                96 / 2f, 54 / 2f, AssetLoader.flipButtonRight, AssetLoader.flipButtonRight, true));

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX * 2);
        screenY = scaleY(screenY * 2);

        if (myWorld.isMenu()) {
            for (GameButton btn : menuButtons) {
                btn.isTouchDown(screenX, screenY);
            }
        } else if (myWorld.isGameOver() || myWorld.isHighScore()) {
            for (GameButton btn : gameOverButtons) {
                btn.isTouchDown(screenX, screenY);
            }
        } else if (myWorld.isControls()) {
            for (GameButton btn : controlsScreenButtons) {
                btn.isTouchDown(screenX, screenY);
            }
        } else if (myWorld.isReady()) {
            myWorld.start();
            myBird.onClick();
        } else if (myWorld.isRunning()) {

            // Screen above ground flaps bird
            if (screenY < myWorld.getMidPointY() * 3) {
                myBird.onClick();
            } else {
                for (GameButton btn : inGameButtons) {
                    btn.isTouchDown(screenX, screenY);
                }
            }
        } else if (myWorld.isPaused()) {

            // Screen above ground flaps bird
            if (screenY < myWorld.getMidPointY() * 3) {
                myWorld.start();
                myBird.onClick();
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX * 2);
        screenY = scaleY(screenY * 2);

        if (myWorld.isMenu()) {
            for (GameButton btn : menuButtons) {
                boolean btnTapped = btn.isTouchUp(screenX, screenY);
                if (btnTapped && btn.getName().equals("play")) {
                    if (myWorld.isGameOver() || myWorld.isHighScore()) {
                        myWorld.restart();
                    } else {
                        myWorld.ready();
                    }
                } else if (btnTapped && btn.getName().equals("quit")) {
                    Gdx.app.exit();
                } else if (btnTapped && btn.getName().equals("highscore")) {
                    myWorld.highScore();
                } else if (btnTapped && btn.getName().equals("controls")) {
                    myWorld.controls();
                }
            }
            return true;
        } else if (myWorld.isGameOver() || myWorld.isHighScore()) {
            for (GameButton btn : gameOverButtons) {
                boolean btnTapped = btn.isTouchUp(screenX, screenY);
                if (btnTapped && btn.getName().equals("playagain")) {
                    myWorld.restart();
                } else if (btnTapped && btn.getName().equals("return")) {
                    myWorld.restart();
                    myWorld.menu();
                }
            }
            return true;
        }
        else if (myWorld.isControls()) {
            for (GameButton btn : controlsScreenButtons) {
                boolean btnTapped = btn.isTouchUp(screenX, screenY);
                if (btnTapped && btn.getName().equals("next")) {
                    myWorld.nextPage();
                } else if (btnTapped && btn.getName().equals("return")) {
                    myWorld.restart();
                    myWorld.menu();
                }
            }
            return true;
        } else if (myWorld.isRunning()) {
            for (GameButton btn : inGameButtons) {
                boolean btnTapped = btn.isTouchUp(screenX, screenY);
                if (btnTapped && btn.getName().equals("fire") && !myBird.isOutOfAmmo()) {
                    if (!myBird.isOutOfAmmo()) {
                        myBird.shoot();
                        if (myBird.isOutOfAmmo()) {
                            btn.setTextures(AssetLoader.fireButtonDisabled, AssetLoader.fireButtonDisabled, false);
                        }
                    }

                } else if (btnTapped && btn.getName().equals("flip")) {
                    myWorld.pause();
                    if (!myWorld.getScroller().flip())
                        btn.setTextures(AssetLoader.flipButtonLeft, AssetLoader.flipButtonLeft, true);
                    else
                        btn.setTextures(AssetLoader.flipButtonRight, AssetLoader.flipButtonRight, true);
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            myBird.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }

        if (keycode == Keys.B) {

            if (myWorld.isRunning() && !myBird.isOutOfAmmo()) {
                myBird.shoot();
                AssetLoader.shoot.play();
            }

        }

        if (keycode == Keys.F) {

            if (myWorld.isRunning()) {
                myWorld.pause();
                myWorld.getScroller().flip();
            }

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<GameButton> getMenuButtons() {
        return menuButtons;
    }

    public List<GameButton> getInGameButtons() {
        return inGameButtons;
    }

    public List<GameButton> getGameOverButtons() {
        return gameOverButtons;
    }

    public List<GameButton> getControlsScreenButtons() {
        return controlsScreenButtons;
    }
}
