package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class PaymentSummaryResponseMap extends ResponseInfo {

    @SerializedName("responsePayload")
    PaymentSummaryMap paymentSummaryMap;

    public PaymentSummaryMap getPaymentSummaryMap() {
        return paymentSummaryMap;
    }

    public void setPaymentSummaryMap(PaymentSummaryMap paymentSummaryMap) {
        this.paymentSummaryMap = paymentSummaryMap;
    }

}
