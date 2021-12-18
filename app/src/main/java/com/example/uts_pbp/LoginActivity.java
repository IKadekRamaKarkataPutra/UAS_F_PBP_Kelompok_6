package com.example.uts_pbp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText input_username, input_password;
    private Button btnClear, btnLogin;

    private FirebaseAuth lAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);

        btnClear = findViewById(R.id.btnClear);
        btnLogin = findViewById(R.id.btnLogin);

        lAuth = FirebaseAuth.getInstance();

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:
                Login();
                break;

            case R.id.btnRegis:
                startActivity(new Intent(this,Registration.class));
                break;
        }
    }

    private void Login() {
        String email = input_username.getText().toString().trim();
        String password = input_password.getText().toString().trim();


        if(email.isEmpty()){
            input_username.setError("Email harus diisi!");
            input_username.requestFocus();
            return;
        }

        if(password.isEmpty()){
            input_password.setError("password harus diisi!");
            input_password.requestFocus();
            return;
        }

        lAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, Saldo.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this,"Check your email Verif",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this,"Failed to Login check your email/password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
