package org.reasuon.example.schedule.mapper;

import org.reasuon.example.schedule.entity.Cron;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Reasuon reasuon@gmail.com
 */
@Repository(value = "cronMapper")
public interface CronMapper extends BaseMapper<Cron> {

}
