package com.bvifsc.mbc.datamodel;

import com.bvifsc.mbc.network.request.FileData;

import java.util.List;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYCDocuments {

    List<KYC>  list;

    public List<KYC> getList() {
        return list;
    }

    public void setList(List<KYC> list) {
        this.list = list;
    }
}
