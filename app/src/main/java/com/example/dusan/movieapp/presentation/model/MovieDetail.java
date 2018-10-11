package com.example.dusan.movieapp.presentation.model;

public class MovieDetail {

  private long id;
  private String backdropPath;
  private String posterPath;
  private String title;
  private String overview;
  private int voteCount;
  private String voteAverage;

  public MovieDetail(long id) {
    this.id = id;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public String getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(String voteAverage) {
    this.voteAverage = voteAverage;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }
}
