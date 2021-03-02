package com.teamname.game;

class SingletonData{
    private SingletonData(){
    }
    private static SingletonData instance;
    public static SingletonData getInstance(){
        if(instance==null){
            instance = new SingletonData(); // can only be assessed here. no other class can generate a second instance
        }
        return instance;
    }

    private PlatformStuff handler;

    public void setHandler(PlatformStuff handler){
        this.handler=handler;
    }

    public PlatformStuff getHandler(){
        return this.handler;
    }
}