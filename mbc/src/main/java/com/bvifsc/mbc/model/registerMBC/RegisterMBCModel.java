package com.bvifsc.mbc.model.registerMBC;

import java.util.List;

/**
 * Created by Ramesh on 08-01-2019.
 */
public class RegisterMBCModel {
    List<Country> countries;
    List<Nationality> nationalities;
    List<BusinessPurpose> businessPurposeList;
    List<ParticipantRights> participantRightsList;
    PaymentSummary paymentSummary;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Nationality> getNationalities() {
        return nationalities;
    }

    public void setNationalities(List<Nationality> nationalities) {
        this.nationalities = nationalities;
    }

    public List<BusinessPurpose> getBusinessPurposeList() {
        return businessPurposeList;
    }

    public void setBusinessPurposeList(List<BusinessPurpose> businessPurposeList) {
        this.businessPurposeList = businessPurposeList;
    }

    public List<ParticipantRights> getParticipantRightsList() {
        return participantRightsList;
    }

    public void setParticipantRightsList(List<ParticipantRights> participantRightsList) {
        this.participantRightsList = participantRightsList;
    }

    public PaymentSummary getPaymentSummary() {
        return paymentSummary;
    }

    public void setPaymentSummary(PaymentSummary paymentSummary) {
        this.paymentSummary = paymentSummary;
    }
}
