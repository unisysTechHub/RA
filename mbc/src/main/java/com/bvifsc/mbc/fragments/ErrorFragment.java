package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bvifsc.core.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ErrorFragment extends Fragment {
    String errorMessage;

    public ErrorFragment() {
        // Required empty public constructor
    }



    public static ErrorFragment newInstance(String errorMessage) {
        Bundle args = new Bundle();
        args.putString("errorMessage",errorMessage);
        ErrorFragment fragment = new ErrorFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorMessage=getArguments().getString("errorMessage");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_error, container, false);
        TextView errorMessageView =view.findViewById(R.id.errorMessage);
        errorMessageView.setText(errorMessage);


        return view;
    }



}
