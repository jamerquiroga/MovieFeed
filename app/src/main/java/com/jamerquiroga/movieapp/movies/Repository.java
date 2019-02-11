package com.jamerquiroga.movieapp.movies;

import com.jamerquiroga.movieapp.http.apimodel.Result;

import io.reactivex.Observable;

/**
 * En este repositorio realizaremos distitos tipos de llamadas
 */

public interface Repository {

    Observable<Result> getResultFromNetwork();
    Observable<Result> getResultFromCache();
    Observable<Result> getResultData();

    Observable<String> getCountryFromNetwork();
    Observable<String> getCountryFromCache();
    Observable<String> getCountryData();

}
