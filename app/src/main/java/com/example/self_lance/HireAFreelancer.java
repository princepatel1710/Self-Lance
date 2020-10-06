package com.example.self_lance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HireAFreelancer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_a_freelancer);
        ImageView back_btn = (ImageView) findViewById(R.id.ivBack);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();
    }

    private void getData() {
        final RecyclerView rvAdminList = findViewById(R.id.rvAdminList);
        rvAdminList.setLayoutManager(new LinearLayoutManager(this));
        rvAdminList.setAdapter(new HelplineAdapter(new ItemClickCallback() {
            @Override
            public void onItemClick(int position) {
                if (rvAdminList.getAdapter() != null) {
                    UserInformation mBean = ((HelplineAdapter) rvAdminList.getAdapter()).getCurrentItem(position);
                    Intent intent = new Intent(HireAFreelancer.this, FreelancerDetails.class);
                    intent.putExtra("user_detail", mBean);
                    startActivity(intent);
                }
            }
        }));
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("User");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<UserInformation> helplineList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserInformation mBean = postSnapshot.getValue(UserInformation.class);
                    helplineList.add(mBean);
                }
                if (rvAdminList.getAdapter() != null)
                    ((HelplineAdapter) rvAdminList.getAdapter()).submitList(helplineList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}