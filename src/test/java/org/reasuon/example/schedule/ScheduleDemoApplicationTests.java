package org.reasuon.example.schedule;

import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.reasuon.example.schedule.entity.Cron;
import org.reasuon.example.schedule.mapper.CronMapper;
import org.reasuon.example.schedule.task.ExampleTask;
import org.reasuon.example.schedule.utils.DynamicScheduleManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ScheduleDemoApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(ScheduleDemoApplicationTests.class);

    @Autowired
    CronMapper cronMapper;

    @Autowired
    private DynamicScheduleManage dynamicScheduleManage;

    @Test
    void dynamicScheduleTest() {
        List<Cron> cronList = cronMapper.selectList(null);
        for (int i = 0; i < cronList.size(); i++) {
            log.info("Name:{},Cron:{}", cronList.get(i).getName(), cronList.get(i).getCron());
            ExampleTask task = new ExampleTask("任务" + (i + 1));
            dynamicScheduleManage.startSchedule(cronList.get(i).getName(), task, cronList.get(i).getCron());
        }
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < cronList.size(); i++) {
            dynamicScheduleManage.stopSchedule("任务" + (i + 1));
        }
    }

    @Test
    void normalTest() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    @Scheduled(cron = "0/1 * * * * ?")
    public void beforeSchedule() {
        Thread thread = Thread.currentThread();
        log.info("线程名称: {}，固定定时任务测试:每1秒循环，当前时间: {}", thread.getName(), DateUtil.now());
    }

    @Before
    @Scheduled(cron = "0/3 * * * * ?")
    public void beforeSchedule2() {
        Thread thread = Thread.currentThread();
        log.info("线程名称: {}，固定定时任务测试:每3秒循环，当前时间: {}", thread.getName(), DateUtil.now());
        log.info("线程名称: {}，休眠3秒，当前时间: {}", thread.getName(), DateUtil.now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名称: {}，休眠结束，当前时间: {}", thread.getName(), DateUtil.now());
    }
}
