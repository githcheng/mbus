package cn.cjam.web.api;

import cn.cjam.model.TestUser;
import cn.cjam.service.TestService;
import cn.cjam.util.JsonResult;
import com.alibaba.fastjson.JSON;
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
import java.util.List;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private TestService service;

    @RequestMapping(value="/index", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject index(HttpServletRequest request,
            HttpServletResponse response ){

        return new JsonResult().success();
    }

    @RequestMapping(value="/getList", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject getList(HttpServletRequest request,
                     HttpServletResponse response ){

        List<TestUser> users = service.getList(10);
        Object o = JSON.toJSON(users);
        return new JsonResult().success().withData(o);
    }
}