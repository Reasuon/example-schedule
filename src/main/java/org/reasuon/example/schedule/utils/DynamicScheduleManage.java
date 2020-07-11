package org.reasuon.example.schedule.utils;

import lombok.extern.slf4j.Slf4j;
import org.reasuon.example.schedule.task.ExampleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态定时任务管理.
 * <p>
 * 用于动态添加、删除定时任务.
 * </p>
 *
 * @author Reasuon reasuon@gmail.com
 */
@Slf4j
@Component
public class DynamicScheduleManage {

    @Autowired
    @Qualifier("DynamicTaskPool")
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 存储定时任务.
     */
    private Map<String, ScheduledFuture<?>> futureHashMap = new HashMap<>(4);

    /**
     * 存储定时任务的行为.
     */
    private Map<String, ExampleTask> runnableMap = new HashMap<>(4);

    /**
     * 启动定时任务.
     * 如果任务名相同，则覆盖之前的任务.
     *
     * @param key 任务唯一标识
     * @param task 任务
     * @param cron Cron表达式
     **/
    public void startSchedule(String key, ExampleTask task, String cron) {
        stopSchedule(key);
        //将任务加入定时任务，并指定执行周期
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
        futureHashMap.put(key, scheduledFuture);
        runnableMap.put(key, task);
    }

    /**
     * 停止任务.
     *
     * @param key 任务唯一标识
     **/
    public void stopSchedule(String key) {
        ScheduledFuture<?> scheduledFuture = futureHashMap.get(key);
        if (scheduledFuture != null) {
            //关闭并删除定时任务
            scheduledFuture.cancel(false);
            futureHashMap.remove(key);
            //删除线程变量
            ExampleTask exampleTask = (ExampleTask) runnableMap.get(key);
            exampleTask.remove();
        }
    }

}
