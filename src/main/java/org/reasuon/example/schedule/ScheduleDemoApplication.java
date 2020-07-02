package org.reasuon.example.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 范例启动类.
 *
 * @author Reasuon reasuon@gmail.com
 */
@SpringBootApplication
@MapperScan({"org.reasuon.example.schedule.mapper"})
public class ScheduleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDemoApplication.class, args);
    }

}
