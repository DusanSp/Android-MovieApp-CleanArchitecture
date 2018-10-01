package com.example.dusan.movieapp.domain.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.entity.TopMovieEntity;
import io.reactivex.Single;

public interface ITopMoviesRepository {

    Single<BaseResponse<TopMovieEntity>> getTopMovies(final int page);
}