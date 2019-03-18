package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.bvifsc.mbc.model.registerMBC.ParticipantRights;

import java.util.List;

/**
 * Created by Ramesh on 08-01-2019.
 */
public class ParticipantRightsSpinnerAdapter implements SpinnerAdapter {
    Context context;
    List<ParticipantRights> participantRightsList;

    public ParticipantRightsSpinnerAdapter(Context context, List<ParticipantRights> participantRightsList)
    {
        this.context=context;
        this.participantRightsList=participantRightsList;


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView= LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);

         TextView participantRightsSelected = convertView.findViewById(android.R.id.text1);
         participantRightsSelected.setText(participantRightsList.get(position).getParticipantRights());
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
        return participantRightsList.size();
    }

    @Override
    public Object getItem(int position) {
        return participantRightsList.get(position).getParticipantRights();
    }

    @Override
    public long getItemId(int position) {
        return participantRightsList.get(position).getParticipantRightsId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView=LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,null);
        TextView participantRights = convertView.findViewById(android.R.id.text1);
        participantRights.setText(participantRightsList.get(position).getParticipantRights());
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
