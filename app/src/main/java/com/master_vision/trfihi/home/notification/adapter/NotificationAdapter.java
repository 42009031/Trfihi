package com.master_vision.trfihi.home.notification.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.AdapterNotificationBinding;
import com.master_vision.trfihi.home.notification.model.NotificationModel;
import com.master_vision.trfihi.home.notification.view_model.NotificationAdapterViewModel;

import java.util.ArrayList;

import io.reactivex.annotations.Nullable;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<NotificationModel> mDataset;
    private Context context;

    public NotificationAdapter(Context context) {
        this.mDataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notification, parent, false);
        return new NotificationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
        NotificationModel dataModel = mDataset.get(position);
        holder.setViewModel(new NotificationAdapterViewModel(dataModel));
    }

    @Override
    public void onViewAttachedToWindow(NotificationAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(NotificationAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable ArrayList<NotificationModel> data) {
        this.mDataset = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterNotificationBinding binding;

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

        void setViewModel(NotificationAdapterViewModel itemViewModel) {
            if (binding != null) {
                binding.setNotAdpVM(itemViewModel);
            }
        }

    }
}
