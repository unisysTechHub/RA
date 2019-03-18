package com.bvifsc.mbc.model;
import android.content.Context;
import android.util.Log;

import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by Ramesh on 27-11-2018.
 */
@Singleton
public class UserAuthInfo {

    int roleId;

    String roleCode;

    int userId;

    String firstName;

    String lastName;

    String authCode;

    String entityId;

    boolean isFirstTimeLogin;

    String entityName;

    String apiKey;

    boolean isKycDoc1Approved;


    boolean isKycDoc2Approved;


    boolean isKycDoc3Approved;


    String userLoginId;

    String userName;

    String password;
    boolean isKycSubmitted;

    String documentName;


    String documentFileExten;


    String document;

    boolean isKycApproved;;

    String kycDoc1RAComment;
    String kycDoc2RAComment;
    String kycDoc3RAcomment;


    private static UserAuthInfo userAuthInfo;



    private  UserAuthInfo()
    {            }

    public static UserAuthInfo getInstance()
    {

    if(userAuthInfo == null)
        userAuthInfo= new UserAuthInfo();

    return  userAuthInfo;

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFileExten() {
        return documentFileExten;
    }

    public void setDocumentFileExten(String documentFileExten) {
        this.documentFileExten = documentFileExten;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }



    public boolean isKycDoc1Approved() {
        return isKycDoc1Approved;
    }

    public void setKycDoc1Approved(boolean kycDoc1Approved) {
        isKycDoc1Approved = kycDoc1Approved;
    }

    public boolean isKycDoc2Approved() {
        return isKycDoc2Approved;
    }

    public void setKycDoc2Approved(boolean kycDoc2Approved) {
        isKycDoc2Approved = kycDoc2Approved;
    }

    public boolean isKycDoc3Approved() {
        return isKycDoc3Approved;
    }

    public void setKycDoc3Approved(boolean kycDoc3Approved) {
        isKycDoc3Approved = kycDoc3Approved;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getKycDoc1RAComment() {
        return kycDoc1RAComment;
    }

    public void setKycDoc1RAComment(String kycDoc1RAComment) {
        this.kycDoc1RAComment = kycDoc1RAComment;
    }

    public String getKycDoc2RAComment() {
        return kycDoc2RAComment;
    }

    public void setKycDoc2RAComment(String kycDoc2RAComment) {
        this.kycDoc2RAComment = kycDoc2RAComment;
    }

    public String getKycDoc3RAcomment() {
        return kycDoc3RAcomment;
    }

    public void setKycDoc3RAcomment(String kycDoc3RAcomment) {
        this.kycDoc3RAcomment = kycDoc3RAcomment;
    }

    public boolean isKycSubmitted() {
        return isKycSubmitted;
    }

    public void setKycSubmitted(boolean kycSubmitted) {
        isKycSubmitted = kycSubmitted;
    }

    public boolean isKycApproved() {
        return isKycApproved;
    }

    public void setKycApproved(boolean kycApproved) {
        isKycApproved = kycApproved;
    }
}
