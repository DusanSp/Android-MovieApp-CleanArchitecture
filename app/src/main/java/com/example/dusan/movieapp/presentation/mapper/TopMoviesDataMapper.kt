package com.example.dusan.movieapp.presentation.mapper

import com.example.dusan.movieapp.domain.data.TopMovieDomainData
import com.example.dusan.movieapp.presentation.model.TopMovie
import java.util.*
import javax.inject.Inject

class TopMoviesDataMapper @Inject constructor() {

    fun transform(topDomainData: TopMovieDomainData?): TopMovie? {
        return topDomainData?.let {
            val topMovie = TopMovie(it.id).apply {
                posterPath = topDomainData.posterPath
                releaseDate = topDomainData.releaseDate
                title = topDomainData.title
                voteAverage = topDomainData.voteAverage
            }

            topMovie
        }
    }

    fun transform(topMovieDomainDataCollection: Collection<TopMovieDomainData>?): Collection<TopMovie>? {
        return topMovieDomainDataCollection?.let {
            val topMovieModelsCollection = mutableListOf<TopMovie>()
            for (topMovieDomainData in topMovieDomainDataCollection) {
                transform(topMovieDomainData)?.let { topMovie ->
                    topMovieModelsCollection.add(topMovie)
                }
            }
            topMovieModelsCollection
        } ?: Collections.emptyList()
    }
}