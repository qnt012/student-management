package com.nhnacademy.edu.springframework.project.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


/** TODO 2 : load 를 완료전에 기타 메소드 실행시 IllegalStateException 이 발생해야 한다.
 **/
@Component
public class CsvScores implements Scores {
    private final List<Score> scores = new ArrayList<>();
    private boolean loaded = false;

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (InputStream inputStream = new ClassPathResource("data/score.csv").getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                scores.add(new Score(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
            loaded = true;
        } catch (IOException e) {
            loaded = false;
        }


    }

    @Override
    public List<Score> findAll() {
        return this.scores;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
