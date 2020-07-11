package org.reasuon.example.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 定时任务配置.
 * <p>
 * 先使用注解 {@link EnableScheduling} 开启定时任务 <br>
 * 然后注册一个线程池的定时任务调度方案，用来管理多个定时任务 <br>
 * 需要注意的是需要指定这个调度方案 Bean 的名称 <br>
 * {@link org.reasuon.example.schedule.utils.DynamicScheduleManage} 需要使用到该 Bean
 * </p>
 *
 * @author Reasuon reasuon@gmail.com
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ExampleSchedulingConfig implements SchedulingConfigurer {

    /**
     * 注册线程池.
     * <p>
     * 在方法 {@link org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor#setScheduler(Object)}
     * 的注释中，提到如果没有指定调度方式，则会在
     * {@link org.springframework.scheduling.TaskScheduler} 中进行寻找 Bean <br>
     * 如果还没有找到，则会寻找名称相同的，已注册的 Bean (外部) <br>
     * 如果都没有，还会在 {@link java.util.concurrent.ScheduledExecutorService} 中执行和 TaskSchedule
     * 相同的查询方式，如果还没有，则创建默认的调度方式，即单线程管理.<br>
     * 所以我们可以在被 Spring 管理的类中去尝试注册一个继承与 TaskSchedule 的 Bean，
     * 即 {@link ThreadPoolTaskScheduler}
     * </p>
     * @see org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor#setScheduler(Object)
     * @return 线程池调度方案
     */
    @Bean(name = "DynamicTaskPool")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        // 手动创建线程池调度方案
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 设置线程池容量
        taskScheduler.setPoolSize(5);
        return taskScheduler;
    }

    /**
     * 创建线程池.
     * <p>shutdown 可能会报找不到，但是必能去除，否则无法启动</p>
     * @return 线程池调度方案
     */
    @Bean(name = "TaskPool", destroyMethod="shutdown")
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        return taskScheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
    }
}
