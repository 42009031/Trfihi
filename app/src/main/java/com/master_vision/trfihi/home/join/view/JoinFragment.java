package com.master_vision.trfihi.home.join.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.FragmentJoinBinding;
import com.master_vision.trfihi.home._main.OnFragmentInteractionListener;
import com.master_vision.trfihi.home.join.view_model.JoinViewModel;


public class JoinFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


    public static JoinFragment newInstance() {
        JoinFragment fragment = new JoinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentJoinBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false);
        View view = binding.getRoot();
        binding.setJoinVM(new JoinViewModel(getActivity()));
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
