package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsTest {
    @Autowired
    private Students students;
    @Autowired
    private Scores scores;


    @Test
    @Order(3)
    void load() {
        students.load();
        assertThat(students.findAll().size()).isNotZero();
    }

    @Test
    void findAll() {
        assertThat(students.findAll().size()).isNotZero();
    }

    @Test
    @Order(1)
    void findAll_notLoaded_throwIllegalStateException() {
        assertThatThrownBy(() -> students.findAll())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void merge() {
        students.merge(scores.findAll());
        Student firstStudent = (Student) students.findAll().toArray()[0];
        assertEquals("A", firstStudent.getName());
        assertEquals(30, firstStudent.getScore().getScore());
    }

    @Test
    @Order(2)
    void merge_notLoaded_throwIllegalStateException() {
        scores.load();
        assertThatThrownBy(() -> students.merge(scores.findAll()))
            .isInstanceOf(IllegalStateException.class);
    }
}