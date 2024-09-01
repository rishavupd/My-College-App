package com.example.collegeapp.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.collegeapp.HomePageActivity;
import com.example.collegeapp.R;
import com.example.collegeapp.admin.faculty.updateFaculty;
import com.example.collegeapp.admin.notice.DeleteNoticeActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView uploadNotice, addGalleryImage, addEbook, faculty, deleteNotice, logout;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin); // Set the content view to activity_main.xml layout

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin", "false").equals("false")) {
            openLogin();
        }

        FirebaseApp.initializeApp(this);

        //FirebaseApp.initializeApp(this); // Initialize FirebaseApp

        uploadNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        logout = findViewById(R.id.logout);

        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this, loginActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        //Intent intent;

        if (view.getId() == R.id.addNotice) {
            Intent intent1 = new Intent(MainActivity.this, com.example.collegeapp.admin.notice.uploadNotice.class);
            startActivity(intent1);
        } else if (view.getId() == R.id.addGalleryImage) {
            Intent intent2 = new Intent(MainActivity.this, uploadImage.class);
            startActivity(intent2);
        }else if (view.getId() == R.id.addEbook) {
            Intent intent3 = new Intent(MainActivity.this, uploadPDF.class);
            startActivity(intent3);
        }else if (view.getId() == R.id.faculty) {
            Intent intent4 = new Intent(MainActivity.this, updateFaculty.class);
            startActivity(intent4);
        }else if (view.getId() == R.id.deleteNotice) {
            Intent intent5 = new Intent(MainActivity.this, DeleteNoticeActivity.class);
            startActivity(intent5);
        }else if (view.getId() == R.id.logout) {
            editor.putString("isLogin","false");
            editor.commit();
            Intent intent6=new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent6);
        }

    }
}
