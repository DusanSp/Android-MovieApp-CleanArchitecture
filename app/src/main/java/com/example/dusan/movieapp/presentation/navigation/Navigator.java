package com.example.dusan.movieapp.presentation.navigation;

import android.content.Intent;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import com.example.dusan.movieapp.presentation.view.activity.MovieDetailActivity;
import com.example.dusan.movieapp.presentation.view.activity.TopMoviesActivity;
import com.example.dusan.movieapp.presentation.view.application.MovieApplication;

public class Navigator {

  private static Navigator instance = null;

  public static Navigator getInstance() {
    if (instance == null) {
      instance = new Navigator();
    }

    return instance;
  }

  public void navigateToTopicMoviesActivity() {
    Intent topMoviesIntent = TopMoviesActivity
        .createIntent(MovieApplication.getApplication().getApplicationContext());
    MovieApplication.getApplication().getApplicationContext().startActivity(topMoviesIntent);
  }

  public void navigateToMovieDetailsActivity(long movieId) {
    Intent movieIntent = MovieDetailActivity
        .createIntent(MovieApplication.getApplication().getApplicationContext(), movieId);
    MovieApplication.getApplication().getApplicationContext().startActivity(movieIntent);
  }
}
