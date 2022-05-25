package com.jedi.api;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jedi.Course;
import com.jedi.GradeCard;
import com.jedi.Student;
import com.jedi.dao.StudentDaoInterface;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentRestAPI {

	StudentDaoInterface studentDao;

	public StudentRestAPI(StudentDaoInterface studentDao) {
		super();
		this.studentDao = studentDao;
	}

    @GET
    @Path("/viewDetails")
    public Response getStudent(@QueryParam("studentId") String studentId) throws SQLException {
            Student student =  studentDao.getStudent(studentId);
            System.out.println(studentId);
        if (student != null)
            return Response.ok(student).build();
        else
            return Response.status(404).build();

    }

    @POST
    @Path("/semesterRegistration")
    public Response semesterRegistration(@QueryParam("studentId")  String studentId) {
        try {
            ArrayList<Integer> list = studentDao.registeredCoursesList(studentId);
            studentDao.registerCourses(studentId,list);
        }catch(Exception e) {
            return Response.status(201).entity("Semester Registration is already done.").build();

        }
        return Response.status(201).entity("Semester Registration done Sucessfully").build();
    }

    @GET
    @Path("/viewCourses/")
    public Response viewAvailableCourses() {
        ArrayList<Course> availableCourses = null;
        try {
            availableCourses = studentDao.viewCourses();
        }catch(SQLException se) {
            se.printStackTrace();
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.ok(availableCourses).build();
    }

    @POST
    @Path("/registerCourses")
    public Response registerCourses(
            @QueryParam("course1") int c1,
            @QueryParam("course2") int c2,
            @QueryParam("course3") int c3,
            @QueryParam("course4") int c4,
            @QueryParam("studentId") String studentId
    ) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(c1);list.add(c2);list.add(c3);list.add(c4);
        try {
            studentDao.registerCourses(studentId, list);
        }
        catch (Exception e) {
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.status(201).entity("Course Registration is completed successfully !! congratulations").build();

    }

    @GET
    @Path("/viewReportCard/")
    public Response viewReportCard(@QueryParam("studentId") String studentId) {
        ArrayList<GradeCard> gradeCards;
        try {
            gradeCards = studentDao.viewGrades(studentId);
        }catch(SQLException se) {
            se.printStackTrace();
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.ok(gradeCards).build();
    }
//    @GET
//    @Path("/checkFeesStatus")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response payFess(@QueryParam("studentId") String studentId) {
//        String ret;
//        try {
//            ret = studentDao.getfeeStatus(studentId);
//        }catch(Exception e) {
//            e.printStackTrace();
//            return Response.status(201).entity("Some Exception Occured !! check logs").build();
//        }
//        return Response.ok(ret).build();
//    }


}
