package com.example.uts_pbp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.uts_pbp.databinding.InputProfileBinding;
import com.google.gson.Gson;



public class InputProfile extends AppCompatActivity {

    Profile prfl;
    InputProfileBinding binding;
    
    private User user;
    private UserPreferences userPreferences;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.input_profile);
        userPreferences = new UserPreferences (InputProfile.this);

        btnLogout = findViewById(R.id.btnLogout);
        prfl = new Profile();
        binding.setPrfl(prfl);
        binding.setActivity(this);
        user = userPreferences.getUserLogin();

        checkLogin();

    btnLogout.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            userPreferences.logout();
            Toast.makeText(InputProfile.this, "Bye", Toast.LENGTH_SHORT).show();
            checkLogin();
        }
    });
}

    private void checkLogin(){
        if(!userPreferences.checkLogin()){
            startActivity(new Intent( InputProfile.this, LoginActivity.class));
            finish();
        }else {
            Toast.makeText(InputProfile.this, "Sudah Login", Toast.LENGTH_SHORT).show();
        }
    }

    public View.OnClickListener btnSaveProfile = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent DetailProfile = new Intent(InputProfile.this, DetailProfile.class);

            Gson gson = new Gson();
            String strProfile = gson.toJson(prfl);

            DetailProfile.putExtra("Profile", strProfile);

            startActivity(DetailProfile);
        }
    };
}
