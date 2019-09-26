package com.tm.bottomsheet.bottomsheetdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LvAdapter extends BaseAdapter {

    private Context context;
    private List<String> list = new ArrayList<>();

    private int textColor = Color.DKGRAY;

    public LvAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 30; i++) {
            list.add(" " + i);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText(list.get(position));
        tv.setTextSize(30);
        tv.setTextColor(textColor);
        return tv;
    }

    public void setTextColor(int colorRes) {
        textColor = context.getResources().getColor(colorRes);
        notifyDataSetChanged();
    }
}
