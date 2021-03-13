package com.ttork.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import cn.zhaiyifan.rememberedittext.RememberEditText;

public class EmailPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText ETemail;
    private EditText ETpassword;
    int number,o;
    public void signin (String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(EmailPasswordActivity.this,ScrollingActivity.class));
                    number = 1;
                    //Toast.makeText(getApplicationContext(), "Aвторизация успешна", Toast.LENGTH_SHORT).show();
                    File file60 = new File("/data/data/com.ttork.myapplication/Sign.txt");
                    Log.d(file60.exists() + "", "true!");
                    try {
                        if (!file60.exists()) file60.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PrintWriter printWriter60 = new PrintWriter(file60);
                        printWriter60.write(String.valueOf(1));
                        //printWriter5.write(String.valueOf(0));
                        printWriter60.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Aвторизация провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void registration (String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    o++;
                } else
                    o++;
                //Toast.makeText(getApplicationContext(), "Регистрация провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_sign_in)
        {
            try {

                signin(ETemail.getText().toString(),ETpassword.getText().toString());
                startActivity(new Intent(EmailPasswordActivity.this,ScrollingActivity.class));
                number = 1;
            }catch (Exception e) {
                e.printStackTrace();
            }
            //startActivity(new Intent(EmailPasswordActivity.this, PersonActivity.class));
        }else if (view.getId() == R.id.btn_registration)
        {
            try {
                registration(ETemail.getText().toString(),ETpassword.getText().toString());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);
        if(number == 1)startActivity(new Intent(EmailPasswordActivity.this,ScrollingActivity.class));
        //----------------------------------------------------------------------------
        String myData9 = "";
        File myExternalFile9 = new File("/data/data/com.ttork.myapplication/Sign.txt");
        try {
            FileInputStream fis9 = new FileInputStream(myExternalFile9);
            DataInputStream in9 = new DataInputStream(fis9);
            BufferedReader br9 = new BufferedReader(new InputStreamReader(in9));
            String strLine9;
            while ((strLine9 = br9.readLine()) != null) {
                myData9 = myData9 + strLine9;
                Log.d("File? ",myData9);
                number = Integer.parseInt(myData9);
            }
            br9.close();
            in9.close();
            fis9.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    startActivity(new Intent(EmailPasswordActivity.this, ScrollingActivity.class));
                } else {
                    o++;
                }
            }
        };
        ETemail = findViewById(R.id.et_email);
        ETpassword = findViewById(R.id.et_password);
        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_registration).setOnClickListener(this);
    }
}
