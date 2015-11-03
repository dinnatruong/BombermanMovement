package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

//import java.awt.Rectangle;

/**
 * Created by Justin on 2015-11-02.
 */
//http://stackoverflow.com/questions/20063281/libgdx-collision-detection-with-tiledmap
    //box2d collision w/ rectangles. (i hope).
public class Character {
    SpriteBatch batch;
    TextureAtlas taBomberman;
    Sprite[] spBomberman;
    int i = 0, nCurrentIndex;
    Animation animFront, animBack, animRight, animLeft;
    float stateTime, fCharacterVelocityX = 0, fCharacterVelocityY = 0, fCharacterX, fCharacterY;
    int nVelocityX, nVelocityY;
    TextureRegion currentFrame;
    TextureRegion[] atrFront, atrBack, atrLeft, atrRight;
    boolean[] arbDirection = new boolean[4];//0=up, 1=down, 2=right, 3=left
    boolean bStop = true, bCollidedX, bCollidedY;
    Sprite sprChar;
    int nSHeight, nSWidth, nLayerCount, nTileWidth, nTileHeight;
    Map map;

    public void setMap(Map _map) {
        map = _map;
        nTileHeight = (int) map.tiledMapTileLayer.getTileHeight();
        nTileWidth = (int) map.tiledMapTileLayer.getTileWidth();
    }

    public void create() {
        nSHeight = Gdx.graphics.getHeight(); //use to make scaling
        nSWidth = Gdx.graphics.getWidth();
        nVelocityX = nSWidth * 10 / 1794;
        nVelocityY = nSHeight * 10 / 1080;
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


    public void setCharacterVelocity(int _nVx, int _nVy) {
        fCharacterVelocityX = nVelocityX * _nVx;
        fCharacterVelocityY = nVelocityY * _nVy;
    }

   /* public boolean getTileID(float fX, float fY, int nWidth, String sID) {
        boolean bCollided = false;
        for (nLayerCount = 0; nLayerCount < map.tiledMap.getLayers().getCount() - 1; nLayerCount++) {

            bCollided = map.arclCollisionLayer[nLayerCount].getCell((int) ((fX + nWidth / 4) / nTileWidth), (int) (fY / nTileHeight)).getTile().getProperties().containsKey(sID);

            bCollided |= map.arclCollisionLayer[nLayerCount].getCell((int) ((fX + 3 * nWidth / 4) / nTileWidth), (int) (fY / nTileHeight))
                    .getTile().getProperties().containsKey(sID);

            bCollided |= map.arclCollisionLayer[nLayerCount].getCell((int) ((fX + nWidth / 2) / nTileWidth), (int) (fY / nTileHeight))
                    .getTile().getProperties().containsKey(sID);
        }
        System.out.println(bCollided);

        return bCollided;

    }*/


    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        for (int i = 0; i < 4; i++) {//set all direction booleans to false unless it's the current direction
            if (nCurrentIndex == i) {
            } else {
                arbDirection[i] = false;
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

        for(RectangleMapObject rectangleMapObject: map.mapObjects.getByType(RectangleMapObject.class)){
            Rectangle rectangle = rectangleMapObject.getRectangle();
            if(Intersector.overlaps(rectangle, sprChar.getBoundingRectangle())){

            }
            if(rectangle.overlaps(sprChar.getBoundingRectangle())){
                System.out.println("collision");
            }
        }
        sprChar = new Sprite(currentFrame);//Create the sprite of the character based on the current texture region frame
       // bCollidedX = getTileID(fCharacterX, fCharacterY, (int) sprChar.getWidth(), "1");//Did it touched a tile with the block ID
        fCharacterX += fCharacterVelocityX / 2;
        fCharacterY += fCharacterVelocityY / 2;
        sprChar.setX(Gdx.graphics.getWidth() / 2);
        batch.begin();
        batch.draw(sprChar, fCharacterX, fCharacterY);
        batch.end();
    }

    public void getBoolsBack(boolean[] _arbDirection, boolean _bStop, int _nCurrentIndex) {
        arbDirection = _arbDirection;
        bStop = _bStop;
        nCurrentIndex = _nCurrentIndex;
    }
}
