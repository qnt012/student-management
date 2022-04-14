package com.nhnacademy.edu.springframework.project.service;

import java.util.ArrayList;
import java.util.List;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {
    @Autowired
    private DefaultStudentService service;
    @Autowired
    private DataLoadService loadService;

    @Test
    @Order(1)
    void getPassedStudents() {
        loadService.loadAndMerge();
        for (Student student : service.getPassedStudents()) {
            assertFalse(student.getScore().isFail());
        }
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> result = new ArrayList<>(service.getStudentsOrderByScore());
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i).compareTo(result.get(i+1)) <= 0);
        }
    }
}