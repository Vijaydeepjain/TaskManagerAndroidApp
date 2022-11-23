package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dashboard extends AppCompatActivity {
//declaration
    TextView alltask;
    TextView todaytask;
    Button taskdone;
    Button logout;
    ImageView add_dashboard;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
// toast message
        Toast.makeText(getApplicationContext(),"Data loading..\nCloud Database\nMake sure your Internet is On",Toast.LENGTH_LONG).show();
//initialization
        alltask=findViewById(R.id.all_task_dashboard);
        taskdone=findViewById(R.id.taskdone_dashboard);
        todaytask=findViewById(R.id.today_task_dashboard);
        add_dashboard=findViewById(R.id.add_dashboard);
        logout=findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();
//current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
//firebase database work
       FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                        .child(mAuth.getCurrentUser().getUid())
                                        .child("userlistdata")
                                .orderByChild("date").equalTo(formatter.format(date))
                                ,model.class)
                        .build();
        FirebaseRecyclerOptions<model2> optionss =
                new FirebaseRecyclerOptions.Builder<model2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                        .child(mAuth.getCurrentUser().getUid())
                                        .child("userlistdata"),model2.class)
                        .build();
        FirebaseRecyclerOptions<model3> optionsss =
                new FirebaseRecyclerOptions.Builder<model3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(mAuth.getCurrentUser().getUid())
                                .child("complete"),model3.class)
                        .build();

// calling todaytask fragment
        todaytask todaytaskk= new todaytask(options);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linear_dashboard,todaytaskk);
        transaction.commit();

//logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i= new Intent(getApplicationContext(),signup.class);
                startActivity(i);
                finish();
            }
        });

//todaytask click listener
        todaytask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todaytask todaytask= new todaytask(options);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_dashboard,todaytask);
                Toast.makeText(getApplicationContext(),"Data loading..\nCloud Database\nMake sure your Internet is On",Toast.LENGTH_LONG).show();
                transaction.commit();
                
            }
        });

//alltask  click listener
        alltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alltask alltask= new alltask(optionss);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_dashboard,alltask);
                Toast.makeText(getApplicationContext(),"Data loading..\nCloud Database\nMake sure your Internet is On",Toast.LENGTH_LONG).show();
                transaction.commit();
            }
        });

//complete task click listener
        taskdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completelist completelist= new completelist(optionsss);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_dashboard,completelist);
                Toast.makeText(getApplicationContext(),"Data loading..\nCloud Database\nMake sure your Internet is On",Toast.LENGTH_LONG).show();
                transaction.commit();
            }
        });

// add task button
         add_dashboard.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(dashboard.this,add.class);
                 startActivity(intent);
             }
         });
    }


//back button close application
/*@Override
public void onBackPressed() {
    super.onBackPressed();
    System.exit(1);
    //finish();
}*/

}