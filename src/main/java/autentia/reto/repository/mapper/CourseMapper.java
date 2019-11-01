package autentia.reto.repository.mapper;

import autentia.reto.model.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {

    @Select("select * from course where isActive=true")
    List<Course> findByIsActive();

    @Select("select * from course where isActive=true order by name asc")
    List<Course> findByIsActiveAsc();

    @Options(useGeneratedKeys = true)
    @Insert("insert into course(isActive, name, level, hours, teacher) values(#{isActive}, #{name}, #{level}, #{hours}, #{teacher})")
    void save(Course course);
}
