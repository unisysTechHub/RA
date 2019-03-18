package com.bvifsc.core.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;


public class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(),container,false);
        injectFragment();
        loadFragmentArguments();

        initFragment(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    protected int getLayout() {
        return 0;
    }

    protected void initFragment(View rootView)
    {}

    protected void loadFragmentArguments()
    {}
   protected void injectFragment(){}



}
