package com.teamname.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.game.Screens.GameSc;
import com.teamname.game.Screens.MenuSc;



public class Main extends Game {
	public static SpriteBatch batch;
	public Texture img;
	public static int WIDTH,HEIGHT;
	public static Texture circle,stickImg,background,actor;
	public MenuSc menu;
	boolean flag=false;
	ActionResolver actionResolver;

	public Main(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
		flag=true;
	}

	public Main() {
	}




	@Override
	public void create () {
		if(flag)actionResolver.LoginFacebook();

		batch = new SpriteBatch();
		WIDTH= Gdx.graphics.getWidth();
		HEIGHT=Gdx.graphics.getHeight();
		circle=new Texture("circle.png");
		stickImg=new Texture("stick.png");
		actor=new Texture("actor.png");
		background=new Texture("testlocation.png");

		setScreen(new GameSc(this));
	}


	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
	}
}
