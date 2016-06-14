package cn.cjam.web.controller;

import cn.cjam.model.SeedTemplate;
import cn.cjam.model.ShowResultTemplate;
import cn.cjam.service.TestMapProcessor;
import cn.cjam.util.ParseUtil;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("web")
public class ProcessorTestController {

    @Autowired
    private TestMapProcessor processor;

    private final Logger logger = LoggerFactory.getLogger(ProcessorTestController.class);

    @RequestMapping(value="/processor", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){

        Map<String, Object> model = Maps.newHashMap();
        String id = request.getParameter("id");
        String startUrl = request.getParameter("startUrl");
        Integer isBrowse = Integer.valueOf(request.getParameter("isBrowse"));
        String content = request.getParameter("content");


        SeedTemplate seedTemplate = new SeedTemplate(startUrl, content, isBrowse);
        JSONArray result = processor.test(seedTemplate);
        model.put("id",id);
        List<ShowResultTemplate> resultTemplates = ParseUtil.parse(result);
        model.put("data",resultTemplates);
        model.put("total",resultTemplates.size());

        return new ModelAndView("test_result",model);
    }
}
