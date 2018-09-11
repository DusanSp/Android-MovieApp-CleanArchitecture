package com.example.dusan.movieapp.data.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.net.ApiManager;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;
import com.example.dusan.movieapp.domain.mapper.TopMoviesDomainDataMapper;
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository;

import java.util.ArrayList;
import java.util.Collection;

import io.reactivex.Single;

public class TopMoviesDataRepository implements ITopMoviesRepository {

    private TopMoviesDomainDataMapper topMoviesDomainDataMapper;

    public TopMoviesDataRepository() {
        this.topMoviesDomainDataMapper = new TopMoviesDomainDataMapper();
    }

    @Override
    public Single<BaseResponse<TopMovieDomainData>> getTopMovies(int page) {
        return ApiManager.getApi().getTopRatedMovies(page)
                .map(movieEntityBaseResponse -> {

                    Collection<TopMovieDomainData> topMovieDomainData =
                            topMoviesDomainDataMapper.transform(movieEntityBaseResponse.getResults());

                    BaseResponse<TopMovieDomainData> dataBaseResponse =
                            new BaseResponse<>(new ArrayList<>(topMovieDomainData));

                    dataBaseResponse.setPage(movieEntityBaseResponse.getPage());
                    dataBaseResponse.setTotalPages(movieEntityBaseResponse.getTotalPages());
                    dataBaseResponse.setTotalResults(movieEntityBaseResponse.getTotalResults());

                    return dataBaseResponse;
                });
    }
}
