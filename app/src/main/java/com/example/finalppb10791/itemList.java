package com.example.finalppb10791;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class itemList extends ArrayAdapter<Hotel> {

    private Activity context;
    List<Hotel> hotels;

    public itemList(Activity context, List<Hotel> hotels){
        super(context, R.layout.activity_item_list,hotels);
        this.context = context;
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_item_list,null,true);

        TextView listName = (TextView) listViewItem.findViewById(R.id.ls_Name);
        TextView listClass = (TextView) listViewItem.findViewById(R.id.ls_Class);
        TextView listPrice = (TextView) listViewItem.findViewById(R.id.ls_Price);

        Hotel hotel =hotels.get(position);
        listName.setText(hotel.getHotelName());
        listClass.setText(hotel.getHotelClass());
        listPrice.setText(hotel.getHotelPrice());

        return listViewItem;
    }
}
