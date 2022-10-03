package avd.jdm.demostudentandcourses.controller;

import avd.jdm.demostudentandcourses.domain.Course;
import avd.jdm.demostudentandcourses.domain.Student;
import avd.jdm.demostudentandcourses.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    // see also: https://www.baeldung.com/spring-request-param
    public ResponseEntity<List<Course>> all(@RequestParam(required = false) String title) {
        List<Course> found = new ArrayList<>();

        if (title == null) {
            courseRepository.findAll().forEach(found::add);
        } else {
            courseRepository.findCoursesByTitleContainingIgnoreCase(title).forEach(found::add);
        }
        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Course>> upComingCourses() {
        List<Course> found = new ArrayList<>();
        courseRepository.findCoursesByStartDateAfter(LocalDate.now()).forEach(found::add);

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    ResponseEntity<Course> newCourse(@RequestBody Course newCourse) {
        try {
            Course course = courseRepository.save(newCourse);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}