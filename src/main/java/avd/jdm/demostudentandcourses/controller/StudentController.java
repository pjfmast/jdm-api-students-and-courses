package avd.jdm.demostudentandcourses.controller;

import avd.jdm.demostudentandcourses.domain.Student;
import avd.jdm.demostudentandcourses.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(@RequestParam(required = false) String name) {
       List<Student> found =  new ArrayList<>();

       if (name == null) {
           found.addAll(studentRepository.findAll());
       } else {
           found.addAll(studentRepository.findStudentsByNameContainsIgnoreCase(name));
       }
       if (found.isEmpty()) {
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(found);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getById(@PathVariable Long id) {
        Optional<Student> found = studentRepository.findById(id);

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student newStudent) {
        try {
            Student student = studentRepository.save(newStudent);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/immature")
    public ResponseEntity<List<Student>> getImmatureStudents() {
        List<Student> found = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate immatureDate = today
                .minusYears(18)
                .plusDays(1);

        found.addAll(studentRepository.
                findStudentByDateOfBirthBetween(immatureDate, today));

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);

    }

}
