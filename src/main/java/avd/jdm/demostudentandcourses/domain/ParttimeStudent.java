package avd.jdm.demostudentandcourses.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ParttimeStudent extends Student {
    private String workingEnvironment;

    public ParttimeStudent() {
    }

    public ParttimeStudent(String name, LocalDate dateOfBirth, String workingEnvironment) {
        super(name, dateOfBirth);
        this.workingEnvironment = workingEnvironment;
    }

    public String getWorkingEnvironment() {
        return workingEnvironment;
    }

    public void setWorkingEnvironment(String workingEnvironment) {
        this.workingEnvironment = workingEnvironment;
    }
}
