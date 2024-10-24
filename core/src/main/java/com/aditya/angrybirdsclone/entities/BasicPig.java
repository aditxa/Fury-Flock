package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class BasicPig extends Pig {
    public BasicPig(float x, float y) {
        super(new Texture("pig1.png"), x, y, 70, 70, 1f);
    }
}
