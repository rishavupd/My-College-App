package com.example.collegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.MainActivity;
import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName, regEmail, regPass, regNumber;
    private Button register;
    private String name, email, password, number;
    private FirebaseAuth auth;
    private DatabaseReference reference, dbRef;
    private TextView openLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPass = findViewById(R.id.regPass);
        regNumber = findViewById(R.id.regNumber);
        openLog = findViewById(R.id.openLog);

        register = findViewById(R.id.register);

        register.setOnClickListener(v -> validateData());

        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }

            private void openLogin() {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class ));
                finish();
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMain();
        }
    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    private void validateData() {
        name = regName.getText().toString();
        email = regEmail.getText().toString();
        password = regPass.getText().toString();
        number = regNumber.getText().toString();

        if (name.isEmpty()) {
            regName.setError("Required");
            regName.requestFocus();
        } else if (email.isEmpty()) {
            regEmail.setError("Required");
            regEmail.requestFocus();
        } else if (password.isEmpty()) {
            regPass.setError("Required");
            regPass.requestFocus();
        }else if (number.isEmpty()) {
            regNumber.setError("Required");
            regNumber.requestFocus();
        }else {
        createUser();
        }
    }

    private void createUser() {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uploadData();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error : " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData() {
        dbRef = reference.child("users");
        String key = dbRef.push().getKey();

        HashMap<String, String> user = new HashMap<>();
        user.put("key", key);
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);
        user.put("number", number);
        user.put("status", "no");

        dbRef.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            openMain();
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}