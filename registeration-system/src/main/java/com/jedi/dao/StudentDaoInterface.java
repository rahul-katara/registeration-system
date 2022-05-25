package com.jedi.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jedi.Course;
import com.jedi.GradeCard;
import com.jedi.Student;

public interface StudentDaoInterface {

    Student getStudent(String studentId) throws SQLException;

//    String getfeeStatus(String studentId) throws SQLException;

    ArrayList<Integer> registeredCoursesList(String studentId) throws SQLException;

    void registerCourses(String studentId,ArrayList<Integer> courses) throws SQLException;

    ArrayList<Course> viewCourses() throws SQLException;

    Course viewCourse(int courseId) throws SQLException;

    String removeStudent(String studentId) throws SQLException;

    ArrayList<GradeCard> viewGrades(String studentId) throws SQLException;
}
