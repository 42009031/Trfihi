package com.master_vision.trfihi.home.games.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master_vision.trfihi.R;

import com.master_vision.trfihi.databinding.AdapterGameBinding;
import com.master_vision.trfihi.home.games.model.GameModel;
import com.master_vision.trfihi.home.games.view_model.GameAdapterViewModel;

import java.util.ArrayList;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private ArrayList<GameModel> mDataset;

    public GamesAdapter() {
        this.mDataset = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game, parent, false);
        return new GamesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GamesAdapter.ViewHolder holder, int position) {
        GameModel dataModel = mDataset.get(position);
        holder.setViewModel(new GameAdapterViewModel(dataModel));
    }

    @Override
    public void onViewAttachedToWindow(GamesAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(GamesAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable ArrayList<GameModel> data) {
        this.mDataset = (data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterGameBinding binding;

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

        void setViewModel(GameAdapterViewModel itemViewModel) {
            if (binding != null) {
                binding.setGameAdapterVM(itemViewModel);
            }
        }

    }
}

