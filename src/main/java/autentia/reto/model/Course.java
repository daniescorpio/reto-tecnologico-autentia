package autentia.reto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

     private Long id;

     private Boolean isActive;

     private String name;

     private CourseLevel level;

     private Long hours;

     private String teacher;
}
