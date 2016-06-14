package cn.cjam;

import cn.cjam.biz.ScheduleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cheng on 2016/6/9.
 */

public class TestScheduleService extends BaseTest{


    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void execute_Test(){
        scheduleService.execute();
    }

}
