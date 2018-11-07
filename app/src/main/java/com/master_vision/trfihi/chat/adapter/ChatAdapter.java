package com.master_vision.trfihi.chat.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.chat.model.ChatModel;
import com.master_vision.trfihi.chat.view_model.ChatAdapterViewModel;
import com.master_vision.trfihi.databinding.AdapterChatBinding;
import java.util.ArrayList;
import io.reactivex.annotations.Nullable;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private ArrayList<ChatModel> mDataset;
    private Context context;

    public ChatAdapter(Context context) {
        this.mDataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chat, parent, false);
        return new ChatAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        ChatModel dataModel = mDataset.get(position);
        holder.setViewModel(new ChatAdapterViewModel(dataModel));
    }

    @Override
    public void onViewAttachedToWindow(ChatAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(ChatAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable ArrayList<ChatModel> data) {
        this.mDataset = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterChatBinding binding;

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

        void setViewModel(ChatAdapterViewModel itemViewModel) {
            if (binding != null) {
                binding.setChatAdapterVM(itemViewModel);
            }
        }

    }
}
