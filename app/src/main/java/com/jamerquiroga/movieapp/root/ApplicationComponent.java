package com.jamerquiroga.movieapp.root;

import com.jamerquiroga.movieapp.MainActivity;
import com.jamerquiroga.movieapp.http.MovieExtraInfoApiModule;
import com.jamerquiroga.movieapp.http.MovieTitleApiModule;
import com.jamerquiroga.movieapp.movies.MoviesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={
        ApplicationModule.class,
        MoviesModule.class,
        MovieTitleApiModule.class,
        MovieExtraInfoApiModule.class})

public interface ApplicationComponent {

    void inject(MainActivity gMainActivity);
}
