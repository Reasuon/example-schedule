package org.reasuon.example.schedule.service.impl;

import org.reasuon.example.schedule.entity.Cron;
import org.reasuon.example.schedule.mapper.CronMapper;
import org.reasuon.example.schedule.service.ICronService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Reasuon reasuon@gmail.com
 */
@Service
public class CronServiceImpl extends ServiceImpl<CronMapper, Cron> implements ICronService {

}
