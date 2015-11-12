package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Bomberman extends ApplicationAdapter {
    Thumbstick thumbstick;
    Character character;

    @Override
    public void create() {
        character = new Character();
        character.create();
        thumbstick = new Thumbstick();
        thumbstick.create();
        thumbstick.setCharacter(character, character.arbDirection, character.bStop);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        thumbstick.render();
        character.render();

    }
}
