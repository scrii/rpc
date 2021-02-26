package com.teamname.game.GraphicsObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GraphicsObj {

    public GraphicsObj(Texture img){
        this.img=img;
    }

    public Texture img;

    public abstract void draw(SpriteBatch batch); //Рисовка
    public abstract void update(); //обновление отрисовки
}
