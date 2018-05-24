package com.example.dusan.movieapp.domain.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TopMoviesDomainData {
  @SerializedName("vote_count")
  @Expose
  private Integer voteCount;
  @SerializedName("id")
  @Expose
  private Long id;
  @SerializedName("video")
  @Expose
  private Boolean video;
  @SerializedName("vote_average")
  @Expose
  private Double voteAverage;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("popularity")
  @Expose
  private Double popularity;
  @SerializedName("poster_path")
  @Expose
  private String posterPath;
  @SerializedName("original_language")
  @Expose
  private String originalLanguage;
  @SerializedName("original_title")
  @Expose
  private String originalTitle;
  @SerializedName("genre_ids")
  @Expose
  private List<Integer> genreIds = null;
  @SerializedName("backdrop_path")
  @Expose
  private String backdropPath;
  @SerializedName("adult")
  @Expose
  private Boolean adult;
  @SerializedName("overview")
  @Expose
  private String overview;
  @SerializedName("release_date")
  @Expose
  private String releaseDate;

  public TopMoviesDomainData(Long id) {
    this.id = id;
  }

  public Integer getVoteCount() {
    return voteCount;
  }

  public Long getId() {
    return id;
  }

  public Boolean getVideo() {
    return video;
  }

  public Double getVoteAverage() {
    return voteAverage;
  }

  public String getTitle() {
    return title;
  }

  public Double getPopularity() {
    return popularity;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public List<Integer> getGenreIds() {
    return genreIds;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public Boolean getAdult() {
    return adult;
  }

  public String getOverview() {
    return overview;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;
  }

  public void setVideo(Boolean video) {
    this.video = video;
  }

  public void setVoteAverage(Double voteAverage) {
    this.voteAverage = voteAverage;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPopularity(Double popularity) {
    this.popularity = popularity;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public void setGenreIds(List<Integer> genreIds) {
    this.genreIds = genreIds;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public void setAdult(Boolean adult) {
    this.adult = adult;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
