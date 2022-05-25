package com.jedi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeCard {

	private String userId;
    private int courseId;
    private String courseName;
    private String grade;
    
    public GradeCard() {
    	
    }
    
    public GradeCard(String userId, int courseId, String grade, String courseName) {
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
        this.courseName = courseName;
    }

    @JsonProperty
    public String getUserId() {
        return userId;
    }

    @JsonProperty
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty
    public int getCourseId() {
        return courseId;
    }

    @JsonProperty
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @JsonProperty
    public String getGrade() {
        return grade;
    }

    @JsonProperty
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getCourseName() {
    	return this.courseName;
    }
    
    public void setCourseName(String courseName) {
    	this.courseName = courseName;
    }
}
