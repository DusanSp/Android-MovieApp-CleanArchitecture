package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.data.entity.MovieDetail;
import com.example.dusan.movieapp.data.network.ApiMethods;
import com.example.dusan.movieapp.domain.repository.IMovieDetailRepository;
import io.reactivex.Single;
import javax.inject.Inject;

public class MovieDetailRepository implements IMovieDetailRepository {

  private final ApiMethods apiMethods;

  @Inject
  public MovieDetailRepository(ApiMethods apiMethods) {
    this.apiMethods = apiMethods;
  }

  @Override
  public Single<MovieDetail> getMovieDetail(long movieId) {
    return apiMethods.getMovie(movieId);
  }
}
