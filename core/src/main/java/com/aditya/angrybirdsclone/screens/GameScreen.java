//package com.aditya.angrybirdsclone.screens;
//
//import com.aditya.angrybirdsclone.Main;
//import com.aditya.angrybirdsclone.entities.*;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//
//public class GameScreen implements Screen {
//    private Main game;
//    private SpriteBatch batch;
//    private Texture background;
//    private Stage stage;
//    private boolean isPaused = false;
//    private ImageButton pauseButton;
//    private int currentLevel;
//
//    private Bird currentBird;
//    private Array<Bird> availableBirds;
//    private Image catapult;
//    private Array<Pig> pigs;
//    private Array<Block> blocks;
//
//    private Vector2 catapultPos;
//    private Vector2 dragStart;
//    private boolean isDragging = false;
//    private ShapeRenderer shapeRenderer;
//
//    private static final float MAX_DRAG_DISTANCE = 100f;
//    private static final float LAUNCH_SPEED_FACTOR = 5f;
//    private static final float GRAVITY = -9.8f;
//
//    private Array<Vector2> trajectoryPoints;
//
//    public GameScreen(Main game, int level) {
//        this.game = game;
//        this.currentLevel = level;
//        batch = new SpriteBatch();
//        background = new Texture("game.png");
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//        shapeRenderer = new ShapeRenderer();
//        trajectoryPoints = new Array<>();
//
//        setupGameElements();
//    }
//
//    private void setupGameElements() {
//        // Setup catapult
//        catapult = new Image(new Texture("catapult.png"));
//        catapult.setPosition(50, 50);
//        catapultPos = new Vector2(catapult.getX() + catapult.getWidth() / 2,
//            catapult.getY() + catapult.getHeight() - 20);
//
//        // Initialize bird arrays
//        availableBirds = new Array<>();
//        setupBirdsForLevel();
//
//        // Setup pigs and blocks arrays
//        pigs = new Array<>();
//        blocks = new Array<>();
//
//        // Setup level
//        if (currentLevel == 1) {
//            setupLevel1();
//        } else if (currentLevel == 2) {
//            setupLevel2();
//        }
//
//        // Setup pause button
//        Texture pauseTexture = new Texture("pause.png");
//        pauseButton = new ImageButton(new TextureRegionDrawable(pauseTexture));
//        pauseButton.setPosition(Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 80);
//        pauseButton.setSize(64, 64);
//        pauseButton.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                isPaused = true;
//                game.setScreen(new PauseScreen(game, GameScreen.this));
//                return true;
//            }
//        });
//
//        // Add static elements to stage
//        stage.addActor(catapult);
//        stage.addActor(pauseButton);
//
//        setupBirdInputListener();
//    }
//
//    private void setupBirdsForLevel() {
//        // Clear any existing birds
//        availableBirds.clear();
//
//        // Add birds based on level
//        if (currentLevel == 1) {
//            availableBirds.add(new RedBird(catapultPos.x, catapultPos.y));
//            availableBirds.add(new RedBird(catapultPos.x, catapultPos.y));
//            availableBirds.add(new YellowBird(catapultPos.x, catapultPos.y));
//        } else if (currentLevel == 2) {
//            availableBirds.add(new YellowBird(catapultPos.x, catapultPos.y));
//            availableBirds.add(new YellowBird(catapultPos.x, catapultPos.y));
//        }
//
//        // Set up the first bird
//        if (availableBirds.size > 0) {
//            currentBird = availableBirds.first();
//            stage.addActor(currentBird);
//            resetBird();
//        }
//    }
//
//    private void setupLevel1() {
//        // Setup basic pig
//        BasicPig pig = new BasicPig(450, 300);
//        pigs.add(pig);
//        stage.addActor(pig);
//
//        // Setup wood blocks
//        for (int i = 0; i < 3; i++) {
//            WoodBlock block = new WoodBlock(470, 57 + i * 78);
//            blocks.add(block);
//            stage.addActor(block);
//        }
//    }
//
//    private void setupLevel2() {
//        // First tower with stone blocks
//        for (int i = 0; i < 3; i++) {
//            StoneBlock block = new StoneBlock(400, 57 + i * 78);
//            blocks.add(block);
//            stage.addActor(block);
//        }
//
//        ArmoredPig pig1 = new ArmoredPig(380, 300);
//        pigs.add(pig1);
//        stage.addActor(pig1);
//
//        // Second tower with wood blocks
//        for (int i = 0; i < 4; i++) {
//            WoodBlock block = new WoodBlock(550, 57 + i * 78);
//            blocks.add(block);
//            stage.addActor(block);
//        }
//
//        BasicPig pig2 = new BasicPig(530, 378);
//        pigs.add(pig2);
//        stage.addActor(pig2);
//    }
//
//    private void setupBirdInputListener() {
//        currentBird.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                if (!currentBird.isFlying()) {
//                    isDragging = true;
//                    dragStart = new Vector2(x, y);
//                }
//                return true;
//            }
//
//            @Override
//            public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                if (isDragging) {
//                    Vector2 dragCurrent = new Vector2(x, y);
//                    Vector2 dragVector = dragStart.cpy().sub(dragCurrent);
//                    if (dragVector.len() > MAX_DRAG_DISTANCE) {
//                        dragVector.setLength(MAX_DRAG_DISTANCE);
//                    }
//                    Vector2 newPos = new Vector2(catapultPos.x - dragVector.x, catapultPos.y - dragVector.y);
//                    currentBird.setPosition(newPos.x - currentBird.getWidth() / 2,
//                        newPos.y - currentBird.getHeight() / 2);
//                    calculateTrajectory(dragVector);
//                }
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (isDragging) {
//                    isDragging = false;
//                    Vector2 dragVector = new Vector2(
//                        catapultPos.x - (currentBird.getX() + currentBird.getWidth() / 2),
//                        catapultPos.y - (currentBird.getY() + currentBird.getHeight() / 2)
//                    );
//                    currentBird.setVelocity(dragVector.scl(LAUNCH_SPEED_FACTOR));
//                }
//            }
//        });
//    }
//
//    private void resetBird() {
//        currentBird.reset();
//        setupBirdInputListener();
//    }
//
//    private void nextBird() {
//        availableBirds.removeIndex(0);  // Remove the current bird
//        if (availableBirds.size > 0) {
//            currentBird = availableBirds.first();
//            stage.addActor(currentBird);
//            resetBird();
//        } else {
//            // No more birds available
//            game.setScreen(new EndScreen(game, "Level Failed!", currentLevel, false));
//        }
//    }
//
//    private void calculateTrajectory(Vector2 dragVector) {
//        trajectoryPoints.clear();
//        Vector2 velocityForTrajectory = dragVector.cpy().scl(LAUNCH_SPEED_FACTOR);
//        Vector2 pos = new Vector2(
//            currentBird.getX() + currentBird.getWidth() / 2,
//            currentBird.getY() + currentBird.getHeight() / 2
//        );
//
//        for (float t = 0; t < 2; t += 0.1f) {
//            float x = pos.x + velocityForTrajectory.x * t;
//            float y = pos.y + velocityForTrajectory.y * t + 0.5f * GRAVITY * t * t;
//            trajectoryPoints.add(new Vector2(x, y));
//        }
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.begin();
//        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.end();
//
//        if (!isPaused) {
//            updateGameState(delta);
//            checkCollisions();
//            stage.act(delta);
//        }
//
//        stage.draw();
//
//        if (isDragging) {
//            renderTrajectory();
//        }
//    }
//
//    private void updateGameState(float delta) {
//        if (currentBird.isFlying()) {
//            Vector2 velocity = currentBird.getVelocity();
//            velocity.y += GRAVITY * delta;
//            currentBird.setVelocity(velocity);
//            currentBird.setPosition(
//                currentBird.getX() + velocity.x * delta,
//                currentBird.getY() + velocity.y * delta
//            );
//
//            // Check if bird is out of bounds
//            if (currentBird.getY() < 0 || currentBird.getX() > Gdx.graphics.getWidth()) {
//                currentBird.remove();
//                nextBird();
//            }
//        }
//    }
//
//    private void checkCollisions() {
//        if (!currentBird.isFlying()) return;
//
//        // Check collisions with pigs
//        for (Pig pig : new Array.ArrayIterator<>(pigs)) {
//            if (currentBird.getBounds().overlaps(pig.getBounds())) {
//                currentBird.onCollision(pig);
//                pig.onCollision(currentBird);
//
//                if (pig.isDestroyed()) {
//                    pig.remove();
//                    pigs.removeValue(pig, true);
//                }
//
//                if (currentBird.isDestroyed()) {
//                    currentBird.remove();
//                    nextBird();
//                }
//
//                // Check if all pigs are destroyed
//                if (pigs.size == 0) {
//                    game.setScreen(new EndScreen(game, "Level Complete!", currentLevel, true));
//                }
//                break;
//            }
//        }
//
//        // Check collisions with blocks
//        for (Block block : new Array.ArrayIterator<>(blocks)) {
//            if (currentBird.getBounds().overlaps(block.getBounds())) {
//                currentBird.onCollision(block);
//                block.onCollision(currentBird);
//
//                if (block.isDestroyed()) {
//                    block.remove();
//                    blocks.removeValue(block, true);
//                }
//
//                if (currentBird.isDestroyed()) {
//                    currentBird.remove();
//                    nextBird();
//                }
//                break;
//            }
//        }
//    }
//
//    private void renderTrajectory() {
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(1, 1, 1, 0.5f);
//        for (int i = 1; i < trajectoryPoints.size; i++) {
//            Vector2 point1 = trajectoryPoints.get(i - 1);
//            Vector2 point2 = trajectoryPoints.get(i);
//            shapeRenderer.line(point1, point2);
//        }
//        shapeRenderer.end();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//    }
//
//    @Override
//    public void show() {}
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        background.dispose();
//        stage.dispose();
//        shapeRenderer.dispose();
//    }
//
//    public void resumeGame() {
//        isPaused = false;
//        Gdx.input.setInputProcessor(stage);
//    }
//}
package com.aditya.angrybirdsclone.screens;

import com.aditya.angrybirdsclone.Main;
import com.aditya.angrybirdsclone.entities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private boolean isPaused = false;
    private ImageButton pauseButton;
    private int currentLevel;

    private GameObject bird;
    private GameObject catapult;
    private Array<GameObject> pigs;
    private Array<GameObject> blocks;
    private Array<Bird> availableBirds;
    private int currentBirdIndex = 0;

    private Vector2 catapultPos;
    private Vector2 dragStart;
    private boolean isDragging = false;
    private boolean isFlying = false;
    private ShapeRenderer shapeRenderer;

    private static final float MAX_DRAG_DISTANCE = 100f;
    private static final float LAUNCH_SPEED_FACTOR = 5f;
    private static final float GRAVITY = -9.8f;

    private Array<Vector2> trajectoryPoints;

    public GameScreen(Main game, int level) {
        this.game = game;
        this.currentLevel = level;
        batch = new SpriteBatch();
        background = new Texture("game.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        shapeRenderer = new ShapeRenderer();
        trajectoryPoints = new Array<>();

        pigs = new Array<>();
        blocks = new Array<>();
        availableBirds = new Array<>();

        setupGameElements();
    }

    private void setupGameElements() {
        // Setup catapult
        catapult = new GameObject(new Texture("catapult.png"), 50, 50, 64, 64) {
            @Override
            public void onCollision(GameObject other) {}
        };
        catapultPos = new Vector2(catapult.getX() + catapult.getWidth() / 2,
            catapult.getY() + catapult.getHeight() - 20);

        // Setup birds based on level
        setupBirds();

        // Setup level
        if (currentLevel == 1) {
            setupLevel1();
        } else if (currentLevel == 2) {
            setupLevel2();
        }

        // Setup pause button
        Texture pauseTexture = new Texture("pause.png");
        pauseButton = new ImageButton(new TextureRegionDrawable(pauseTexture));
        pauseButton.setPosition(Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 80);
        pauseButton.setSize(64, 64);
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPaused = true;
                game.setScreen(new PauseScreen(game, GameScreen.this));
                return true;
            }
        });

        // Add elements to stage
        stage.addActor(catapult);
        stage.addActor(bird);
        stage.addActor(pauseButton);

        setupBirdInput();
    }

    private void setupBirds() {
        // Clear existing birds
        availableBirds.clear();

        // Add different types of birds based on level
        if (currentLevel == 1) {
            availableBirds.add(new RedBird(catapultPos.x, catapultPos.y));
            availableBirds.add(new RedBird(catapultPos.x, catapultPos.y));
            availableBirds.add(new YellowBird(catapultPos.x, catapultPos.y));
        } else if (currentLevel == 2) {
            availableBirds.add(new YellowBird(catapultPos.x, catapultPos.y));
            availableBirds.add(new YellowBird(catapultPos.x, catapultPos.y));
            availableBirds.add(new RedBird(catapultPos.x, catapultPos.y));
        }

        currentBirdIndex = 0;
        bird = availableBirds.get(currentBirdIndex);
        resetBird();
    }

    private void setupLevel1() {
        // Setup basic pig
        BasicPig pig = new BasicPig(450, 300);
        pigs.add(pig);
        stage.addActor(pig);

        // Setup wood blocks
        for (int i = 0; i < 3; i++) {
            WoodBlock block = new WoodBlock(470, 57 + i * 78);
            blocks.add(block);
            stage.addActor(block);
        }
    }

    private void setupLevel2() {
        // First tower with stone blocks
        for (int i = 0; i < 3; i++) {
            StoneBlock block = new StoneBlock(400, 57 + i * 78);
            blocks.add(block);
            stage.addActor(block);
        }

        ArmoredPig pig1 = new ArmoredPig(380, 300);
        pigs.add(pig1);
        stage.addActor(pig1);

        // Second tower with wood blocks
        for (int i = 0; i < 4; i++) {
            WoodBlock block = new WoodBlock(550, 57 + i * 78);
            blocks.add(block);
            stage.addActor(block);
        }

        ArmoredPig pig2 = new ArmoredPig(530, 378);
        pigs.add(pig2);
        stage.addActor(pig2);
    }

    private void setupBirdInput() {
        bird.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!isFlying && bird instanceof Bird) {
                    isDragging = true;
                    dragStart = new Vector2(x, y);
                }
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isDragging && bird instanceof Bird) {
                    Bird currentBird = (Bird) bird;
                    Vector2 dragCurrent = new Vector2(x, y);
                    Vector2 dragVector = dragStart.cpy().sub(dragCurrent);

                    if (dragVector.len() > MAX_DRAG_DISTANCE) {
                        dragVector.setLength(MAX_DRAG_DISTANCE);
                    }

                    Vector2 newPos = new Vector2(
                        catapultPos.x - dragVector.x - bird.getWidth() / 2,
                        catapultPos.y - dragVector.y - bird.getHeight() / 2
                    );
                    bird.setPosition(newPos.x, newPos.y);
                    calculateTrajectory(dragVector);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isDragging && bird instanceof Bird) {
                    Bird currentBird = (Bird) bird;
                    isDragging = false;
                    isFlying = true;
                    Vector2 dragVector = new Vector2(
                        catapultPos.x - (bird.getX() + bird.getWidth() / 2),
                        catapultPos.y - (bird.getY() + bird.getHeight() / 2)
                    );
                    currentBird.setVelocity(dragVector.scl(LAUNCH_SPEED_FACTOR));
                }
            }
        });
    }

    private void nextBird() {
        currentBirdIndex++;
        if (currentBirdIndex < availableBirds.size) {
            bird = availableBirds.get(currentBirdIndex);
            resetBird();
            stage.addActor(bird);
            setupBirdInput();
        } else {
            // No more birds available - level failed
            game.setScreen(new EndScreen(game, "Level Failed!", currentLevel, false));
        }
    }

    private void resetBird() {
        if (bird instanceof Bird) {
            Bird currentBird = (Bird) bird;
            currentBird.setPosition(
                catapultPos.x - bird.getWidth() / 2,
                catapultPos.y - bird.getHeight() / 2
            );
            currentBird.reset();
            isFlying = false;
        }
    }

    private void calculateTrajectory(Vector2 dragVector) {
        trajectoryPoints.clear();
        Vector2 velocityForTrajectory = dragVector.cpy().scl(LAUNCH_SPEED_FACTOR);
        Vector2 pos = new Vector2(
            bird.getX() + bird.getWidth() / 2,
            bird.getY() + bird.getHeight() / 2
        );

        for (float t = 0; t < 2; t += 0.1f) {
            float x = pos.x + velocityForTrajectory.x * t;
            float y = pos.y + velocityForTrajectory.y * t + 0.5f * GRAVITY * t * t;
            trajectoryPoints.add(new Vector2(x, y));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        if (!isPaused) {
            updateGame(delta);
        }

        stage.act(delta);
        stage.draw();

        if (isDragging) {
            renderTrajectory();
        }
    }

    private void updateGame(float delta) {
        if (isFlying && bird instanceof Bird) {
            Bird currentBird = (Bird) bird;
            Vector2 velocity = currentBird.getVelocity();
            velocity.y += GRAVITY * delta;
            currentBird.setPosition(
                currentBird.getX() + velocity.x * delta,
                currentBird.getY() + velocity.y * delta
            );

            checkCollisions();

            // Check if bird is out of bounds
            if (currentBird.getY() < 0 ||
                currentBird.getX() > Gdx.graphics.getWidth() ||
                currentBird.getX() < 0) {
                currentBird.remove();
                nextBird();
            }
        }
    }

    private void checkCollisions() {
        Bird currentBird = (Bird) bird;

        // Check collisions with pigs
        for (GameObject pig : new Array.ArrayIterator<>(pigs)) {
            if (pig.getBounds().overlaps(currentBird.getBounds())) {
                currentBird.onCollision(pig);
                pig.onCollision(currentBird);

                if (pig.isDestroyed()) {
                    pig.remove();
                    pigs.removeValue(pig, true);
                }

                if (currentBird.isDestroyed()) {
                    currentBird.remove();
                    nextBird();
                }

                // Check if all pigs are destroyed
                if (pigs.size == 0) {
                    handleLevelComplete();
                }
                break;
            }
        }

        // Check collisions with blocks
        for (GameObject block : new Array.ArrayIterator<>(blocks)) {
            if (block.getBounds().overlaps(currentBird.getBounds())) {
                currentBird.onCollision(block);
                block.onCollision(currentBird);

                if (block.isDestroyed()) {
                    block.remove();
                    blocks.removeValue(block, true);
                }

                if (currentBird.isDestroyed()) {
                    currentBird.remove();
                    nextBird();
                }
                break;
            }
        }
    }

    private void handleLevelComplete() {
        if (currentLevel == 1) {
            // Progress to level 2
            game.setScreen(new EndScreen(game, "Level Complete!", currentLevel, true));
        } else if (currentLevel == 2) {
            // Game complete
            game.setScreen(new EndScreen(game, "Game Complete!", currentLevel, true));
        }
    }

    private void renderTrajectory() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 0.5f);
        for (int i = 1; i < trajectoryPoints.size; i++) {
            Vector2 point1 = trajectoryPoints.get(i - 1);
            Vector2 point2 = trajectoryPoints.get(i);
            shapeRenderer.line(point1, point2);
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {}

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
        shapeRenderer.dispose();
    }

    public void resumeGame() {
        isPaused = false;
        Gdx.input.setInputProcessor(stage);
    }
}
