package com.example.artroo;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

@SuppressWarnings("deprecation")
public class StableArrayAdapter extends BaseAdapter {
	ArrayList<DataForMain> dataList;
	private Context context;
	
	public StableArrayAdapter(Context context,ArrayList<DataForMain> dataList) {
		// TODO Auto-generated constructor stub
		this.dataList=dataList;
		this.context=context;
	}
	@Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	  TwoLineListItem twoLineListItem;
    	if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setText(dataList.get(position).getName());
        text2.setText("" +dataList.get(position).getAmount());

        return twoLineListItem;
    }
}