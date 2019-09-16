package com.example.quartz.jdbcTest;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class QuartzJdbcTest {
     @Autowired
     private Scheduler scheduler;
    /**
     * 开始一个simpleSchedule()调度
     */
    public void startSchedule(){
        try{
            //1.创建一个JobDetail实例，指定Quartz
            JobDetail jobDetail= JobBuilder.newJob(MyJob.class)
                                           .storeDurably(true)
                                           .withIdentity("job_01","jdbcGroup")
                                           .build();
            //创建simpleSchedule
            //SimpleScheduleBuilder simpleScheduleBuilder=SimpleScheduleBuilder.repeatSecondlyForTotalCount(5);
            CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
            //2.创建Trigger
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1","tGroup1")
                                                       .withSchedule(cronScheduleBuilder)
                                                        .build();
            //3.创建核心调度器 Scheduler
            scheduler.start();

            //4.调度执行
            scheduler.scheduleJob(jobDetail,trigger);
            try {
                Thread.sleep(6000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            scheduler.shutdown();
        }catch (SchedulerException e){
            e.printStackTrace();
        }
    }

    /**
     * 从数据库中找到已存在的job，并重新开启调度
     */
    public void resumeJob(){
        try{
            SchedulerFactory schedulerFactory=new StdSchedulerFactory();
            Scheduler scheduler=schedulerFactory.getScheduler();
            JobKey jobKey=new JobKey("028f90e0d32942b99bca226c361a9c28","group_sign");
            List< ? extends Trigger> triggers=scheduler.getTriggersOfJob(jobKey);
            if(triggers.size()>0){
                for (Trigger tg:triggers){
                    if((tg instanceof CronTrigger) || (tg instanceof SimpleTrigger)){
                        //恢复job运行
                        scheduler.resumeJob(jobKey);
                    }
                }
                scheduler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
