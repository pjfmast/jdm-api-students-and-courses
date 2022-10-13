package service;

import avd.jdm.demostudentandcourses.domain.Course;
import avd.jdm.demostudentandcourses.domain.Student;
import avd.jdm.demostudentandcourses.repository.CourseRepository;
import avd.jdm.demostudentandcourses.repository.StudentRepository;
import avd.jdm.demostudentandcourses.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class StudentRegistrationServiceTests {

    /* From: https://www.baeldung.com/java-spring-mockito-mock-mockbean#:~:text=We%20can%20use%20the%20%40MockBean,new%20one%20will%20be%20added.
    We can use the @MockBean to add mock objects to the Spring application context. The mock will replace any existing bean of the same type in the application context.
    If no bean of the same type is defined, a new one will be added.
     */
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private CourseRepository courseRepository;

    private RegistrationService sut;
    private Student student;


    // @BeforeEach and @BeforeAll are the JUnit 5 equivalents of @Before and @BeforeClass.
    @BeforeEach
    void beforeEach() {

        /*
            Mocking for unit testing is when you create an object that implements the behavior of a real subsystem in controlled ways.
            In short, mocks are used as a replacement for a dependency.
         */
        studentRepository = mock(StudentRepository.class);
        courseRepository = mock(CourseRepository.class);
        sut = new RegistrationService(studentRepository, courseRepository);

        student = mock(Student.class);
        student.setId(1L);

        Course course = mock(Course.class);
        course.setId(100L);

        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));
        when(courseRepository.save(any(Course.class))).thenAnswer(i -> i.getArgument(0));

        when(studentRepository.findById(1L)).thenAnswer(i -> Optional.of(student));
        when(courseRepository.findById(100L)).thenAnswer(i -> Optional.of(course));
    }

    @Test
    void new_registration_updates_student() {
        // arrange

        // act
        sut.addRegistration(1L, 100L);

        // assert
//        verify(mockObject, atLeast(2)).someMethod("was called at least twice");
//        verify(mockObject, times(3)).someMethod("was called exactly three times");
        verify(studentRepository, times(1)).save(student);
    }

}
