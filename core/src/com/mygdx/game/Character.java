package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Justin on 2015-11-02.
 */
public class Character {
    SpriteBatch batch;
    TextureAtlas taBomberman;
    Sprite[] spBomberman;
    int i = 0, nCurrentIndex;
    Animation animFront, animBack, animRight, animLeft;
    float stateTime;
    TextureRegion currentFrame;
    TextureRegion[] atrFront, atrBack, atrLeft, atrRight;
    boolean[] arbDirection = new boolean[4];//0=up, 1=down, 2=right, 3=left
    boolean bStop = true;
    Sprite sChar;

    public void create() {
        arbDirection[0] = true;
       // Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        //Create an array sprites loaded from the TextureAtlas
        taBomberman = new TextureAtlas(Gdx.files.internal("bomberchar.pack"));
        spBomberman = new Sprite[4];
        atrFront = new TextureRegion[2];
        atrBack = new TextureRegion[2];
        atrRight = new TextureRegion[2];
        atrLeft = new TextureRegion[2];
        for (int j = 0; j <= 1; j++) {
            atrFront[i] = taBomberman.findRegion("frame-" + i);
            i++;
        }
        animFront = new Animation(0.15f, atrFront);
        for (int j = 0; j <= 1; j++) {
            atrBack[j] = taBomberman.findRegion("frame-" + i);
            i++;
        }
        animBack = new Animation(0.15f, atrBack);
        for (int j = 0; j <= 1; j++) {
            atrLeft[j] = taBomberman.findRegion("frame-" + i);
            i++;
        }
        animLeft = new Animation(0.15f, atrLeft);
        for (int j = 0; j <= 1; j++) {
            atrRight[j] = taBomberman.findRegion("frame-" + i);
            i++;
        }
        animRight = new Animation(0.15f, atrRight);
        stateTime = 0f;
    }

    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        System.out.println(stateTime);
        for (int i = 0; i < 4; i++) {//set all direction booleans to false unless it's the current direction
            if (nCurrentIndex == i) {
            } else {
                arbDirection[i] = false;
                //bStop = false;
                //System.out.println(i +" : "+arbDirection[i]);
            }
        }

        if (arbDirection[0]) {//up,down,right,left
            if (bStop) {
                currentFrame = atrBack[0];
            } else {
                currentFrame = animBack.getKeyFrame(stateTime, true);
            }
        } else if (arbDirection[1]) {
            if (bStop) {
                currentFrame = atrFront[0];
            } else {
                currentFrame = animFront.getKeyFrame(stateTime, true);
            }
        } else if (arbDirection[2]) {
            if (bStop) {
                currentFrame = atrLeft[0];
            } else {
                currentFrame = animLeft.getKeyFrame(stateTime, true);
            }
        } else if (arbDirection[3]) {
            if (bStop) {
                currentFrame = atrRight[0];
            } else {
                currentFrame = animRight.getKeyFrame(stateTime, true);
            }
        }
        sChar = new Sprite(currentFrame);//Create the sprite of the character based on the current texture region
        sChar.setX(Gdx.graphics.getWidth()/2);
        batch.begin();
        sChar.draw(batch);
        //  batch.draw(currentFrame, 50, 50);
        batch.end();
    }

   /* @Override
    public boolean keyDown(int keycode) {
        bStop=false;
        if (keycode == Input.Keys.UP) {
            arbDirection[0] = true;
            nCurrentIndex = 0;
            System.out.println("up");
        } else if (keycode == Input.Keys.DOWN) {
            arbDirection[1] = true;
            nCurrentIndex = 1;
        } else if (keycode == Input.Keys.LEFT) {
            arbDirection[2] = true;
            nCurrentIndex = 2;
        } else if (keycode == Input.Keys.RIGHT) {
            arbDirection[3] = true;
            nCurrentIndex = 3;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        bStop = true;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }*/
}
