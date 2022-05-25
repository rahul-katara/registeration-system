package com.jedi.dao;

import com.jedi.Course;
import com.jedi.Professor;
import com.jedi.Student;

public interface AdminDaoInterface {

	boolean addProfessor(Professor professor);
    boolean addCourse(Course course);
    boolean dropCourse(int courseId);
    boolean validateCredentials(String adminId, String password);
	boolean addStudent(Student student);
}
