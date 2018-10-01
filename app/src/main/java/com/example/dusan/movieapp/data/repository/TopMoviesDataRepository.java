package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.entity.TopMovieEntity;
import com.example.dusan.movieapp.data.network.ApiMethods;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;
import io.reactivex.Single;
import javax.inject.Inject;

public class TopMoviesDataRepository implements ITopMoviesRepository {

  private final ApiMethods apiMethods;

  @Inject
  public TopMoviesDataRepository(ApiMethods apiMethods) {
    this.apiMethods = apiMethods;
  }

  @Override
  public Single<BaseResponse<TopMovieEntity>> getTopMovies(int page) {
    return apiMethods.getTopRatedMovies(page);
  }
}
