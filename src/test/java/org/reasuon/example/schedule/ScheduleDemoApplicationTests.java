package org.reasuon.example.schedule;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.reasuon.example.schedule.entity.Cron;
import org.reasuon.example.schedule.mapper.CronMapper;
import org.reasuon.example.schedule.task.ExampleTask;
import org.reasuon.example.schedule.utils.DynamicScheduleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
class ScheduleDemoApplicationTests {

    @Autowired
    CronMapper cronMapper;

    @Autowired
    private DynamicScheduleManage dynamicScheduleManage;

    @Test
    void contextLoads() {
        List<Cron> cronList = cronMapper.selectList(null);
        for (int i = 0; i < cronList.size(); i++) {
            System.out.println(String.format("Name:%s,Cron:%s", cronList.get(i).getName(), cronList.get(i).getCron()));
            ExampleTask task = new ExampleTask("任务" + (i + 1));
            dynamicScheduleManage.startSchedule(cronList.get(i).getName(), task, cronList.get(i).getCron());
        }
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < cronList.size(); i++) {
            dynamicScheduleManage.stopSchedule("任务" + (i + 1));
        }
    }
}
