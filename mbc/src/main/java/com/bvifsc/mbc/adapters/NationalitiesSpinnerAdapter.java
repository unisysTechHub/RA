package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bvifsc.mbc.model.registerMBC.Nationality;

import java.util.List;

/**
 * Created by Ramesh on 08-01-2019.
 */
public class NationalitiesSpinnerAdapter implements SpinnerAdapter {
    Context context;
    List<Nationality> nationalities;
    public NationalitiesSpinnerAdapter(Context context, List<Nationality> nationalities)
    {
        this.context=context;
        this.nationalities=nationalities;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);
        TextView nationalitySelected = convertView.findViewById(android.R.id.text1);
        nationalitySelected.setText(nationalities.get(position).getNationality());
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
        return nationalities.size();
    }

    @Override
    public Object getItem(int position) {
        return nationalities.get(position).getNationality();
    }

    @Override
    public long getItemId(int position) {
        return nationalities.get(position).getNationalityId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView= LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

                   TextView nationality= convertView.findViewById(android.R.id.text1);
                   nationality.setText(nationalities.get(position).getNationality());
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
