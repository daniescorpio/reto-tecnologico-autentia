package autentia.reto.service;

import autentia.reto.model.Course;
import autentia.reto.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    public List<Course> getActiveCourses(boolean asc) {
        return courseRepository.findByIsActive(asc);
    }

    public Course addNewCourse(Course course) {
        return courseRepository.save(course);
    }
}
