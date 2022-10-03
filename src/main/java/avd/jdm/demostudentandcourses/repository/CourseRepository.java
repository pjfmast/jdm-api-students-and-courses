package avd.jdm.demostudentandcourses.repository;

import avd.jdm.demostudentandcourses.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCoursesByTitleContainingIgnoreCase(String title);
    List<Course> findCoursesByStartDateAfter(LocalDate startDate);
}
