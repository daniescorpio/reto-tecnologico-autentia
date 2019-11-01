package autentia.reto.service;

import autentia.reto.model.Course;
import autentia.reto.model.CourseLevel;
import autentia.reto.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourceServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Spy
    @InjectMocks
    private CourseService courseService;

    @Captor
    ArgumentCaptor<Course> captor;

    @Test
    void shouldGetAllActiveCourses_whenGetActiveCoursesIsCalled() {
        Course courseBasic = Course.builder().level(CourseLevel.BASIC).build();
        Course courseMedium = Course.builder().level(CourseLevel.MEDIUM).build();
        Course courseAdvance = Course.builder().level(CourseLevel.ADVANCE).build();
        List<Course> courseList = List.of(courseBasic, courseMedium, courseAdvance);

        when(courseRepository.findByIsActive(anyBoolean())).thenReturn(courseList);

        List<Course> activeCourses = courseService.getActiveCourses(true);

        verify(courseRepository).findByIsActive(true);
        assertEquals(courseList, activeCourses);
    }

    @Test
    void shouldInsertNewCourse_whenAddNewCourseIsCalled() {
        Course courseToAdd = Course.builder().build();

        courseService.addNewCourse(courseToAdd);

        verify(courseRepository).save(captor.capture());
        assertEquals(courseToAdd, captor.getValue());
    }
}
