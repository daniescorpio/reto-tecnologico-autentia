package autentia.reto.controller;

import autentia.reto.model.Course;
import autentia.reto.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api")
@Controller
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GET
    @Path("/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveCourses(@QueryParam("order") boolean asc) {
        return Response.ok()
                .entity(courseService.getActiveCourses(asc))
                .build();
    }

    @POST
    @Path("/course")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewCourse(Course course) {
        return Response.created(URI.create(""))
                .entity(courseService.addNewCourse(course))
                .build();
    }
}
