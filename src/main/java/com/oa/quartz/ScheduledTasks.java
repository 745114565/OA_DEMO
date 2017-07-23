package com.oa.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HOZANDUNG on 17/7/23.
 */
public class ScheduledTasks implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

}
