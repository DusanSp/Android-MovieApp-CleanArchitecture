package com.example.dusan.movieapp.presentation.binding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.dusan.movieapp.R;

public class ActivityBindingAdapters {

  private final Activity activity;

  public ActivityBindingAdapters(Activity activity) {
    this.activity = activity;
  }

  @BindingAdapter("posterUrl")
  public static void bindPosterImage(ImageView imageView, String url) {
    Glide.with(imageView.getContext())
        .load("https://image.tmdb.org/t/p/w154" + url)
        .into(imageView);
  }

  @BindingAdapter("imageUrl")
  public static void bindImage(ImageView imageView, String url) {
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

  @BindingAdapter("voteAverage")
  public static void setVoteAverage(TextView textView, Float voteAverage) {
    String text = textView.getContext().getString(R.string.vote_average, voteAverage);
    textView.setText(text);
  }
}
