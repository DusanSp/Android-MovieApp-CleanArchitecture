package com.example.dusan.movieapp.domain.interactor

import com.example.dusan.movieapp.data.entity.BaseResponse
import com.example.dusan.movieapp.data.entity.TopMovieEntity
import com.example.dusan.movieapp.domain.data.TopMovieDomainData
import com.example.dusan.movieapp.domain.mapper.TopMoviesDomainDataMapper
import com.example.dusan.movieapp.domain.repository.ITopMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class TopMoviesUseCase
@Inject constructor(private var topMoviesRepository: ITopMoviesRepository,
                    private var topMoviesDomainDataMapper: TopMoviesDomainDataMapper) : UseCase<BaseResponse<TopMovieDomainData>, TopMoviesUseCase.Companion.Params>() {

    companion object {

        class Params(_pageNumber: Int?) {
            var pageNumber: Int? = _pageNumber
                private set(value) {
                    field = value
                }
        }

        @JvmStatic
        fun page(pageNumber: Int?) = Params(pageNumber)
    }

    override fun buildUseCaseObservable(params: TopMoviesUseCase.Companion.Params?): Single<BaseResponse<TopMovieDomainData>> {
        return params?.let { nonNullParams ->
            nonNullParams.pageNumber?.let {
                topMoviesRepository.getTopMovies(it)
                        .map { baseResponse: BaseResponse<TopMovieEntity> ->
                            val topMovieDomainData = topMoviesDomainDataMapper.transform(baseResponse.results)
                            val dataBaseResponse: BaseResponse<TopMovieDomainData> =
                                    BaseResponse(ArrayList<TopMovieDomainData>(topMovieDomainData))

                            dataBaseResponse.apply {
                                page = baseResponse.page
                                totalPages = baseResponse.totalPages
                                totalResults = baseResponse.totalResults
                            }
                        }
            } ?: Single.error(IllegalArgumentException("Page number is null."))
        } ?: Single.error(IllegalArgumentException("Param is null."))
    }
}