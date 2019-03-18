package com.bvifsc.mbc.assemblers;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.bvifsc.mbc.network.response.amendCharter.AmendCharterResponseMap;
import com.bvifsc.mbc.network.response.amendCharter.MBCDataResponseMap;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocumentsDetailsResponseMap;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocumentsListResponseMap;
import com.bvifsc.mbc.network.response.mbcView.MBCGenericDataResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.BusinessPurposeResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.CountriesResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.NationalitiesResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.ParticipantRightsResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.PaymentSummaryResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.RegisterMBCResponseMap;
import com.bvifsc.mbc.network.response.signIn.SignInResponseMap;
import com.bvifsc.mbc.network.response.signUp.SecurityQuestionsResponseMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class MBCServerResponseLoader {
    public static Map<String,Class<? extends ResponseInfo>> RESPONSE_MAPPING = new HashMap<>();
    public static String  HOME_PAGE = "homePage";
    public static String  SIGN_UP= "signUp";
    public static String  SECURITY_QS = "securityQuestions";
    public static String  KYC_DOCUMENTS_TYPES = "kycDocumentTypes";
    public static String UPLOAD_KYC="uploadKYC";
    public static String KYC_DOCS_DETAILS="kycDocsDetails";

    public static String COUNTRIES = "countries";
    public static String NATIONALITIES="nationalities";
    public static String BUSINESS_PURPOSE="businessPurpose";
    public static String PARTICIPANT_RIGHTS="participantRights";
    public static String PAYMENT_SUMMARY= "paymentSummary";
    public static String REGISTER_MBC="registerMBC";
    public static String MBC_DATA="mbcData";
    public static String MBC_GENERIC_DATA="mbcGenericData";
    public static String AMEND_CHARTER="amendCharter";




    static
    {
        RESPONSE_MAPPING.put(HOME_PAGE, SignInResponseMap.class);
        RESPONSE_MAPPING.put(SECURITY_QS, SecurityQuestionsResponseMap.class);
        RESPONSE_MAPPING.put(SIGN_UP, SignInResponseMap.class);
        RESPONSE_MAPPING.put(KYC_DOCUMENTS_TYPES, KYCDocumentsListResponseMap.class);
        RESPONSE_MAPPING.put(UPLOAD_KYC,SignInResponseMap.class);
        RESPONSE_MAPPING.put(KYC_DOCS_DETAILS, KYCDocumentsDetailsResponseMap.class);
        //Register MBC
        RESPONSE_MAPPING.put(COUNTRIES, CountriesResponseMap.class);
        RESPONSE_MAPPING.put(NATIONALITIES, NationalitiesResponseMap.class);
        RESPONSE_MAPPING.put(BUSINESS_PURPOSE, BusinessPurposeResponseMap.class);
        RESPONSE_MAPPING.put(PARTICIPANT_RIGHTS, ParticipantRightsResponseMap.class);
        RESPONSE_MAPPING.put(PAYMENT_SUMMARY, PaymentSummaryResponseMap.class);
        RESPONSE_MAPPING.put(REGISTER_MBC,RegisterMBCResponseMap.class);
        RESPONSE_MAPPING.put(MBC_DATA, MBCDataResponseMap.class);
        RESPONSE_MAPPING.put(MBC_GENERIC_DATA, MBCGenericDataResponseMap.class);
        RESPONSE_MAPPING.put(AMEND_CHARTER,AmendCharterResponseMap.class);

    }

    public static  Map<String,Class<? extends ResponseInfo>> getResponseMap()
    {return RESPONSE_MAPPING;}
}
