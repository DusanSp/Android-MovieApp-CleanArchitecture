package com.example.dusan.movieapp.presentation.common;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

  private T mBinding;

  public DataBoundViewHolder(T binding) {
    super(binding.getRoot());
    this.mBinding = binding;
  }

  public DataBoundViewHolder(View view, T binding) {
    super(view);
    this.mBinding = binding;
  }

  protected T getBinding() {
    return mBinding;
  }
}
