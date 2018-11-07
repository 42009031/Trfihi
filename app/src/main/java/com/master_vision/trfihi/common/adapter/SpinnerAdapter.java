package com.master_vision.trfihi.common.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.AdapterDropDownSpinnerBinding;
import com.master_vision.trfihi.databinding.SpinnerItemBinding;

import java.util.ArrayList;


public class SpinnerAdapter<T> extends ArrayAdapter<T> {


    private ArrayList<T> arrayList;

    public SpinnerAdapter(Context context, int textViewResourceId, ArrayList<T> objects) {
        super(context, textViewResourceId, objects);
        arrayList = objects;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        AdapterDropDownSpinnerBinding dropDownSpinnerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_drop_down_spinner, parent, false);
        convertView = dropDownSpinnerBinding.getRoot();

        dropDownSpinnerBinding.dropDownText.setText(arrayList.get(position).toString());
        if (position == 0) {
            dropDownSpinnerBinding.dropDownText.setTextColor(Color.GRAY);
        } else {
            dropDownSpinnerBinding.dropDownText.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public void setListItems(ArrayList<T> arrayList){
        if(arrayList.size() != 0){
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }

    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        SpinnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.spinner_item, parent, false);
        View view = binding.getRoot();

        binding.spinnerTextView.setText(arrayList.get(position).toString());
        if (this.isEnabled(position)) {
            if (position != 0) {
                binding.spinnerTextView.setTextColor(Color.BLACK);
            } else {
                binding.spinnerTextView.setTextColor(Color.GRAY);
            }
        }
        return view;
    }
}