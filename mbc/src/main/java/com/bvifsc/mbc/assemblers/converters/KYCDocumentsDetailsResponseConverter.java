package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.assemblers.CommonModelConverter;
import com.bvifsc.mbc.datamodel.KYC;
import com.bvifsc.mbc.model.kycDocuments.KYCDocsDetailsResponseModel;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.network.request.FileData;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocumentMap;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocumentsDetailsResponseMap;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocsDetailsMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 31-12-2018.
 */
public class KYCDocumentsDetailsResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {

        KYCDocumentsDetailsResponseMap kycDocumentsDetailsResponseMap=(KYCDocumentsDetailsResponseMap) var1;

        KYCDocsDetailsResponseModel useKYCDocsDetailsResponseModel = new KYCDocsDetailsResponseModel("useKYCDocsDetails",null);


        if(!kycDocumentsDetailsResponseMap.isSuccess())
        {
            useKYCDocsDetailsResponseModel.setSuccess(false);
        }
        else
            useKYCDocsDetailsResponseModel.setSuccess(true);

        List<KYC> kycDocsDetailsListModel;

        if(kycDocumentsDetailsResponseMap.getKycDocsDetailsMap()!= null) {

            kycDocsDetailsListModel = new ArrayList<>();
            updateUseAuthInfo(kycDocumentsDetailsResponseMap.getKycDocsDetailsMap());


            if (kycDocumentsDetailsResponseMap.getKycDocsDetailsMap().getKyc3DocumentsDetails().getKycDocument1Map() != null) {

                kycDocsDetailsListModel.add(convert(kycDocumentsDetailsResponseMap.getKycDocsDetailsMap().getKyc3DocumentsDetails().getKycDocument1Map()));

            }
            if (kycDocumentsDetailsResponseMap.getKycDocsDetailsMap().getKyc3DocumentsDetails().getKycDocument2Map() != null) {

                kycDocsDetailsListModel.add(convert(kycDocumentsDetailsResponseMap.getKycDocsDetailsMap().getKyc3DocumentsDetails().getKycDocument2Map()));
            }

            if (kycDocumentsDetailsResponseMap.getKycDocsDetailsMap().getKyc3DocumentsDetails().getKycDocument3Map() != null) {

                kycDocsDetailsListModel.add(convert(kycDocumentsDetailsResponseMap.getKycDocsDetailsMap().getKyc3DocumentsDetails().getKycDocument3Map()));
            }


            useKYCDocsDetailsResponseModel.setUserKycDocsDetailsList(kycDocsDetailsListModel);
        }


        return (R) useKYCDocsDetailsResponseModel;
    }

    KYC convert(KYCDocumentMap kycDocumentMap)
    {
           KYC kyc = new KYC();
        kyc.setKycDocType(kycDocumentMap.getKycDocumentTypeMap().getDocumentTypeId());
        FileData kycFileData= new FileData();
        kycFileData.setFileName(kycDocumentMap.getDocumentName());
        kycFileData.setBitmap(CommonModelConverter.converter(kycDocumentMap.getImageEncodedString()));
        kyc.setFileData(kycFileData);

        return kyc;
    }
    void updateUseAuthInfo( KYCDocsDetailsMap userKYCDocsDetailsMap) {

        UserAuthInfo userAuthInfo = UserAuthInfo.getInstance();

        if (userKYCDocsDetailsMap.getKycDoc1ApprovedInd() != null) {
            if (userKYCDocsDetailsMap.getKycDoc1ApprovedInd().equals("Y"))
                userAuthInfo.setKycDoc1Approved(true);
            else
                userAuthInfo.setKycDoc1Approved(false);

        } else
            userAuthInfo.setKycDoc1Approved(false);


        if (userKYCDocsDetailsMap.getKycDoc2ApprovedInd() != null) {
            if (userKYCDocsDetailsMap.getKycDoc2ApprovedInd().equals("Y"))
                userAuthInfo.setKycDoc2Approved(true);
            else
                userAuthInfo.setKycDoc2Approved(false);
        } else
            userAuthInfo.setKycDoc2Approved(false);

        if (userKYCDocsDetailsMap.getKycDoc3ApprovedInd() != null) {
            if (userKYCDocsDetailsMap.getKycDoc3ApprovedInd().equals("Y"))
                userAuthInfo.setKycDoc3Approved(true);
            else
                userAuthInfo.setKycDoc3Approved(false);
        } else
            userAuthInfo.setKycDoc3Approved(false);

        userAuthInfo.setUserId(userKYCDocsDetailsMap.getUserLoginId());

        userAuthInfo.setKycDoc1RAComment(userKYCDocsDetailsMap.getKyc3DocumentsDetails().getKycDoc1AuthComment());
        userAuthInfo.setKycDoc2RAComment(userKYCDocsDetailsMap.getKyc3DocumentsDetails().getKycDoc2AuthComment());
        userAuthInfo.setKycDoc3RAcomment(userKYCDocsDetailsMap.getKyc3DocumentsDetails().getKycDoc3AuthComment());
    }


}


