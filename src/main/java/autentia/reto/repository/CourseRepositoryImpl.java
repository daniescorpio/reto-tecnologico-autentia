package autentia.reto.repository;

import autentia.reto.model.Course;
import autentia.reto.repository.mapper.CourseMapper;
import io.micronaut.validation.Validated;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Singleton
@Validated
@Repository
@AllArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseMapper courseMapper;

    @Override
    public List<Course> findByIsActive(boolean asc) {
        return asc
                ? courseMapper.findByIsActiveAsc()
                : courseMapper.findByIsActive();
    }

    @Override
    public Course save(@NotBlank Course course) {
        courseMapper.save(course);
        return course;
    }
}
