package com.example.dusan.movieapp.domain.mapper;

import com.example.dusan.movieapp.data.entity.TopMovieEntity;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TopMoviesDomainDataMapper {

    public TopMovieDomainData transform(TopMovieEntity topMovieEntity) {
        if (topMovieEntity == null) {
            throw new IllegalArgumentException("Cannot transform null value");
        }

        final TopMovieDomainData topMovieDomainData = new TopMovieDomainData(topMovieEntity.getId());

        topMovieDomainData.setAdult(topMovieEntity.getAdult());
        topMovieDomainData.setBackdropPath(topMovieEntity.getBackdropPath());
        topMovieDomainData.setGenreIds(topMovieEntity.getGenreIds());
        topMovieDomainData.setOriginalLanguage(topMovieEntity.getOriginalLanguage());
        topMovieDomainData.setOriginalTitle(topMovieEntity.getOriginalTitle());
        topMovieDomainData.setOverview(topMovieEntity.getOverview());
        topMovieDomainData.setPopularity(topMovieEntity.getPopularity());
        topMovieDomainData.setPosterPath(topMovieEntity.getPosterPath());
        topMovieDomainData.setReleaseDate(topMovieEntity.getReleaseDate());
        topMovieDomainData.setTitle(topMovieEntity.getTitle());
        topMovieDomainData.setVideo(topMovieEntity.getVideo());
        topMovieDomainData.setVoteAverage(topMovieEntity.getVoteAverage());
        topMovieDomainData.setVoteCount(topMovieEntity.getVoteCount());

        return topMovieDomainData;
    }

    public Collection<TopMovieDomainData> transform(Collection<TopMovieEntity> topMoviesCollection) {
        Collection<TopMovieDomainData> topMovieModelsCollection;

        if (topMoviesCollection != null && !topMoviesCollection.isEmpty()) {
            topMovieModelsCollection = new ArrayList<>();

            for (TopMovieEntity topMovieEntity : topMoviesCollection) {
                topMovieModelsCollection.add(transform(topMovieEntity));
            }
        } else {
            topMovieModelsCollection = Collections.emptyList();
        }

        return topMovieModelsCollection;
    }
}
