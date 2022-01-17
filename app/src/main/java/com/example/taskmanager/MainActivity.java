package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView splash_screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      splash_screen=findViewById(R.id.splash_screen);
      splash_screen.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             Intent intent=new Intent(MainActivity.this,signup.class);
             startActivity(intent);
             finish();
          }
      });

    }
}