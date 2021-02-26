package com.teamname.game.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.teamname.game.GraphicsObj.GraphicsObj;

import Tools.Circle;
import Tools.Point2D;

public abstract class Actor extends GraphicsObj {

    public Point2D position;
    public float Speed,R;
    public Circle bounds; // границы
    public Point2D direction;

    public Actor(Texture img, Point2D position, float Speed, float R) {
        super(img);
        this.position=new Point2D(position);
        this.Speed=Speed;
        this.R=R;
        bounds = new Circle(position,R);
        direction=new Point2D(0,0);
    }


    public void setDirection(Point2D dir){
        direction=dir;
    }
}
