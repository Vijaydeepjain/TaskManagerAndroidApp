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

public class edit extends AppCompatActivity {
    EditText taskname_update;
    DatePicker datePickerupdate;
    TimePicker timePickerupdate;
    Button updatebutton;
    Button back_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        back_update=findViewById(R.id.back_update);
        datePickerupdate=findViewById(R.id.datePickerupdate);
        timePickerupdate=findViewById(R.id.timePickerupdate);
        taskname_update=findViewById(R.id.taskname_update);
        updatebutton=findViewById(R.id.updatebutton_update);
        Intent in =getIntent();
        String taskname1=in.getStringExtra("taskname");
        String position=in.getStringExtra("position");
        taskname_update.setText(taskname1);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth;
                mAuth=FirebaseAuth.getInstance();
                String taskname=taskname_update.getText().toString();
                String time_update = timePickerupdate.getHour() + ":" + timePickerupdate.getMinute();
                String date_update="";

                if((datePickerupdate.getMonth()+1)<10)  {
                    if((datePickerupdate.getDayOfMonth())<10){
                        date_update = "0"+datePickerupdate.getDayOfMonth() + "-0" + (datePickerupdate.getMonth() + 1) + "-" + datePickerupdate.getYear();
                    }
                    else {
                        date_update = datePickerupdate.getDayOfMonth() + "-0" + (datePickerupdate.getMonth() + 1) + "-" + datePickerupdate.getYear();
                    }
                    Map<String,Object> mapp=new HashMap<>();
                    mapp.put("date",date_update);
                    mapp.put("time",time_update);
                    mapp.put("name",taskname);
                    FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid())
                            .child("userlistdata")
                            .child(position).updateChildren(mapp)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG)
                                            .show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG)
                                            .show();
                                }
                            });
                }
                if((datePickerupdate.getMonth()+1)>=10){
                    if((datePickerupdate.getDayOfMonth())<10){
                        date_update = "0"+datePickerupdate.getDayOfMonth() + "-0" + (datePickerupdate.getMonth() + 1) + "-" + datePickerupdate.getYear();
                    }
                    else
                     date_update = datePickerupdate.getDayOfMonth() + "-0" + (datePickerupdate.getMonth() + 1) + "-" + datePickerupdate.getYear();
                    Map<String,Object>mapp=new HashMap<>();
                    mapp.put("date",date_update);
                    mapp.put("time",time_update);
                    mapp.put("name",taskname);
                    FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid())
                            .child("userlistdata")
                            .child(position).updateChildren(mapp)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG)
                                            .show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG)
                                            .show();
                                }
                            });
                }

          /*  Intent i3=new Intent();
            i3.putExtra("taskname",taskname);
            i3.putExtra("time",time_update);
            i3.putExtra("date",date_update);*/

            }
        });

        back_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iii=new Intent(edit.this,dashboard.class);
                startActivity(iii);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ii=new Intent(edit.this,dashboard.class);
        startActivity(ii);
        finish();
    }
    }
