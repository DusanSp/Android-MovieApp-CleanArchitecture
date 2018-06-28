package com.example.dusan.movieapp.domain.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;

import io.reactivex.Observable;

public interface ITopMoviesRepository {

    Observable<BaseResponse<TopMovieDomainData>> getTopMovies(final int page);
}