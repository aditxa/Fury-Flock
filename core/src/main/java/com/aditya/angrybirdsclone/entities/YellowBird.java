package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class YellowBird extends Bird {
    public YellowBird(float x, float y) {
        super(new Texture("bird2.png"), x, y, 70, 70, 2f);
    }
}
