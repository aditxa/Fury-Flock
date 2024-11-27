package com.aditya.angrybirdsclone.screens;

import com.aditya.angrybirdsclone.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseScreen implements Screen {
    private Main game;
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private Texture background;
    private GameScreen gameScreen;

    public PauseScreen(Main game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        background = new Texture("pausescreen.png");

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create border texture
        Texture borderTexture = createBorderTexture(2, Color.WHITE);

        TextButton resumeButton = new TextButton("Resume", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Set border drawable
        resumeButton.getStyle().up = new TextureRegionDrawable(borderTexture);
        exitButton.getStyle().up = new TextureRegionDrawable(borderTexture);

        // Set text color for normal and hover states
        resumeButton.getStyle().fontColor = Color.BLACK;
        exitButton.getStyle().fontColor = Color.BLACK;
        resumeButton.getStyle().overFontColor = Color.WHITE;
        exitButton.getStyle().overFontColor = Color.WHITE;

        // Resume button listener
        resumeButton.addListener(event -> {
            if (resumeButton.isPressed()) {
                gameScreen.resumeGame();
                game.setScreen(gameScreen);
                return true;
            }
            return false;
        });

        // Exit button listener
        exitButton.addListener(event -> {
            if (exitButton.isPressed()) {
                game.setScreen(game.getLevelsScreen());
                return true;
            }
            return false;
        });

        // Add Resume and Exit buttons
        table.add(resumeButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton).fillX().uniformX();

        // Add temporary "Next Level" button if level is completed
        boolean levelCompleted = true; // Set this flag based on your game's level completion logic
        if (levelCompleted) {
            TextButton nextLevelButton = new TextButton("Next Level", skin);
            nextLevelButton.getStyle().up = new TextureRegionDrawable(borderTexture);
            nextLevelButton.getStyle().fontColor = Color.BLACK;
            nextLevelButton.getStyle().overFontColor = Color.WHITE;

            // Next Level button listener
            nextLevelButton.addListener(event -> {
                if (nextLevelButton.isPressed()) {
                    game.setScreen(new GameScreen(game, 2));
                    return true;
                }
                return false;
            });

            // Add Next Level button to the table
            table.row().pad(10);
            table.add(nextLevelButton).fillX().uniformX();
        }
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
        pixmap.dispose(); // Dispose of pixmap after creating texture
        return borderTexture;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        batch.dispose();
    }
}