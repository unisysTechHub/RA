package com.bvifsc.mbc.assemblers;

import com.bvifsc.core.common.Converter;
import com.bvifsc.mbc.assemblers.converters.AmendCharterResponseConverter;
import com.bvifsc.mbc.assemblers.converters.BusinessPurposeResponseConverter;
import com.bvifsc.mbc.assemblers.converters.CountriesResponseConverter;
import com.bvifsc.mbc.assemblers.converters.HomePageConverter;
import com.bvifsc.mbc.assemblers.converters.KYCDocumentsListResponseConverter;
import com.bvifsc.mbc.assemblers.converters.MBCDataResponseConverter;
import com.bvifsc.mbc.assemblers.converters.MBCGenericDataResponseConverter;
import com.bvifsc.mbc.assemblers.converters.NationalitesReponseConverter;
import com.bvifsc.mbc.assemblers.converters.ParticipantRightsResponseConverter;
import com.bvifsc.mbc.assemblers.converters.PaymentSummaryResponseConverter;
import com.bvifsc.mbc.assemblers.converters.RegisterMBCResponseConverter;
import com.bvifsc.mbc.assemblers.converters.SecurityQuestionsResponseConverter;
import com.bvifsc.mbc.assemblers.converters.KYCDocumentsDetailsResponseConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class MBCServerResponseConverterLoader {
    public static Map<String,Class<? extends Converter>> CONVERTER_MAPPING = new HashMap<>();
    public static final String  HOME_PAGE = "homePage";
    public static String  SECURITY_QS = "securityQuestions";
    public static final String  SIGN_UP = "signUp";
    public static String  KYC_DOCUMENTS_TYPES = "kycDocumentTypes";
    public static String UPLOAD_KYC = "uploadKYC";
    public static String USER_KYC_DETAILS="kycDocsDetails";
    public static String COUNTRIES = "countries";
    public static String NATIONALITIES="nationalities";
    public static String BUSINESS_PURPOSE="businessPurpose";
    public static String PARTICIPANT_RIGHTS="participantRights";
    public static String PAYMENT_SUMMARY= "paymentSummary";
    public static String REGISTER_MBC="registerMBC";
    public static String MBC_DATA="mbcData";
    public static String AMEND_CHARTER="amendCharter";
    public static String MBC_GENERIC_DATA="mbcGenericData";


    static
    {
        CONVERTER_MAPPING.put(HOME_PAGE, HomePageConverter.class);
        CONVERTER_MAPPING.put(SECURITY_QS, SecurityQuestionsResponseConverter.class);
        CONVERTER_MAPPING.put(SIGN_UP, HomePageConverter.class);
        CONVERTER_MAPPING.put(KYC_DOCUMENTS_TYPES, KYCDocumentsListResponseConverter.class);
        CONVERTER_MAPPING.put(UPLOAD_KYC,HomePageConverter.class);
        CONVERTER_MAPPING.put(USER_KYC_DETAILS, KYCDocumentsDetailsResponseConverter.class);
        CONVERTER_MAPPING.put(COUNTRIES, CountriesResponseConverter.class);
        CONVERTER_MAPPING.put(NATIONALITIES, NationalitesReponseConverter.class);
        CONVERTER_MAPPING.put(BUSINESS_PURPOSE, BusinessPurposeResponseConverter.class);
        CONVERTER_MAPPING.put(PARTICIPANT_RIGHTS, ParticipantRightsResponseConverter.class);
        CONVERTER_MAPPING.put(PAYMENT_SUMMARY, PaymentSummaryResponseConverter.class);
        CONVERTER_MAPPING.put(REGISTER_MBC,RegisterMBCResponseConverter.class);
        CONVERTER_MAPPING.put(MBC_DATA, MBCDataResponseConverter.class);
        CONVERTER_MAPPING.put(AMEND_CHARTER,AmendCharterResponseConverter.class);
        CONVERTER_MAPPING.put(MBC_GENERIC_DATA, MBCGenericDataResponseConverter.class);

    }

    Map<String,Class<? extends Converter>> getConverters()
    {return  CONVERTER_MAPPING;}
}
