package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project.repository.Score;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GradeQueryServiceTest {
    @Autowired
    private DefaultGradeQueryService service;
    @Autowired
    private DataLoadService loadService;

    @Test
    @Order(1)
    void getScoreByStudentName() {
        loadService.loadAndMerge();
        List<Score> result = service.getScoreByStudentName("C");
        assertEquals(3, result.get(0).getStudentSeq());
        assertEquals(70, result.get(0).getScore());
    }

    @Test
    void getScoreByStudentName_sameName() {
        List<Score> result = service.getScoreByStudentName("A");
        assertEquals(1, result.get(0).getStudentSeq());
        assertEquals(30, result.get(0).getScore());
        assertEquals(11, result.get(1).getStudentSeq());
        assertEquals(20, result.get(1).getScore());
    }

    @Test
    void getScoreByStudentSeq() {
        assertEquals(30, service.getScoreByStudentSeq(1).getScore());
    }

    @Test
    void getScoreByStudentSeq_biggerIndex_throwIndexOutOfBoundsException() {
        assertThatThrownBy(() -> service.getScoreByStudentSeq(Integer.MAX_VALUE))
            .isInstanceOf(IndexOutOfBoundsException.class);

    }
}