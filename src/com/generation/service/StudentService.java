package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public StudentService() {
        subscribeStudent(new Student("001", "John", "johndoe@gmail.com", new Date("01/01/2000")));
        subscribeStudent(new Student("002", "Sally", "sallydoe@hotmail.com", new Date("01/01/2005")));
        subscribeStudent(new Student("003", "Jane", "janesmith@yahoo.com", new Date("01/01/2010")));
    }

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        //TODO implement this method

        return students.containsKey(studentId);
    }

    public boolean isCourseApproved (String courseCode) {
        //TODO implement this method
        for (Student student : students.values()) {
            List<Course> approvedCourses = student.getApprovedCourses();

            for (Course course : approvedCourses) {
                if (course.getCode().equals(courseCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showSummary() {

        //1. Show each student information ✅
        //2. Along with the course(s) that each student is taking ✅

        System.out.println("Students Information:");
        students.forEach((studentId, student) -> {
            System.out.println(student);

            List<Course> studentCourses = student.getApprovedCourses();

            if (studentCourses.size() > 0) {
                System.out.printf("Course(s) taken by studentId: %s%n", studentId);
                for (Course course : studentCourses) {
                    System.out.println(course);
                }

                System.out.println("%n");
            } else
                System.out.printf("No Courses taken by studentId: %s%n%n", studentId);
        });
    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    public void showPassedCourses(Student student) {

        List<Course> passedCourses = student.findPassedCourses();

        if(passedCourses.size() > 0){
            System.out.println("Courses that the student passed:");
            passedCourses.forEach((course)->{
                System.out.println(course);
            });
        }else{
            System.out.println("The student did not pass any course(s).");
        }
    }
}