package com.jamerquiroga.movieapp.movies;

import com.jamerquiroga.movieapp.http.MovieTitleApiService;
import com.jamerquiroga.movieapp.http.MoviesExtraInfoApiService;
import com.jamerquiroga.movieapp.http.apimodel.Result;

import io.reactivex.Observable;

public class MoviesRepository implements Repository {

    private MovieTitleApiService piMovieTitleApiService;
    private MoviesExtraInfoApiService piMoviesExtraInfoApiService;

    public MoviesRepository(MovieTitleApiService piMovieTitleApiService, MoviesExtraInfoApiService piMoviesExtraInfoApiService) {
        this.piMovieTitleApiService = piMovieTitleApiService;
        this.piMoviesExtraInfoApiService = piMoviesExtraInfoApiService;
    }

    @Override
    public Observable<Result> getResultData() {
        return null;
    }

    @Override
    public Observable<String> getCountryData() {
        return null;
    }

}
