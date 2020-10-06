package com.example.self_lance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.self_lance.Fragments.AboutUsFragment;
import com.example.self_lance.Fragments.ChatFragment;
import com.example.self_lance.Fragments.CompleteProfileFragment;
import com.example.self_lance.Fragments.HireAFreelancerFragment;
import com.example.self_lance.Fragments.HomeFragment;
import com.example.self_lance.Fragments.LookingForJobFragment;
import com.example.self_lance.Fragments.ProfileFragment;
import com.example.self_lance.Fragments.RateSelfLanceFragment;
import com.example.self_lance.Fragments.UploadCvFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    TextView tvUserName, tvUserEmail;
    Button btnJob, btnFreelancer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        btnJob = findViewById(R.id.btnLookingForAjob);
        btnFreelancer = findViewById(R.id.btnHireAfreelancer);


        //Toolbar
        setSupportActionBar(toolbar);
        //toggle

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);




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
        loadFragment(new HomeFragment());
    }

    private void setUserDetail() {
        final UserInformation userInformation = new UserInformation();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.getUid();
            DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("User");
            databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.e("DataSnapShot", dataSnapshot.child(user.getUid()).child("firstName").getValue().toString());
                    //UserInformation info = dataSnapshot.child(user.getUid()).getValue(UserInformation.class);
                    //Constant.setInfo(info);
                    String firstName = String.valueOf(dataSnapshot.child(user.getUid()).child("firstName").getValue());
                    String lastName = String.valueOf(dataSnapshot.child(user.getUid()).child("lastName").getValue());
                  //  String uid = String.valueOf(dataSnapshot.child(user.getUid()).child("uid").getValue());
                   // String password = String.valueOf(dataSnapshot.child(user.getUid()).child("password").getValue());
                    String email = String.valueOf(dataSnapshot.child(user.getUid()).child("email").getValue());
                   // String username = String.valueOf(dataSnapshot.child(user.getUid()).child("username").getValue());

                    userInformation.setFirstName(firstName);
                    userInformation.setLastName(lastName);
                   // userInformation.setUid(uid);
                    //userInformation.setPassword(password);
                    userInformation.setEmail(email);
                   // userInformation.setUsername(username);



                    /*if (info != null && tvUserName != null && tvUserEmail != null) {
                        String userName = info.getfirstName() + " " + info.getLastName();
                        tvUserName.setText(userName);
                        tvUserEmail.setText(info.getEmail());
                    }*/
                    if (firstName != null && lastName != null && email != null) {
                        String userName = firstName + " " + lastName;
                        tvUserName.setText(userName);
                        tvUserEmail.setText(email);
                    }

//                    Log.d("Uri", info.getImagePath());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                loadFragment(new HomeFragment());
                break;
            case R.id.nav_freelancer:
                loadFragment(new HireAFreelancerFragment());
                break;
            case R.id.nav_profile:
                loadFragment(new ProfileFragment());
                break;
            case R.id.nav_chat:
                loadFragment(new ChatFragment());
                break;
            case R.id.nav_job:
                loadFragment(new LookingForJobFragment());
                break;
            case R.id.nav_complete_profile:
                loadFragment(new CompleteProfileFragment());
                break;
            case R.id.nav_upload_cv:
                loadFragment(new UploadCvFragment());
                break;
            case R.id.nav_about_app:
                loadFragment(new AboutUsFragment());
                break;
            case R.id.nav_rate_us:
                loadFragment(new RateSelfLanceFragment());
                break;
            case R.id.nav_upload_project:
                startActivity(new Intent(getApplicationContext(),UploadProject.class));
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }

    public void loadHome() {
        loadFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

        }
        super.onBackPressed();
    }

}