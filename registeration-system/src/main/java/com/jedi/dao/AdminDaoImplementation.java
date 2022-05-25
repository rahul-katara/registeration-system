package com.jedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jedi.Course;
import com.jedi.Professor;
import com.jedi.Student;
import com.jedi.utils.ConnectionUtil;

public class AdminDaoImplementation implements AdminDaoInterface {

	ConnectionUtil connectionUtil;

	public AdminDaoImplementation(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

    @Override
    public boolean addProfessor(Professor professor) {
        
        try(Connection con = connectionUtil.getConnection();) {
        	
        	String sql = "select * from professor where professorId=?";
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, professor.getUserId());
			
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				return false;
			}
        	
            sql = "insert into user(userId, password, userName,emailId, contactNo) values('" + professor.getUserId() + "', '" + professor.getPassword()+ "' , '"+ professor.getUserName()+ "', '" + professor.getEmailId() + "' , '" + professor.getContactNo() + "')";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate(sql);

            sql = "insert into professor(professorId, areaOfExpertise, yearsOfExperience) values('"+ professor.getUserId()+ "' ,' "+professor.getAreaOfExpertise()+"',' "+professor.getYearsOfExperience()+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return true;
    }

    public boolean validateCredentials(String adminId, String password){
        try{
            Connection conn = connectionUtil.getConnection();

            String sql = "SELECT * FROM user where userId like '"+adminId+"' and password like  '"+password+"'";
//            String sql = "select * from user where userid="+studentId+" and password="+password;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }

    }
    
    @Override
    public boolean addCourse(Course course) {
        
    	try(Connection con = connectionUtil.getConnection();) {
    		String sql = "select * from course where courseId=?";
			PreparedStatement s = con.prepareStatement(sql);
			s.setInt(1, course.getCourseId());
			
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				return false;
			}
        	
            sql = "insert into course values(" + course.getCourseId() + ", '" + course.getCourseName() + "', '" + course.getCourseFee()+"')";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean dropCourse(int courseId) {
        
        try(Connection con = connectionUtil.getConnection();) {
        	String sql = "select * from course where courseId=?";
			PreparedStatement s = con.prepareStatement(sql);
			s.setInt(1, courseId);
			
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				sql = "DELETE FROM course WHERE courseId= " + courseId;
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.executeUpdate(sql);
			}
			else
			{
				return false;
			}
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
    
    @Override
    public boolean addStudent(Student student) {
        
        try(Connection con = connectionUtil.getConnection();) {
        	
        	String sql = "select * from student where studentId=?";
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, student.getUserId());
			
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				return false;
			}
        	
            sql = "insert into user(userId, password, userName, emailId, contactNo) values('" + student.getUserId() + "', '" + student.getPassword()+ "' , '"+ student.getUserName()+ "', '" + student.getEmailId() + "' , '" + student.getContactNo() + "')";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate(sql);

            sql = "insert into student(studentId, semester, grade, feeStatus) values('"+ student.getUserId()+ "' ,' "+ student.getSemester()+"',' "+student.getGrade()+"',' "+student.getFeeStatus()+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
	
}
