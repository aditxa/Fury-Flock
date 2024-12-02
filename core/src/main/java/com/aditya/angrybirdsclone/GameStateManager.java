package com.aditya.angrybirdsclone;

import java.io.*;

public class GameStateManager {
    private static final String FILE_NAME = "gameState.dat";

    public static void saveGameState(GameState gameState) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(gameState);
            System.out.println("Game state saved successfully: " + gameState);
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
        }
    }

    public static GameState loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            GameState gameState = (GameState) ois.readObject();
            System.out.println("Game state loaded successfully: " + gameState);
            return gameState;
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting with a new game state.");
            return new GameState(1, 0); // Default state
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            return new GameState(1, 0); // Default state
        }
    }
}
