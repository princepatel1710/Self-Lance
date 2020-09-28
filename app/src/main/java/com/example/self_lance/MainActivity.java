package com.example.self_lance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button looking_for_job_button = (Button) findViewById(R.id.btnLookingForAjob);
        Button hire_a_freelancer_button = (Button) findViewById(R.id.btnHireAfreelancer);


        {
            hire_a_freelancer_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), HireAFreelancer.class));
                }
            });

            looking_for_job_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Looking_for_job.class));
           /* if you want to finish the first activity then
            finish(); */
                }
            });
        }

    }
}