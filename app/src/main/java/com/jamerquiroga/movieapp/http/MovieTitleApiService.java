package com.jamerquiroga.movieapp.http;

import com.jamerquiroga.movieapp.http.apimodel.TopMoviesRated;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieTitleApiService {

    @GET("top_rated")
    Observable<TopMoviesRated> getTopMoviesRated(@Query("page") int piPage);
}
