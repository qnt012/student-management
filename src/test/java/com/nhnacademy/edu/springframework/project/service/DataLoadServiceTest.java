package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainConfiguration.class)
class DataLoadServiceTest {
    @Autowired
    private Students students;
    @Autowired
    private Scores scores;

    @Test
    void loadAndMerge() {
        students.load();
        scores.load();
        students.merge(scores.findAll());

        assertThat(students.findAll().size()).isNotZero();
        assertThat(scores.findAll().size()).isNotZero();

        Student firstStudent = (Student) students.findAll().toArray()[0];
        assertEquals("A", firstStudent.getName());
        assertEquals(30, firstStudent.getScore().getScore());
    }
}