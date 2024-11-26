package com.aditya.angrybirdsclone.entities;

import com.badlogic.gdx.graphics.Texture;

public class GlassBlock extends Block {
    public GlassBlock(float x, float y) {
        super(new Texture("glassblock.png"), x, y, 64f, 64f, 2f, 1f);
    }
}
