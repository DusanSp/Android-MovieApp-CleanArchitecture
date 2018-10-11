package com.example.dusan.movieapp.di;

import com.example.dusan.movieapp.presentation.view.activity.MovieDetailActivity;
import com.example.dusan.movieapp.presentation.view.activity.TopMoviesActivity;
import com.example.dusan.movieapp.presentation.viewmodels.ViewModelBuilderModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ViewModelBuilderModule.class})
public abstract class ActivityBuilderModule {

  @ActivityScope
  @ContributesAndroidInjector
  abstract TopMoviesActivity bindTopMoviesActivity();

  @ActivityScope
  @ContributesAndroidInjector
  abstract MovieDetailActivity bindMovieDetailActivity();
}
