package com.aditya.angrybirdsclone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.aditya.angrybirdsclone.screens.HomeScreen;
import com.aditya.angrybirdsclone.screens.LevelsScreen;

public class Main extends Game {
    public SpriteBatch batch;
    private LevelsScreen levelsScreen;
    private Music backgroundMusic; // Music instance for the background music

    @Override
    public void create() {
        batch = new SpriteBatch();
//
//        // Load and play background music
//        backgroundMusic = com.badlogic.gdx.Gdx.audio.newMusic(com.badlogic.gdx.Gdx.files.internal("music.mp3"));
//        backgroundMusic.setLooping(true); // Set music to loop
//        backgroundMusic.setVolume(0.5f); // Adjust volume (0.0f to 1.0f)
//        backgroundMusic.play(); // Start playing the music

        // Load the unlocked level from the saved state
        GameState loadedState = GameStateManager.loadGameState();
        int savedLevel = 1; // Default level if loading fails
        if (loadedState != null) {
            savedLevel = loadedState.getCurrentLevel(); // Get the saved level from the loaded state
        }

        // Pass the saved state to LevelsScreen
        levelsScreen = new LevelsScreen(this);
        levelsScreen.setUnlockedLevel(savedLevel);

        this.setScreen(new HomeScreen(this)); // Start at the HomeScreen
    }

    public void saveGameState() {
        GameState gameState = new GameState(levelsScreen.getUnlockedLevel(), 0); // Create the game state with the unlocked level and high score
        GameStateManager.saveGameState(gameState); // Save the game state using the GameStateManager
    }

    public void loadGameState() {
        GameState gameState = GameStateManager.loadGameState(); // Load the GameState object

        if (gameState != null) {
            levelsScreen.setUnlockedLevel(gameState.getCurrentLevel()); // If the game state was loaded successfully, set the unlocked level
        } else {
            levelsScreen.setUnlockedLevel(1); // Default level
        }
    }

    public LevelsScreen getLevelsScreen() {
        return levelsScreen;
    }

    @Override
    public void dispose() {
        batch.dispose();
        levelsScreen.dispose(); // Ensure resources are disposed

//        if (backgroundMusic != null) {
//            backgroundMusic.stop(); // Stop the music
//            // Release resources
//        }
    }
}
