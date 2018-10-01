package com.example.dusan.movieapp.domain.interactor;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;
import com.example.dusan.movieapp.domain.mapper.TopMoviesDomainDataMapper;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class TopMoviesUseCase extends
    UseCase<BaseResponse<TopMovieDomainData>, TopMoviesUseCase.Params> {

  private final ITopMoviesRepository topMoviesRepository;
  private final TopMoviesDomainDataMapper topMoviesDomainDataMapper;

  @Inject
  public TopMoviesUseCase(ITopMoviesRepository topMoviesRepository,
      TopMoviesDomainDataMapper topMoviesDomainDataMapper) {
    this.topMoviesRepository = topMoviesRepository;
    this.topMoviesDomainDataMapper = topMoviesDomainDataMapper;
  }

  @Override
  Single<BaseResponse<TopMovieDomainData>> buildUseCaseObservable(Params params) {
    return this.topMoviesRepository.getTopMovies(params.getPageNumber()).map(
        topMovieEntityBaseResponse -> {
          Collection<TopMovieDomainData> topMovieDomainData =
              topMoviesDomainDataMapper.transform(topMovieEntityBaseResponse.getResults());

          BaseResponse<TopMovieDomainData> dataBaseResponse =
              new BaseResponse<>(new ArrayList<>(topMovieDomainData));

          dataBaseResponse.setPage(topMovieEntityBaseResponse.getPage());
          dataBaseResponse.setTotalPages(topMovieEntityBaseResponse.getTotalPages());
          dataBaseResponse.setTotalResults(topMovieEntityBaseResponse.getTotalResults());

          return dataBaseResponse;
        }
    );
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
