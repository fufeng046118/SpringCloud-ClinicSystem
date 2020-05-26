package cn.project.config;

import org.quartz.CronTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

//@Configuration
public class QuartzConfig {
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail(){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("quartzController");
        bean.setTargetMethod("hello");
        return bean;
    }
    @Bean
    CronTriggerFactoryBean cronTrigger(){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail().getObject());
        bean.setCronExpression("10 * * * * ?");
        return bean;
    }
    @Bean
    SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        CronTrigger cronTrigger = cronTrigger().getObject();
        bean.setTriggers(cronTrigger);
        return bean;
    }
}
