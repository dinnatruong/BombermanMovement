package com.mygdx.game;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Thumbstick implements ApplicationListener {
    Stage stage;
    private SpriteBatch batch;
    public Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skTouchPad;
    private Drawable drwTouchbg;
    private Drawable drwTouchpad;
    private Texture txRect;
    private Sprite sprRect;
    private float fSpeed;
    Texture txFG;
    Sprite sprFG, sprBG;
    Map map;
    float fTouchPadHeight;

    @Override
    public void create() {
        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        //Create a skin for the touchpad
        skTouchPad = new Skin();
        //Set background and knob imgs
        txFG= new Texture("ThumbstickFGsmall.png");
        sprFG = new Sprite(txFG);
        sprBG = new Sprite(new Texture("ThumbstickBG.png"));
        skTouchPad.add("drwTouchbg", sprBG);
        skTouchPad.add("drwTouchpad", sprFG);
        touchpadStyle = new Touchpad.TouchpadStyle();
        //make drawables based off the skin
        drwTouchbg = skTouchPad.getDrawable("drwTouchbg");
        drwTouchpad = skTouchPad.getDrawable("drwTouchpad");
        //Apply the bg and knob drawables to the touchpad
        touchpadStyle.background = drwTouchbg;
        touchpadStyle.knob = drwTouchpad;

        touchpad = new Touchpad(10, touchpadStyle);
        //Initiate the touchpad based on the style we just created
        touchpad.setBounds(0, 0, 100, 100);
        //set where the touchpad will be on the screen
        stage = new Stage();
        //create the stage and add the touchpad to it

        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);
        //create our crappy rect that is going to move around with the touchpad
        txRect = new Texture(Gdx.files.internal("block.png"));
        sprRect = new Sprite(txRect);
        sprRect.setPosition(Gdx.graphics.getWidth()/2-sprRect.getWidth()/2, Gdx.graphics.getHeight()/2-sprRect.getHeight()/2);
        fSpeed = 5;
        //set the default speed to multiply by when the touchpad is moved around to move the rect
    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void render() {
        //Move the rect based on the knob percent
        sprRect.setX(sprRect.getX() + touchpad.getKnobPercentX()*fSpeed);
        sprRect.setY(sprRect.getY() + touchpad.getKnobPercentY()* fSpeed);

        batch.begin();
        sprRect.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
