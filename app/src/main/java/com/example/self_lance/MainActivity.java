package com.example.self_lance;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button looking_for_job_btn = (Button) findViewById(R.id.btnLookingForAjob);
        Button hire_a_freelancer_btn = (Button) findViewById(R.id.btnHireAfreelancer);



        {
            hire_a_freelancer_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), HireAFreelancer.class));
                }
            });

            looking_for_job_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Looking_for_job.class));
           /* if you want to finish the first activity then
            finish(); */
                }
            });

            // Navigation bar
            drawerLayout=findViewById(R.id.drawer_layout);
            navigationView=findViewById(R.id.nav_view);
           // toolbar=findViewById(R.id.toolbar);

            //Toolbar
           toolbar= (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //toggle


            //Navigation Drawer Menu
           // ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.naigation_drawer_open,R.string.naigation_drawer_close);
            //
            //
            toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.naigation_drawer_open, R.string.naigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {

        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
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
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), login.class));
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}