package com.aditya.angrybirdsclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;

import static java.awt.Event.SAVE_FILE;

public class GameStateManager {
    private static final String FILE_NAME = "gameState.txt";



    // Save the current unlocked level to the file
    public static void saveGameState(GameState gameState) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gameState.txt"))) {
            oos.writeObject(gameState);
            System.out.println("Game state saved: " + gameState);
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }








    public static GameState loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gameState.txt"))) {
            GameState gameState = (GameState) ois.readObject();
            System.out.println("Loaded game state: " + gameState);
            return gameState;
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting new game.");
            return new GameState(1, 0); // Default state
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game state: " + e.getMessage());
            return new GameState(1, 0); // Default state
        }
    }




}
