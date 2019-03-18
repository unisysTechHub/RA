package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.mbc.Common.SharedPreferencesUtils;
import com.bvifsc.mbc.adapters.UserMBCListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserMBCListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserMBCListFragment extends BaseFragment implements TextWatcher{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RadioGroup userMBCsRadioGroup;
    RecyclerView user_mbcs_recyclerView;
    EditText searchString;
    List<String> useALlMBCs;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    @Inject
    EventBus eventBus;
    List<String> userMBCs;
   public UserMBCListAdapter userMBCListAdapter;

    public UserMBCListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserMBCListFragmeent.
     */
    // TODO: Rename and change types and number of parameters
    public static UserMBCListFragment newInstance(String param1, String param2) {
        UserMBCListFragment fragment = new UserMBCListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_user_mbclist_fragmeent;
    }

    @Override
    protected void initFragment(View rootView) {

        setUpToListUserMBCS(rootView);
        setUpToListUserMBCSRecylerView(rootView);
    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }

    void setUpToListUserMBCS(View rootView)
    {
        searchString=rootView.findViewById(R.id.search_string);
        searchString.addTextChangedListener(this);
        userMBCsRadioGroup=rootView.findViewById(R.id.user_mbc_list_radio_group);
        List<String> userMBCList=new ArrayList<>(sharedPreferencesUtils.getMBCsSet());
        int currentMBCIndex=userMBCList.indexOf(userMBCList.indexOf(sharedPreferencesUtils.getCurrentMBC()));

        for(int index=0;index<userMBCList.size();index++)
        {
            RadioButton radioButton = new RadioButton(userMBCsRadioGroup.getContext());
            radioButton.setText(userMBCList.get(index));
            radioButton.setTag(index);
            if(currentMBCIndex == index)
               radioButton.setChecked(true);
            RadioGroup.LayoutParams layoutParams= new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton.setOnCheckedChangeListener((view, isChecked) ->
            {
                if(isChecked)
                {
                    String currentMbcNumber=userMBCList.get(Integer.parseInt(view.getTag().toString()));
                            sharedPreferencesUtils.setCurrentMBC(currentMbcNumber);

                }

            });
            userMBCsRadioGroup.addView(radioButton,layoutParams);

        }

    }

    void setUpToListUserMBCSRecylerView(View rootView)
    {

        Log.d("@MBC", "setUpToListUserMBCS");
        user_mbcs_recyclerView =rootView.findViewById(R.id.user_mbc_list_recycler_view);
        user_mbcs_recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        user_mbcs_recyclerView.setLayoutManager(linearLayoutManager);


        useALlMBCs= new ArrayList<>(sharedPreferencesUtils.getMBCsSet());
        userMBCs=new ArrayList<>();
        userMBCs.addAll(useALlMBCs);

         userMBCListAdapter= new UserMBCListAdapter(this.getContext(),userMBCs,sharedPreferencesUtils,eventBus);

        user_mbcs_recyclerView.setAdapter(userMBCListAdapter);


    }

    void searchMBC(String serachString)
    {

        List<String> mbcsWithSearchString = new ArrayList<>();

        for(String mbcNumber : useALlMBCs)
        { Log.d("@MBC", "mbcs with search STring :" + mbcNumber+ " " + serachString+ " " + mbcNumber.contains(serachString));
             if(mbcNumber.toLowerCase().contains(serachString.toLowerCase()))
             {

                 mbcsWithSearchString.add(mbcNumber);
             }


        }
        Log.d("@MBC", "mbcs with search STring :" + mbcsWithSearchString.size());
        userMBCs.clear();
        userMBCs.addAll(mbcsWithSearchString);
        userMBCListAdapter.notifyDataSetChanged();

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("@MBC", s.toString());
        searchMBC(s.toString());

    }
}
