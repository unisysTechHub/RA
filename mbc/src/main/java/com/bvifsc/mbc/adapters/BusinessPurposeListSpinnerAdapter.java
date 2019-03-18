package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bvifsc.mbc.model.registerMBC.BusinessPurpose;

import java.util.List;

/**
 * Created by Ramesh on 08-01-2019.
 */
public class BusinessPurposeListSpinnerAdapter implements SpinnerAdapter {
    Context context;
    List<BusinessPurpose> businessPurposeList;
    public BusinessPurposeListSpinnerAdapter(Context context, List<BusinessPurpose> businessPurposeList)
    {
        this.context=context;
        this.businessPurposeList=businessPurposeList;

    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        convertView= LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, null);
        TextView businessPurposeSelected = convertView.findViewById(android.R.id.text1);
        businessPurposeSelected.setText(businessPurposeList.get(position).getBusinessPurpose());

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
        return businessPurposeList.size();
    }

    @Override
    public Object getItem(int position) {
        return businessPurposeList.get(position).getBusinessPurpose();
    }

    @Override
    public long getItemId(int position) {
        return businessPurposeList.get(position).getBusinessPurposeId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView= LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

        TextView businessPurpose=convertView.findViewById(android.R.id.text1);
        businessPurpose.setText(businessPurposeList.get(position).getBusinessPurpose());


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
