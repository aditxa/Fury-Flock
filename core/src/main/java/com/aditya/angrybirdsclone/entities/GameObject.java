package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject extends Image {
    protected float health;
    protected boolean isDestroyed;
    protected Rectangle bounds;

    public GameObject(Texture texture, float x, float y, float width, float height) {
        super(texture);
        setPosition(x, y);
        setSize(width, height);
        bounds = new Rectangle(x, y, width, height);
        isDestroyed = false;
    }

    public abstract void onCollision(GameObject other);

    public void updateBounds() {
        bounds.setPosition(getX(), getY());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void takeDamage(float damage) {
        health -= damage;
        if (health <= 0) {
            isDestroyed = true;
        }
    }
}
