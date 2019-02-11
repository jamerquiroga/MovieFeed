package com.jamerquiroga.movieapp.http;

import com.jamerquiroga.movieapp.http.apimodel.ExtraInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesExtraInfoApiService {

    @GET("/")
    Observable<ExtraInfo> getExtraInfo(@Query("apikey") String psApiKey, @Query("t") String psCountry);

}
