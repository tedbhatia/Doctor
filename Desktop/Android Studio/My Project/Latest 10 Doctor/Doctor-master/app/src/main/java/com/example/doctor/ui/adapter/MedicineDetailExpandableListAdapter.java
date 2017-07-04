package com.example.doctor.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.doctor.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Pankaj on 6/12/2017.
 */

public class MedicineDetailExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeading;
    private HashMap<String, String> listMap;

    public MedicineDetailExpandableListAdapter(Context context, List<String> listHeading, HashMap<String, String> listMap) {
        this.context = context;
        this.listHeading = listHeading;
        this.listMap = listMap;
    }

    @Override
    public int getGroupCount() {
        return listHeading.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeading.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listMap.get(listHeading.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.medicine_detail_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.listHeading);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.medicine_detail_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.listDetail);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
