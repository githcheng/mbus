package cn.cjam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("test")
public class IndexController {

    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){
        return new ModelAndView("/index",null);
    }
}
