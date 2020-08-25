package com.example.finalppb10791;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class dataList extends AppCompatActivity {

    ListView dataHotel;
    List<Hotel> hotels;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        databaseReference = FirebaseDatabase.getInstance().getReference("Hotel");
        dataHotel = (ListView) findViewById(R.id.listView);
        hotels = new ArrayList<>();

        dataHotel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Hotel hotel = hotels.get(position);
                showDialog(hotel.getHotelId(), hotel.getHotelName(), hotel.getHotelClass(), hotel.getHotelFacility(), hotel.getHotelBed(), hotel.getHotelNote(), hotel.getHotelPrice());
                return false;
            }
        });

        /*dataHotel.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSingle = new Intent(dataList.this, dataSingle.class);
                startActivity(toSingle);
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Attaching value event listener
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Clearing previous hotel data list
                hotels.clear();

                //Iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting hotel
                    Hotel hotel = postSnapshot.getValue(Hotel.class);
                    //Adding hotel to the itemList
                    hotels.add(hotel);
                }

                //Creating adapter
                itemList hotelAdapter = new itemList(dataList.this, hotels);

                //Adapter itemList -> dataList.listView
                dataHotel.setAdapter(hotelAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void showDialog(final String hotelId, final String hotelName, final String hotelClass, final String hotelFacility, final String hotelBed, final String hotelNote, final String hotelPrice){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_dialog);
        dialog.show();

        final Button view = (Button) dialog.findViewById(R.id.bt_View);
        final Button edit = (Button) dialog.findViewById(R.id.bt_Edit);
        final Button del  = (Button) dialog.findViewById(R.id.bt_Delete);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Hotel").child(hotelId);
                Hotel hotel = new Hotel(hotelId, hotelName, hotelBed, hotelClass, hotelFacility, hotelNote, hotelPrice);
                Intent toDetail = new Intent(dataList.this, dataSingle.class);
                toDetail.putExtra("name", hotelName);
                toDetail.putExtra("class", hotelClass);
                toDetail.putExtra("facil", hotelFacility);
                toDetail.putExtra("bed", hotelBed);
                toDetail.putExtra("note", hotelNote);
                toDetail.putExtra("price", hotelPrice);
                startActivity(toDetail);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Hotel").child(hotelId);
                Hotel hotel = new Hotel(hotelId, hotelName, hotelBed, hotelClass, hotelFacility, hotelNote, hotelPrice);
                Intent toUpdate = new Intent(dataList.this, dataUpload.class);
                toUpdate.putExtra("name", hotelName);
                toUpdate.putExtra("class", hotelClass);
                toUpdate.putExtra("facil", hotelFacility);
                toUpdate.putExtra("bed", hotelBed);
                toUpdate.putExtra("note", hotelNote);
                toUpdate.putExtra("price", hotelPrice);
                toUpdate.putExtra("id", hotelId);
                toUpdate.putExtra("updateKey", 1);
                startActivity(toUpdate);

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(hotelId);
                dialog.dismiss();

            }
        });
    }

    private boolean deleteData(String id){
        DatabaseReference delFire = FirebaseDatabase.getInstance().getReference("Hotel").child(id);
        delFire.removeValue();
        Toast.makeText(this,"Delete Success", Toast.LENGTH_LONG).show();

        return true;
    }
    public void onBackPressed(){
        Intent intent = new Intent(dataList.this, MainActivity.class);
        startActivity(intent);
    }
}
