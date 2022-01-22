package com.example.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class add extends AppCompatActivity {
    DatePicker datepickeradd;
    TimePicker timePickeradd;
    EditText taskname_add;
    Button button_add;
    Button back_add;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        setContentView(R.layout.activity_add);
        datepickeradd=findViewById(R.id.datePickeradd);
        timePickeradd=findViewById(R.id.timePickeradd);
        taskname_add=findViewById(R.id.taskname_add);
        button_add=findViewById(R.id.updatebutton_add);
        back_add=findViewById(R.id.back_add);
        mAuth= FirebaseAuth.getInstance();

        //getActionBar().setDisplayHomeAsUpEnabled(false);
        //getActionBar().setHomeButtonEnabled(false);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date_add;
                String task_add = taskname_add.getText().toString();
                //if((datepickeradd.getMonth()+1)<10) {
                date_add = datepickeradd.getDayOfMonth() + "-0" + (datepickeradd.getMonth() + 1) + "-" + datepickeradd.getYear();
                //}
                if ((datepickeradd.getMonth() + 1) >= 10) {
                    date_add = datepickeradd.getDayOfMonth() + "-" + (datepickeradd.getMonth() + 1) + "-" + datepickeradd.getYear();
                }
                String time_add = timePickeradd.getHour() + ":" + timePickeradd.getMinute();
                Map<String, Object> mappp = new HashMap<>();
                mappp.put("date", date_add);
                mappp.put("time", time_add);
                mappp.put("name", task_add);
                FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid())
                        .child("userlistdata")
                        .push()
                        .setValue(mappp)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                taskname_add.setText("");
                                Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Could not insert", Toast.LENGTH_LONG).show();
                            }
                        });
            }

            });

        back_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttt=new Intent(getApplicationContext(),dashboard.class);
                startActivity(intenttt);
               finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentttt=new Intent(getApplicationContext(),dashboard.class);
        startActivity(intentttt);
        finish();
    }
}
