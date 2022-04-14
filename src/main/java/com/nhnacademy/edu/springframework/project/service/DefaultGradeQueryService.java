package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {
    Scores scores;
    Students studentRepository;

    @Autowired
    public DefaultGradeQueryService(Scores scores, Students studentRepository) {
        this.scores = scores;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        // TODO 5: 학생 이름으로 점수를 반환합니다. 동명이인 고려합니다.
        List<Score> result = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getName().equals(name)) {
                result.add(student.getScore());
                System.out.println();
            }
        }
        return result;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        // TODO 6 : 학생 번호로 점수를 반환합니다.
        if (seq >= scores.findAll().size()) throw new IndexOutOfBoundsException();
        for (Score score : scores.findAll()) {
            if (score.getStudentSeq() == seq) return score;
        }
        return null;
    }
}
