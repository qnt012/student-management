package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {
    Students studentRepository;

    @Autowired
    public DefaultStudentService(Students studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        // TODO 1 : pass한 학생만 반환하도록 수정하세요.
        return studentRepository.findAll()
                .stream()
                .filter(student -> !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        // TODO 4 : 성적 순으로 학생 정보를 반환합니다.
        //Comparator<Student> comparator = Comparator.comparingInt(s -> s.getScore().getScore());
        return studentRepository.findAll()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
