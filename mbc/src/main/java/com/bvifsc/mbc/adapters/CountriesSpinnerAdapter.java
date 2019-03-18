package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bvifsc.mbc.model.registerMBC.Country;

import java.util.List;

/**
 * Created by Ramesh on 08-01-2019.
 */
public class CountriesSpinnerAdapter implements SpinnerAdapter {
    Context context;
    List<Country> countries;
    public CountriesSpinnerAdapter(Context context, List<Country> countries)
    {

        this.context=context;
        this.countries=countries;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

        TextView selectedCountryView= convertView.findViewById(android.R.id.text1);
        selectedCountryView.setText(countries.get(position).getMbcCountryOfOperation());

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
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position).getMbcCountryOfOperation();
    }

    @Override
    public long getItemId(int position) {
        return countries.get(position).getCountryId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView=LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

        TextView country=convertView.findViewById(android.R.id.text1);
        country.setText(countries.get(position).getMbcCountryOfOperation());
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
