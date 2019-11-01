package autentia.reto.controller;

import autentia.reto.model.Course;
import autentia.reto.model.CourseLevel;
import autentia.reto.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CourseControllerTest {

    @MockBean
    private CourseService courseService;

    @SpyBean
    private CourseController courseController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Disabled(value = "Works without Jersey")
    void shouldCallCourseServiceToGetInformation_whenGetCoursesEndpointIsInvoked() throws Exception {
        Course courseBasic = Course.builder().level(CourseLevel.BASIC).build();
        Course courseMedium = Course.builder().level(CourseLevel.MEDIUM).build();
        Course courseAdvance = Course.builder().level(CourseLevel.ADVANCE).build();
        List<Course> courseList = List.of(courseBasic, courseMedium, courseAdvance);

        when(courseService.getActiveCourses(anyBoolean())).thenReturn(courseList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(status().isOk())
                .andReturn();

        verify(courseService).getActiveCourses(true);

        ArrayList responseBody = mapper.readValue(result.getResponse().getContentAsString(), ArrayList.class);

        assertEquals(3, responseBody.size());
        assertEquals(CourseLevel.BASIC.name(), ((LinkedHashMap) responseBody.get(0)).get("level"));
        assertEquals(CourseLevel.MEDIUM.name(), ((LinkedHashMap) responseBody.get(1)).get("level"));
        assertEquals(CourseLevel.ADVANCE.name(), ((LinkedHashMap) responseBody.get(2)).get("level"));
    }

    @Test
    @Disabled(value = "Works without Jersey")
    void shouldCallCourseServiceToAddNewCourse_whenPostCourseEndpointIsInvoked() throws Exception {
        Course course = Course.builder()
                .level(CourseLevel.MEDIUM)
                .hours(40L)
                .isActive(true)
                .name("Course")
                .teacher("teacher")
                .build();
        String body = mapper.writeValueAsString(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/course")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        verify(courseService).addNewCourse(course);
    }
}
