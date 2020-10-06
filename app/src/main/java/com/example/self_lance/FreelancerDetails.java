package com.example.self_lance;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class FreelancerDetails extends AppCompatActivity {

    private TextView tvUserName, tvPrice, tvDesc, tvSkill, tvExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_details);
        ImageView back_btn = (ImageView) findViewById(R.id.ivBack);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setData();
    }

    private void setData() {
        UserInformation information = getIntent().getParcelableExtra("user_detail");
        tvUserName = findViewById(R.id.tvUserName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDesc = findViewById(R.id.tvDesc);
        tvSkill = findViewById(R.id.tvSkill);
        tvExp = findViewById(R.id.tvExp);

        tvUserName.setText(information.getFirstName() + " " + information.getLastName());
        tvPrice.setText(information.getPrice());
        tvDesc.setText(information.getDescription());
        tvExp.setText(information.getExeperience() + " Years");
        if (information.getSkillList() != null && information.getSkillList().size() > 0) {
            List<Integer> skillList = information.getSkillList();
            StringBuilder skills = new StringBuilder();
            if (skillList.contains(0)) {
                skills.append("Skill-1");
                skills.append(", ");
            }
            if (skillList.contains(1)) {
                skills.append("Skill-2");
                skills.append(", ");
            }
            if (skillList.contains(2)) {
                skills.append("Skill-3");
                skills.append(", ");
            }
            if (skillList.contains(3)) {
                skills.append("Skill-4");
                skills.append(", ");
            }
            if (skillList.contains(4)) {
                skills.append("Skill-5");
                skills.append(", ");
            }
            if (skillList.contains(5)) {
                skills.append("Skill-6");
            }
            tvSkill.setText(skills.toString());
        }
    }
}