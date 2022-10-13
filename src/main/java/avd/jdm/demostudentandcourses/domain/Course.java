package avd.jdm.demostudentandcourses.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String title;
    private String description;
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private CourseCoordinator courseCoordinator;

    // Add many-to-many relation between Course and Student.
    // performance advice ManyToMany: use Set<T> instead of List<T>
    // todo workshop lesson 7: add many-to-many relation between course and student
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
