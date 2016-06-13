package cn.cjam.web.api;

import cn.cjam.biz.SeedTemplateService;
import cn.cjam.model.SeedTemplate;
import cn.cjam.service.TestService;
import cn.cjam.util.JsonResult;
import cn.cjam.web.util.StrUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("api")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);


    @Autowired
    private SeedTemplateService seedTemplateService;

    @RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
     public @ResponseBody
     JSONObject add(HttpServletRequest request,
                    HttpServletResponse response ){
        String type = StrUtils.replaceBlank(request.getParameter("type"));
        String content = StrUtils.replaceBlank(request.getParameter("content"));
        String startUrl = StrUtils.replaceBlank(request.getParameter("startUrl"));
        String isBrowse = StrUtils.replaceBlank(request.getParameter("isBrowse"));
        String operator = StrUtils.replaceBlank(request.getParameter("operator"));

        logger.info("startUrl:{}",startUrl);

        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setContent(content);
        seedTemplate.setOperator(operator);
        seedTemplate.setState(0);
        seedTemplate.setIsBrowse(Integer.valueOf(isBrowse));
        seedTemplate.setStartUrl(startUrl);
        seedTemplate.setType(Integer.valueOf(type));

        seedTemplateService.insert(seedTemplate);
        return new JsonResult().success();
    }

    @RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject del(HttpServletRequest request,
                   HttpServletResponse response ){
        String id = request.getParameter("id");
        logger.info("id:{}",id);
        seedTemplateService.delete(Long.valueOf(id));
        return new JsonResult().success();
    }

    @RequestMapping(value="/confirm", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject confirm(HttpServletRequest request,
                   HttpServletResponse response ){
        String id = request.getParameter("id");
        logger.info("id:{}", id);
        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setId(Long.valueOf(id));
        seedTemplate.setState(1);
        seedTemplateService.confirm(seedTemplate);
        return new JsonResult().success();
    }

    @RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject modify(HttpServletRequest request,
                   HttpServletResponse response){

        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String state = request.getParameter("state");
        String content = request.getParameter("content");
        String startUrl = request.getParameter("startUrl");
        String isBrowse = request.getParameter("isBrowse");
        String operator = request.getParameter("operator");

        logger.info("startUrl:{}", startUrl);

        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setId(Long.valueOf(id));
        seedTemplate.setContent(content);
        seedTemplate.setOperator(operator);
        seedTemplate.setIsBrowse(Integer.valueOf(isBrowse));
        seedTemplate.setStartUrl(startUrl);
        seedTemplate.setType(Integer.valueOf(type));
        seedTemplate.setState(Integer.valueOf(state));

        seedTemplateService.update(seedTemplate);
        // 更新状态
        seedTemplateService.confirm(seedTemplate);
        return new JsonResult().success();
    }

    @RequestMapping(value="/copy", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JSONObject copy(HttpServletRequest request,
                      HttpServletResponse response){

        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String state = request.getParameter("state");
        String content = request.getParameter("content");
        String startUrl = request.getParameter("startUrl");
        String isBrowse = request.getParameter("isBrowse");
        String operator = request.getParameter("operator");

        logger.info("startUrl:{}", startUrl);

        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setContent(content);
        seedTemplate.setOperator(operator);
        seedTemplate.setIsBrowse(Integer.valueOf(isBrowse));
        seedTemplate.setStartUrl(startUrl+"请修改地址...");
        seedTemplate.setType(Integer.valueOf(type));
        seedTemplate.setState(Integer.valueOf(state));

        seedTemplateService.insert(seedTemplate);
        return new JsonResult().success();
    }
}