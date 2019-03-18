package com.bvifsc.mbc.model.registerMBC;

import com.bvifsc.core.models.response.BaseResponse;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class PaymentSummaryResponseModel extends BaseResponse {

    PaymentSummary paymentSummary;

    public PaymentSummaryResponseModel(String pageType, String header) {
        super(pageType, header);
    }


    public PaymentSummary getPaymentSummary() {
        return paymentSummary;
    }

    public void setPaymentSummary(PaymentSummary paymentSummary) {
        this.paymentSummary = paymentSummary;
    }
}
