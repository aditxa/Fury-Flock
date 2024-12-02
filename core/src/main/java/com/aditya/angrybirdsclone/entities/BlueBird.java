package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class BlueBird extends Bird {
    public BlueBird(float x, float y) {
        super(new Texture("bird3.png"), x, y, 70, 70, 5f);
    }
}
