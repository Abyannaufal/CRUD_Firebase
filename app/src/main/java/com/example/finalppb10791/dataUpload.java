package com.example.finalppb10791;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class dataUpload extends AppCompatActivity {

    TextView price, standard, supperior, deluxe;
    EditText name, bed, note;
    Spinner classes, facility;
    Button add;

    DatabaseReference databaseHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_upload);
        Intent intent = getIntent();
        int updateKey = intent.getIntExtra("updateKey", 0);
        //Put value for updateKey. if 0 then add, if 1 then update
        Log.d("updateKey", ""+updateKey);

        //-----------------REMOVE ZONE-------------------\\
        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        final int permQuotaSt = prefs.getInt("myIntSt", 0);
        final int permQuotaSu = prefs.getInt("myIntSu", 0);
        final int permQuotaDl = prefs.getInt("myIntDl", 0);
        //-----------------REMOVE ZONE-------------------\\

        price = (TextView) findViewById(R.id.txt_Price);
        name = (EditText) findViewById(R.id.et_Name);
        bed = (EditText) findViewById(R.id.et_Bed);
        note = (EditText)findViewById(R.id.et_Note);
        classes = (Spinner) findViewById(R.id.sp_Classes);
        facility =(Spinner) findViewById(R.id.sp_Facility);
        add = (Button) findViewById(R.id.bt_Add);
        standard = (TextView) findViewById(R.id.q_st);
        supperior = (TextView) findViewById(R.id.q_su);
        deluxe = (TextView) findViewById(R.id.q_dl);

        if(updateKey==0) {
            add.setText("Check-In ");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(permQuotaSt > 0 || permQuotaSu > 0 || permQuotaDl>0) {
                        int amt_Bed = Integer.parseInt(bed.getText().toString());
                        Integer sum_Part1 = (amt_Bed * 10000);

                        //---------IF ELSE CLASS ZONE-------------------\\
                        String std = "Standard";
                        String sup = "Superrior";
                        String dlx = "Deluxe";

                        String st = classes.getSelectedItem().toString();
                        String su = classes.getSelectedItem().toString();
                        String dx = classes.getSelectedItem().toString();
                        if (st.equals(std)) {
                            SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                            editor.putInt("myIntSt", permQuotaSt - 1);
                            editor.apply();
                            Integer sum_Part2 = 100000 + sum_Part1;
                            price.setText(String.valueOf(sum_Part2));
                        } else if (su.equals(sup)) {
                            SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                            editor.putInt("myIntSu", permQuotaSu - 1);
                            editor.apply();
                            Integer sum_Part2 = 200000 + sum_Part1;
                            price.setText(String.valueOf(sum_Part2));
                        } else if (dx.equals(dlx)) {
                            SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                            editor.putInt("myIntDl", permQuotaDl - 1);
                            editor.apply();
                            Integer sum_Part2 = 300000 + sum_Part1;
                            price.setText(String.valueOf(sum_Part2));
                        }
                        //---------IF ELSE CLASS ZONE-------------------\\

                        addHotel();
                    }
                    else{
                        toastWarning();
                    }
                }
            });
        }
        else{
            add.setText("Edit ");
            name.setText(intent.getStringExtra("name"));
            bed.setText(intent.getStringExtra("bed"));
            note.setText(intent.getStringExtra("note"));

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int amt_Bed = Integer.parseInt(bed.getText().toString());
                    Integer sum_Part1 = (amt_Bed*10000);

                    //---------IF ELSE CLASS ZONE-------------------\\
                    String std = "Standard";
                    String sup = "Superrior";
                    String dlx = "Deluxe";

                    String st = classes.getSelectedItem().toString();
                    String su = classes.getSelectedItem().toString();
                    String dx = classes.getSelectedItem().toString();
                    if(st.equals(std)){
                        Integer sum_Part2 = 100000+sum_Part1;
                        price.setText(String.valueOf(sum_Part2));
                    }
                    else if(su.equals(sup)){
                        Integer sum_Part2 = 200000+sum_Part1;
                        price.setText(String.valueOf(sum_Part2));
                    }
                    else if(dx.equals(dlx)){
                        Integer sum_Part2 = 300000+sum_Part1;
                        price.setText(String.valueOf(sum_Part2));
                    }
                    //---------IF ELSE CLASS ZONE-------------------\\
                    updateHotel();
                }
            });
        }

        standard.setText("Quota STD: " + permQuotaSt);
        supperior.setText("Quota SUP: " + permQuotaSu);
        deluxe.setText("Quota DLX: " + permQuotaDl);


    }
    private void addHotel(){
        String addName = name.getText().toString().trim();
        String addBed = bed.getText().toString().trim();
        String addNote = note.getText().toString().trim();
        String addClasses = classes.getSelectedItem().toString();
        String addFacility= facility.getSelectedItem().toString();
        String addPrice= price.getText().toString().trim();

        if(!TextUtils.isEmpty(addName)){

            String std = "Standard";
            String sup = "Superrior";
            String dlx = "Deluxe";

            String st = classes.getSelectedItem().toString();
            String su = classes.getSelectedItem().toString();
            String dx = classes.getSelectedItem().toString();
            if(st.equals(std)){
                databaseHotel = FirebaseDatabase.getInstance().getReference("Standard");
                String id = databaseHotel.push().getKey();
                Hotel hotel = new Hotel(id, addName , addClasses, addFacility, addBed, addNote, addPrice);

                databaseHotel.child(id).setValue(hotel);

                Toast.makeText(this, "Check-in Success", Toast.LENGTH_LONG).show();
            }
            else if(su.equals(sup)){
                databaseHotel = FirebaseDatabase.getInstance().getReference("Superrior");
                String id = databaseHotel.push().getKey();
                Hotel hotel = new Hotel(id, addName , addClasses, addFacility, addBed, addNote, addPrice);

                databaseHotel.child(id).setValue(hotel);

                Toast.makeText(this, "Check-in Success", Toast.LENGTH_LONG).show();
            }
            else if(dx.equals(dlx)){
                databaseHotel = FirebaseDatabase.getInstance().getReference("Deluxe");
                String id = databaseHotel.push().getKey();
                Hotel hotel = new Hotel(id, addName , addClasses, addFacility, addBed, addNote, addPrice);

                databaseHotel.child(id).setValue(hotel);

                Toast.makeText(this, "Check-in Success", Toast.LENGTH_LONG).show();
            }


        }

        else{
            Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
        }
    }
    private void updateHotel(){
        Intent intent = getIntent();
        String addName = name.getText().toString().trim();
        String addBed = bed.getText().toString().trim();
        String addNote = note.getText().toString().trim();
        String addClasses = classes.getSelectedItem().toString();
        String addFacility= facility.getSelectedItem().toString();
        String addPrice= price.getText().toString().trim();
        final String id = intent.getStringExtra("id");

        if(!TextUtils.isEmpty(addName) && !TextUtils.isEmpty(addBed) && !TextUtils.isEmpty(addNote) && !TextUtils.isEmpty(addClasses) && !TextUtils.isEmpty(addFacility)){
            DatabaseReference update = FirebaseDatabase.getInstance().getReference("Hotel").child(id);
            Hotel hotel = new Hotel(id, addName, addClasses, addFacility, addBed, addNote, addPrice);
            update.setValue(hotel);
            Toast.makeText(dataUpload.this, "Data Updated!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(dataUpload.this, "Try again", Toast.LENGTH_LONG).show();
        }
    }
    private void toastWarning(){
        Toast.makeText(this, "Quota run out, please wait until restock", Toast.LENGTH_LONG).show();
    }
    public void onBackPressed(){
        Intent back = new Intent(dataUpload.this, MainActivity.class);
        startActivity(back);
    }
}
