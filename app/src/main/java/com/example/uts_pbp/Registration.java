package com.example.uts_pbp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editName,editEmail,editAlamat,editRek,editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_profile);

        auth = FirebaseAuth.getInstance();

        editName = (EditText) findViewById(R.id.nama);
        editEmail = (EditText) findViewById(R.id.email);
        editAlamat = (EditText) findViewById(R.id.alamat);
        editRek = (EditText) findViewById(R.id.rekening);
        editPassword = (EditText) findViewById(R.id.password);

    }

    private void registerUser (){
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String alamat = editAlamat.getText().toString().trim();
        String rekening = editRek.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(name.isEmpty()){
            editName.setError("Nama harus diisi!");
            editName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editEmail.setError("Email harus diisi!");
            editEmail.requestFocus();
            return;
        }

        if(alamat.isEmpty()){
            editAlamat.setError("Alamat harus diisi!");
            editAlamat.requestFocus();
            return;
        }
        if(rekening.isEmpty()){
            editRek.setError("rekening harus diisi!");
            editRek.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editRek.setError("password harus diisi!");
            editRek.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Profile profile = new Profile(name,email,alamat,rekening,password);

                    FirebaseDatabase.getInstance().getReference( "Profile")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText( registerUser.this,"Akun berhasil didaftar ", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText( registerUser.this,"Akun gagal didaftar ", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText( registerUser.this,"Akun gagal didaftar ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
