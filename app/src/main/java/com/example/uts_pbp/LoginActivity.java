package com.example.uts_pbp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText input_username, input_password;
    private Button btnClear, btnLogin;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(LoginActivity.this);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);

        btnClear = findViewById(R.id.btnClear);
        btnLogin = findViewById(R.id.btnLogin);

        checkLogin();

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                input_username.setText("");
                input_password.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(validateForm()) {
                    if (input_username.getText().toString().trim().equals("admin")
                            && input_password.getText().toString().trim().equals("1234")) {

                        userPreferences.setLogin(input_username.getText().toString().trim(),input_password.getText().toString().trim());
                        checkLogin();
                    } else {
                        Toast.makeText(LoginActivity.this, " Username atau Password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateForm(){

        if(input_username.getText().toString().trim().isEmpty() || input_password.getText().toString().trim().isEmpty()){
            Toast.makeText(LoginActivity.this, "Username atau Password Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkLogin(){
        if(userPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, InputProfile.class));
            finish();
        }
    }
}
