package com.edufees.studentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private String studentId;
    private String name;
    private String grade;
    private String mobileNumber;
    private String schoolName;

    // --- Constructors ---

    public Student() {
    }

    public Student(String studentId, String name, String grade, String mobileNumber, String schoolName) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.mobileNumber = mobileNumber;
        this.schoolName = schoolName;
    }

    // --- Getters and Setters ---

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
