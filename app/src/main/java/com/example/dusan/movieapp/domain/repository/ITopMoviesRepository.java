package com.example.dusan.movieapp.domain.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;

import io.reactivex.Single;

public interface ITopMoviesRepository {

    Single<BaseResponse<TopMovieDomainData>> getTopMovies(final int page);
}