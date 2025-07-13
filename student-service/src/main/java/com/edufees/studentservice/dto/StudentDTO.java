package com.edufees.studentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class StudentDTO {

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Grade is required")
    private String grade;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^\\d{9}$",
        message = "Mobile number must be exactly 9 digits and contain only numbers")
    private String mobileNumber;

    @NotBlank(message = "School name is required")
    private String schoolName;

    // --- Constructors ---

    public StudentDTO() {
    }

    public StudentDTO(String studentId, String name, String grade, String mobileNumber, String schoolName) {
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
