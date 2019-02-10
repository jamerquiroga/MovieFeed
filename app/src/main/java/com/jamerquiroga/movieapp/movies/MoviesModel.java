package com.jamerquiroga.movieapp.movies;

import com.jamerquiroga.movieapp.http.apimodel.Result;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MoviesModel implements MoviesMVP.Model {

    private Repository giRepository;

    public MoviesModel(Repository giRepository) {
        this.giRepository = giRepository;
    }


    /**
     * Construimos nuestro objeto, conformado por dos repositorios, rxJava zipea la info en un solo objeto.
     * @return
     */
    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(giRepository.getResultData(), giRepository.getCountryData(), new BiFunction<Result, String, ViewModel>() {
            @Override
            public ViewModel apply(Result result, String psCountry) throws Exception {
                return new ViewModel(result.toString(),psCountry);
            }
        });
    }
}
