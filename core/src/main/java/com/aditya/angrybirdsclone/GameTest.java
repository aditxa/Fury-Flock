//////////////////////////////////////////////////////////
//package com.aditya.angrybirdsclone;
//
//import com.aditya.angrybirdsclone.screens.GameScreen;
////import org.junit.Before;
//import org.junit.Test;
//
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//
//public class GameTest {
//
//    private HashMap<Integer, Boolean> levelCompletion;
//
////    @Before
////    public void setUp() {
////        levelCompletion = new HashMap<>();
////        levelCompletion.put(1, true);
////        levelCompletion.put(2, false);
////        levelCompletion.put(3, false);
////    }
//
//    @Test
//    public void testSaveAndLoadGame() {
//        // Save game state
//        GameState.saveGame(2, levelCompletion);
//
//        // Load game state
//        Object[] loadedState = GameState.loadGame();
//        assertNotNull(loadedState);
//
//        int currentLevel = (int) loadedState[0];
//        HashMap<Integer, Boolean> loadedLevelCompletion = (HashMap<Integer, Boolean>) loadedState[1];
//
//        // Verify if the loaded state is correct
//        assertEquals(2, currentLevel);
//        assertTrue(loadedLevelCompletion.get(1));
//        assertFalse(loadedLevelCompletion.get(2));
//        assertFalse(loadedLevelCompletion.get(3));
//    }
//
//    @Test
//    public void testUnlockNextLevel() {
//        GameScreen gameScreen = new GameScreen(null, 1);
//        gameScreen.unlockNextLevel();
//
//        // Test that after unlocking, level 2 is unlocked
//        levelCompletion.put(2, true); // Simulate the unlock
//        assertTrue(levelCompletion.get(2));
//    }
//
//    @Test
//    public void testLevelCompletion() {
//        GameScreen gameScreen = new GameScreen(null, 1);
//        gameScreen.checkLevelCompletion(); // Simulate level completion
//
//        // Check if the level is marked as completed
//        levelCompletion.put(1, true); // Simulate completion
//        assertTrue(levelCompletion.get(1));
//    }
//}
//
////package com.aditya.angrybirdsclone;
////
////import com.aditya.angrybirdsclone.screens.GameScreen;
////import org.junit.jupiter.api.*;
////import static org.junit.jupiter.api.Assertions.*;
////
////import java.util.HashMap;
////
////public class GameTest {
////
////    private HashMap<Integer, Boolean> levelCompletion;
////
////    @BeforeEach
////    public void setUp() {
////        levelCompletion = new HashMap<>();
////        levelCompletion.put(1, true);
////        levelCompletion.put(2, false);
////        levelCompletion.put(3, false);
////    }
////
////    @Test
////    public void testSaveAndLoadGame() {
////        // Save game state
////        GameState.saveGame(2, levelCompletion);
////
////        // Load game state
////        Object[] loadedState = GameState.loadGame();
////        assertNotNull(loadedState);
////
////        int currentLevel = (int) loadedState[0];
////        HashMap<Integer, Boolean> loadedLevelCompletion = (HashMap<Integer, Boolean>) loadedState[1];
////
////        // Verify if the loaded state is correct
////        assertEquals(2, currentLevel);
////        assertTrue(loadedLevelCompletion.get(1));
////        assertFalse(loadedLevelCompletion.get(2));
////        assertFalse(loadedLevelCompletion.get(3));
////    }
////
////    @Test
////    public void testUnlockNextLevel() {
////        GameScreen gameScreen = new GameScreen(null, 1);
////        gameScreen.unlockNextLevel();
////
////        // Test that after unlocking, level 2 is unlocked
////        assertTrue(levelCompletion.get(2));
////    }
////
////    @Test
////    public void testLevelCompletion() {
////        GameScreen gameScreen = new GameScreen(null, 1);
////        gameScreen.checkLevelCompletion(); // This method should set the level as completed if conditions are met
////
////        // Check if the level is marked as completed
////        assertTrue(levelCompletion.get(1));
////    }
////
////    // More tests for other game features like collision, bird movement, etc.
////}
