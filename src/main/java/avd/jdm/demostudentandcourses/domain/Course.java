package avd.jdm.demostudentandcourses.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

// Note:
// @Data give a performance warning. See https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
// when we extend with a new field: add getter/setter for thees
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String title;
    private String description;
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_coordinator_id")
    private CourseCoordinator courseCoordinator;

    // Add many-to-many relation between Course and Student.
    // performance advice ManyToMany: use Set<T> instead of List<T>
    // todo workshop lesson 7-2b: add many-to-many relation between course and student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course(String title, String description, int capacity, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course(String title, String description, int capacity, LocalDate startDate, LocalDate endDate, CourseCoordinator courseCoordinator) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseCoordinator = courseCoordinator;
    }
}
