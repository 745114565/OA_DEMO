package com.oa.quartz;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by HOZANDUNG on 17/7/23.
 */
@Component
public class InitializingCronTrigger extends BaseCronTrigger {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public InitializingCronTrigger() {
        init ();
    }

    @Override
    public String getMyCronExpression(){
//        return "0/5 * * * * ?";//每隔5秒触发
        return "0 0 6 * * ?";//每天早上6点触发
    }

    @Override
    public Job getMyTargetObject(){
        return new ScheduledTasks ();
    }

    public void parse(){
        try {
            schedulerFactoryBean.getObject ().pauseAll ();
        } catch (SchedulerException e) {
            e.printStackTrace ();
        }
    }

}
