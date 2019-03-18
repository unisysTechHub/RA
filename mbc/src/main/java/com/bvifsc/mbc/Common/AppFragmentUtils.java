package com.bvifsc.mbc.Common;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bvifsc.core.R;
import com.bvifsc.core.fragments.NotificationFragment;
import com.bvifsc.mbc.assemblers.MBC_PageLoder;
import com.bvifsc.mbc.events.OnResponseErrorEvent;
import com.bvifsc.mbc.fragments.AmendParticipantFragment;
import com.bvifsc.mbc.fragments.RegisterMBCPayment;
import com.bvifsc.mbc.fragments.RegisterParticipantMBC;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;

/**
 * Created by Ramesh on 30-11-2018.
 */
public class AppFragmentUtils {
    public static FragmentManager fragmentManager;
   public static FragmentManager getSupportFragmentManager()
   {return  fragmentManager;}
    public  static void sendNotification( OnResponseErrorEvent onResponseErrorEvent){



        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.notificationBarOverlay,NotificationFragment.newInstance(onResponseErrorEvent.getErrorMessage(),null),"TopNotification");
        fragmentTransaction.commit();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                NotificationFragment fragment= (NotificationFragment) fragmentManager.findFragmentByTag("TopNotification");
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();


            }
        },5000);


    }

    public static void sendErrorNotification(OnResponseErrorEvent onResponseErrorEvent)
    {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, onResponseErrorEvent.getErrorFragment(), onResponseErrorEvent.getPageType());
        fragmentTransaction.addToBackStack(onResponseErrorEvent.getPageType());
        fragmentTransaction.commit();

    }

    public static void loadLoginPage()
    {
        /*

        Converter converter = new SignInConverter();
        SignInResponseModel signInResponseModel=converter.convert(null);
        ResponseHandlingEvent responseHandlingEvent=signInResponseModel.buildResponseHandlingEvent();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout,responseHandlingEvent.fragment,responseHandlingEvent.baseResponse.getPageType());
        fragmentTransaction.addToBackStack(responseHandlingEvent.baseResponse.getPageType());
        fragmentTransaction.commit();
        */

        try {
            FragmentLoader fragmentLoader= (FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(MBC_Constants.signIn).newInstance();
            fragmentLoader.load();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void loadRegisterParticipantMBCFragment(RegisterMBCPageResponseModel registerMBCPageResponseModel,  String tag)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, RegisterParticipantMBC.newInstance(registerMBCPageResponseModel),tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();


    }

    public static void loadRegisterMBCPaymentFragment(RegisterMBCPageResponseModel registerMBCPageResponseModel)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, RegisterMBCPayment.newInstance(registerMBCPageResponseModel),MBC_Constants.REGISTER_MBC_PAYMENT_SUMMARY);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    public static void loadAmendParticipantFragment(RegisterMBCPageResponseModel registerMBCPageResponseModel)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, AmendParticipantFragment.newInstance(registerMBCPageResponseModel),MBC_Constants.AMEND_PARTICIPANT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();





    }

}
