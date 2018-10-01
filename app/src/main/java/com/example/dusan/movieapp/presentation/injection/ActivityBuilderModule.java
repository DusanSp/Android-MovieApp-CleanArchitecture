package com.example.dusan.movieapp.presentation.injection;

import com.example.dusan.movieapp.presentation.view.activity.MovieDetailActivity;
import com.example.dusan.movieapp.presentation.view.activity.TopMoviesActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

  @ContributesAndroidInjector
  abstract TopMoviesActivity bindTopMoviesActivity();

  @ContributesAndroidInjector
  abstract MovieDetailActivity bindMovieDetailActivity();
}
