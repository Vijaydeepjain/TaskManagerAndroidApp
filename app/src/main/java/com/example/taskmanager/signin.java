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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class signin extends AppCompatActivity {
    EditText login_email,login_password;
    Button login;
    TextView forgetpassword;
    ProgressBar plogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    //code initialization
        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        login=findViewById(R.id.login);
        plogin=findViewById(R.id.plogin);
        forgetpassword=findViewById(R.id.forgetpassword);
        mAuth=FirebaseAuth.getInstance();
    // coding sign in button and doing on success intent to dashboard
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plogin.setVisibility(View.VISIBLE);
                String email_login= login_email.getText().toString();
                String password_login=login_password.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email_login,password_login)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    plogin.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                                if(!task.isSuccessful()){
                                    plogin.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(),"TRY AGAIN",Toast.LENGTH_LONG)
                                            .show();

                                }
                            }
                        });
            }
        });

        // forget password  and dialogplus use
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(signin.this)
                        .setContentHolder(new ViewHolder(R.layout.dialogforgetpassword))
                        .setExpanded(true,1600)
                        .create();
                View myview =dialogPlus.getHolderView();
                EditText UserEmaill=myview.findViewById(R.id.userEmail);
                ProgressBar p=myview.findViewById(R.id.progressBardialog);
                Button forgetdialogbutton =myview.findViewById(R.id.dialogbuttonpassword);
                dialogPlus.show();
                forgetdialogbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        p.setVisibility(View.VISIBLE);
                        String userEmail=UserEmaill.getText().toString();
                        if(userEmail.isEmpty()){
                            dialogPlus.dismiss();
                            Toast.makeText(signin.this, "give valid email address", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            mAuth.sendPasswordResetEmail(userEmail)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                dialogPlus.dismiss();
                                                Toast.makeText(signin.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                dialogPlus.dismiss();
                                                Toast.makeText(signin.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                             }
                    }
                });


            }
        });

    }
}