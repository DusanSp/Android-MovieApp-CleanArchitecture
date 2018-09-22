package com.example.dusan.movieapp.presentation.model;

public class MovieDetail {

  private long id;
  private String posterPath;
  private String title;

  public MovieDetail(long id) {
    this.id = id;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
