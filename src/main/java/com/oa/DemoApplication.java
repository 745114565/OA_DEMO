package com.oa;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;



@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public SchedulerFactoryBean schedulerFactory(CronTriggerBean[] cronTriggerBean){
		SchedulerFactoryBean bean = new SchedulerFactoryBean ();
		System.err.println (cronTriggerBean[0]);
		bean.setTriggers (cronTriggerBean);

		return bean;
	}

}
