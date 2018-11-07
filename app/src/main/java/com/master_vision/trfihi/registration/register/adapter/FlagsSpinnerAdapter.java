package com.master_vision.trfihi.registration.register.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.model.GenericModel;

import java.util.ArrayList;


public class FlagsSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GenericModel> flags ;
    private LayoutInflater inflater;

    public FlagsSpinnerAdapter(Context applicationContext, ArrayList<GenericModel> flags) {
        this.context = applicationContext;
        this.flags = flags;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_flags, null);
        ImageView icon = (ImageView) view.findViewById(R.id.flag);
        icon.setImageResource(flags.get(i).getImage());
        return view;
    }
}