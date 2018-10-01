package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.domain.repository.IMovieDetailRepository;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoriesModule {

  @Binds
  public abstract ITopMoviesRepository provideTopMoviesDataRepository(
      TopMoviesDataRepository topMoviesDataRepository);

  @Binds
  public abstract IMovieDetailRepository provideMovieDetailRepository(
      MovieDetailRepository movieDetailRepository);
}
