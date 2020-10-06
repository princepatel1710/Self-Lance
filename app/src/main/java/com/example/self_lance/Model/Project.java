package com.example.self_lance.Model;

public class Project {
    private String projectName;
    private String budget;
    private String description;


    public Project(String projectName, String budget, String description) {
        this.projectName = projectName;
        this.budget = budget;
        this.description=description;

    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBudget() {

        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description;
    }

    public Project()
    {

    }
}
