package com.bvifsc.mbc.model.registerMBC;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class PaymentSummary implements Parcelable{

    String transactionDate;


    String raName;


    double balance;


    String transactionName;


    double transactionFee;


    double finalFee;
    public PaymentSummary()
    {}

    protected PaymentSummary(Parcel in) {
        transactionDate = in.readString();
        raName = in.readString();
        balance = in.readDouble();
        transactionName = in.readString();
        transactionFee = in.readDouble();
        finalFee = in.readDouble();
    }

    public static final Creator<PaymentSummary> CREATOR = new Creator<PaymentSummary>() {
        @Override
        public PaymentSummary createFromParcel(Parcel in) {
            return new PaymentSummary(in);
        }

        @Override
        public PaymentSummary[] newArray(int size) {
            return new PaymentSummary[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(transactionDate);
        dest.writeString(raName);
        dest.writeDouble(balance);
        dest.writeString(transactionName);
        dest.writeDouble(transactionFee);
        dest.writeDouble(finalFee);
    }
}
