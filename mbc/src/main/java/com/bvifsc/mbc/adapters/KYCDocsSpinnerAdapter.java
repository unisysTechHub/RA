package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentTypeModel;
import java.util.List;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYCDocsSpinnerAdapter implements SpinnerAdapter {
    Context context;
    List<KYCDocumentTypeModel> kycDocTypesList;

    public KYCDocsSpinnerAdapter(Context context, List<KYCDocumentTypeModel> kycDocTypesList)
    {this.context=context;
    this.kycDocTypesList=kycDocTypesList;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
       if(convertView == null)
            convertView=LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

        TextView docType=convertView.findViewById(android.R.id.text1);
        docType.setText(kycDocTypesList.get(position).getDocumentType());

        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return kycDocTypesList.size();
    }

    @Override
    public Object getItem(int position) {
        return kycDocTypesList.get(position).getDocumentType();
    }

    @Override
    public long getItemId(int position) {
        return kycDocTypesList.indexOf(kycDocTypesList.get(position).getDocumentType());
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView=LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

        TextView docType=convertView.findViewById(android.R.id.text1);
        docType.setText(kycDocTypesList.get(position).getDocumentType());

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return android.R.id.text1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
