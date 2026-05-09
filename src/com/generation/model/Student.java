package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student
        extends Person
        implements Evaluation {
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    private final Map<String, Double> courseGrade = new HashMap<>(); // <courseId, grade>

    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }

    public void enrollToCourse(Course course) {
        //TODO (DONE) implement this method
        //if the course DOES NOT exist in courses
        //then we add the course to courses and approvedCourses
        //the course credits is also to average
        if (!courses.contains(course)) {
            courses.add(course);
            registerApprovedCourse(course);
            this.average += (double) course.getCredits() / 2;
        }
    }


    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public boolean isCourseApproved(String courseCode) {
        //TODO implement this method
        return false;
    }

    public String setGrade(String courseId, double score){

        if(approvedCourses.containsKey(courseId)){

            if(!courseGrade.containsKey(courseId)){
                courseGrade.put(courseId, score);
                return String.format("Score for course ID: %s is recorded successfully.", courseId);
            }else{
                return String.format("Duplicate entry. Cannot enter score for course ID: %s.", courseId);
            }
        }
        return String.format("Student did not take the course ID: %s.", courseId);
    }


    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    public List<Course> findPassedCourses()
    {
        List<Course> passedCourses = new ArrayList<>();

        courseGrade.forEach((courseId, score)->{

            // find the course by the courseId
            // get the course credit
            // if score is greater than avg of course credit
            // add to the passedCourses

            if(approvedCourses.containsKey(courseId)){
                Course course = approvedCourses.get(courseId);

                if(score >= course.getCredits()/2){
                    passedCourses.add(course);
                }
            }

        });

        return passedCourses;
    }

    public boolean isAttendingCourse(String courseCode) {
        //TODO (Done) implement this method
        //returns true / false if approvedCourses contains the
        //parameter of the courseCode passed to the method
        return approvedCourses.containsKey(courseCode);
    }

    @Override
    public double getAverage() {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses() {
        //TODO (DONE) implement this method
        //return courses that the student is taking
        List<Course> listedCourses = new ArrayList<>();

        if (!approvedCourses.isEmpty()) {

            approvedCourses.forEach((courseId, course) -> {
                listedCourses.add(course);
            });
        }
        return listedCourses;
    }

    @Override
    public String toString() {
        return "Student {" + super.toString() + "}";
    }
}
