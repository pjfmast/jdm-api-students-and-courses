package avd.jdm.demostudentandcourses.repository;

import avd.jdm.demostudentandcourses.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByNameContainsIgnoreCase(String name);
    List<Student> findStudentByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate);
}
