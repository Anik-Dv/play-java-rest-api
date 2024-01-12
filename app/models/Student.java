package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String department;

    @Column
    private String assignmentName;

    public Student() {
        super();
    }

    public Student(Long studentId, String name, Integer age, String department, String assignmentName) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.department = department;
        this.assignmentName = assignmentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
}
