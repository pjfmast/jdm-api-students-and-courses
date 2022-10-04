package avd.jdm.demostudentandcourses.repository;

import avd.jdm.demostudentandcourses.domain.CourseCoordinator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCoordinatorRepository extends JpaRepository<CourseCoordinator, Long> {
}
