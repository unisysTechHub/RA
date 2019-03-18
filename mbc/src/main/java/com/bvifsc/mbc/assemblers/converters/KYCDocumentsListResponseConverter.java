package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentsListResponseModel;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocumentTypeMap;
import com.bvifsc.mbc.network.response.kycdocuments.KYCDocumentsListResponseMap;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentTypeModel;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentsListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYCDocumentsListResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T response) {

        KYCDocumentsListResponseMap kycDocumentsListResponseMap=(KYCDocumentsListResponseMap)response;
        KYCDocumentsListResponseModel kycDocumentsListResponseModel=null;
        if(kycDocumentsListResponseMap != null)
        {
             kycDocumentsListResponseModel = new KYCDocumentsListResponseModel("KYCDocumentsList",null);

            if(!kycDocumentsListResponseMap.isSuccess())
            {
                 kycDocumentsListResponseModel.setSuccess(false);
            }
            else
                kycDocumentsListResponseModel.setSuccess(true);


            if(kycDocumentsListResponseMap.getKycDocumentTypeListMap() != null)
            {
                KYCDocumentsListModel kycDocumentsListModel = new KYCDocumentsListModel();

                kycDocumentsListModel.setKycDocTypeList(convert(kycDocumentsListResponseMap.getKycDocumentTypeListMap()));

                kycDocumentsListResponseModel.setKycDocumentsListModel(kycDocumentsListModel);

            }

        }

        return (R) kycDocumentsListResponseModel;
    }

    private List<KYCDocumentTypeModel> convert(List<KYCDocumentTypeMap> kycDocumentTypeMapList)
    {
        List<KYCDocumentTypeModel> kycDocumentsList = new ArrayList<>();
        KYCDocumentTypeModel kycDocumentModelSelect = new KYCDocumentTypeModel();
        kycDocumentModelSelect.setDocumentTypeId(0);
        kycDocumentModelSelect.setDocumentType("Select Document Type");
        kycDocumentsList.add(kycDocumentModelSelect);

        for(KYCDocumentTypeMap kycDocumentTypeMap : kycDocumentTypeMapList)
        {
            if(kycDocumentTypeMap.getDocumentTypeId() > 0 && kycDocumentTypeMap.getDocumentType() != null) {

                KYCDocumentTypeModel kycDocumentModel= new KYCDocumentTypeModel();
                kycDocumentModel.setDocumentTypeId(kycDocumentTypeMap.getDocumentTypeId());
                kycDocumentModel.setDocumentType(kycDocumentTypeMap.getDocumentType());
                kycDocumentsList.add(kycDocumentModel);
            }

        }

        return kycDocumentsList;
    }
}
