package com.example.user.myapplication;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 2017-08-17.
 */

public class ListViewAdapter extends BaseAdapter{
    private static final int ITEM_VIEW_TYPE_DATE = 0;
    private static final int ITEM_VIEW_TYPE_GRAPH = 1;
    private static final int ITEM_VIEW_TYPE_MAX = 2;

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    @Override
    public int getViewTypeCount(){
        return ITEM_VIEW_TYPE_MAX;
    }

    @Override
    public int getItemViewType(int position){
        return listViewItemList.get(position).getType() ;
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position) ;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_DATE:
                    convertView = inflater.inflate(R.layout.nav_right_parent, parent, false);
                    TextView parentTextView = (TextView) convertView.findViewById(R.id.nav_right_parent_date);
                    parentTextView.setText(listViewItem.getDate() + " (" + listViewItem.getGraphNo() + ")");
                    break;

                case ITEM_VIEW_TYPE_GRAPH:
                    convertView = inflater.inflate(R.layout.nav_right_child, parent, false);
                    TextView dateTextView = (TextView) convertView.findViewById(R.id.nav_right_child_date) ;
                    TextView eqipTextView = (TextView) convertView.findViewById(R.id.nav_right_child_equip) ;
                    TextView numTextView = (TextView) convertView.findViewById(R.id.nav_right_child_num) ;
                    ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.nav_right_child_img) ;

                    dateTextView.setText(listViewItem.getDate());
                    eqipTextView.setText(listViewItem.getEquipment());
                    numTextView.setText(listViewItem.getNum());
                    break;
            }
        }
        return convertView;
    }


    public void addItem(String date, int graphNo){
        ListViewItem item = new ListViewItem();

        item.setType(ITEM_VIEW_TYPE_DATE);
        item.setDate(date);
        item.setGraphNo(graphNo);

        listViewItemList.add(item);
    }

    public void addItem(String date, String equipment, String num){
        ListViewItem item = new ListViewItem();

        item.setType(ITEM_VIEW_TYPE_GRAPH);
        item.setDate(date);
        item.setEquipment(equipment);
        item.setNum(num);

        listViewItemList.add(item);
    }
}
