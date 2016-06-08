package cn.cjam.web.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("web")
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){

        Map<String, Object> model = Maps.newHashMap();
        model.put("time",1);
        model.put("title", "Velocity Template");
        model.put("content1", "Content in Velocity Template");


        return new ModelAndView("index",model);

    }

    @RequestMapping("/index2")
    public String index2(HttpServletRequest request,
                              HttpServletResponse response){

        Map<String, Object> model = Maps.newHashMap();
        model.put("title", "Velocity Template");
        model.put("content", "Content in Velocity Template");

        return "index";

    }
}
