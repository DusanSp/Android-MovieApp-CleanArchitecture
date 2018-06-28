package com.example.dusan.movieapp.presentation.model;

public class TopMovie {

    private long id;
    private String releaseDate;
    private String title;
    private String posterPath;
    private Double voteAverage;

    public TopMovie(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
