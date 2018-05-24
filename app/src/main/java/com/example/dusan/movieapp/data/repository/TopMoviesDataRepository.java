package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.net.ApiManager;
import com.example.dusan.movieapp.domain.data.TopMoviesDomainData;
import com.example.dusan.movieapp.domain.mapper.TopMoviesDomainDataMapper;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collection;

public class TopMoviesDataRepository implements ITopMoviesRepository {

  private TopMoviesDomainDataMapper topMoviesDomainDataMapper;

  public TopMoviesDataRepository() {
    this.topMoviesDomainDataMapper = new TopMoviesDomainDataMapper();
  }

  @Override
  public Observable<BaseResponse<TopMoviesDomainData>> topMovies(int page) {
    return ApiManager.getApi().getTopRatedMovies(page)
        .map(movieEntityBaseResponse -> {

          Collection<TopMoviesDomainData> topMoviesDomainData =
              topMoviesDomainDataMapper.transform(movieEntityBaseResponse.getResults());

          BaseResponse<TopMoviesDomainData> dataBaseResponse =
              new BaseResponse<>(new ArrayList<>(topMoviesDomainData));

          dataBaseResponse.setPage(movieEntityBaseResponse.getPage());
          dataBaseResponse.setTotalPages(movieEntityBaseResponse.getTotalPages());
          dataBaseResponse.setTotalResults(movieEntityBaseResponse.getTotalResults());

          return dataBaseResponse;
        });
  }
}
