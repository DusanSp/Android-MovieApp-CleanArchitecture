package com.example.dusan.movieapp.presentation.adapters;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.databinding.ItemTopMovieBinding;
import com.example.dusan.movieapp.presentation.common.DataBoundListAdapter;
import com.example.dusan.movieapp.presentation.model.TopMovie;

public class TopMoviesAdapter extends DataBoundListAdapter<TopMovie, ItemTopMovieBinding> {

    private DataBindingComponent dataBindingComponent;

    public TopMoviesAdapter(@NonNull AsyncDifferConfig.Builder<TopMovie> diffCallback, DataBindingComponent dataBindingComponent) {
        super(diffCallback);
        this.dataBindingComponent = dataBindingComponent;
    }

    @Override
    protected ItemTopMovieBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_top_movie, parent, false, dataBindingComponent);
    }

    @Override
    protected void bind(ItemTopMovieBinding binding, TopMovie item) {
        binding.setVm(item);
    }
}
