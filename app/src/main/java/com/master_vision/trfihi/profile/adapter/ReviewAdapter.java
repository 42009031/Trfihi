package com.master_vision.trfihi.profile.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master_vision.trfihi.R;

import com.master_vision.trfihi.databinding.AdapterReviewBinding;
import com.master_vision.trfihi.profile.model.ReviewModel;
import com.master_vision.trfihi.profile.viewModel.ReviewItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private ArrayList<ReviewModel> mDataset;


    public ReviewAdapter() {
        this.mDataset = new ArrayList<>();

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_review, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReviewModel dataModel = mDataset.get(position);
        holder.setViewModel(new ReviewItemViewModel(dataModel));
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable ArrayList<ReviewModel> data) {
        this.mDataset = (data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterReviewBinding binding;

        private ViewHolder(View v) {
            super(v);
            bind();
        }

        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        void setViewModel(ReviewItemViewModel itemViewModel) {
            if (binding != null) {
                binding.setItemVM(itemViewModel);
            }
        }

    }
}
