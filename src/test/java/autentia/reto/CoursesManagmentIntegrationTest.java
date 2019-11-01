package autentia.reto;

import autentia.reto.model.Course;
import autentia.reto.model.CourseLevel;
import autentia.reto.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CoursesManagmentIntegrationTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Disabled(value = "Works without Jersey")
    void shouldGetActiveCourses_whenThereAreActiveCoursesInDB() throws Exception {
        Course courseBasic = Course.builder().name("BASIC").isActive(true).build();
        Course courseMedium = Course.builder().name("MEDIUM").build();
        Course courseAdvance = Course.builder().name("ADVANCE").isActive(true).build();

        courseRepository.save(courseBasic);
        courseRepository.save(courseMedium);
        courseRepository.save(courseAdvance);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(status().isOk())
                .andReturn();

        ArrayList responseBody = mapper.readValue(result.getResponse().getContentAsString(), ArrayList.class);

        assertEquals(2, responseBody.size());
        assertEquals(CourseLevel.BASIC.name(), ((LinkedHashMap) responseBody.get(0)).get("name"));
        assertEquals(CourseLevel.ADVANCE.name(), ((LinkedHashMap) responseBody.get(1)).get("name"));
    }
}
