package avd.jdm.demostudentandcourses.integration;

import avd.jdm.demostudentandcourses.domain.Course;
import avd.jdm.demostudentandcourses.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CourseRepositoryIntegrationTests {

    @Autowired
    private CourseRepository repository;
    Course course1 = new Course("t1", "d1", 10, LocalDate.now(), LocalDate.now());
    Course course2 = new Course("t2", "d2", 10, LocalDate.now(), LocalDate.now());


    @Test
    public void whenDeleteByIdFromRepository_thenDeletingShouldBeSuccessful() {
        // arrange
        long countBefore = repository.count();
        repository.save(course1);
        repository.save(course2);

        //act
        repository.deleteById(course1.getId());

        //assert
        assertThat(repository.count()).isEqualTo(countBefore + 1);
    }

}
