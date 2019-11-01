package autentia.reto.repository;

import autentia.reto.model.Course;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CourseRepository {

    List<Course> findByIsActive(boolean asc);

    Course save(@NotNull Course course);

}
