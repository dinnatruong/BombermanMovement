package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Bomberman extends ApplicationAdapter {
    Map map;
    Thumbstick thumbstick;

    @Override
    public void create() {
        map = new Map();
        map.create();
        thumbstick = new Thumbstick();
        thumbstick.create();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render();
        thumbstick.render();
    }
}
