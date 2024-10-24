package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public abstract class Block extends GameObject {
    protected float hardness;

    public Block(Texture texture, float x, float y, float width, float height, float health, float hardness) {
        super(texture, x, y, width, height);
        this.health = health;
        this.hardness = hardness;
    }

    @Override
    public void takeDamage(float damage) {
        super.takeDamage(damage / hardness);
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Bird) {
            takeDamage(((Bird) other).getDamage());
        }
    }
}
