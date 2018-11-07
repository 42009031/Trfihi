package com.master_vision.trfihi.home.join.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.AdapterJoinBinding;
import com.master_vision.trfihi.home.join.model.JoinModel;
import com.master_vision.trfihi.home.join.view_model.JoinAdapterViewModel;

import java.util.ArrayList;


import io.reactivex.annotations.Nullable;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ViewHolder> {

    private ArrayList<JoinModel> mDataset;
    private Context context;

    public JoinAdapter(Context context) {
        this.mDataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public JoinAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_join, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JoinModel dataModel = mDataset.get(position);
        holder.setViewModel(new JoinAdapterViewModel(dataModel));
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

    public void updateData(@Nullable ArrayList<JoinModel> data) {
        this.mDataset = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterJoinBinding binding;

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

        void setViewModel(JoinAdapterViewModel itemViewModel) {
            if (binding != null) {
                binding.setJoinAdpVM(itemViewModel);
            }
        }

    }
}