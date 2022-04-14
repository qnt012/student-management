package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {
    Scores scores;
    Students students;

    public CsvDataLoadService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }

    @Override
    public void loadAndMerge() {
        scores.load();

        students.load();
        students.merge(scores.findAll());

    }
}
