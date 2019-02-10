package com.jamerquiroga.movieapp.root;

import android.app.Application;

import com.jamerquiroga.movieapp.http.MovieExtraInfoApiModule;
import com.jamerquiroga.movieapp.http.MovieTitleApiModule;
import com.jamerquiroga.movieapp.movies.MoviesModule;

public class App extends Application {

    private ApplicationComponent giApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        giApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .moviesModule(new MoviesModule())
                .movieTitleApiModule(new MovieTitleApiModule())
                .movieExtraInfoApiModule(new MovieExtraInfoApiModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return giApplicationComponent;
    }
}
