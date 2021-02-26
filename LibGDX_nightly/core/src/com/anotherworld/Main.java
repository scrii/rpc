package com.anotherworld;

import com.anotherworld.Screens.GameSc;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends Game {
	public static SpriteBatch batch;
	public Texture img;
	public static int WIDTH,HEIGHT;
	public static Texture circle,actor,background;
	//public MenuSc menu;

	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH= Gdx.graphics.getWidth();
		HEIGHT=Gdx.graphics.getHeight();
		circle=new Texture("circle.png");
		actor=new Texture("actor.png");
		background=new Texture("testlocation.png");
		setScreen(new GameSc(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
