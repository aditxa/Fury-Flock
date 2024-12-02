package com.aditya.angrybirdsclone;

import java.io.Serializable;
import java.util.*;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Integer, Boolean> levelCompletion;
    private int currentLevel;
    private int highScore;
    private String playerName;

    // Constructor
    public GameState(int currentLevel, int highScore) {
        this.currentLevel = currentLevel;
        this.highScore = highScore;
        this.playerName = "Player"; // Default name
        this.levelCompletion = new HashMap<>();
    }

    // Getters and Setters
    public HashMap<Integer, Boolean> getLevelCompletion() {
        return levelCompletion;
    }

    public void setLevelCompletion(HashMap<Integer, Boolean> levelCompletion) {
        this.levelCompletion = levelCompletion;
    }
    // Getters and Setters
    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "GameState { Level=" + currentLevel + ", HighScore=" + highScore + ", Player=" + playerName + " }";
    }
}
