package com.example.dusan.movieapp.presentation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopMovie {

  @SerializedName("id")
  @Expose
  private long id;
  @SerializedName("release_date")
  @Expose
  private String releaseDate;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("poster_path")
  @Expose
  private String posterPath;
  @SerializedName("vote_average")
  @Expose
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
