package avd.jdm.demostudentandcourses.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

// Note:
// @Data give a performance warning. See https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
@Entity
@NoArgsConstructor
@Getter
@Setter

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ParttimeStudent.class, name="PARTTIME"),
        @JsonSubTypes.Type(value = FulltimeStudent.class, name="FULLTIME")
})
public abstract class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private LocalDate dateOfBirth;

    /*
        Here, Student will be the parent entity,
        and we are using mappedBy="students" in the role entity.
        The @ManytoMany annotation shows that it is a Many to Many relationship,
         and using @ManytoMany annotations at both sides of the relation
          (i.e.: in Student Entity and Course Entity) shows that it is a bidirectional relation.
     */
    // We are not using cascade type ALL,
    // as this may propagate the delete operation to the courses as well
    // and deletes all the associated courses if a student object is deleted.
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
 /* optie2: toevoegen van join properties
    @JoinTable(
            name = "course_registration",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))*/
    // performance advice ManyToMany: use Set<T> instead of List<T>
    private Set<Course> courses;
    public Student(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }
}
