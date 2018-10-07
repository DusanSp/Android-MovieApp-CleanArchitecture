package com.example.dusan.movieapp.presentation.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import javax.inject.Singleton;

@Module
public abstract class ViewModelBuilderModule {

  @Binds
  @Singleton
  abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory factory);

  @Binds
  @IntoMap
  @Singleton
  @ViewModelKey(TopMoviesViewModel.class)
  abstract ViewModel bindTopMoviesViewModel(TopMoviesViewModel topMoviesViewModel);

  @Binds
  @IntoMap
  @Singleton
  @ViewModelKey(MovieDetailViewModel.class)
  abstract ViewModel bindMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel);
}
