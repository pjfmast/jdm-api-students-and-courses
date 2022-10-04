package avd.jdm.demostudentandcourses.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class FulltimeStudent extends Student{
    private String studentCoach;

    public FulltimeStudent() {
    }

    public FulltimeStudent(String name, LocalDate dateOfBirth, String studentCoach) {
        super(name, dateOfBirth);
        this.studentCoach = studentCoach;
    }

    public String getStudentCoach() {
        return studentCoach;
    }

    public void setStudentCoach(String studentCoach) {
        this.studentCoach = studentCoach;
    }
}
