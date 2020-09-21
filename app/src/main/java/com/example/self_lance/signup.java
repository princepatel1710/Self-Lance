package com.example.self_lance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup_button=(Button)findViewById(R.id.btnSignUp);
        signup_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), login.class));
                Toast.makeText(signup.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });
    }
}