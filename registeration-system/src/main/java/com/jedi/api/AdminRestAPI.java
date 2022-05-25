package com.jedi.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jedi.Course;
import com.jedi.Professor;
import com.jedi.Student;
import com.jedi.dao.AdminDaoInterface;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminRestAPI {
	
	AdminDaoInterface adminDao;

	public AdminRestAPI(AdminDaoInterface adminDao) {
		super();
		this.adminDao = adminDao;
	}

    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    public Response addProfessor(@Valid Professor professor) {
        try {
            boolean created = adminDao.addProfessor(professor);
            
            if(!created)
            {
            	return Response.status(400).entity("Professor with the given Id has already registered!!").build();
            }
        }
        catch (Exception e) {
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.status(201).entity("Professor Added Successfully !!").build();
    }

    @POST
    @Path("/addCourse")
    @Consumes("application/json")
    public Response addCourse(@Valid Course course) {
        try {
            boolean created = adminDao.addCourse(course);
            
            if(!created)
            {
            	return Response.status(400).entity("Course with given id already registered!!").build();
            }
        }
        catch (Exception e) {
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.status(201).entity("Course Added Successfully !!").build();
    }

    @POST
    @Path("/dropCourse/{courseId}")
    @Consumes("application/json")
    public Response dropCourse(@PathParam("courseId") int courseId) {
        try {
            boolean dropped = adminDao.dropCourse(courseId);
            
            if(!dropped)
            {
            	Response.status(400).entity("Course with given doesn't exist!!").build();
            }
        }
        catch (Exception e) {
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.status(201).entity("Course dropped Successfully !!").build();
    }
    
    @POST
    @Path("/addStudent")
    @Consumes("application/json")
    public Response addStudent(@Valid Student student) {
        try {
            boolean created = adminDao.addStudent(student);
            
            if(!created)
            {
            	return Response.status(400).entity("Student with given Id has already registered!!").build();
            }
        }
        catch (Exception e) {
            return Response.status(201).entity("Some Exception Occured !! check logs").build();
        }
        return Response.status(201).entity("Student Added Successfully !!").build();
    }

}
