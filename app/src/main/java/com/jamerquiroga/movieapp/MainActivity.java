package com.jamerquiroga.movieapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.jamerquiroga.movieapp.movies.MovieAdapter;
import com.jamerquiroga.movieapp.movies.MoviesMVP;
import com.jamerquiroga.movieapp.movies.ViewModel;
import com.jamerquiroga.movieapp.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesMVP.View {

    private final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.lnlMainView)
    ViewGroup lnlMainView;

    @BindView(R.id.rcvMovies)
    RecyclerView rcvMovies;

    @Inject
    MoviesMVP.Presenter giPresenter;

    private MovieAdapter gaMovieAdapter;
    private List<ViewModel> goResultList = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        giPresenter.setView(this);
        giPresenter.loadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        gaMovieAdapter = new MovieAdapter(goResultList);

        rcvMovies.setAdapter(gaMovieAdapter);
        rcvMovies.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rcvMovies.setItemAnimator(new DefaultItemAnimator());
        rcvMovies.setHasFixedSize(true); //Todas las celdas del mismo tamaño
        rcvMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        giPresenter.rxJavaUnsubscribe();
        goResultList.clear();
        gaMovieAdapter.notifyDataSetChanged(); //notificamos que limpie la pantalla
    }

    @Override
    public void updateData(ViewModel poViewModel) {

        goResultList.add(poViewModel);
        gaMovieAdapter.notifyItemChanged(goResultList.size()-1);
        Log.d(TAG, "New info: " + poViewModel.getName());

    }

    @Override
    public void showSnackBar(String psMessage) {

        Snackbar.make(lnlMainView, psMessage, Snackbar.LENGTH_SHORT).show();

    }
}
