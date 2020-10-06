package com.example.self_lance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UploadProject extends AppCompatActivity {

    private Button save;
    private EditText projectName, budget, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_project);

        save = findViewById(R.id.save);
        projectName=findViewById(R.id.projectName);
        budget=findViewById(R.id.budget);
        description=findViewById(R.id.description);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map=new HashMap<>();
                map.put("projectName",projectName.getText().toString());
                map.put("budget",budget.getText().toString());
                map.put("description",description.getText().toString());
                startActivity(new Intent(getApplicationContext(), Recycleview_Job.class));

                FirebaseDatabase.getInstance().getReference().child("projectDetail").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("successfully save","Successfully saved");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Something Wrong","Fail to create"+e.toString());
                    }
                });


            }
        });

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
