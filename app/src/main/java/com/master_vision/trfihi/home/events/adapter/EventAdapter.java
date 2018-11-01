package com.master_vision.trfihi.home.events.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.AdapterEventsBinding;
import com.master_vision.trfihi.home.events._EventCallBack;
import com.master_vision.trfihi.home.events.model.EventModel;
import com.master_vision.trfihi.home.events.view_model.EventAdapterViewModel;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements _EventCallBack{

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.onDeleteEvent(null);
    }

    private ArrayList<EventModel> mDataset;

    public EventAdapter() {
        this.mDataset = new ArrayList<>();
    }

    @Override
    public int getItemCount() { return mDataset.size(); }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_events, parent, false);
        return new EventAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        EventModel dataModel = mDataset.get(position);
        holder.setViewModel(new EventAdapterViewModel(dataModel, this));
    }

    @Override
    public void onViewAttachedToWindow(EventAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(EventAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable ArrayList<EventModel> data) {
        this.mDataset = (data);
        notifyDataSetChanged();
    }

    public void deleteItem(@Nullable EventModel model) {
        for (int i = 0; i < mDataset.size(); i++) {
            if(mDataset.get(i).getGameEventId().equals(model.getGameEventId())){
                this.mDataset.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    @Override
    public void onDeleteEvent(EventModel model) {
        deleteItem(model);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterEventsBinding binding;

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

        void setViewModel(EventAdapterViewModel itemViewModel) {
            if (binding != null) {
                binding.setEventAdapterVM(itemViewModel);
            }
        }
    }




}
