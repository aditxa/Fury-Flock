package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public abstract class Pig extends GameObject {
    public Pig(Texture texture, float x, float y, float width, float height, float health) {
        super(texture, x, y, width, height);
        this.health = health;
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Bird) {
            takeDamage(((Bird) other).getDamage());
        }
    }
}
