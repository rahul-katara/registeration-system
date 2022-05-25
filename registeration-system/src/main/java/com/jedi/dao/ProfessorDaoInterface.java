package com.jedi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.jedi.Course;


public interface ProfessorDaoInterface {
	public ArrayList<Course> viewCourses(String professorId) throws SQLException, ClassNotFoundException;
    public boolean registerCourses(String professorId,int courseId) throws SQLException;
    public ArrayList<Course> viewAvailableCourses() throws SQLException ;
    public Map<String, ArrayList<String> > viewEnrolledStudents(String professorId) throws SQLException;
    public int provideGrade(String professorId, int courseId,String studentId,String Grade) throws SQLException;

}
