package com.example.self_lance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.example.self_lance.Model.Project;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Recycleview_Job extends AppCompatActivity {


    private RecyclerView projectList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview__job);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("projectDetail");
        mDatabase.keepSynced(true); //database update automatically...

        projectList=(RecyclerView)findViewById(R.id.myRecyclerView);
        projectList.setHasFixedSize(true);
        projectList.setLayoutManager(new LinearLayoutManager(this));


        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Project, ProjectViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Project, ProjectViewHolder>
                (Project.class,R.layout.looking_job,ProjectViewHolder.class,mDatabase)
        {
            protected void populateViewHolder(ProjectViewHolder viewHolder, final Project model, int position){
                viewHolder.setprojectName(model.getProjectName());
                viewHolder.setbudget(model.getBudget());
                viewHolder.setdescription(model.getDescription());
                viewHolder.Linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),LookingForJobDescription.class);
                        intent.putExtra("projectName", model.getProjectName());
                        intent.putExtra("budget",model.getBudget());
                        intent.putExtra("description",model.getDescription());
                        startActivity(intent);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    }
                });
            }
        };
        projectList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class ProjectViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        LinearLayout Linear;
        public ProjectViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
            Linear=itemView.findViewById(R.id.Linear);
        }
        public void setprojectName(String projectName)
        {
            TextView pName=(TextView)mView.findViewById(R.id.pName);
            pName.setText(projectName);

        }
        public void setbudget(String budget)
        {
            TextView pBudget=(TextView)mView.findViewById(R.id.pBudget);
            pBudget.setText(budget);
        }

        public void setdescription(String description)
        {
            TextView pDesc=(TextView)mView.findViewById(R.id.pDesc);
            pDesc.setText(description);
        }
    }
}