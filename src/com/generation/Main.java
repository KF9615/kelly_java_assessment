package com.generation;

import com.generation.model.Course;

import com.generation.model.Student;

import com.generation.service.CourseService;

import com.generation.service.StudentService;

import com.generation.utils.PrinterHelper;

import java.text.ParseException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)

            throws ParseException {

        StudentService studentService = new StudentService();

        CourseService courseService = new CourseService();

        Scanner scanner = new Scanner(System.in);

        int option = 0;

        do {

            PrinterHelper.showMainMenu();

            option = scanner.nextInt();

            switch (option) {

                case 1:

                    registerStudent(studentService, scanner);

                    break;

                case 2:

                    findStudent(studentService, scanner);

                    break;

                case 3:

                    gradeStudent(studentService, scanner);

                    break;

                case 4:

                    enrollStudentToCourse(studentService, courseService, scanner);

                    break;

                case 5:

                    showStudentsSummary(studentService, scanner);

                    break;

                case 6:

                    showCoursesSummary(courseService, scanner);

                    break;

                case 7:

                    showPassedCourses(studentService, scanner);

                    break;

            }

        }

        while (option != 8);

    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,

                                              Scanner scanner) {

        System.out.println("Insert student ID");

        String studentId = scanner.next();

        Student student = studentService.findStudent(studentId);

        if (student == null) {

            System.out.println("Invalid Student ID");

            return;

        }

        System.out.println(student);

        System.out.println("Insert course ID");

        String courseId = scanner.next();

        Course course = courseService.getCourse(courseId);

        if (course == null) {

            System.out.println("Invalid Course ID");

            return;

        }

        System.out.println(course);

        courseService.enrollStudent(courseId, student);

        studentService.enrollToCourse(studentId, course);

        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {

        courseService.showSummary();

    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {

        studentService.showSummary();

    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {

        // What do we do if before we grade a student?

        // 1. Ask for the student ID first

        // 1.1. Check whether the student exists

        // 2. Ask for the course ID next

        // 2.1. find whether the student is taking the course

        // What is the grade to assign to the student?

        // 3.1 If the student is taking the course, the student is given the credit (9)

        System.out.println("Enter student ID: ");

        String studentId = scanner.next();

        Student student = studentService.findStudent(studentId);

        if ( student != null )
        {

            System.out.printf( "Enter the course ID for student ID (%s): ", studentId);

            String courseId = scanner.next();

            boolean isAttendingCourse = student.isAttendingCourse(courseId);

            if(isAttendingCourse){

                System.out.printf( "Enter the score for course ID (%s): ", courseId);

                double score = scanner.nextDouble();

                if(score < 0 || score > 9)

                    System.out.println("Invalid score entry");
                else

                    System.out.println(student.setGrade(courseId, score));

            }
            else{

                System.out.println("Student is not attending this course.");

            }
        }
        else
        {

            System.out.println("Student with Id = " + studentId + " not found");

        }

    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner){

        System.out.println( "Enter student ID: " );

        String studentId = scanner.next();

        Student student = studentService.findStudent( studentId );

        if ( student != null )
        {

            System.out.println( "Student Found: " );

            System.out.println( student );

            // Show the courses the student passed

            studentService.showPassedCourses(student);

        }
        else
        {

            System.out.println( "Student with Id = " + studentId + " not found" );

        }
    }

    private static void findStudent(StudentService studentService, Scanner scanner) {

        System.out.println("Enter student ID: ");

        String studentId = scanner.next();

        Student student = studentService.findStudent(studentId);

        if (student != null) {

            System.out.println("Student Found: ");

            System.out.println(student);

        } else {

            System.out.println("Student with Id = " + studentId + " not found");

        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner)

            throws ParseException {

        Student student = PrinterHelper.createStudentMenu(scanner);

        studentService.subscribeStudent(student);

    }
}
