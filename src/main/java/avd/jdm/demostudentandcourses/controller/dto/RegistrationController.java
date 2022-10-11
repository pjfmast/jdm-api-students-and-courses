package avd.jdm.demostudentandcourses.controller.dto;

import avd.jdm.demostudentandcourses.domain.Student;
import avd.jdm.demostudentandcourses.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/students_for_course/{courseId}")
    public ResponseEntity<Set<Student>> getById(@PathVariable Long courseId) {
        Set<Student> found = registrationService.getRegisteredStudentsForCourse(courseId);

        // https://stackoverflow.com/questions/11746894/what-is-the-proper-rest-response-code-for-a-valid-request-but-an-empty-data
        // courseId does not exist or courseId exists but no students are enrolled:
        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    ResponseEntity<RegistrationDto> register(@RequestBody RegistrationDto registrationDto) {
        boolean canRegister = registrationService.addRegistration(registrationDto.getStudentId(), registrationDto.getCourseId());

        if (canRegister) {
            return new ResponseEntity<>(registrationDto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    ResponseEntity<RegistrationDto> remove(@RequestBody RegistrationDto registrationDto) {
        boolean canRemove = registrationService.removeRegistration(registrationDto.getStudentId(), registrationDto.getCourseId());

        if (canRemove) {
            return new ResponseEntity<>(registrationDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
