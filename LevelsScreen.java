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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class LevelsScreen implements Screen {
    private Main game;
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private int unlockedLevel;
    private Texture background;
    private Color terracottaColor = new Color(0.833f, 0.452f, 0.258f, 1); // Define terracotta color
    private Random random;

    public LevelsScreen(Main game) {
        this.game = game;
        this.unlockedLevel = 1;  // Initially only level 1 is unlocked
        batch = new SpriteBatch();
        background = new Texture("level.png"); // Background for Levels Screen
        stage = new Stage(new ScreenViewport());
        random = new Random();

        Gdx.input.setInputProcessor(stage); // Set input processor for the stage

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Create the table and add level buttons
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        createLevelButtons(table);

        // Add the Random level button
        createRandomButton(table);

        // Add Back button to navigate to HomeScreen
        createBackButton();
    }

    private void createLevelButtons(Table table) {

        for (int i = 1; i <= 3; i++) {
            TextButton levelButton = new TextButton("Level " + i, skin);
            levelButton.setColor(terracottaColor);
            // Style the button
            levelButton.getLabel().setFontScale(2f); // Increase font size
            if (i <= unlockedLevel) {
                System.out.println(i);
                levelButton.setColor(Color.ORANGE);
            } else {
//                levelButton.setColor(terracottaColor);
//                levelButton.setDisabled(true);
            }

            table.add(levelButton).pad(20).width(300).height(100).uniformX();

            final int currentLevel = i;
            levelButton.addListener(event -> {
                if (event.isHandled() && currentLevel <= unlockedLevel) {
                    game.setScreen(new GameScreen(game, currentLevel));
                    return true;
                }
                return false;
            });

            if (i % 2 == 0) table.row(); // Move to the next row after 2 buttons
        }
    }

    private void createRandomButton(Table table) {
        TextButton randomButton = new TextButton("Random", skin);

        // Style the button
        randomButton.getLabel().setFontScale(2f); // Increase font size
        randomButton.setColor(Color.CYAN);

        // Add listener to pick a random level and start it
        randomButton.addListener(event -> {
            if (event.isHandled()) {
                int randomLevel = random.nextInt(unlockedLevel) + 1; // Choose a random unlocked level (1 to unlockedLevel)
                game.setScreen(new GameScreen(game, randomLevel));
                return true;
            }
            return false;
        });

        // Add to the table
        table.row(); // Move to a new row
        table.add(randomButton).pad(20).width(300).height(100).colspan(2); // Center across two columns
    }

    private void createBackButton() {
        // Load back button texture
        Texture backTexture = new Texture("back.png"); // Ensure this texture is in the assets folder
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backTexture));

        // Position the back button in the top-right corner
        backButton.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        backButton.setSize(80, 80); // Adjust size

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
                String buttonText = button.getText().toString();
                if (buttonText.startsWith("Level")) { // Ensure it's a level button
                    int levelNumber = Integer.parseInt(buttonText.replace("Level ", ""));

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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        stage.dispose();
    }
}
