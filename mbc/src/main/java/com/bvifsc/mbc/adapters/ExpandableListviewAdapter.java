package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bvifsc.core.R;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by Ramesh on 04-12-2018.
 */
public class ExpandableListviewAdapter implements ExpandableListAdapter {
    Map<String,String[]> nav_menu;
    Context context;
    String[] nav_main_menu;


    public ExpandableListviewAdapter(Context context, Map<String,String[]> nav_menu, String[] nav_main_menu)
    {

        this.nav_menu=nav_menu;
        this.context=context;
        this.nav_main_menu=nav_main_menu;


    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return nav_menu.keySet().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return nav_menu.get(nav_main_menu[groupPosition]).length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return nav_menu.get(nav_main_menu[groupPosition]);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return nav_menu.get(nav_main_menu[groupPosition])[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return Arrays.asList(nav_main_menu).indexOf(nav_main_menu[groupPosition]);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        String[] childGroup=nav_menu.get(nav_main_menu[groupPosition]);
        return  Arrays.asList(childGroup).indexOf(childGroup[childPosition]);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ra_nav_menu_group_row, null);
        }

            TextView groupMenuItem=convertView.findViewById(R.id.groupHeader);

            groupMenuItem.setText(nav_main_menu[groupPosition]);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.ra_nav_menu_child_row,null);

        }

        TextView subMenuItem=convertView.findViewById(R.id.submenuHeader);
                subMenuItem.setText((String)getChild(groupPosition,childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
