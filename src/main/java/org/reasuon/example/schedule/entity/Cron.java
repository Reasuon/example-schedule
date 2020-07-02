package org.reasuon.example.schedule.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Reasuon reasuon@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cron")
public class Cron implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String cron;


}
