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

public class LevelsScreen implements Screen  {
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
            Texture levelTexture = new Texture("level" + i + ".png"); // Load level PNG for each level


            // Create an ImageButton for each level
            ImageButton levelButton = new ImageButton(new TextureRegionDrawable(levelTexture));

            // Check if the level is unlocked or locked
            if (i <= unlockedLevel) {
                levelButton.getStyle().imageUp = new TextureRegionDrawable(levelTexture); // Set unlocked level image
            } else {
                levelButton.getStyle().imageUp = new TextureRegionDrawable(levelTexture); // Set level image for locked
                levelButton.addListener(event -> {
                    if (event.isHandled()) {
                        // For locked levels, show no action or disable interaction
                        return true;
                    }
                    return false;
                });
            }

            // Add a locked overlay if the level is locked


            // Set up button layout
            table.add(levelButton).pad(10).width(100).height(100).uniformX();

            // Set listener for level button clicks
            final int currentLevel = i;
            levelButton.addListener(event -> {
                if (event.isHandled() && currentLevel <= unlockedLevel) {
                    game.setScreen(new GameScreen(game, currentLevel)); // Go to the selected level
                    return true;
                }
                return false;
            });

            if (i % 3 == 0) table.row(); // Move to the next row after every two buttons
        }
    }


    private void createRandomButton(Table table) {
        // Load the texture for the random button
        Texture randomTexture = new Texture("random.png");

        // Create an ImageButton using the randomTexture
        ImageButton randomButton = new ImageButton(new TextureRegionDrawable(randomTexture));

        // Add listener to start a random level
        randomButton.addListener(event -> {
            if (event.isHandled()) {
                game.setScreen(new GameScreen(game, -1)); // -1 for random level
                return true;
            }
            return false;
        });

        // Add to the table
        table.row(); // Move to a new row
        table.add(randomButton).pad(10).width(300).height(100).colspan(3); // Center across two columns
    }


    public int getUnlockedLevel() {
        return unlockedLevel;
    }

    public void setUnlockedLevel(int level) {
        if (level > 0 && level <= 3) { // Ensure level is within valid bounds
            unlockedLevel = level;
            updateLevelButtons(); // Update buttons if needed
        } else {
            System.out.println("Invalid level: " + level);
        }
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

    public boolean isLevelUnlocked(int level) {
        return level <= unlockedLevel;
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
