package com.jamerquiroga.movieapp.root;

import android.app.Application;

public class App extends Application {

    private ApplicationComponent giApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        giApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent(){
        return giApplicationComponent;
    }
}
