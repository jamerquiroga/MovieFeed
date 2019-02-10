package com.jamerquiroga.movieapp.movies;

import com.jamerquiroga.movieapp.http.MovieTitleApiService;
import com.jamerquiroga.movieapp.http.MoviesExtraInfoApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    public MoviesMVP.Presenter providerMoviesPresenter(MoviesMVP.Model piModel){
        return new MoviesPresenter(piModel);
    }

    @Provides
    public MoviesMVP.Model providerMoviesModel(Repository piRepository){
        return new MoviesModel(piRepository);
    }

    @Singleton
    @Provides
    public Repository providerMoviesRepository(MovieTitleApiService piMovieTitleApiService, MoviesExtraInfoApiService piMoviesExtraInfoApiService){
        return new MoviesRepository(piMovieTitleApiService, piMoviesExtraInfoApiService);
    }
}
