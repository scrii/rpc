package com.teamname.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.game.Main;

import Tools.Joystick;
import Tools.Point2D;

public class Player extends Actor {

    private int Score;
    private float health;
    public float X;
    public float Y;
    public Point2D send;

    public Player(Texture img, Point2D position, float Speed, float R, float health) {
        super(img, position, Speed, R);
        this.health=health;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX()-R,position.getY()-R,100,100);
    }

    @Override
    public void update() {
        // не зашел ли игрок за границу
        if(position.getX()+R>= Main.WIDTH)position.setX(Main.WIDTH-R);
        if(position.getX()-R<=0)position.setX(R);
        if(position.getY()+R>=Main.HEIGHT)position.setY(Main.HEIGHT-R);
        if(position.getY()-R<=0)position.setY(R);


        X=direction.getX()*Speed;
        Y=direction.getY()*Speed;
        position.add(X,Y);
        send=position;
    }

    public Point2D getPosition(){
        return position;
    }
}
