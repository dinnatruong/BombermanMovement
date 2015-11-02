package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
    TiledMap tiledMap;
    public OrthographicCamera camera;
    public OrthogonalTiledMapRenderer tiledMapRenderer;
    int nTile = 32;
    TiledMapTileLayer tiledMapTileLayer;
    int nMapWidth, nMapHeight, nTileSize;
   private float fTouchPadHeight;
    void ThumbstickHeight(float _height){
        fTouchPadHeight=_height;
        System.out.println(fTouchPadHeight);
    }


    public void create() {
        tiledMap = new TmxMapLoader().load("bombermap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        nTileSize = (int) tiledMapTileLayer.getTileWidth();
        nMapHeight = tiledMapTileLayer.getHeight() * nTile;
        nMapWidth = tiledMapTileLayer.getWidth() * nTile;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, nMapWidth, nMapHeight + fTouchPadHeight);
        //LibGdx coordinate systems origin is bottom left, whereas norm is upper left
        //Set cam ortho to true and flip all textureregions so origin is upper left
        camera.update();
    }


    public void render() {
        //  Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        camera.update();
    }
}