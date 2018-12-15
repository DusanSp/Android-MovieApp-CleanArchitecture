package com.example.dusan.movieapp.data.repository

import com.example.dusan.movieapp.data.entity.BaseResponse
import com.example.dusan.movieapp.data.entity.TopMovieEntity
import com.example.dusan.movieapp.data.network.ApiMethods
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class TopMoviesDataRepository
@Inject constructor(private var apiMethods: ApiMethods) : ITopMoviesRepository {

    override fun getTopMovies(page: Int): Single<BaseResponse<TopMovieEntity>> {
        return apiMethods.getTopRatedMovies(page)
    }
}