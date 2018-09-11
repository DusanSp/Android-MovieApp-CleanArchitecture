package com.example.dusan.movieapp.presentation.adapters;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.databinding.ItemTopMovieBinding;
import com.example.dusan.movieapp.presentation.common.DataBoundListAdapter;
import com.example.dusan.movieapp.presentation.common.ITopMovieItemActionsListener;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import com.example.dusan.movieapp.presentation.navigation.Navigator;

public class TopMoviesAdapter extends DataBoundListAdapter<TopMovie, ItemTopMovieBinding> {

  private static final String TAG = TopMoviesAdapter.class.getSimpleName();
  private DataBindingComponent dataBindingComponent;

  public TopMoviesAdapter(@NonNull AsyncDifferConfig.Builder<TopMovie> diffCallback,
      DataBindingComponent dataBindingComponent) {
    super(diffCallback);
    this.dataBindingComponent = dataBindingComponent;
  }

  @Override
  protected ItemTopMovieBinding createBinding(ViewGroup parent) {
    return DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_top_movie, parent, false,
            dataBindingComponent);
  }

  @Override
  protected void bind(ItemTopMovieBinding binding, TopMovie item) {
    binding.setVm(item);

    ITopMovieItemActionsListener topMovieItemActionsListener = topMovie -> {
      Log.d(TAG, "bind: " + item.getTitle());
      Navigator.getInstance().navigateToMovieActivity(item);
    };

    binding.setListener(topMovieItemActionsListener);
  }
}
