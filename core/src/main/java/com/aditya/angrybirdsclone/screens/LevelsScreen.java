
package com.aditya.angrybirdsclone.screens;

import com.aditya.angrybirdsclone.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelsScreen implements Screen {
    private Main game;
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private int unlockedLevel;
    private Texture background;
    private Color terracottaColor = new Color(0.833f, 0.452f, 0.258f, 1); // Define terracotta color

    public LevelsScreen(Main game) {
        this.game = game;
        this.unlockedLevel = 1;  // Initially only level 1 is unlocked
        batch = new SpriteBatch();
        background = new Texture("level.png"); // Background for Levels Screen
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // Set input processor for the stage

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Create the table and add level buttons
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        createLevelButtons(table);

        // Add Back button to navigate to HomeScreen
        createBackButton();
    }

    private void createLevelButtons(Table table) {
        // Changed to 2 levels
        for (int i = 1; i <= 3; i++) {
            TextButton levelButton = new TextButton("Level " + i, skin);

            if (i <= unlockedLevel) {
                levelButton.setColor(Color.ORANGE);
            } else {
                levelButton.setColor(terracottaColor);
                levelButton.setDisabled(true);
            }

            table.add(levelButton).pad(10).fillX().uniformX();

            final int currentLevel = i;
            levelButton.addListener(event -> {
                if (event.isHandled() && currentLevel <= unlockedLevel) {
                    game.setScreen(new GameScreen(game, currentLevel));
                    return true;
                }
                return false;
            });

            if (i % 2 == 0) table.row();
        }
    }

    private void createBackButton() {
        // Load back button texture
        Texture backTexture = new Texture("back.png"); // Ensure this texture is in the assets folder
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backTexture));

        // Position the back button in the top-right corner
        backButton.setPosition(Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 80);
        backButton.setSize(64, 64); // Adjust size if needed

        // Add listener to handle back button click
        backButton.addListener(event -> {
            if (backButton.isPressed()) {
                game.setScreen(new HomeScreen(game)); // Navigate to HomeScreen
            }
            return true;
        });

        // Add the back button to the stage
        stage.addActor(backButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); // Reset input processor to stage when shown
    }

    @Override
    public void render(float delta) {
        batch.begin();
        // Draw the background texture, scaled to fit the window
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
        batch.dispose();
        background.dispose();
        stage.dispose();
    }

    // Call this method after completing a level to unlock the next level
    public void unlockNextLevel(int levelToUnlock) {
        if (levelToUnlock > unlockedLevel && levelToUnlock <= 3) {
            unlockedLevel = levelToUnlock;
            updateLevelButtons(); // Refresh buttons
        }
    }

    // Update the buttons' enabled states based on unlocked levels
    private void updateLevelButtons() {
        // Iterate over all actors in the stage
        for (var actor : stage.getActors()) {
            if (actor instanceof TextButton) { // Check if the actor is a TextButton
                TextButton button = (TextButton) actor;
                int levelNumber = Integer.parseInt(button.getText().toString().replace("Level ", ""));

                if (levelNumber <= unlockedLevel) {
                    button.setColor(Color.ORANGE); // Set color for unlocked levels
                    button.setDisabled(false); // Enable the button
                } else {
                    button.setColor(terracottaColor); // Set color for locked levels
                    button.setDisabled(true); // Disable the button
                }
            }
        }
    }
}
