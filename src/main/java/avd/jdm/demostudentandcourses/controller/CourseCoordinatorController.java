package avd.jdm.demostudentandcourses.controller;

import avd.jdm.demostudentandcourses.domain.Course;
import avd.jdm.demostudentandcourses.domain.CourseCoordinator;
import avd.jdm.demostudentandcourses.repository.CourseCoordinatorRepository;
import avd.jdm.demostudentandcourses.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/course_coordinators")
public class CourseCoordinatorController {

    private final CourseCoordinatorRepository courseCoordinatorRepository;

    public CourseCoordinatorController(CourseCoordinatorRepository courseCoordinatorRepository) {
        this.courseCoordinatorRepository = courseCoordinatorRepository;
    }

    @GetMapping
    public ResponseEntity<List<CourseCoordinator>> all() {
        List<CourseCoordinator> found = courseCoordinatorRepository.findAll();

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    ResponseEntity<CourseCoordinator> newCourseCoordinator(@RequestBody CourseCoordinator newCourseCoordinator) {
        try {
            CourseCoordinator courseCoordinator = courseCoordinatorRepository.save(newCourseCoordinator);
            return new ResponseEntity<>(courseCoordinator, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
