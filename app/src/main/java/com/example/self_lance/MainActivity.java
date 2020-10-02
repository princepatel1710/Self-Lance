package com.example.self_lance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Button btnJob, btnFreelancer;
    TextView tvUserName, tvUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            // hooks
            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            toolbar = findViewById(R.id.toolbar);
            btnJob=findViewById(R.id.btnLookingForAjob);
            btnFreelancer=findViewById(R.id.btnHireAfreelancer);

            //Toolbar
            setSupportActionBar(toolbar);
            //toggle

            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_home);


            //Button
            btnJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), LookingForJobDescription.class));
                }
            });
            btnFreelancer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), FreelancerDetails.class));
                }
            });

            NavigationView navigationView = findViewById(R.id.nav_view);
            if (navigationView.getHeaderCount() > 0) {
                tvUserName = navigationView.getHeaderView(0).findViewById(R.id.tvUserName);
                tvUserEmail = navigationView.getHeaderView(0).findViewById(R.id.tvUserEmail);
            }

            navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                    pref.edit().putBoolean("is_login", false).apply();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this, login.class));
                    finishAffinity();
                    return true;
                }
            });

            setUserDetail();


        }


    }
     @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_freelancer:
                startActivity(new Intent(getApplicationContext(), HireAFreelancer.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), profile.class));
                break;
            case R.id.nav_chat:
                startActivity(new Intent(getApplicationContext(), chat.class));
                break;
            case R.id.nav_job:
                startActivity(new Intent(getApplicationContext(), Looking_for_job.class));
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }




    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

        }
        super.onBackPressed();
    }
    private void setUserDetail() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.getUid();
            DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("User");
            databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserInformation info = dataSnapshot.child(user.getUid()).getValue(UserInformation.class);

                    Constant.setInfo(info);

                    if (info != null && tvUserName != null && tvUserEmail != null) {
                        String userName = info.getfirstName() + " " + info.getLastName();
                        tvUserName.setText(userName);
                        tvUserEmail.setText(info.getEmail());
                    }

//                    Log.d("Uri", info.getImagePath());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }



}