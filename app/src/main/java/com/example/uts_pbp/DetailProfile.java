package com.example.uts_pbp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts_pbp.databinding.DetailProfileBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class DetailProfile extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Profile");
        ID = user.getUid();

        final TextView nameView = (TextView) findViewById(R.id.detailnama);
        final TextView emailView = (TextView) findViewById(R.id.detailemail);
        final TextView alamatView = (TextView) findViewById(R.id.detailalamat);
        final TextView rekeningView = (TextView) findViewById(R.id.detailrekening);

        reference.child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Profile userProfile = snapshot.getValue(Profile.class);

                if(userProfile != null){
                    String nama = userProfile.input_nama;
                    String email = userProfile.input_email;
                    String alamat = userProfile.input_alamat;
                    String rekening = userProfile.input_rekening;

                    nameView.setText(nama);
                    emailView.setText(email);
                    alamatView.setText(alamat);
                    rekeningView.setText(rekening);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailProfile.this,"Profil anda bermasalah",Toast.LENGTH_LONG).show();
            }
        });
    }
}