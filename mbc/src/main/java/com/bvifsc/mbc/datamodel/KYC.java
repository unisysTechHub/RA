package com.bvifsc.mbc.datamodel;

import android.graphics.Bitmap;

import com.bvifsc.mbc.network.request.FileData;

import java.util.List;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYC {

    int kycDocType;
    FileData fileData =new FileData();


    public FileData getFileData() {
        return fileData;
    }

    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

    public int getKycDocType() {
        return kycDocType;
    }

    public void setKycDocType(int kycDocType) {
        this.kycDocType = kycDocType;
    }


}
