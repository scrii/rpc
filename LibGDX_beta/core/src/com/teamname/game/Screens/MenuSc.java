package com.teamname.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.teamname.game.Main;

import Tools.Buttons;

public class MenuSc implements Screen {

    private static final float BUTTONS_WIDTH=400;
    private static final float BUTTONS_HEIGHT=200;

    private static final float REG_X=100;
    private static final float REG_Y=100;

    private static final float AUTH_X=100;
    private static final float AUTH_Y=300;

    Main main;

    Texture reg_unpressed;
    Texture reg_pressed;
    Texture auth_unpressed;
    Texture auth_pressed;

    Buttons reg_button;
    Buttons auth_button;

    public MenuSc(Main main){
        this.main=main;

        reg_unpressed=new Texture("regunpressed.png");
        reg_pressed=new Texture("regpressed.png");
        auth_unpressed=new Texture("authunpressed.png");
        auth_pressed=new Texture("authpressed.png");

        reg_button=new Buttons(reg_unpressed,reg_pressed,BUTTONS_WIDTH,BUTTONS_HEIGHT,REG_X,REG_Y);
        auth_button=new Buttons(auth_unpressed,auth_pressed,BUTTONS_WIDTH,BUTTONS_HEIGHT,AUTH_X,AUTH_Y);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        reg_button.draw(Main.batch,Gdx.input.getX(),Gdx.input.getY());
        auth_button.draw(Main.batch,Gdx.input.getX(),Gdx.input.getY());
        Main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
