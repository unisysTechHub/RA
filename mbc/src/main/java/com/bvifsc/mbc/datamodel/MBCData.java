package com.bvifsc.mbc.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.bvifsc.mbc.model.registerMBC.BusinessPurpose;
import com.bvifsc.mbc.model.registerMBC.Country;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 04-01-2019.
 */
public class MBCData implements Parcelable {


    @SerializedName("mbcNamePrefix")
     String NamePrefix;


    @SerializedName("country")
     Country countryOfOperation;


    @SerializedName("businessPurpose")
     BusinessPurpose businessPurpose;


    @SerializedName("mbcDoingBusinessAs")
     String doingBusinessAs;


    @SerializedName("principalShareholder")
     PrincipalShareholder principalShareholder;


    @SerializedName("participantShareholderTbls")
     List<ParticipantShareholder> participantShareholders;


    @SerializedName("mbcTotalShares")
     int noOfShares;

    transient int   firstParticipantSeqNo;

    transient int transactionFee;

    transient boolean confirmMBCAct;
    transient boolean isPrincipalDataChanged;


    @SerializedName("paymentMode")
     String paymentMode;


    @SerializedName("checkBox")
     boolean checkBoxTerms;


    @SerializedName("registeredAgentId")
     RegisteredAgent registeredAgent;


   @SerializedName("mbcGrossAssetsInd")
     String mbcGrossAssetsInd= "Y";


   @SerializedName("mbcNumberEmployeesInd")
    String mbcNumberEmployeesInd="Y";


    @SerializedName("raWrittenConfirmFlag")
     String raWrittenConfirmFlag ="Y";


    @SerializedName("raConsentFlag")
     String raConsentFlag="Y";


     int mbcId = 0;

     String mbcNumber="MBC";

    String raUserName;
    String dateOfIncorporation;
    int transformationFilingYear=0;

    public MBCData()
    {}

    protected MBCData(Parcel in) {
        NamePrefix = in.readString();
        countryOfOperation = in.readParcelable(Country.class.getClassLoader());
        businessPurpose = in.readParcelable(BusinessPurpose.class.getClassLoader());
        doingBusinessAs = in.readString();
        principalShareholder = in.readParcelable(PrincipalShareholder.class.getClassLoader());
        participantShareholders = in.createTypedArrayList(ParticipantShareholder.CREATOR);
        noOfShares = in.readInt();
        paymentMode = in.readString();
        checkBoxTerms = in.readByte() != 0;
        registeredAgent = in.readParcelable(RegisteredAgent.class.getClassLoader());
        mbcGrossAssetsInd = in.readString();
        mbcNumberEmployeesInd = in.readString();
        raWrittenConfirmFlag = in.readString();
        raConsentFlag = in.readString();
        mbcId = in.readInt();
        mbcNumber = in.readString();
        raUserName = in.readString();
        dateOfIncorporation = in.readString();
        transformationFilingYear = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(NamePrefix);
        dest.writeParcelable(countryOfOperation, flags);
        dest.writeParcelable(businessPurpose, flags);
        dest.writeString(doingBusinessAs);
        dest.writeParcelable(principalShareholder, flags);
        dest.writeTypedList(participantShareholders);
        dest.writeInt(noOfShares);
        dest.writeString(paymentMode);
        dest.writeByte((byte) (checkBoxTerms ? 1 : 0));
        dest.writeParcelable(registeredAgent, flags);
        dest.writeString(mbcGrossAssetsInd);
        dest.writeString(mbcNumberEmployeesInd);
        dest.writeString(raWrittenConfirmFlag);
        dest.writeString(raConsentFlag);
        dest.writeInt(mbcId);
        dest.writeString(mbcNumber);
        dest.writeString(raUserName);
        dest.writeString(dateOfIncorporation);
        dest.writeInt(transformationFilingYear);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MBCData> CREATOR = new Creator<MBCData>() {
        @Override
        public MBCData createFromParcel(Parcel in) {
            return new MBCData(in);
        }

        @Override
        public MBCData[] newArray(int size) {
            return new MBCData[size];
        }
    };

    public String getNamePrefix() {
        return NamePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        NamePrefix = namePrefix;
    }

    public Country getCountryOfOperation() {
        return countryOfOperation;
    }

    public void setCountryOfOperation(Country countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }

    public BusinessPurpose getBusinessPurpose() {
        return businessPurpose;
    }

    public void setBusinessPurpose(BusinessPurpose businessPurpose) {
        this.businessPurpose = businessPurpose;
    }

    public String getDoingBusinessAs() {
        return doingBusinessAs;
    }

    public void setDoingBusinessAs(String doingBusinessAs) {
        this.doingBusinessAs = doingBusinessAs;
    }

    public PrincipalShareholder getPrincipalShareholder() {
        return principalShareholder;
    }

    public void setPrincipalShareholder(PrincipalShareholder principalShareholder) {
        this.principalShareholder = principalShareholder;
    }

    public List<ParticipantShareholder> getParticipantShareholders() {
        return participantShareholders;
    }

    public void setParticipantShareholders(List<ParticipantShareholder> participantShareholders) {
        this.participantShareholders = participantShareholders;
    }

    public int getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(int noOfShares) {
        this.noOfShares = noOfShares;
    }

    public int getFirstParticipantSeqNo() {
        return firstParticipantSeqNo;
    }

    public void setFirstParticipantSeqNo(int firstParticipantSeqNo) {
        this.firstParticipantSeqNo = firstParticipantSeqNo;
    }

    public int getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(int transactionFee) {
        this.transactionFee = transactionFee;
    }

    public boolean isConfirmMBCAct() {
        return confirmMBCAct;
    }

    public void setConfirmMBCAct(boolean confirmMBCAct) {
        this.confirmMBCAct = confirmMBCAct;
    }

    public boolean isPrincipalDataChanged() {
        return isPrincipalDataChanged;
    }

    public void setPrincipalDataChanged(boolean principalDataChanged) {
        isPrincipalDataChanged = principalDataChanged;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public boolean isCheckBoxTerms() {
        return checkBoxTerms;
    }

    public void setCheckBoxTerms(boolean checkBoxTerms) {
        this.checkBoxTerms = checkBoxTerms;
    }

    public RegisteredAgent getRegisteredAgent() {
        return registeredAgent;
    }

    public void setRegisteredAgent(RegisteredAgent registeredAgent) {
        this.registeredAgent = registeredAgent;
    }

    public String getMbcGrossAssetsInd() {
        return mbcGrossAssetsInd;
    }

    public void setMbcGrossAssetsInd(String mbcGrossAssetsInd) {
        this.mbcGrossAssetsInd = mbcGrossAssetsInd;
    }

    public String getMbcNumberEmployeesInd() {
        return mbcNumberEmployeesInd;
    }

    public void setMbcNumberEmployeesInd(String mbcNumberEmployeesInd) {
        this.mbcNumberEmployeesInd = mbcNumberEmployeesInd;
    }

    public String getRaWrittenConfirmFlag() {
        return raWrittenConfirmFlag;
    }

    public void setRaWrittenConfirmFlag(String raWrittenConfirmFlag) {
        this.raWrittenConfirmFlag = raWrittenConfirmFlag;
    }

    public String getRaConsentFlag() {
        return raConsentFlag;
    }

    public void setRaConsentFlag(String raConsentFlag) {
        this.raConsentFlag = raConsentFlag;
    }

    public int getMbcId() {
        return mbcId;
    }

    public void setMbcId(int mbcId) {
        this.mbcId = mbcId;
    }

    public String getMbcNumber() {
        return mbcNumber;
    }

    public void setMbcNumber(String mbcNumber) {
        this.mbcNumber = mbcNumber;
    }

    public String getRaUserName() {
        return raUserName;
    }

    public void setRaUserName(String raUserName) {
        this.raUserName = raUserName;
    }

    public String getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(String dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public int getTransformationFilingYear() {
        return transformationFilingYear;
    }

    public void setTransformationFilingYear(int transformationFilingYear) {
        this.transformationFilingYear = transformationFilingYear;
    }
}
