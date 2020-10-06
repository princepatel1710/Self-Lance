package com.example.self_lance;

class UserSkill {
    int id;
    String skillName;

    public UserSkill(int id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
