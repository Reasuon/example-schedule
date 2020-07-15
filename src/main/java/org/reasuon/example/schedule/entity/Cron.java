package org.reasuon.example.schedule.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Reasuon reasuon@gmail.com
 */
@TableName("t_cron")
public class Cron implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String cron;

    public String getName() {
        return this.name;
    }

    public String getCron() {
        return this.cron;
    }

}
