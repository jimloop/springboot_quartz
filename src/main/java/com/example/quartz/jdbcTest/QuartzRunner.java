package com.example.quartz.jdbcTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class QuartzRunner implements CommandLineRunner {

    @Autowired
    QuartzJdbcTest quartzJdbcTest;
    @Override
    public void run(String... args) throws Exception {
        quartzJdbcTest.startSchedule();
    }
}
