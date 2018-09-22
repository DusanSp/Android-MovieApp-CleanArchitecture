package com.example.dusan.movieapp.presentation.binding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ActivityBindingAdapters {

  private final Activity activity;

  public ActivityBindingAdapters(Activity activity) {
    this.activity = activity;
  }

  @BindingAdapter("imageUrl")
  public static void bindImage(ImageView imageView, String url) {
    Glide.with(imageView.getContext())
        .load("https://image.tmdb.org/t/p/w92" + url)
        .into(imageView);
  }

  @BindingAdapter("posterUrl")
  public static void bindPosterImage(ImageView imageView, String url) {
    Glide.with(imageView.getContext())
        .load("https://image.tmdb.org/t/p/w780" + url)
        .into(imageView);
  }

  @BindingAdapter("dividerItemDecoration")
  public static void addDividerDirection(RecyclerView recyclerView, int itemDecoration) {
    recyclerView
        .addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), itemDecoration));
  }

  @BindingAdapter("visibleGone")
  public static void setVisibilityGone(View view, boolean isGone) {
    if (isGone) {
      view.setVisibility(View.GONE);
    }
  }
}
