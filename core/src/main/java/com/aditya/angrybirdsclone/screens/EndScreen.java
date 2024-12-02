package com.aditya.angrybirdsclone.screens;

import com.aditya.angrybirdsclone.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen implements Screen {
    private Main game;
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private Texture background;
    private String message;
    private boolean levelCompleted;
    private int currentLevel;

    public EndScreen(Main game, String message, int currentLevel, boolean levelCompleted) {
        this.game = game;
        this.message = message;
        this.levelCompleted = levelCompleted;
        this.currentLevel = currentLevel;

        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        background = new Texture("endscreen.png"); // Make sure you have this image in your assets

        setupUI();
    }

private void setupUI() {
    // Load button textures
    Texture retryTexture = new Texture("retry.png");
    Texture exitTexture = new Texture("exit2.png");
    Texture nextTexture = new Texture("next.png");

    // Create ImageButtons
    ImageButton retryButton = new ImageButton(new TextureRegionDrawable(retryTexture));
    ImageButton exitButton = new ImageButton(new TextureRegionDrawable(exitTexture));
    ImageButton nextButton = new ImageButton(new TextureRegionDrawable(nextTexture));

    // Assign positions for buttons
    retryButton.setPosition(160, 150); // Example coordinates for Retry button
    exitButton.setPosition(190, 240); // Example coordinates for Exit button
    nextButton.setPosition(220, 90); // Example coordinates for Next button

    // Add buttons to the stage
    stage.addActor(retryButton);
    stage.addActor(exitButton);
    if (levelCompleted && currentLevel != 3) {
        stage.addActor(nextButton);
    }

    // Add listeners for buttons
    retryButton.addListener(event -> {
        if (retryButton.isPressed()) {
            game.setScreen(new GameScreen(game, currentLevel)); // Restart the current level
            return true;
        }
        return false;
    });

    exitButton.addListener(event -> {
        if (exitButton.isPressed()) {
            if (levelCompleted && currentLevel < 3) {
                game.getLevelsScreen().unlockNextLevel(currentLevel + 1); // Unlock next level
            }
            game.setScreen(new LevelsScreen(game)); // Go to levels screen
            return true;
        }
        return false;
    });

    nextButton.addListener(event -> {
        if (nextButton.isPressed()) {
            game.getLevelsScreen().unlockNextLevel(currentLevel + 1); // Unlock next level
            game.setScreen(new GameScreen(game, currentLevel + 1)); // Load the next level
            return true;
        }
        return false;
    });
}

    private Texture createBorderTexture(int borderWidth, Color borderColor) {
        Pixmap pixmap = new Pixmap(200, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(borderColor);
        pixmap.fill();

        // Draw border
        pixmap.setColor(Color.ORANGE);
        pixmap.drawRectangle(0, 0, 200, 100);
        pixmap.setColor(borderColor);
        pixmap.drawRectangle(borderWidth, borderWidth, 200 - 2 * borderWidth, 100 - 2 * borderWidth);

        Texture borderTexture = new Texture(pixmap);
        pixmap.dispose();
        return borderTexture;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
        batch.dispose();
    }
}
