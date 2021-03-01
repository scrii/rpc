package com.teamname.game;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class ActionResolverAndroid implements ActionResolver {
    Handler handler;
    Context context;


    public ActionResolverAndroid(Context context) {
        handler = new Handler();
        this.context = context;
    }

    public void LoginFacebook()
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

    }}