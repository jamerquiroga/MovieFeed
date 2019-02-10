package com.jamerquiroga.movieapp.movies;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MoviesModel implements MoviesMVP.Model {

    private MoviesRepository giMoviesRepository;

    public MoviesModel(MoviesRepository giMoviesRepository) {
        this.giMoviesRepository = giMoviesRepository;
    }


    /**
     * Construimos nuestro objeto, conformado por dos repositorios, rxJava zipea la info en un solo objeto.
     * @return
     */
    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(giMoviesRepository.getResultData(), giMoviesRepository.getCountryData(), new BiFunction<Result, String, ViewModel>() {
            @Override
            public ViewModel apply(Result result, String psCountry) throws Exception {
                //TODO PENDIENTE CAMBIAR EL RESULT POR EL POJO DE DATOS
                return new ViewModel(result.toString(),psCountry);
            }
        });
    }
}
