package com.example.finalppb10791;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class dataStock extends AppCompatActivity {

    Spinner classes;
    EditText quota;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_stock);
        classes = (Spinner) findViewById(R.id.st_Room);
        quota = (EditText) findViewById(R.id.st_Quota);
        add = (Button) findViewById(R.id.st_Add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuota();
            }
        });

    }
    private void addQuota(){
        SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
        String std = "Standard";
        String sup = "Superrior";
        String dlx = "Deluxe";

        String st = classes.getSelectedItem().toString();
        String su = classes.getSelectedItem().toString();
        String dx = classes.getSelectedItem().toString();

        if(st.equals(std)){
            int permQuotaSt = Integer.parseInt(quota.getText().toString());
            editor.putInt("myIntSt", permQuotaSt);
            editor.apply();
            Toast.makeText(this, "Success, Standard class have "+permQuotaSt+" quota now", Toast.LENGTH_LONG).show();
        }
        else if(su.equals(sup)){
            int permQuotaSu = Integer.parseInt(quota.getText().toString());
            editor.putInt("myIntSu", permQuotaSu);
            editor.apply();
            Toast.makeText(this, "Success, Standard class have "+permQuotaSu+" quota now", Toast.LENGTH_LONG).show();
        }
        else if(dx.equals(dlx)){
            int permQuotaDl = Integer.parseInt(quota.getText().toString());
            editor.putInt("myIntDl", permQuotaDl);
            editor.apply();
            Toast.makeText(this, "Success, Standard class have "+permQuotaDl+" quota now", Toast.LENGTH_LONG).show();
        }
    }
}
