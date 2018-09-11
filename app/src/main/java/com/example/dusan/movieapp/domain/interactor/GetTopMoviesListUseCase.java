package com.example.dusan.movieapp.domain.interactor;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.repository.TopMoviesDataRepository;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;

import io.reactivex.Single;

public class GetTopMoviesListUseCase extends UseCase<BaseResponse<TopMovieDomainData>, GetTopMoviesListUseCase.Params> {

    private ITopMoviesRepository topMoviesRepository;

    public GetTopMoviesListUseCase() {
        this.topMoviesRepository = new TopMoviesDataRepository();
    }

    @Override
    Single<BaseResponse<TopMovieDomainData>> buildUseCaseObservable(Params params) {
        return this.topMoviesRepository.getTopMovies(params.getPageNumber());
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
