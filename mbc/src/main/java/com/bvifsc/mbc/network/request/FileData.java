package com.bvifsc.mbc.network.request;

import android.graphics.Bitmap;

/**
 * Created by Ramesh on 24-12-2018.
 */
public class FileData {
    String fileName;
    Bitmap bitmap;
    int fileType;

     interface FILE_TYPE
    {
          int IMAGE_JPEG = 1;
          int IMAGE_PNG = 2;

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
}
