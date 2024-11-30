package com.aditya.angrybirdsclone.screens;

import com.aditya.angrybirdsclone.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HomeScreen implements Screen {
    private Main game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture background;

    public HomeScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture("homescreen.png"); // Your home screen background

        // Load button textures
        Texture startTexture = new Texture("start.png");
        Texture exitTexture = new Texture("exit1.png");

        // Create ImageButtons
        ImageButton startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(startTexture)));
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitTexture)));

        // Handle Start button click
        startButton.addListener(event -> {
            if (event.isHandled()) {
                game.setScreen(new LevelsScreen(game)); // Go to levels screen
                return true;
            }
            return false;
        });

        // Handle Exit button click
        exitButton.addListener(event -> {
            if (event.isHandled()) {
                Gdx.app.exit(); // Exit the application
                return true;
            }
            return false;
        });

        // Add buttons to a table
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add(startButton).size(300, 200);
        table.row();
        table.add(exitButton).size(100, 100);
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
