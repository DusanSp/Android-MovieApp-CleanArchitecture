package com.example.dusan.movieapp.data.entity;

import com.google.gson.annotations.SerializedName;

public class MovieDetail {
  @SerializedName("adult")
  private Boolean adult;
  @SerializedName("backdrop_path")
  private String backdropPath;
  @SerializedName("budget")
  private Integer budget;
  @SerializedName("homepage")
  private String homepage;
  @SerializedName("id")
  private long id;
  @SerializedName("imdb_id")
  private String imdbId;
  @SerializedName("original_language")
  private String originalLanguage;
  @SerializedName("original_title")
  private String originalTitle;
  @SerializedName("overview")
  private String overview;
  @SerializedName("popularity")
  private Double popularity;
  @SerializedName("poster_path")
  private String posterPath;
  @SerializedName("release_date")
  private String releaseDate;
  @SerializedName("revenue")
  private Integer revenue;
  @SerializedName("runtime")
  private Integer runtime;
  @SerializedName("status")
  private String status;
  @SerializedName("tagline")
  private String tagline;
  @SerializedName("title")
  private String title;
  @SerializedName("video")
  private Boolean video;
  @SerializedName("vote_average")
  private Double voteAverage;
  @SerializedName("vote_count")
  private Integer voteCount;

  public long getId() {
    return id;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public String getTitle() {
    return title;
  }
}
