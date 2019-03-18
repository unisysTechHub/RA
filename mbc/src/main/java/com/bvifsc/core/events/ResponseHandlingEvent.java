package com.bvifsc.core.events;

import android.support.v4.app.Fragment;

import com.bvifsc.core.models.response.BaseResponse;

/**
 * Created by Ramesh on 19-11-2018.
 */
public class ResponseHandlingEvent {
      public  enum Action {ADD_FRAGMENT, REPLACE_FRAGMENT,DIALOG_FRAGMENT,ERROR_FRAGMENT,INFO_FRAGMENT}
     public Fragment fragment;
      public ResponseHandlingEvent.Action fragmentAction;
      public boolean addtobackstack;
     public boolean popbackstack;
     public BaseResponse baseResponse;



    ResponseHandlingEvent(Fragment fragment, ResponseHandlingEvent.Action fragmentAction, boolean addtobackstack, boolean popbackstack,BaseResponse baseResponse)
    {

        this.fragment=fragment;
        this.fragmentAction=fragmentAction;
        this.addtobackstack=addtobackstack;
        this.popbackstack=popbackstack;
        this.baseResponse=baseResponse;

    }
    public static ResponseHandlingEvent  createEventToReplaceFragment ( Fragment fragment, boolean addtobackstack,BaseResponse baseResponse)
    {
        return  new ResponseHandlingEvent(fragment,Action.REPLACE_FRAGMENT,addtobackstack,false,baseResponse);

    }

    public static ResponseHandlingEvent createEventToAddFragment(Fragment fragment,boolean addtobackstack,BaseResponse baseResponse)
    {
        return  new ResponseHandlingEvent(fragment,Action.ADD_FRAGMENT,addtobackstack,false,baseResponse);
    }

    public static ResponseHandlingEvent createEventToReplaceFragmentWithNoPopUp(Fragment fragment,BaseResponse baseResponse)
    {
        return new ResponseHandlingEvent(fragment,Action.REPLACE_FRAGMENT,true,false,baseResponse);
    }

    public static ResponseHandlingEvent createEventToReplaceFragmentWithPopUp(Fragment fragment,BaseResponse baseResponse)
    {
        return  new ResponseHandlingEvent(fragment,Action.REPLACE_FRAGMENT,true,true,baseResponse);

    }
    public static ResponseHandlingEvent createEventToOpenDialogFragment(Fragment fragment,BaseResponse baseResponse)
    {
        return  new ResponseHandlingEvent(fragment,Action.DIALOG_FRAGMENT,false,false,baseResponse);
    }
    public static ResponseHandlingEvent createEventToReplaceErrorFragment(Fragment fragment,BaseResponse baseResponse)
    {
        return new ResponseHandlingEvent(fragment,Action.ERROR_FRAGMENT,true,true,baseResponse);

    }

}
