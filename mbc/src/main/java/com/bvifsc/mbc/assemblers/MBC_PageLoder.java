package com.bvifsc.mbc.assemblers;

import com.bvifsc.mbc.pageModels.AmendCharter;
import com.bvifsc.mbc.pageModels.MBCView;
import com.bvifsc.mbc.pageModels.RegisterMBCPage;
import com.bvifsc.mbc.pageModels.SignInPageModel;
import com.bvifsc.mbc.pageModels.KYCDocumentsPage;
import com.bvifsc.mbc.pageModels.SignUpPageModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class MBC_PageLoder {
    public static Map<String,Class<?>> PAGE_MAPPING = new HashMap<>();
        public static String SIGN_IN = "signIn";
        public static String  SIGN_UP= "signUp";
        public static String KYC_DOCUMENTS= "kycDocuments";
        public static String REGISTER_MBC="registerMBC";
        public static String AMEND_CHARTER="amendCharter";
        public static String MBC_VIEW="mbcView";


    static
    {
        PAGE_MAPPING.put(SIGN_IN, SignInPageModel.class);
        PAGE_MAPPING.put(SIGN_UP, SignUpPageModel.class);
        PAGE_MAPPING.put(KYC_DOCUMENTS, KYCDocumentsPage.class);
        PAGE_MAPPING.put(REGISTER_MBC, RegisterMBCPage.class);
        PAGE_MAPPING.put(AMEND_CHARTER, AmendCharter.class);
        PAGE_MAPPING.put(MBC_VIEW,MBCView.class);

    }

    public static  Map<String,Class<?>> getResponseMap()
    {return PAGE_MAPPING;}
}
