package com.example.dusan.movieapp.presentation.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Resource<T> {

    @NonNull
    private final Status status;
    @Nullable
    private final T data;
    @Nullable
    private final String message;

    /**
     * Constructor for Resource wrapper
     *
     * @param status  status of resource
     * @param data    data type of T
     * @param message message
     */
    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /**
     * Success
     *
     * @param data successfully received data
     * @param <T>  type of data
     * @return data typeof <>T</>
     */
    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    /**
     * Error
     *
     * @param message error message
     * @param data    error data. Can be null.
     * @param <T>     type of data
     * @return error data
     */
    public static <T> Resource<T> error(String message, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, message);
    }

    /**
     * Loading
     *
     * @param data loading data. Can be null.
     * @param <T>  type of data
     * @return type of data
     */
    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}
