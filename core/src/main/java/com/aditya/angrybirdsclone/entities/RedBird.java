package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class RedBird extends Bird {
    public RedBird(float x, float y) {
        super(new Texture("bird1.png"), x, y, 70, 70, 4f);
    }
}
