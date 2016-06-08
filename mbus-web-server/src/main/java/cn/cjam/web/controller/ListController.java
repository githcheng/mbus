package cn.cjam.web.controller;

import cn.cjam.model.SeedTemplate;
import cn.cjam.web.api.ProcessorController;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ListController {

    private final Logger logger = LoggerFactory.getLogger(ListController.class);

    @RequestMapping("/list")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){

        Map<String, Object> model = Maps.newHashMap();
        ArrayList<SeedTemplate> seedTemplates = new ArrayList<SeedTemplate>();
        newSeedTemplate(seedTemplates);
        logger.info("size:{}",seedTemplates.size());
        model.put("seedList",seedTemplates);
        return new ModelAndView("list",model);

    }

    public void newSeedTemplate(List<SeedTemplate> seedTemplates){
        for (int i=0;i<20;i++){
            SeedTemplate seedTemplate = new SeedTemplate();
            seedTemplate.setId(Long.valueOf(i));
            seedTemplate.setContent("weeeeeeeeeeeeee");
            seedTemplate.setOperator("jam");
            seedTemplate.setState(0);
            seedTemplate.setIsBrowse(0);
            seedTemplate.setStartUrl("www.58.com");
            seedTemplates.add(seedTemplate);
        }
    }
}
