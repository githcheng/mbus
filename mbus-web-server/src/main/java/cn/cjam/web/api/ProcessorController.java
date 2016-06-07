package cn.cjam.web.api;

import cn.cjam.service.TjProcessor;
import cn.cjam.util.JsonResult;
import com.alibaba.fastjson.JSONArray;
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
@RequestMapping("processor")
public class ProcessorController {

    private final Logger logger = LoggerFactory.getLogger(ProcessorController.class);


    @Autowired
    private TjProcessor service;

    @RequestMapping(value="/test", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject index(HttpServletRequest request,
            HttpServletResponse response ){

        JSONArray result = service.getResult();
        return new JsonResult().withData(result).success();
    }
}