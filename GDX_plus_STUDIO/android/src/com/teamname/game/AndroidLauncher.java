package com.teamname.game;


import android.content.Intent;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication{

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		//AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new Main(), config);
	}
}

