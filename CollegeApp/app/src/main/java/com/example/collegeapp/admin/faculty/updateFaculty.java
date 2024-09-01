package com.example.collegeapp.admin.faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class updateFaculty extends AppCompatActivity {
    FloatingActionButton fab;

    private RecyclerView aimlDepartment, csDepartment, civilDepartment, eceDepartment, meDepartment;
    private LinearLayout aimlNoData, csNoData, civilNoData, eceNoData, meNoData;
    private List<teacherData> list1, list2, list3, list4, list5;

    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);


        aimlDepartment = findViewById(R.id.aimlDepartment);
        csDepartment = findViewById(R.id.csDepartment);
        civilDepartment = findViewById(R.id.civilDepartment);
        eceDepartment = findViewById(R.id.eceDepartment);
        meDepartment = findViewById(R.id.meDepartment);


        aimlNoData = findViewById(R.id.aimlNoData);
        csNoData = findViewById(R.id.csNoData);
        civilNoData = findViewById(R.id.civilNoData);
        eceNoData = findViewById(R.id.eceNoData);
        meNoData = findViewById(R.id.meNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");

        csDepartment();
        aimlDepartment();
        civilDepartment();
        eceDepartment();
        meDepartment();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(updateFaculty.this, addTeachers.class));
            }
        });
    }

    private void aimlDepartment() {
        dbRef = reference.child("AI");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    aimlNoData.setVisibility(View.VISIBLE);
                    aimlDepartment.setVisibility(View.GONE);
                } else {
                    aimlNoData.setVisibility(View.GONE);
                    aimlDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        teacherData data = snapshot.getValue(teacherData.class);
                        list1.add(data);
                    }
                    aimlDepartment.setHasFixedSize(true);
                    aimlDepartment.setLayoutManager(new LinearLayoutManager(updateFaculty.this));
                    adapter = new TeacherAdapter(list1, updateFaculty.this,"AI");
                    aimlDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(updateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void civilDepartment() {
        dbRef = reference.child("Civil");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()) {
                    civilDepartment.setVisibility(View.VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                } else {
                    civilNoData.setVisibility(View.GONE);
                    civilDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        teacherData data = dataSnapshot.getValue(teacherData.class);
                        list2.add(data);
                    }
                    civilDepartment.setHasFixedSize(true);
                    civilDepartment.setLayoutManager(new LinearLayoutManager(updateFaculty.this));
                    adapter = new TeacherAdapter(list2, updateFaculty.this, "Civil");
                    civilDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(updateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void csDepartment() {
        dbRef = reference.child("CSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                } else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        teacherData data = dataSnapshot.getValue(teacherData.class);
                        list3.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(updateFaculty.this));
                    adapter = new TeacherAdapter(list3, updateFaculty.this, "CSE");
                    csDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(updateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void eceDepartment() {
        dbRef = reference.child("ECE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()) {
                    eceNoData.setVisibility(View.VISIBLE);
                    eceDepartment.setVisibility(View.GONE);
                } else {
                    eceNoData.setVisibility(View.GONE);
                    eceDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        teacherData data = dataSnapshot.getValue(teacherData.class);
                        list4.add(data);
                    }
                    eceDepartment.setHasFixedSize(true);
                    eceDepartment.setLayoutManager(new LinearLayoutManager(updateFaculty.this));
                    adapter = new TeacherAdapter(list4, updateFaculty.this, "ECE");
                    eceDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(updateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void meDepartment() {
        dbRef = reference.child("ME");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if (!snapshot.exists()) {
                    meNoData.setVisibility(View.VISIBLE);
                    meDepartment.setVisibility(View.GONE);
                } else {
                    meNoData.setVisibility(View.GONE);
                    meDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        teacherData data = dataSnapshot.getValue(teacherData.class);
                        list5.add(data);
                    }
                    meDepartment.setHasFixedSize(true);
                    meDepartment.setLayoutManager(new LinearLayoutManager(updateFaculty.this));
                    adapter = new TeacherAdapter(list5, updateFaculty.this, "ME");
                    meDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(updateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}