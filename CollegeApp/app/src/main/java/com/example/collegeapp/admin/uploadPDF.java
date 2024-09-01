package com.example.collegeapp.admin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class uploadPDF extends AppCompatActivity {

    private CardView addPDF;
    private final int REQ = 1;
    private Uri pdfData;
    private TextView pdfTextView;

    private EditText PDFTitle;
    private Button uploadPDFBtn;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ProgressDialog pd;

    private String pdfName, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        pd = new ProgressDialog(this);
        addPDF = findViewById(R.id.addPDF);
        PDFTitle = findViewById(R.id.PDFTitle);
        uploadPDFBtn = findViewById(R.id.UploadPDFBtn);
        pdfTextView = findViewById(R.id.pdfTextView);


        addPDF.setOnClickListener(v -> openGallery());

        uploadPDFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = PDFTitle.getText().toString();
                if(title.isEmpty()){
                    PDFTitle.setError("Empty");
                    PDFTitle.requestFocus();
                } else if (pdfData == null) {
                    Toast.makeText(uploadPDF.this, "Please upload PDF", Toast.LENGTH_SHORT).show();
                    
                } else {
                    uploadpdf();
                }

            }
        });
    }

    private void uploadpdf() {
        pd.setTitle("Please wait...");
        pd.setMessage("Uploading pdf...");
        pd.setCancelable(false); // Disallow dialog cancellation
        pd.show();

        StorageReference reference = storageReference.child("pdf/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri uri = uriTask.getResult();
                uploadData(String.valueOf(uri));
                        
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(uploadPDF.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void uploadData(String downloadUrl) {
        String uniqueKey = databaseReference.child("pdf").push().getKey();
        HashMap data = new HashMap();
        data.put("PDFTitle",title);
        data.put("pdfUrl", downloadUrl);
        databaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(uploadPDF.this, "PDF uploaded Successfully", Toast.LENGTH_SHORT).show();
                PDFTitle.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(uploadPDF.this, "Failed to upload PDF", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select PDF File"), REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK) {
            pdfData = data.getData();

            if (pdfData.toString().startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = uploadPDF.this.getContentResolver().query(pdfData,null,null,null,null);
                    if(cursor != null && cursor.moveToFirst()){
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }else if(pdfData.toString().startsWith("file://")){
                pdfName = new File(pdfData.toString()).getName();

            }
            pdfTextView.setText(pdfName);
        }
    }
}