package com.example.self_lance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}

   /* TextView tvFirstName, tvLastName, tvUserName, tvEmail,userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvFirstName = findViewById(R.id.firstName);
        tvLastName = findViewById(R.id.lastName);
        tvUserName = findViewById(R.id.userName);
        tvEmail = findViewById(R.id.email);
        userProfile = findViewById(R.id.userProfile);

        Button logout_btn= (Button) findViewById(R.id.btnLogout);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                pref.edit().putBoolean("is_login", false).apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profile.this, login.class));
                finishAffinity();
            }
        });
        setUserDetail();



    }

    private void setUserDetail() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user !=null)

        {
            user.getUid();
            DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("User");
            databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserInformation info = dataSnapshot.child(user.getUid()).getValue(UserInformation.class);

                    Constant.setInfo(info);

                    if (info != null && tvUserName != null && tvEmail != null && tvFirstName != null && tvLastName != null && userProfile != null) {
                        userProfile.setText(info.getUsername());
                        tvUserName.setText(info.getUsername());
                        tvEmail.setText(info.getEmail());
                        tvFirstName.setText(info.getfirstName());
                        tvLastName.setText(info.getLastName());

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
}*/