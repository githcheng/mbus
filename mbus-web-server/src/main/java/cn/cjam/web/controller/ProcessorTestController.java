package cn.cjam.web.controller;

import cn.cjam.model.SeedTemplate;
import cn.cjam.service.TestProcessor;
import cn.cjam.web.model.ShowResultTemplate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("web")
public class ProcessorTestController {

    @Autowired
    private TestProcessor processor;

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
        model.put("data",parse(result));

        return new ModelAndView("test_result",model);
    }


    public List<ShowResultTemplate> parse(JSONArray result){

        ArrayList<ShowResultTemplate> templates = new ArrayList<ShowResultTemplate>();
        if (CollectionUtils.isEmpty(result)){
            return templates;
        }
        for (int i=0;i<result.size();i++){
            JSONObject obj = result.getJSONObject(i);
            ShowResultTemplate element = new ShowResultTemplate();
            element.title = obj.getJSONObject("all").getString("title");
            element.contentSize = StringUtils.length(obj.getJSONObject("all").getString("content"));
            element.url = obj.getJSONObject("request").getString("url");
            element.statusCode = obj.getJSONObject("request").getJSONObject("extras").getInteger("statusCode");
            templates.add(element);
        }
        return templates;
    }


}
