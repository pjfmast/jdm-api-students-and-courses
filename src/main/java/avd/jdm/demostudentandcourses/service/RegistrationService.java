package avd.jdm.demostudentandcourses.service;

import avd.jdm.demostudentandcourses.domain.Course;
import avd.jdm.demostudentandcourses.domain.Student;
import avd.jdm.demostudentandcourses.repository.CourseRepository;
import avd.jdm.demostudentandcourses.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
/*
    @Service is specialization of generic stereotype @Component
    and annotates classes at the service layer to indicate that they're holding the business logic
 */
@Service
public class RegistrationService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public RegistrationService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public boolean addRegistration(Long studentId, Long courseId) {
        Optional<Student> maybeStudent = studentRepository.findById(studentId);
        Optional<Course> maybeCourse = courseRepository.findById(courseId);

        if (maybeStudent.isPresent() && maybeCourse.isPresent()) {
            maybeStudent.get().addCourse(maybeCourse.get());
            studentRepository.save(maybeStudent.get());
            courseRepository.save(maybeCourse.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean removeRegistration(Long studentId, Long courseId) {
        Optional<Student> maybeStudent = studentRepository.findById(studentId);
        Optional<Course> maybeCourse = courseRepository.findById(courseId);

        if (maybeStudent.isPresent() && maybeCourse.isPresent()) {
            maybeStudent.get().removeCourse(maybeCourse.get());
            studentRepository.save(maybeStudent.get());
            courseRepository.save(maybeCourse.get());

            return true;
        } else {
            return false;
        }
    }

    public Set<Student> getRegisteredStudentsForCourse(Long courseId) {
        Optional<Course> maybeCourse = courseRepository.findById(courseId);

        if (maybeCourse.isPresent()) {
            return maybeCourse.get().getStudents();
        } else {
            return Collections.emptySet();
        }
    }

}
