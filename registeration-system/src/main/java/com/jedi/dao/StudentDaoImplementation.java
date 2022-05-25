package com.jedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jedi.Course;
import com.jedi.GradeCard;
import com.jedi.Student;
import com.jedi.utils.ConnectionUtil;


public class StudentDaoImplementation implements StudentDaoInterface {

	ConnectionUtil connectionUtil;

	public StudentDaoImplementation(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

    @Override
    public Student getStudent(String studentId) throws SQLException {

    	try (Connection conn = connectionUtil.getConnection();){
            String sql = "SELECT * FROM student where studentId=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, studentId);
            ResultSet rs = statement.executeQuery();
            String sql1 = "SELECT * FROM user where userId=?";
            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setString(1, studentId);
            ResultSet rs1 = statement1.executeQuery();
            while(rs.next()&& rs1.next())
            {
                Student student=new Student(studentId,rs1.getString(2),rs1.getString(3), rs1.getString(4),rs1.getString(5),rs.getInt(2),rs.getString(3), rs.getString(4));
                return student;
            }
    	}
        return null;
    }

//    @Override
//    public String getfeeStatus(String studentId) throws SQLException {
//        Connection conn = connectionUtil.getConnection();
//        String sql = "SELECT paymentId FROM bookkeeper where studentId='"+studentId+"'";
//        PreparedStatement statement = conn.prepareStatement(sql);
//        ResultSet rs = statement.executeQuery();
//        while(rs.next())
//        {String x = "Fees Paid";
//            return x;
//        }
//        return "Fees not paid";
//    }

    @Override
    public ArrayList<Integer> registeredCoursesList(String studentId) throws SQLException {
        try(Connection conn = connectionUtil.getConnection();){
        	String sql = "SELECT * FROM registrar where userId='"+studentId+"'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            ArrayList<Integer> courses=new ArrayList<>();
            while(rs.next())
            {
                courses.add(rs.getInt(2));
            }
            return courses;
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        return null;
    }

    @Override
    public void registerCourses(String studentId, ArrayList<Integer> courses) throws SQLException {
        try(Connection connection = connectionUtil.getConnection();){
            for(Integer course:courses) {
            	
            	String sql = "Select * from registrar where courseId=?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, course);
                ResultSet rs = preparedStatement.executeQuery();
                
                int cnt = 0;
                
                while(rs.next())
                {
                	if(cnt > 10) break;
                	cnt++;
                }
                
                if(cnt>=10) continue;
            	
            	sql = "insert into registrar values (?, ?, ?)";
            	preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, studentId);
                preparedStatement.setInt(2, course);
                preparedStatement.setString(3, " NA ");
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public ArrayList<Course> viewCourses() throws SQLException {
        ArrayList<Course> courses=new ArrayList<Course>();
        Connection conn = connectionUtil.getConnection();
        String sql = "SELECT * FROM course where course.courseId not in (select courseId from registrar Group by courseId Having COUNT(*) > 10)";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
          Course course=new Course();
          course.setCourseId(rs.getInt(1));
          course.setCourseName(rs.getString(2));
          course.setCourseFee(rs.getInt(3));
          courses.add(course);
        }
        return courses;
    }

    @Override
    public Course viewCourse(int courseId) throws SQLException {
        Connection conn = connectionUtil.getConnection();
        String sql = "SELECT * FROM course where courseId="+courseId;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
            Course course=new Course();
            course.setCourseId(rs.getInt(1));
            course.setCourseName(rs.getString(2));
            return course;
        }
        return null;
    }

    @Override
    public String removeStudent(String studentId) throws SQLException {

        Connection con = connectionUtil.getConnection();
        Statement stmt = con.createStatement();
        String sql = "delete from student where studentId = " + studentId;
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected == 1) {
            return "Student Removed!";
        }
        return null;
    }

    @Override
    public ArrayList<GradeCard> viewGrades(String studentId) throws SQLException {
    	ArrayList<GradeCard> gradeCards=new ArrayList<>();
        Connection conn = connectionUtil.getConnection();
        String sql = "SELECT registrar.studentId, registrar.courseId, registrar.grade, course.courseName FROM registrar, course where registrar.studentId=? and registrar.courseId=course.courseId";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, studentId);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
            GradeCard g=new GradeCard(rs.getString(1), rs.getInt(2),rs.getString(3), rs.getString(4));
            gradeCards.add(g);
        }
        return gradeCards;
    }
}
