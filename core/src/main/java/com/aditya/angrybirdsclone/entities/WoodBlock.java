package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class WoodBlock extends Block {
    public WoodBlock(float x, float y) {
        super(new Texture("block1.png"), x, y, 30, 100, 2f, 1f);
    }
}
