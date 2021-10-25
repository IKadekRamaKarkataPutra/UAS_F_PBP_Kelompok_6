package com.example.uts_pbp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uts_pbp.databinding.DetailProfileBinding;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;

public class DetailProfile extends AppCompatActivity {
    Profile prfl;
    DetailProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.detail_profile);

        String strProfile = getIntent().getStringExtra("Profile");
        Gson gson = new Gson();
        prfl = gson.fromJson(strProfile, Profile.class);

        binding.setPrfl(prfl);
        binding.setActivity(this);
    }
}