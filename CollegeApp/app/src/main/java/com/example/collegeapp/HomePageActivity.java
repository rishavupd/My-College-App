package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.collegeapp.authentication.ForgetPasswordActivity;
import com.example.collegeapp.authentication.LoginActivity;

public class HomePageActivity extends AppCompatActivity {

    private Button loginAdmin, loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        loginAdmin = findViewById(R.id.admin);
        loginUser = findViewById(R.id.user);

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
            }
        });

        loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, com.example.collegeapp.admin.MainActivity.class));
            }
        });

    }
}