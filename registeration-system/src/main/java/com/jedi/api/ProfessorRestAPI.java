package com.jedi.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jedi.Course;
import com.jedi.dao.ProfessorDaoInterface;

@Path("/professor")
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorRestAPI {

	ProfessorDaoInterface professorDao;

	public ProfessorRestAPI(ProfessorDaoInterface professorDao) {
		super();
		this.professorDao = professorDao;
	}

    @GET
    @Path("/viewRegisteredCourses")
    public Response viewCourses(@QueryParam("professorId") String professorId) throws SQLException, ClassNotFoundException {
        ArrayList<Course> courses = professorDao.viewCourses(professorId);
        if (courses != null)
            return Response.ok(courses).build();
        else
            return Response.status(204).entity("You are not registered with any course!!").build();
    }
    
    @GET
    @Path("/viewAvailableCourses")
    public Response viewAvailableCourses(@QueryParam("professorId") String professorId) throws SQLException, ClassNotFoundException {
        ArrayList<Course> courses = professorDao.viewAvailableCourses();
        if (courses != null)
            return Response.ok(courses).build();
        else
            return Response.status(204).entity("No course available!!").build();
    }

    @GET
    @Path("/viewEnrolledStudents")
    public Response viewStudents(@QueryParam("professorId") String professorId) throws SQLException {
        Map<String, ArrayList<String> > students=professorDao.viewEnrolledStudents(professorId);
        if (students != null)
            return Response.ok(students).build();
        else
            return Response.status(204).entity("No student is registered with your courses!!").build();
    }

    @POST
    @Path("/registerCourses")
    public Response registerCourses(@QueryParam("professorId") String professorId,@QueryParam("courseId") int courseId) throws SQLException, IOException, IOException {
        boolean registered = professorDao.registerCourses(professorId,courseId);
        
        if(!registered)
        {
        	return Response.status(400).entity("Course already registered!!").build();
        }
        
        return Response.ok("Course is successfully Registered").build();
    }

    @POST
    @Path("/assignGrades")
    public Response assignGrades(@QueryParam("professorId") String professorId,@QueryParam("courseId") int courseId,@QueryParam("studentId") String studentId,@QueryParam("grade") String Grade) throws SQLException, IOException {
        int code = professorDao.provideGrade(professorId,courseId,studentId,Grade);
        if(code==1) {
        	return Response.status(400).entity("Professor with given Id is not registrated with the provided courseId").build();
        }
        else if(code==2) {
        	return Response.status(400).entity("Student with given Id is not enrolled in the provided course").build();
        }
        return Response.ok("Student successfully graded!!").build();
    }

}

