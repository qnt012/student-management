package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.*;
import java.util.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/** TODO 3 : load 를 완료전에 기타 메소드 실행시 IllegalStateException 이 발생해야 한다.
 **/

/**
 * TODO 7 : singleton 클래스를 만드세요.
 */
@Component
public class CsvStudents implements Students {
    private final Map<Integer,Student> students = new HashMap<>();
    private boolean loaded = false;

    // TODO 6 : student.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (InputStream inputStream = new ClassPathResource("data/student.csv").getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                int seq = Integer.parseInt(split[0]);
                students.put(seq, new Student(seq, split[1]));
            }
            loaded = true;
        } catch (IOException e) {
            loaded = false;
        }
    }

    @Override
    public Collection<Student> findAll() {
        return this.students.values();
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for (Score score : scores) {
            students.get(score.getStudentSeq()).setScore(score);
        }
    }

    public boolean isLoaded() {
        return loaded;
    }
}
