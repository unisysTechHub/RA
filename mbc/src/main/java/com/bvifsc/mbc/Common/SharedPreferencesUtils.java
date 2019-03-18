package com.bvifsc.mbc.Common;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Ramesh on 27-12-2018.
 */
public class SharedPreferencesUtils {
    SharedPreferences sharedPreferences;
    public static String IS_WHEN_APP_LAUNCHED="whenAppLaunched";
    public static String MBC_LIST="myMBCList";
    public static String MBC_NUMBER="mbcNumber";
  public  SharedPreferencesUtils(SharedPreferences preferences)
    {
        this.sharedPreferences=preferences;

    }
   public boolean isWhenApplaunched(boolean isEnabled)
    {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(IS_WHEN_APP_LAUNCHED, isEnabled);
        return prefsEditor.commit();
        //return false;
    }

    public boolean getIsWhenAppLaunched()
    {
        return sharedPreferences.getBoolean(IS_WHEN_APP_LAUNCHED,false);

    }

    public boolean saveRegisteredMBCNumber(String mbcNumber)
    {
        Set<String> myMbcs = getMBCsSet();
        if(myMbcs == null) {
            myMbcs=new HashSet<>();
        }

        myMbcs.add(mbcNumber);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putStringSet(MBC_LIST, myMbcs);
         prefsEditor.commit();
         getMBCsSet();
        return true;
    }

    public Set<String> getMBCsSet()
    {
        Set<String> defValues=null;



        return sharedPreferences.getStringSet(MBC_LIST,null);
    }


    public void setCurrentMBC(String mbcNumber)
    {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(MBC_NUMBER, mbcNumber);
        prefsEditor.commit();

    }

    public String getCurrentMBC()
    {
        return sharedPreferences.getString(MBC_NUMBER,null);
    }

}
