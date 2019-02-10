package com.jamerquiroga.movieapp.movies;

import io.reactivex.Observable;

/**
 * En este repositorio realizaremos distitos tipos de llamadas
 */

public interface MoviesRepository {

    Observable<Result> getResultData();

    Observable<String> getCountryData();

}
