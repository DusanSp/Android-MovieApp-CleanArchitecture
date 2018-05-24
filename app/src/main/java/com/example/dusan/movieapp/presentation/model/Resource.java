package com.example.dusan.movieapp.presentation.model;

public class Resource<T> {

  public ResourceState state;
  public T data;
  public Throwable error;

  public Resource(ResourceState state, T data, Throwable error) {
    this.state = state;
    this.data = data;
    this.error = error;
  }

  public ResourceState getState() {
    return state;
  }

  public T getData() {
    return data;
  }

  public Throwable getError() {
    return error;
  }
}
