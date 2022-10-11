package avd.jdm.demostudentandcourses.integration;

import avd.jdm.demostudentandcourses.domain.Course;
import avd.jdm.demostudentandcourses.domain.Student;
import avd.jdm.demostudentandcourses.repository.CourseRepository;
import avd.jdm.demostudentandcourses.repository.StudentRepository;
import avd.jdm.demostudentandcourses.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class StudentRegistrationApplicationTests {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    private RegistrationService sut;
    private Student student;


    @BeforeEach
    void beforeEach() {
        studentRepository = mock(StudentRepository.class);
        courseRepository = mock(CourseRepository.class);
        sut = new RegistrationService(studentRepository, courseRepository);

        student = mock(Student.class);
        student.setId(1L);

        Course course = mock(Course.class);
        course.setId(100L);

        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));
        when(courseRepository.save(any(Course.class))).thenAnswer(i -> i.getArgument(0));

        when(studentRepository.findById(anyLong())).thenAnswer(i -> Optional.of(student));
        when(courseRepository.findById(anyLong())).thenAnswer(i -> Optional.of(course));
    }

    @Test
    void new_registration_updates_student() {
        // arrange

        // act
        sut.addRegistration(1L, 100L);

        // assert
        verify(studentRepository, times(1)).save(student);
    }

}
