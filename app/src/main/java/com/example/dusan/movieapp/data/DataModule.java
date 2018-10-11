package com.example.dusan.movieapp.data;

import com.example.dusan.movieapp.data.network.NetworkModule;
import com.example.dusan.movieapp.data.repository.MovieDetailRepository;
import com.example.dusan.movieapp.data.repository.TopMoviesDataRepository;
import com.example.dusan.movieapp.domain.repository.IMovieDetailRepository;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;
import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;

@Module(includes = NetworkModule.class)
public abstract class DataModule {

  @Singleton
  @Binds
  public abstract ITopMoviesRepository provideTopMoviesDataRepository(
      TopMoviesDataRepository topMoviesDataRepository);

  @Singleton
  @Binds
  public abstract IMovieDetailRepository provideMovieDetailRepository(
      MovieDetailRepository movieDetailRepository);
}
