package com.bvifsc.mbc.network.response.registerMBC;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class PaymentSummaryMap {
    @SerializedName("transactionDate")
    String transactionDate;

    @SerializedName("raName")
    String raName;

    @SerializedName("balance")
    double balance;

    @SerializedName("transactionName")
    String transactionName;

    @SerializedName("transactionFee")
    double transactionFee;

    @SerializedName("finalFee")
    double finalFee;


    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRaName() {
        return raName;
    }

    public void setRaName(String raName) {
        this.raName = raName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public double getFinalFee() {
        return finalFee;
    }

    public void setFinalFee(double finalFee) {
        this.finalFee = finalFee;
    }
}
