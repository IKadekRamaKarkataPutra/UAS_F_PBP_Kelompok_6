package com.example.uts_pbp;


import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editName,editEmail,editAlamat,editRek,editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_profile);

        auth = FirebaseAuth.getInstance();

    }
}
