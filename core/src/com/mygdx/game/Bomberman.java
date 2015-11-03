package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Bomberman extends ApplicationAdapter {
    Map map;
    Thumbstick thumbstick;
    Character character;

    @Override
    public void create() {
        character = new Character();
        character.create();
        thumbstick = new Thumbstick();
        thumbstick.create();
        thumbstick.setCharacter(character, character.arbDirection, character.bStop);
        map = new Map();
        map.ThumbstickHeight(thumbstick.sprFG.getHeight() * 2);
        map.create();
        character.setMap(map);
        System.out.println(thumbstick.touchpad.getHeight());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render();
        thumbstick.render();
        character.render();

    }
}
