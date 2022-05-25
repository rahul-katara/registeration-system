package com.jedi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Professor extends User{

	private String professorId;
    private String areaOfExpertise;
    private int yearsOfExperience;

    public Professor() {
    	
    }

    public Professor(String userId, String userName, String emailId, String password, String contactNo, String professorId, String areaOfExpertise, int yearsOfExperience) {
        super(userId, userName, emailId, password, contactNo);
        this.professorId = professorId;
        this.areaOfExpertise = areaOfExpertise;
        this.yearsOfExperience = yearsOfExperience;
    }

    @JsonProperty
    public String getProfessorId() {
        return professorId;
    }

    @JsonProperty
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @JsonProperty
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    @JsonProperty
    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }
    
    @JsonProperty
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    
    @JsonProperty
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
