package org.reasuon.example.schedule.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;

/**
 * 范例任务.
 *
 * @author Reasuon reasuon@gmail.com
 */
@Data
@Slf4j
public class ExampleTask implements Runnable{

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
        } catch (NullPointerException e) {
            threadLocal.set(num);
        }
        log.info("任务: {}，线程名称: {}，执行次数: {}，当前时间: {}", name, thread.getName(), threadLocal.get(), DateUtil.now());
        threadLocal.set(threadLocal.get() + 1);
    }
}
