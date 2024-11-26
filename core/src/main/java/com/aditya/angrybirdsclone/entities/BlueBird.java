package com.aditya.angrybirdsclone.entities;

import com.aditya.angrybirdsclone.screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BlueBird extends Bird {

    private static final float SPLIT_SPEED = 10f;

    public BlueBird(float x, float y) {
        super(new Texture("blue_bird.png"), x, y, 64, 64,1.5f);
    }

    @Override
    public void onCollision(GameObject other) {
        super.onCollision(other);


    }

    // Split the bird into three smaller bird

    @Override
    public void reset() {
        super.reset();
        // Any specific reset logic for the BlueBird if needed
    }
}
