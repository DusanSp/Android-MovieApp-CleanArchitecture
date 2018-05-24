package com.example.dusan.movieapp.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BaseResponse<D> {

  @SerializedName("page")
  @Expose
  private Integer page;
  @SerializedName("total_results")
  @Expose
  private Integer totalResults;
  @SerializedName("total_pages")
  @Expose
  private Integer totalPages;
  @SerializedName("results")
  @Expose
  private List<D> results;

  public BaseResponse(List<D> data) {
    this.results = data;
  }

  public Integer getPage() {
    return page;
  }

  public Integer getTotalResults() {
    return totalResults;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public List<D> getResults() {
    return results;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public void setTotalResults(Integer totalResults) {
    this.totalResults = totalResults;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }
}
