package com.jamerquiroga.movieapp.root;

import com.jamerquiroga.movieapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity gMainActivity);
}
