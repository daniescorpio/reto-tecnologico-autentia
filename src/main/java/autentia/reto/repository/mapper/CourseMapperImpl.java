package autentia.reto.repository.mapper;

import autentia.reto.model.Course;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@AllArgsConstructor
public class CourseMapperImpl implements CourseMapper{

    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Course> findByIsActive() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getCourseMapper(sqlSession).findByIsActive();
        }
    }

    @Override
    public List<Course> findByIsActiveAsc() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getCourseMapper(sqlSession).findByIsActiveAsc();
        }
    }

    @Override
    public void save(Course course) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getCourseMapper(sqlSession).save(course);
            sqlSession.commit();
        }
    }

    private CourseMapper getCourseMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CourseMapper.class);
    }
}
