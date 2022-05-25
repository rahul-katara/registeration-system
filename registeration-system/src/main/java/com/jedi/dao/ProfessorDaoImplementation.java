package com.jedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jedi.Course;
import com.jedi.utils.ConnectionUtil;

public class ProfessorDaoImplementation implements ProfessorDaoInterface {
	
	ConnectionUtil connectionUtil;

	public ProfessorDaoImplementation(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

	public Map<String, ArrayList<String> > viewEnrolledStudents(String professorId) throws SQLException {

        
        try(Connection conn = connectionUtil.getConnection();){
        	Map<String,ArrayList<String> > students=new LinkedHashMap<>();
            String sql = "select registrar.studentId,user.userName,course.courseId,course.courseName " +
                    "from registrar,user,course where registrar.courseId in(select courseId from professorreg " +
                    "where professorreg.professorId='"+professorId+"' ) and registrar.studentId=user.userId and " +
                    "registrar.courseId=course.courseId ";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                String user=rs.getString(1)+" "+rs.getString(2);
                String course=rs.getString(3)+" "+rs.getString(4);
                if(!students.containsKey(course))
                    students.put(course,new ArrayList<>());
                students.get(course).add(user);
            }
            return students;
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
        
        return null;
    }
	
    public int provideGrade(String professorId, int courseId,String studentId,String Grade) throws SQLException {
    	
        try (Connection conn = connectionUtil.getConnection();) {
        	
        	String sql = "select * from professorReg where courseId=? and professorId=?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, courseId);
			s.setString(2, professorId);
			
			ResultSet rs = s.executeQuery();
			
			if (!rs.next()) {
				return 1;
			}
			
			sql = "select * from registrar where courseId=? and studentId=?";
			s = conn.prepareStatement(sql);
			s.setInt(1, courseId);
			s.setString(2, studentId);
			
			rs = s.executeQuery();
			
			if(!rs.next()) {
				return 2;
			}
        	
            sql = "UPDATE registrar set grade=? where studentId=? and courseId=?";

            PreparedStatement pstmt = conn.prepareStatement(sql); 
            
            pstmt.setString(1,  Grade);
            pstmt.setString(2, studentId);
            pstmt.setInt(3, courseId);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return 0;
    }
    
    public boolean registerCourses(String professorId,int courseId) throws SQLException {
    	
        try (Connection conn = connectionUtil.getConnection();){
        	
        	String sql = "select * from professorReg where courseId=?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, courseId);
			
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				return false;
			}
			
            sql = "INSERT INTO professorreg(professorId,courseId)" + "VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
        	
            pstmt.setString(1, professorId);
            pstmt.setString(2, String.valueOf(courseId));

            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }
    
    public ArrayList<Course> viewAvailableCourses() throws SQLException {
        
        try(Connection conn = connectionUtil.getConnection();){
        	ArrayList<Course> courses=new ArrayList<Course>();
            String sql="select courseId,courseName from course where courseId not in (select courseId from professorReg)";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next())
            {
                Course course=new Course();
                course.setCourseId(rs.getInt(1));
                course.setCourseName(rs.getString(2));
                courses.add(course);
            }
            return courses;
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
        return null;
    }

    public ArrayList<Course> viewCourses(String professorId) throws SQLException{

        ArrayList<Course> courses=new ArrayList<Course>();
        
        try(Connection conn = connectionUtil.getConnection();){
        	String sql = "SELECT course.courseId, course.courseName, course.courseFee FROM course, professorReg where professorId=? and professorReg.courseId = course.courseId";
            PreparedStatement s = conn.prepareStatement(sql);
            s.setString(1, professorId);
            ResultSet rs = s.executeQuery();
            
            while(rs.next())
            {
                Course course = new Course();
                course.setCourseId(rs.getInt(1));
                course.setCourseName(rs.getString(2));
                course.setCourseFee(rs.getInt(3));
                courses.add(course);
            }
            return courses;
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        return null;
    }

}
