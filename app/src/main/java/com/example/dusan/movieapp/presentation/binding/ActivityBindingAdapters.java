package com.example.dusan.movieapp.presentation.binding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ActivityBindingAdapters {

    private Activity activity;

    public ActivityBindingAdapters(Activity activity) {
        this.activity = activity;
    }

    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String url) {
        Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w92" + url)
                .into(imageView);
    }
}
