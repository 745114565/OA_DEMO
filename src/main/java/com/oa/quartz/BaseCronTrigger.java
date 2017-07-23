package com.oa.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerBean;

import java.io.Serializable;

/**
 * Created by HOZANDUNG on 17/7/23.
 */
public abstract class BaseCronTrigger extends CronTriggerBean implements Serializable {

    public void init(){
        // 得到任务
        JobDetail jobdetail = new JobDetail (this.getClass ().getSimpleName (),this.getMyTargetObject ().getClass ());
        this.setJobDetail (jobdetail);
        this.setJobName (jobdetail.getName ());
        this.setName (this.getClass ().getSimpleName ());
        try {
            this.setCronExpression (this.getMyCronExpression ());
        } catch (java.text.ParseException e) {
            e.printStackTrace ();
        }

    }

    public abstract String getMyCronExpression();

    public abstract Job getMyTargetObject();

}
