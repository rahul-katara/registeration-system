package com.jedi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student extends User {

    private int semester;
    private String grade;
    private String feeStatus;
    
    public  Student(){}
    public Student(String userId, String userName, String emailId, String password, String contactNo, int semester, String grade, String feeStatus) {
        super(userId, userName, emailId, password, contactNo);
        this.semester = semester;
        this.grade = grade;
        this.feeStatus = feeStatus;
    }

    @JsonProperty
    public int getSemester() {
        return semester;
    }

    @JsonProperty
    public void setSemester(int semester) {
        this.semester = semester;
    }

    @JsonProperty
    public String getGrade() {
        return grade;
    }

    @JsonProperty
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JsonProperty
    public String getFeeStatus() {
        return feeStatus;
    }

    @JsonProperty
    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

}
