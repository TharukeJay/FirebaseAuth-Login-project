package com.example.hsltestloginprocesswithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passwordField;
    private ProgressBar progressBar;
    private Button lgbtn;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.pswrd);
        progressBar = findViewById(R.id.progressBar);
        lgbtn = findViewById(R.id.lgbtn);
        btn = findViewById(R.id.btn);

        mAuth = FirebaseAuth.getInstance();

        lgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivuty.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,"Authentication faild" + task.getException(),Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(SignUpActivity.this,LoginActivuty.class));
                            finish();
                        }

                    }
                });

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
