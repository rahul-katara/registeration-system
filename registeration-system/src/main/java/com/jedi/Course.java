package com.jedi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {

	private int courseId;
    private String courseName;
    private int courseFee;

    public Course() {
    	
    }

    public Course(int courseId, String courseName, int courseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
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
    public String getCourseName() {
        return courseName;
    }

	@JsonProperty
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
	
	@JsonProperty
	public int getCourseFee() {
		return this.courseFee;
	}
	
	@JsonProperty
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
}
