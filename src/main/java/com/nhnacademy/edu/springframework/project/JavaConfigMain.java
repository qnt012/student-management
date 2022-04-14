package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//5c8e551af8d52f9ca6877ef591f3cbd8a678038e
public class JavaConfigMain {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project")) {
            context.getBean("csvDataLoadService", CsvDataLoadService.class).loadAndMerge();

            DefaultStudentService studentService = context.getBean("defaultStudentService", DefaultStudentService.class);

            System.out.println(studentService.getPassedStudents());
            System.out.println(studentService.getStudentsOrderByScore());
        }
    }
}
