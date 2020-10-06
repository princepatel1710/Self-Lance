package com.example.self_lance.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.self_lance.MainActivity;
import com.example.self_lance.R;
import com.example.self_lance.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class CompleteProfileFragment extends Fragment {
    EditText FirstName, LastName, UserName, Email, Description, Experience, edtPrice;
    TextView userProfile;
    Button save_btn;
    CheckBox skill_1;
    CheckBox skill_2;
    CheckBox skill_3;
    CheckBox skill_4;
    CheckBox skill_5;
    CheckBox skill_6;
    DatabaseReference reference;
    UserInformation userInformation;
    int i = 0;
    private String userID;
    private ProgressDialog prgDialog;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirstName = root.findViewById(R.id.edtFN);
        LastName = root.findViewById(R.id.edtLN);
        UserName = root.findViewById(R.id.edtUN);
        Email = root.findViewById(R.id.edtEmail);
        userProfile = root.findViewById(R.id.userProfile);
        Description = (EditText) root.findViewById(R.id.edtDescription);
        Experience = (EditText) root.findViewById(R.id.edtExperience);
        edtPrice = root.findViewById(R.id.edtPrice);
        userInformation = new UserInformation();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("User");
        save_btn = root.findViewById(R.id.save_edit_profile);


        skill_1 = root.findViewById(R.id.cb_skill_1);
        skill_2 = root.findViewById(R.id.cb_skill_2);
        skill_3 = root.findViewById(R.id.cb_skill_3);
        skill_4 = root.findViewById(R.id.cb_skill_4);
        skill_5 = root.findViewById(R.id.cb_skill_5);
        skill_6 = root.findViewById(R.id.cb_skill_6);

        setUserDetail();
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataComplete())
                    signUp();
            }
        });
    }

    private void setUserDetail() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.getUid();
            DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("User");
            databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.e("DataSnapShot", dataSnapshot.child(user.getUid()).child("firstName").getValue().toString());
                    UserInformation info = dataSnapshot.child(user.getUid()).getValue(UserInformation.class);
                    userInformation = info;
                    FirstName.setText(userInformation.getFirstName());
                    LastName.setText(userInformation.getLastName());
                    UserName.setText(userInformation.getUsername());
                    Experience.setText(userInformation.getExeperience());
                    Description.setText(userInformation.getDescription());
                    edtPrice.setText(userInformation.getPrice());

                    List<Integer> skillList = userInformation.getSkillList();
                    if (skillList != null) {
                        skill_1.setChecked(skillList.contains(0));
                        skill_2.setChecked(skillList.contains(1));
                        skill_3.setChecked(skillList.contains(2));
                        skill_4.setChecked(skillList.contains(3));
                        skill_5.setChecked(skillList.contains(4));
                        skill_6.setChecked(skillList.contains(5));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("TAG", "==>" + databaseError.getMessage());
                }
            });
        }
    }

    private boolean isDataComplete() {
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();
        String userName = UserName.getText().toString().trim();
        String exp = Experience.getText().toString().trim();
        String price = edtPrice.getText().toString().trim();
        String desc = Description.getText().toString().trim();
        FirstName.setError(null);
        LastName.setError(null);
        UserName.setError(null);
        Experience.setError(null);
        edtPrice.setError(null);
        Description.setError(null);
        int isSkill = getSkillList().size();
        if (TextUtils.isEmpty(firstName)) {
            FirstName.setError("Please Enter Firs Name");
            FirstName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(lastName)) {
            LastName.setError("Please Enter Last Name");
            LastName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(userName)) {
            UserName.setError("Please Enter UserName");
            UserName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(exp)) {
            Experience.setError("Please Enter your Experience");
            Experience.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(price)) {
            edtPrice.setError("Please Enter Price");
            edtPrice.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(desc)) {
            Description.setError("Please Enter Description");
            Description.requestFocus();
            return false;
        } else if (isSkill <= 0) {
            Toast.makeText(getContext(), "Select atleast one skill", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public void signUp() {
        prgDialog = new ProgressDialog(getContext());
        prgDialog.setMessage("Updatiing User....");
        prgDialog.show();
        prgDialog.setCancelable(false);

        UserInformation info = new UserInformation(FirstName.getText().toString().trim(),
                LastName.getText().toString().trim(), userID, userInformation.getEmail(),
                userInformation.getPassword(), UserName.getText().toString().trim(),
                Experience.getText().toString().trim(), Description.getText().toString().trim(),
                getSkillList(), edtPrice.getText().toString().trim());

        reference.child(userID).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                prgDialog.dismiss();
                if (task.isSuccessful()) {
                    Log.d("TAG", "Profile Update");
                    ((MainActivity) getActivity()).loadHome();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                prgDialog.dismiss();
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(e.getLocalizedMessage())
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Error");
                alert.show();
                e.printStackTrace();
                Log.e("not inserted", "user not Created", e);
            }
        });
    }

    private List<Integer> getSkillList() {
        ArrayList<Integer> skillList = new ArrayList<Integer>();
        if (skill_1.isChecked())
            skillList.add(0);
        if (skill_2.isChecked())
            skillList.add(1);
        if (skill_3.isChecked())
            skillList.add(2);
        if (skill_4.isChecked())
            skillList.add(3);
        if (skill_5.isChecked())
            skillList.add(4);
        if (skill_6.isChecked())
            skillList.add(5);


        return skillList;
    }

}