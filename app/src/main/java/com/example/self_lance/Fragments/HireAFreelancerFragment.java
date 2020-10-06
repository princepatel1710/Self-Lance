package com.example.self_lance.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.self_lance.FreelancerDetails;
import com.example.self_lance.HelplineAdapter;
import com.example.self_lance.ItemClickCallback;
import com.example.self_lance.R;
import com.example.self_lance.UserInformation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HireAFreelancerFragment extends Fragment {
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_hire_freelancer, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();

    }


    private void getData() {
        final RecyclerView rvAdminList = root.findViewById(R.id.rvAdminList);
        rvAdminList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAdminList.setAdapter(new HelplineAdapter(new ItemClickCallback() {
            @Override
            public void onItemClick(int position) {
                if (rvAdminList.getAdapter() != null) {
                    UserInformation mBean = ((HelplineAdapter) rvAdminList.getAdapter()).getCurrentItem(position);
                    Intent intent = new Intent(getContext(), FreelancerDetails.class);
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