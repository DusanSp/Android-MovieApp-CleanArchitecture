package com.example.dusan.movieapp.domain.interactor;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.repository.TopMoviesDataRepository;
import com.example.dusan.movieapp.domain.data.TopMoviesDomainData;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;
import io.reactivex.Observable;

public class GetTopMoviesList extends
    UseCase<BaseResponse<TopMoviesDomainData>, GetTopMoviesList.Params> {

  private ITopMoviesRepository topMoviesRepository;

  public GetTopMoviesList() {
    this.topMoviesRepository = new TopMoviesDataRepository();
  }

  @Override
  Observable<BaseResponse<TopMoviesDomainData>> buildUseCaseObservable(Params params) {
    return this.topMoviesRepository.topMovies(params.getPageNumber());
  }

  public static final class Params {

    private final int pageNumber;

    private Params(int pageNumber) {
      this.pageNumber = pageNumber;
    }

    public static Params page(int pageNumber) {
      return new Params(pageNumber);
    }

    int getPageNumber() {
      return pageNumber;
    }
  }
}
