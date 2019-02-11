package com.jamerquiroga.movieapp.movies;

import com.jamerquiroga.movieapp.http.MovieExtraInfoApiModule;
import com.jamerquiroga.movieapp.http.MovieTitleApiService;
import com.jamerquiroga.movieapp.http.MoviesExtraInfoApiService;
import com.jamerquiroga.movieapp.http.apimodel.ExtraInfo;
import com.jamerquiroga.movieapp.http.apimodel.Result;
import com.jamerquiroga.movieapp.http.apimodel.TopMoviesRated;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MoviesRepository implements Repository {

    private MovieTitleApiService piMovieTitleApiService;
    private MoviesExtraInfoApiService piMoviesExtraInfoApiService;

    private List<String> gaoCountries;
    private List<Result> gaoResults;
    private long glTimeStamp;

    private static final long CACHE_LIFETIME = 20 * 1000; //Caché que durará 20 segundos

    public MoviesRepository(MovieTitleApiService piMovieTitleApiService, MoviesExtraInfoApiService piMoviesExtraInfoApiService) {
        this.piMovieTitleApiService = piMovieTitleApiService;
        this.piMoviesExtraInfoApiService = piMoviesExtraInfoApiService;

        this.glTimeStamp = System.currentTimeMillis();

        this.gaoCountries = new ArrayList<>();
        this.gaoResults = new ArrayList<>();
    }

    public boolean isUpdated(){
        return (System.currentTimeMillis() - glTimeStamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Result> getResultFromNetwork() {

        final Observable<TopMoviesRated> loTopMoviesRatedObservable = piMovieTitleApiService.getTopMoviesRated(1)
                .concatWith(piMovieTitleApiService.getTopMoviesRated(2))
                .concatWith(piMovieTitleApiService.getTopMoviesRated(3));

        return loTopMoviesRatedObservable
                .concatMap(new Function<TopMoviesRated, Observable<Result>>() {
                    @Override
                    public Observable<Result> apply(TopMoviesRated topMoviesRated){
                        return Observable.fromIterable(topMoviesRated.getResults());
                    }
                }).doOnNext(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) {
                        gaoResults.add(result);
                    }
                });
    }

    @Override
    public Observable<Result> getResultFromCache() {
        if(isUpdated()){
            return Observable.fromIterable(gaoResults);
        }else {
            glTimeStamp = System.currentTimeMillis();
            gaoResults.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork());
    }

    @Override
    public Observable<String> getCountryFromNetwork() {

        return getResultFromNetwork().concatMap(new Function<Result, Observable<ExtraInfo>>() {
                @Override
                public Observable<ExtraInfo> apply(Result result){
                    return piMoviesExtraInfoApiService.getExtraInfo(MovieExtraInfoApiModule.API_KEY, result.getTitle());
                }
            }).concatMap(new Function<ExtraInfo, Observable<String>>() {
                @Override
                public Observable<String> apply(ExtraInfo extraInfo){
                    if(extraInfo != null && extraInfo.getCountry() != null){
                        return Observable.just(extraInfo.getCountry());
                    }else{
                        return Observable.just("Desconocido");
                    }

                }
            }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String psCountry) {

                gaoCountries.add(psCountry);
            }
        });

    }

    @Override
    public Observable<String> getCountryFromCache() {
        if(isUpdated()){
            return Observable.fromIterable(gaoCountries);
        }else {
            glTimeStamp = System.currentTimeMillis();
            gaoCountries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork());
    }

}
