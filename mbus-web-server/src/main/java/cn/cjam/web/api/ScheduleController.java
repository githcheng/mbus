package cn.cjam.web.api;

import cn.cjam.biz.ScheduleService;
import cn.cjam.util.JsonResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("schedule")
public class ScheduleController {

    private final Logger logger = LoggerFactory.getLogger(ScheduleController.class);


    @Autowired
    private ScheduleService service;

    @RequestMapping(value="/index", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject index(HttpServletRequest request,
            HttpServletResponse response ){
        service.execute();
        return new JsonResult().success();
    }
}