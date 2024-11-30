package com.aditya.angrybirdsclone;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import com.badlogic.gdx.files.FileHandle;
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private int level;
    private String playerName;
    // Ensure compatibility across versions

    private int currentLevel;
    private int highScore;

    // Constructor
    public GameState(int currentLevel, int highScore) {
        this.level = 1;
        this.playerName = "player";
        this.currentLevel = 1;
        this.highScore = highScore;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "GameState [level=" + level + "]";
    }

}
