package com.example.dusan.movieapp.domain.interactor;

import com.example.dusan.movieapp.data.entity.MovieDetail;
import com.example.dusan.movieapp.data.repository.MovieDetailRepository;
import com.example.dusan.movieapp.domain.repository.IMovieDetailRepository;
import io.reactivex.Single;

public class MovieDetailsUseCase extends UseCase<MovieDetail, MovieDetailsUseCase.Params> {

  private IMovieDetailRepository movieDetailRepository;

  public MovieDetailsUseCase() {
    this.movieDetailRepository = new MovieDetailRepository();
  }

  @Override
  Single<MovieDetail> buildUseCaseObservable(Params params) {
    return movieDetailRepository.getMovieDetail(params.getMovieId());
  }

  public static final class Params {

    private final long movieId;

    private Params(long movieId) {
      this.movieId = movieId;
    }

    public static Params movieId(long movieId) {
      return new Params(movieId);
    }

    long getMovieId() {
      return movieId;
    }
  }
}
