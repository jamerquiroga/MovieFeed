package com.jamerquiroga.movieapp.movies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamerquiroga.movieapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private List<ViewModel> goModelList;

    public MovieAdapter(List<ViewModel> goModelList) {
        this.goModelList = goModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ViewModel loViewModel = goModelList.get(i);

        myViewHolder.bind(loViewModel);

    }

    @Override
    public int getItemCount() {
        return goModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtMovieTitle)
        TextView txtMovieTitle;

        @BindView(R.id.txtMovieCountry)
        TextView txtMovieCountry;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(ViewModel poViewModel){

                if(poViewModel.getName() != null && poViewModel.getCountry() != null){

                txtMovieTitle.setText(poViewModel.getName());
                txtMovieCountry.setText(poViewModel.getCountry());

            }

        }
    }

}
