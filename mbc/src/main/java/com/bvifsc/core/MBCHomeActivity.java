package com.bvifsc.core;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.bvifsc.core.common.Converter;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.fragments.NotificationFragment;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.AppFragmentUtils;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.SharedPreferencesUtils;
import com.bvifsc.mbc.adapters.UserMBCListAdapter;
import com.bvifsc.mbc.assemblers.MBC_PageLoder;
import com.bvifsc.mbc.events.OnExceptionEvent;
import com.bvifsc.mbc.events.OnMBCSelected;
import com.bvifsc.mbc.events.OnNewMbcRegistered;
import com.bvifsc.mbc.events.OnResponseErrorEvent;
import com.bvifsc.mbc.fragments.AmendParticipantFragment;
import com.bvifsc.mbc.fragments.ErrorFragment;
import com.bvifsc.mbc.fragments.RegisterMBCFragment;
import com.bvifsc.mbc.fragments.RegisterParticipantMBC;
import com.bvifsc.mbc.fragments.UserMBCListFragment;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.pageModels.KYCDocumentsPage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;


public class MBCHomeActivity extends AppCompatActivity {
        android.support.v7.widget.Toolbar toolbar;
        TextView actionBarTitle;
        DrawerLayout mDrawerLayout;
        NavigationView navigationView;
        Spinner myMBCs;
        ArrayList<String> myMBCList = new ArrayList<>();
        ArrayAdapter myMBCListAdapter;

        @Inject
        SharedPreferencesUtils sharedPreferencesUtils;

        @Inject
        UserAuthInfo userAuthInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ra_home_drawer_layout);
        BVIRA_Application.getObjectGraph(getApplicationContext()).inject(this);
        mDrawerLayout=findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setVisibility(View.VISIBLE);
        myMBCs=navigationView.getHeaderView(0).findViewById(R.id.my_mbcs);

        disableAllNavigationMenuOptions();
        //ExpandableListView navExpandedView=findViewById(R.id.nav_expanded_view);
        //

        toolbar=findViewById(R.id.toolbar);
        actionBarTitle=findViewById(R.id.actionbarTitle);
        actionBarTitle.setText("BVI RA ");
        setSupportActionBar(toolbar);


        setUpMyMBCsSpinner(myMBCs);
        AppFragmentUtils.fragmentManager=getSupportFragmentManager();
        sharedPreferencesUtils.isWhenApplaunched(true);



    }

    void setUpMyMBCsSpinner(Spinner myMBCsSpinner)
    {

        if(sharedPreferencesUtils.getMBCsSet() != null)
        myMBCList.addAll(sharedPreferencesUtils.getMBCsSet());
        myMBCListAdapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,myMBCList);
        myMBCsSpinner.setAdapter(myMBCListAdapter);
        myMBCsSpinner.setSelection(myMBCList.indexOf(sharedPreferencesUtils.getCurrentMBC()));

        myMBCsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentMBCNumber =myMBCList.get(position);
                sharedPreferencesUtils.setCurrentMBC(currentMBCNumber);
                ((TextView) view).setTextColor(MBCHomeActivity.this.getResources().getColor(R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
   void setDrawerActionViewListeners()
    {


          MenuItem mbc_management = navigationView.getMenu().findItem(R.id.MBC_management);

        mbc_management.getActionView().setOnClickListener(view -> {

            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("expand")) {

                navigationView.getMenu().setGroupVisible(R.id.mbc_Management_submenu,true);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.minus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("collapse");
            }
            else
            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("collapse"))
            {
                navigationView.getMenu().setGroupVisible(R.id.mbc_Management_submenu,false);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.plus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("expand");


            }
        });

        MenuItem mbc_disposition = navigationView.getMenu().findItem(R.id.mbc_Disposition);

        mbc_disposition.getActionView().setOnClickListener(view -> {

            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("expand")) {
                navigationView.getMenu().setGroupVisible(R.id.mbc_Disposition_submenu,true);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.minus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("collapse");
            }
            else
            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("collapse"))
            {
                navigationView.getMenu().setGroupVisible(R.id.mbc_Disposition_submenu,false);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.plus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("expand");


            }
        });

        MenuItem mbc_transformation = navigationView.getMenu().findItem(R.id.mbc_transformation);

        mbc_transformation.getActionView().setOnClickListener(view -> {

            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("expand")) {
                navigationView.getMenu().setGroupVisible(R.id.transformation_submenu,true);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.minus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("collapse");
            }
            else
            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("collapse"))
            {
                navigationView.getMenu().setGroupVisible(R.id.transformation_submenu,false);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.plus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("expand");


            }
        });

        MenuItem mbc_administration = navigationView.getMenu().findItem(R.id.mbc_administration);

        mbc_administration.getActionView().setOnClickListener(view -> {

            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("expand")) {
                navigationView.getMenu().setGroupVisible(R.id.mbc_administration_submenu,true);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.minus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("collapse");
            }
            else
            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("collapse"))
            {
                navigationView.getMenu().setGroupVisible(R.id.mbc_administration_submenu,false);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.plus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("expand");


            }
        });

        MenuItem mbc_portfolio_management = navigationView.getMenu().findItem(R.id.portfolio_management);

        mbc_portfolio_management.getActionView().setOnClickListener(view -> {

            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("expand")) {
                navigationView.getMenu().setGroupVisible(R.id.portfolio_management_submenu,true);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.minus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("collapse");
            }
            else
            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("collapse"))
            {
                navigationView.getMenu().setGroupVisible(R.id.portfolio_management_submenu,false);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.plus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("expand");


            }
        });

        MenuItem mbc_quick_links = navigationView.getMenu().findItem(R.id.quick_links);

        mbc_quick_links.getActionView().setOnClickListener(view -> {

            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("expand")) {
                navigationView.getMenu().setGroupVisible(R.id.quick_links_sub_menu,true);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.minus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("collapse");
            }
            else
            if(view.findViewById(R.id.expand_collapse_icon).getTag().equals("collapse"))
            {
                navigationView.getMenu().setGroupVisible(R.id.quick_links_sub_menu,false);
                ((ImageView)view.findViewById(R.id.expand_collapse_icon)).setImageResource(R.drawable.plus_sign);
                view.findViewById(R.id.expand_collapse_icon).setTag("expand");


            }
        });

    }

    void onNavigationITemSelected()
    {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        Log.d("@BVIRA", "menu Ttile " + menuItem.getTitle());
                        menuItem.setChecked(true);
                        if(menuItem.getItemId() == R.id.KYC)
                        {

                            try {
                                FragmentLoader    fragmentLoader = (FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(MBC_Constants.KYC_DOCUMENTS).newInstance();
                                fragmentLoader.load();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }



                        }

                        if(menuItem.getItemId() == R.id.register_mbc)
                        {

                            try {
                                FragmentLoader fragmentLoader = (FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(MBC_Constants.REGISTER_MBC).newInstance();
                                fragmentLoader.load();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }

                        }

                        if(menuItem.getItemId() == R.id.amend_charter)
                        {
                            try {

                                FragmentLoader fragmentLoader= (FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(MBC_Constants.AMEND_CHARTER).newInstance();
                                fragmentLoader.load();

                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }


                        }
                        if(menuItem.getItemId() == R.id.user_mbc_list)
                        {
                            UserMBCListFragment userMBCListFragment= new UserMBCListFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,userMBCListFragment).addToBackStack(null).commit();

                        }

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onEvent(ResponseHandlingEvent responseHandlingEvent)
    {
        Log.d("@MBC", "KYC Submitted Ind " + userAuthInfo.isKycSubmitted());
        Log.d("@MBC", "KYC approved ind " + userAuthInfo.isKycApproved() );
        actionBarTitle.setText(userAuthInfo.getEntityName());
        if(responseHandlingEvent.baseResponse.getPageType().equalsIgnoreCase("homePage")  )
            loadNavigationMenu(responseHandlingEvent);
        if(responseHandlingEvent.baseResponse.getPageType().equalsIgnoreCase("signUp"))
            loadNavigationMenu(responseHandlingEvent);

        Log.d("@Ramesh", "ResponseHandling Event" + responseHandlingEvent.baseResponse.getPageType());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,responseHandlingEvent.fragment,responseHandlingEvent.baseResponse.getPageType());
        fragmentTransaction.addToBackStack(responseHandlingEvent.baseResponse.getPageType());
        fragmentTransaction.commit();


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    void onEvent(OnNewMbcRegistered onNewMbcRegistered)
    {
        myMBCList.add(0,onNewMbcRegistered.getMbcNumber());
        myMBCListAdapter.notifyDataSetChanged();
        myMBCs.setSelection(myMBCList.indexOf(onNewMbcRegistered.getMbcNumber()));



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onEvent(OnMBCSelected onMBCSelected)
    {
        myMBCs.setSelection(myMBCList.indexOf(onMBCSelected.getMbcNumber()));



    }



    void loadNavigationMenu(ResponseHandlingEvent responseHandlingEvent)
    {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setVisibility(View.VISIBLE);
        View headerLayout = navigationView.getHeaderView(0);

        ImageView userProfileImage=headerLayout.findViewById(R.id.mbc_user_image);
        TextView mbcUserName=headerLayout.findViewById(R.id.mbc_user_name);

       if(userAuthInfo.getDocument()!=null) {
           byte[] byteArray = Base64.decode(userAuthInfo.getDocument(), Base64.DEFAULT);
           Bitmap profileImageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
           userProfileImage.setImageBitmap(profileImageBitmap);

       }
        if(userAuthInfo.getFirstName()!=null)
        mbcUserName.setText(userAuthInfo.getFirstName());



        if(( !userAuthInfo.isKycSubmitted() ) || (userAuthInfo.isKycSubmitted() && !userAuthInfo.isKycApproved()))
        {
            enableOnlyKYCNavigationMenu();
        }


        if(userAuthInfo.isKycApproved())
            enableAllNavigationMenuOptions();

        setDrawerActionViewListeners();

        onNavigationITemSelected();

    }
    void disableAllNavigationMenuOptions()
    {
        Log.d("@MBC", "disableAllNavigationMenuOptions");
        navigationView.getMenu().setGroupVisible(R.id.mbc_Management_submenu,false);
        navigationView.getMenu().setGroupVisible(R.id.mbc_Disposition_submenu,false);
        navigationView.getMenu().setGroupVisible(R.id.transformation_submenu,false);
        navigationView.getMenu().setGroupVisible(R.id.mbc_administration_submenu,false);
        navigationView.getMenu().setGroupVisible(R.id.portfolio_management_submenu,false);
        navigationView.getMenu().setGroupVisible(R.id.quick_links_sub_menu,false);
        navigationView.getMenu().findItem(R.id.dashBoard).setVisible(false);
        navigationView.getMenu().findItem(R.id.issueManagement).setVisible(false);
        navigationView.getMenu().findItem(R.id.MBC_management).setVisible(false);
        navigationView.getMenu().findItem(R.id.mbc_Disposition).setVisible(false);
        navigationView.getMenu().findItem(R.id.mbc_transformation).setVisible(false);
        navigationView.getMenu().findItem(R.id.quick_links).setVisible(false);
        navigationView.getMenu().findItem(R.id.portfolio_management).setVisible(false);
        navigationView.getMenu().findItem(R.id.mbc_administration).setVisible(false);
        navigationView.getMenu().findItem(R.id.KYC).setVisible(false);
        navigationView.getMenu().findItem(R.id.user_mbc_list).setVisible(false);

    }
    void enableAllNavigationMenuOptions()
    {
        Log.d("@MBC","enableAllNavigationMenuOptions" );

        navigationView.getMenu().findItem(R.id.MBC_management).setVisible(true);
        navigationView.getMenu().findItem(R.id.mbc_Disposition).setVisible(true);
        navigationView.getMenu().findItem(R.id.mbc_transformation).setVisible(true);
        navigationView.getMenu().findItem(R.id.mbc_administration).setVisible(true);
        navigationView.getMenu().findItem(R.id.portfolio_management).setVisible(true);
        navigationView.getMenu().findItem(R.id.quick_links).setVisible(true);

        navigationView.getMenu().findItem(R.id.dashBoard).setVisible(true);
        navigationView.getMenu().findItem(R.id.issueManagement).setVisible(true);
        navigationView.getMenu().findItem(R.id.MBC_management).setVisible(true);
        navigationView.getMenu().findItem(R.id.mbc_Disposition).setVisible(true);
        navigationView.getMenu().findItem(R.id.mbc_transformation).setVisible(true);
        navigationView.getMenu().findItem(R.id.quick_links).setVisible(true);
        navigationView.getMenu().findItem(R.id.portfolio_management).setVisible(true);
        navigationView.getMenu().findItem(R.id.mbc_administration).setVisible(true);
        navigationView.getMenu().findItem(R.id.KYC).setVisible(true);
        navigationView.getMenu().findItem(R.id.user_mbc_list).setVisible(true);


    }
    void enableOnlyKYCNavigationMenu()
    {
        Log.d("@MBC", "enableOnlyKYCNavigationMenu" );

        navigationView.getMenu().findItem(R.id.KYC).setVisible(true);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    void onEvent(OnResponseErrorEvent onResponseErrorEvent)
    {
        Log.d("@MBCRA", "onError Response event");
        if(onResponseErrorEvent.getMessageStyle().equalsIgnoreCase("center")) {

            sendErrorNotification(onResponseErrorEvent);
        }
        else
            if(onResponseErrorEvent.getMessageStyle().equalsIgnoreCase("top"))
                sendTopNotification(onResponseErrorEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onEvent(OnExceptionEvent onExceptionEvent)
    {
                OnResponseErrorEvent onResponseErrorEvent = new OnResponseErrorEvent();
                onResponseErrorEvent.setErrorMessage("We're sorry. It looks like we are having trouble connecting. Check your connection and Try Again");
        onResponseErrorEvent.setErrorFragment(ResponseHandlingEvent.createEventToReplaceErrorFragment(ErrorFragment.newInstance(onResponseErrorEvent.getErrorMessage()),null).fragment);


        sendErrorNotification(onResponseErrorEvent);

    }

    void sendTopNotification(OnResponseErrorEvent onResponseErrorEvent)
    {
        AppFragmentUtils.sendNotification(onResponseErrorEvent);


    }
    void sendErrorNotification(OnResponseErrorEvent onResponseErrorEvent)
    {

        AppFragmentUtils.sendErrorNotification(onResponseErrorEvent);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sharedPreferencesUtils.getIsWhenAppLaunched()) {
            AppFragmentUtils.loadLoginPage();
            sharedPreferencesUtils.isWhenApplaunched(false);

        }

    }

    @Override
    public void onStop() {

        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
