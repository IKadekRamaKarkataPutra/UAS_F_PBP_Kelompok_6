package com.example.uts_pbp;

import android.content.Context;
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
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editName,editEmail,editAlamat,editRek,editPassword;
    private Button regis;
    private Button login;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_profile);

        auth = FirebaseAuth.getInstance();

        regis = (Button) findViewById(R.id.btnRegis);
        regis.setOnClickListener((View.OnClickListener) this);

        editName = (EditText) findViewById(R.id.nama);
        editEmail = (EditText) findViewById(R.id.email);
        editAlamat = (EditText) findViewById(R.id.alamat);
        editRek = (EditText) findViewById(R.id.rekening);
        editPassword = (EditText) findViewById(R.id.password);
        logout = (Button) findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Registration.this, LoginActivity.class));
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(this,Saldo.class));
                break;

            case R.id.btnRegis:
                register();
                break;
        }
    }

    private void register() {
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
            editPassword.setError("password harus diisi!");
            editPassword.requestFocus();
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
                                Toast.makeText(Registration.this,"Berhasil membuat akun!!",Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(Registration.this,"Gagal membuat akun!!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Registration.this,"Gagal membuat akun!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
