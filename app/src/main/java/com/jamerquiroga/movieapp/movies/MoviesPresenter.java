package com.jamerquiroga.movieapp.movies;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenter implements MoviesMVP.Presenter {

    private MoviesMVP.View giView;

    private MoviesMVP.Model giModel;

    private Disposable gDisposable;

    public MoviesPresenter(MoviesMVP.Model piModel) {
        this.giModel = piModel;
    }

    @Override
    public void loadData() {

        //Scheduler.io hilo que se ejecuta en segundo plano

        gDisposable = giModel.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
                    @Override
                    public void onNext(ViewModel viewModel) {
                        if(giView != null){
                            giView.updateData(viewModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(giView != null){
                            giView.showSnackBar( "Error al descargar las películas" + e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(giView != null){
                            giView.showSnackBar("Información descargada con éxito");
                        }
                    }
                });
    }

    @Override
    public void rxJavaUnsubscribe() {

        if(gDisposable != null && !gDisposable.isDisposed()){
            gDisposable.dispose();
        }

    }

    @Override
    public void setView(MoviesMVP.View poView) {
        this.giView = poView;
    }
}
