package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.data.net.ApiManager;
import com.example.dusan.movieapp.domain.repository.IMovieRepository;
import io.reactivex.Single;

public class MovieRepository implements IMovieRepository {

  @Override
  public Single<String> getMovie(long movieId) {
    return ApiManager.getApi().getMovie(movieId);
  }
}
