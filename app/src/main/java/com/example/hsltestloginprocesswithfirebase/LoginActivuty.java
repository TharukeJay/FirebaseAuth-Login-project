package com.example.hsltestloginprocesswithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.w3c.dom.Text;

public class LoginActivuty extends AppCompatActivity {

    private EditText lgEmailField;
    private EditText lgPswrdField;
    private Button lgbtnField;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activuty);

        mAuth = FirebaseAuth.getInstance();
        lgEmailField = findViewById(R.id.lgemail);
        lgPswrdField = findViewById(R.id.lgpswrd);
        lgbtnField = findViewById(R.id.lgbtn);
        progressBar = findViewById(R.id.lgprograssbar);

        progressBar.setVisibility(View.GONE);

//        mAuthListner = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                if (firebaseAuth.getCurrentUser() != null) {
//
//                    startActivity(new Intent(LoginActivuty.this,MainActivity.class));
//
//                }
//
//            }
//        };

        lgbtnField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startSignIn();

            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListner);
//    }

    private void startSignIn() {
        String email = lgEmailField.getText().toString();
        String pswrd = lgPswrdField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pswrd)) {
            Toast.makeText(LoginActivuty.this,"Fields are empty",Toast.LENGTH_LONG).show();
        } else {

            mAuth.signInWithEmailAndPassword(email,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivuty.this,"Sign in problem",Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(LoginActivuty.this,MainActivity.class));
                    }

                }
            });

        }


    }

}
