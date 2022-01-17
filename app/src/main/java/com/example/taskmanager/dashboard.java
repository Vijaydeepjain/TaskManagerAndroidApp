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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class dashboard extends AppCompatActivity {
//declaration
    RecyclerView rec_todaylist,rec_alllist;
    /*adapter adaptor;
    adapter2 adapter2;*/
    TextView alltask;
    TextView todaytask;
    Button logout;
    ImageView add_dashboard;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//initialization
        alltask=findViewById(R.id.all_task_dashboard);
        todaytask=findViewById(R.id.today_task_dashboard);
        /*rec_todaylist=findViewById(R.id.todaylist);
        rec_alllist=findViewById(R.id.alllist);*/
        add_dashboard=findViewById(R.id.add_dashboard);
        logout=findViewById(R.id.logout);
        /*rec_alllist.setLayoutManager(new LinearLayoutManager(this));
        rec_todaylist.setLayoutManager(new LinearLayoutManager(this));*/
        mAuth=FirebaseAuth.getInstance();

//firebase database work
       FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("userlistdata")
                                .orderByChild("date").equalTo("15-01-2022")
                                ,model.class)
                        .build();
        FirebaseRecyclerOptions<model2> optionss =
                new FirebaseRecyclerOptions.Builder<model2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("userlistdata")
                                        .orderByChild("date")
                                ,model2.class)
                        .build();
// calling todaytask fragment
        todaytask todaytaskk= new todaytask(options);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linear_dashboard,todaytaskk);
        transaction.commit();
//setting adapter in recycler

     /* adaptor=new adapter(options,getApplicationContext());
      rec_todaylist.setAdapter(adaptor);*/


//logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i= new Intent(getApplicationContext(),signup.class);
                startActivity(i);
            }
        });
//todaytask click listener
        todaytask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todaytask todaytask= new todaytask(options);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_dashboard,todaytask);
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
                transaction.commit();
            }
        });
    }


//back button close application
@Override
public void onBackPressed() {
    super.onBackPressed();
    moveTaskToBack(true);
    android.os.Process.killProcess(android.os.Process.myPid());
    System.exit(1);
}

}