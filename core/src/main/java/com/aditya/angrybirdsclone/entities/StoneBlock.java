package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class StoneBlock extends Block {
    public StoneBlock(float x, float y) {
        super(new Texture("block2.png"), x, y, 30, 100, 3f, 2f);
    }
}
