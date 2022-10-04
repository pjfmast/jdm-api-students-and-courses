package avd.jdm.demostudentandcourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ParttimeStudent extends Student {
    private String workingEnvironment;

    public ParttimeStudent(String name, LocalDate dateOfBirth, String workingEnvironment) {
        super(name, dateOfBirth);
        this.workingEnvironment = workingEnvironment;
    }
}
