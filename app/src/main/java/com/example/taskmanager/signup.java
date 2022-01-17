package com.example.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText enter_email,enter_password;
    TextView registered;
    Button signup;
    ProgressBar psignup;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        // code to skip signin page if user already login
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(signup.this,dashboard.class));
            finish();
        }
        setContentView(R.layout.activity_signup);
        //code initialization
             enter_email=findViewById(R.id.enter_email);
             enter_password=findViewById(R.id.enter_password);
             registered=findViewById(R.id.sinuptosingin);
             signup=findViewById(R.id.signup);
             psignup=findViewById(R.id.psignup);
             mAuth = FirebaseAuth.getInstance();

        //code  signup button firebase and intent to new activity on success

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                psignup.setVisibility(View.VISIBLE);
                String email=enter_email.getText().toString();
                String password=enter_password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    psignup.setVisibility(View.INVISIBLE);
                                    enter_email.setText("");
                                    enter_password.setText("");
                                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                                    Intent inn =new Intent(getApplicationContext(),dashboard.class);
                                    startActivity(inn);
                                }
                                else {
                                    psignup.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        // if user already registed intent to signin activity

        registered.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getApplicationContext(),signin.class);
                startActivity(ii);
            }
        });



    }
}