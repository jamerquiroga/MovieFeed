package com.jamerquiroga.movieapp.movies;

import io.reactivex.Observable;

public interface MoviesMVP {

    interface View {

        void updateData(ViewModel poViewModel);

        void showSnackBar(String psMessage);

    }

    interface Presenter{

        void loadData();

        void rxJavaUnsubscribe();

        void setView(View poView);

    }

    interface Model {

        Observable<ViewModel> result();

    }
}
