package com.example.self_lance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LookingForJobDescription extends AppCompatActivity {
    TextView name,budget,description;
    String projectName,Budget,Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_for_job_description);

        name=findViewById(R.id.proName);
        budget=findViewById(R.id.proBudget);
        description=findViewById(R.id.proDesc);
        //description=findViewById(R.id.proDesc);
        projectName=getIntent().getStringExtra("projectName");
        Budget=getIntent().getStringExtra("budget");
        Description=getIntent().getStringExtra("description");
        name.setText(projectName);
        budget.setText(Budget);
        description.setText(Description);
    }
}
