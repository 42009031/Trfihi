package com.master_vision.trfihi.home.messages.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.home.messages.model.MessageModel;
import com.master_vision.trfihi.home.messages.viewmodel.MessageAdapterViewModel;
import com.master_vision.trfihi.databinding.AdapterMessagesBinding;
import java.util.ArrayList;


import io.reactivex.annotations.Nullable;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<MessageModel> mDataset;
    private Context context;

    public MessageAdapter(Context context) {
        this.mDataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_messages, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageModel dataModel = mDataset.get(position);
        holder.setViewModel(new MessageAdapterViewModel(dataModel));
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

    public void updateData(@Nullable ArrayList<MessageModel> data) {
        this.mDataset = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterMessagesBinding binding;

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

        void setViewModel(MessageAdapterViewModel itemViewModel) {
            if (binding != null) {
                binding.setMesAdpVM(itemViewModel);
            }
        }

    }
}