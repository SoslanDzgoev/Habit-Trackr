package com.example.habittrackr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.habittrackr")
@EntityScan("com.example.habittrackr")
public class HabitTrackrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HabitTrackrApplication.class, args);
    }
}
