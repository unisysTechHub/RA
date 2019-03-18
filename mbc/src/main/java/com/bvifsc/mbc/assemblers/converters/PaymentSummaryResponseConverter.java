package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.registerMBC.PaymentSummary;
import com.bvifsc.mbc.model.registerMBC.PaymentSummaryResponseModel;
import com.bvifsc.mbc.network.response.registerMBC.PaymentSummaryMap;
import com.bvifsc.mbc.network.response.registerMBC.PaymentSummaryResponseMap;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class PaymentSummaryResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {

        PaymentSummaryResponseMap paymentSummaryResponseMap=(PaymentSummaryResponseMap) var1;

        PaymentSummaryResponseModel paymentSummaryResponseModel=null;

        if(paymentSummaryResponseMap != null)
        {
            paymentSummaryResponseModel= new PaymentSummaryResponseModel(MBC_Constants.PAYMENT_SUMMARY,null);

            if(!paymentSummaryResponseMap.isSuccess())
            {
                paymentSummaryResponseModel.setSuccess(false);
                BusinessError businessError= new BusinessError(paymentSummaryResponseMap.getResponseCode(),null,paymentSummaryResponseMap.getMessage(),
                                MBC_Constants.NOTIFICATION,MBC_Constants.TOP);
                paymentSummaryResponseModel.setBusinessError(businessError);


            }
            else
              paymentSummaryResponseModel.setSuccess(true);

            paymentSummaryResponseModel.setPaymentSummary(convert(paymentSummaryResponseMap.getPaymentSummaryMap()));


        }

        return (R) paymentSummaryResponseModel;
    }

    PaymentSummary convert(PaymentSummaryMap paymentSummaryMap)
    {
        PaymentSummary paymentSummary = new PaymentSummary();
        paymentSummary.setTransactionDate(paymentSummaryMap.getTransactionDate());
        paymentSummary.setRaName(paymentSummaryMap.getRaName());
        paymentSummary.setTransactionName(paymentSummaryMap.getTransactionName());
        paymentSummary.setTransactionFee(paymentSummaryMap.getTransactionFee());
        paymentSummary.setFinalFee(paymentSummaryMap.getFinalFee());

        return paymentSummary;


    }
}
