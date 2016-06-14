package cn.cjam.web.job;

import cn.cjam.biz.ScheduleService;
import cn.cjam.util.JsonResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cheng on 2016/6/14.
 */

@Controller
@RequestMapping("job")
public class CrawlerJob extends AbstractJob{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int jobOff = 0;


    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void start() {
        long start = System.currentTimeMillis();
        logger.info("CrawlerJob start...");
        System.out.println("CrawlerJob start...");
        if (jobOff == 0){
            logger.info("CrawlerJob on...");
            scheduleService.execute();
        } else {
            logger.info("CrawlerJob off...");
        }

        long end = System.currentTimeMillis();
        logger.info("CrawlerJob end cost:{}ms...",(end-start));
    }

    @RequestMapping(value="/set", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject set(HttpServletRequest request,
                    HttpServletResponse response,
        Integer off){
        jobOff = off;
        return new JsonResult().success();
    }

    @RequestMapping(value="/now", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject now(HttpServletRequest request,
                    HttpServletResponse response){
        start();
        return new JsonResult().success();
    }
}
