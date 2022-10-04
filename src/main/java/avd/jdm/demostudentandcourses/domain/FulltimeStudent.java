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
public class FulltimeStudent extends Student{
    private String studentCoach;

    public FulltimeStudent(String name, LocalDate dateOfBirth, String studentCoach) {
        super(name, dateOfBirth);
        this.studentCoach = studentCoach;
    }
}
