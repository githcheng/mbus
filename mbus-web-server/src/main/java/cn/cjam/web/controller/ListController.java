package cn.cjam.web.controller;

import cn.cjam.biz.SeedTemplateService;
import cn.cjam.model.SeedTemplate;
import cn.cjam.web.api.ProcessorController;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("web")
public class ListController {

    private final Logger logger = LoggerFactory.getLogger(ListController.class);


    @Autowired
    private SeedTemplateService service;

    @RequestMapping("/list")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){

        Map<String, Object> model = Maps.newHashMap();
        List<SeedTemplate> seedTemplates = service.getSeedTemplateListByState(0,1,2);
        logger.info("size:{}",seedTemplates.size());
        model.put("seedList",seedTemplates);
        return new ModelAndView("list",model);

    }

    public void newSeedTemplate(List<SeedTemplate> seedTemplates){
        for (int i=0;i<20;i++){
            SeedTemplate seedTemplate = new SeedTemplate();
            seedTemplate.setId(Long.valueOf(i));
            seedTemplate.setContent(sampleSeed().toJSONString());
            seedTemplate.setOperator("jam");
            seedTemplate.setState(0);
            seedTemplate.setIsBrowse(0);
            seedTemplate.setStartUrl("http://www.tjztb.gov.cn/zbgg/gczb/");
            seedTemplate.setType(new Random().nextInt(4)+1);
            seedTemplates.add(seedTemplate);
        }
    }

    public JSONObject sampleSeed(){
        JSONObject object = new JSONObject();
        object.put("URL_POST","(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/\\d+/t\\d+_\\d+\\.shtml)");
        object.put("URL_LIST","(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/index_\\[1-3]\\.shtml)");
        object.put("title", "/html/body/div/div[5]/div[3]/text()");
        object.put("content", "/html/body/div/div[5]");
        return object;
    }
}
