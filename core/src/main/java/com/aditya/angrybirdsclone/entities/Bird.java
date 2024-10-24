package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Bird extends GameObject {
    protected float damage;
    protected Vector2 velocity;
    protected boolean isFlying;
    protected Vector2 originalPosition;

    public Bird(Texture texture, float x, float y, float width, float height, float damage) {
        super(texture, x, y, width, height);
        this.damage = damage;
        this.velocity = new Vector2();
        this.isFlying = false;
        this.originalPosition = new Vector2(x, y);
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
        isFlying = true;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getDamage() {
        return damage;
    }

    public void reset() {
        setPosition(originalPosition.x, originalPosition.y);
        velocity.setZero();
        isFlying = false;
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Pig || other instanceof Block) {
            other.takeDamage(damage);
            isDestroyed = true;
        }
    }
}
