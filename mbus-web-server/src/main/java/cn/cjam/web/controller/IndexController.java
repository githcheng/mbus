package cn.cjam.web.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("test")
public class IndexController {

    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){

        Map<String, Object> model = Maps.newHashMap();
        model.put("title", "Velocity Template");
        model.put("content", "Content in Velocity Template");

        return new ModelAndView("index",model);
    }
}
