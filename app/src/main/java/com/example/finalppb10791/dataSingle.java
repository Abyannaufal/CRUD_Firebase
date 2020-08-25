package com.example.finalppb10791;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class dataSingle extends AppCompatActivity {

    TextView name, classes, facility, bed, note, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_single);
        Hotel hotel = new Hotel();
        name = (TextView) findViewById(R.id.vw_Name);
        classes = (TextView) findViewById(R.id.vw_Class);
        facility = (TextView) findViewById(R.id.vw_Facility);
        bed = (TextView) findViewById(R.id.vw_Bed);
        note = (TextView) findViewById(R.id.vw_Note);
        price = (TextView) findViewById(R.id.vw_Price);

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        classes.setText(intent.getStringExtra("class"));
        facility.setText(intent.getStringExtra("facil"));
        bed.setText(intent.getStringExtra("bed"));
        note.setText(intent.getStringExtra("note"));
        price.setText("Rp." +intent.getStringExtra("price"));
    }

    public void onBackPressed(){
        Intent intent = new Intent(dataSingle.this, dataList.class);
        startActivity(intent);
    }
}
