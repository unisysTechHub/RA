package com.bvifsc.mbc.network.response.signIn;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;

/**
 * Created by Ramesh on 27-11-2018.
 */
public class UserInfo {


    @SerializedName("roleId")
    int roleId;
    @SerializedName("roleCode")
    String roleCode;

    @SerializedName("userId")
    int userId;

    @SerializedName("firstName")
    String firstName;

    @SerializedName("lastName")
    String lastName;

    @SerializedName("authCode")
    String authCode;

    @SerializedName("entityId")
    String entityId;

    @SerializedName("isFirstTimeLogin")
    boolean isFirstTimeLogin;

    @SerializedName("entityName")
    String entityName;

    @SerializedName("documentName")
    String documentName;

    @SerializedName("documentFileExten")
    String documentFileExten;

    @SerializedName("document")
    String document;

    @SerializedName("apiKey")
    String apiKey;

    @SerializedName("kycSubmittedInd")
    String kycSubmittedInd;

    @SerializedName("kycDoc1ApprovedInd")
    String kycDoc1ApprovedInd;

    @SerializedName("kycDoc2ApprovedInd")
    String kycDoc2ApprovedInd;

    @SerializedName("kycDoc3ApprovedInd")
    String kycDoc3ApprovedInd;

    @SerializedName("userLoginId")
    int userLoginId;

    @SerializedName("username")
    String userName;

    @SerializedName("password")
    String password;

    @SerializedName("authorizationInd")
    String authorizationInd;

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

    public String getKycSubmittedInd() {
        return kycSubmittedInd;
    }

    public void setKycSubmittedInd(String kycSubmittedInd) {
        this.kycSubmittedInd = kycSubmittedInd;
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

    public String getAuthorizationInd() {
        return authorizationInd;
    }

    public void setAuthorizationInd(String authorizationInd) {
        this.authorizationInd = authorizationInd;
    }

    public String getKycDoc1ApprovedInd() {
        return kycDoc1ApprovedInd;
    }

    public void setKycDoc1ApprovedInd(String kycDoc1ApprovedInd) {
        this.kycDoc1ApprovedInd = kycDoc1ApprovedInd;
    }

    public String getKycDoc2ApprovedInd() {
        return kycDoc2ApprovedInd;
    }

    public void setKycDoc2ApprovedInd(String kycDoc2ApprovedInd) {
        this.kycDoc2ApprovedInd = kycDoc2ApprovedInd;
    }

    public String getKycDoc3ApprovedInd() {
        return kycDoc3ApprovedInd;
    }

    public void setKycDoc3ApprovedInd(String kycDoc3ApprovedInd) {
        this.kycDoc3ApprovedInd = kycDoc3ApprovedInd;
    }

    public int getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(int userLoginId) {
        this.userLoginId = userLoginId;
    }
}
