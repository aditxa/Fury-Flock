//package com.aditya.angrybirdsclone;
//
//import com.aditya.angrybirdsclone.screens.GameScreen;
//import org.junit.Before;
//import org.junit.Test;
////import static org.junit.jupiter.*;
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.testng.AssertJUnit.assertTrue;
//
//public class GameScreenTest {
//    private GameScreen gameScreen;
//    private HashMap<Integer, Boolean> levelCompletion;
//
//    @Before
//    public void setUp() {
//        levelCompletion = new HashMap<>();
//        levelCompletion.put(1, true); // Level 1 completed
//        levelCompletion.put(2, false); // Level 2 not yet unlocked
//        gameScreen = new GameScreen(null, 1); // Initialize GameScreen with level 1
//    }
//
//    @Test
//    public void testSaveAndLoadGameState() {
//        GameState initialState = new GameState(1, 100);
//        GameStateManager.saveGameState(initialState);
//
//        GameState loadedState = GameStateManager.loadGameState();
//        assertNotNull(loadedState);
//        assertEquals(initialState.getCurrentLevel(), loadedState.getCurrentLevel());
//        assertEquals(initialState.getHighScore(), loadedState.getHighScore());
//    }
//
//    @Test
//    public void testUnlockNextLevel() {
//        gameScreen.levelCompletion = levelCompletion; // Simulate existing level completion
//        gameScreen.handleLevelComplete();
//
//        assertTrue("Next level should be unlocked", gameScreen.levelCompletion.get(2));
//    }
//
//    @Test
//    public void testLevelCompletion() {
//        gameScreen.checkLevelCompletion(); // Simulate completing level 1
//
//        assertTrue("Level 1 should be marked complete", gameScreen.levelCompletion.get(1));
//    }
//}
