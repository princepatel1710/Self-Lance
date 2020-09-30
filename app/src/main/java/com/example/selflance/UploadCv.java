package com.example.selflance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UploadCv extends AppCompatActivity {
private Button SelectBtn;
private Button PauseBtn;
private Button CancelBtn;
private ProgressBar progressBar;
private StorageTask mStorageTask;
private final static int FILE_SELECT_CODE = 1;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_cv);
        SelectBtn = findViewById(R.id.selectbutton);
        PauseBtn = findViewById(R.id.pausebutton);
        CancelBtn = findViewById(R.id.cancelbutton);
        progressBar = findViewById(R.id.progressBar);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        SelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileSelector();

            }
        });

        PauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = PauseBtn.getText().toString();

                if (btnText.equals("Pause Upload")) {
                    mStorageTask.pause();
                    PauseBtn.setText("Resume Upload");
                } else {
                    mStorageTask.resume();
                    PauseBtn.setText("Pause Upload");
                }
            }
        });
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorageTask.cancel();
            }
        });
    }

    private void openFileSelector() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent,"Select a File to Upload"),
            FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK) {
            Uri fileUri = data.getData();

            String uriString = fileUri.toString();

            File myFile = new File(uriString);
            //String path = myFile.getAbsolutePath();

            String displayName = null;

            if(uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = UploadCv.this.getContentResolver().query(fileUri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }

                } finally {
                    cursor.close();
                }
            }else if (uriString.startsWith("file://")) {
                      displayName = myFile.getName();
                }

            StorageReference riversRef = mStorageRef.child("files/" +displayName);

            mStorageTask = riversRef.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                            progressBar.setVisibility(View.VISIBLE);

                            Toast.makeText(UploadCv.this, "File Uploaded!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(UploadCv.this, "There was an error in uploading file.",
                                    Toast.LENGTH_SHORT).show();
                            // ...
                        }
                    });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
