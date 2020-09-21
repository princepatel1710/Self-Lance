package com.example.self_lance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = (Button) findViewById(R.id.btnLogin);
        Button signup_button = (Button) findViewById(R.id.btnSignup);
        TextView forgot_password_button = (TextView) findViewById(R.id.btnForgot);

        {
            signup_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), signup.class));
                }
            });

            login_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(login.this, "You are logged in...!!", Toast.LENGTH_SHORT).show();

           /* if you want to finish the first activity then
            finish(); */
                }
            });
            forgot_password_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), forgot_password.class));
                }
            });
        }
    }
}