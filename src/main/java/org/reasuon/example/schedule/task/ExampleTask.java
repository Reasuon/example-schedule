package org.reasuon.example.schedule.task;

import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * 范例任务.
 *
 * @author Reasuon reasuon@gmail.com
 */
public class ExampleTask implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(ExampleTask.class);

    private String name;

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public ExampleTask(String name) {
        this.name = name;
    }

    public void remove() {
        threadLocal.remove();
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        int num = 1;
        try {
            num = threadLocal.get();
            int millisecond = 3000;
            log.info("休眠{}秒", millisecond / 1000);
            Thread.sleep(millisecond);
        } catch (NullPointerException | InterruptedException e) {
            threadLocal.set(num);
        }
        log.info("任务: {}，线程名称: {}，执行次数: {}，当前时间: {}", name, thread.getName(), threadLocal.get(), DateUtil.now());
        threadLocal.set(threadLocal.get() + 1);
    }
}
