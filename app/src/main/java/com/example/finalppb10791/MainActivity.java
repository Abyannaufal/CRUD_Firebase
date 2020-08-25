package com.example.finalppb10791;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView dataList, dataUpload, about, exit, stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dataList = (CardView) findViewById(R.id.bt_dataList);
        dataUpload = (CardView) findViewById(R.id.bt_dataUpload);
        about = (CardView) findViewById(R.id.bt_About);
        exit = (CardView) findViewById(R.id.bt_Exit);
        stock = (CardView) findViewById(R.id.bt_Stock);

        dataList.setOnClickListener(this);
        dataUpload.setOnClickListener(this);
        about.setOnClickListener(this);
        stock.setOnClickListener(this);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.bt_dataList : i = new Intent(this,dataList.class); startActivity(i); break;
            case R.id.bt_dataUpload : i = new Intent(this,dataUpload.class); i.putExtra("updateKey", 0); startActivity(i); break;
            case R.id.bt_About : i = new Intent(this, aboutMe.class); startActivity(i); break;
            case R.id.bt_Stock : i = new Intent(this, dataStock.class); startActivity(i); break;
        }
    }
}
