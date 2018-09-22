package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.data.entity.MovieDetail;
import com.example.dusan.movieapp.data.network.ApiManager;
import com.example.dusan.movieapp.domain.repository.IMovieDetailRepository;
import io.reactivex.Single;

public class MovieDetailRepository implements IMovieDetailRepository {

  @Override
  public Single<MovieDetail> getMovieDetail(long movieId) {
    return ApiManager.getApi().getMovie(movieId);
  }
}
