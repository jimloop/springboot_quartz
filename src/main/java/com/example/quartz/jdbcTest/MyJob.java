package com.example.quartz.jdbcTest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob implements Job {
    private static Logger _log= LoggerFactory.getLogger(MyJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.info("MyJob is start ............");
        _log.info("Hello quartz "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        _log.info(jobExecutionContext.getJobDetail().toString());
        _log.info("MyJob is end .................");
    }
}
