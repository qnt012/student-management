package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.MainConfiguration;
import org.junit.jupiter.api.DisplayName;
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
class ScoresTest {
    @Autowired
    private Scores scores;

    @Test
    @Order(2)
    void load() {
        scores.load();
        assertFalse(scores.findAll().isEmpty());
    }

    @Test
    void findAll() {
        assertFalse(scores.findAll().isEmpty());
    }

    @Test
    @Order(1)
    void findAll_notLoaded_throwIllegalStateException() {
        assertThatThrownBy(() -> scores.findAll())
            .isInstanceOf(IllegalStateException.class);
    }
}