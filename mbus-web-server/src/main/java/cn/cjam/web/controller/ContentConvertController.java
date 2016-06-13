package cn.cjam.web.controller;

import cn.cjam.util.JsonResult;
import cn.cjam.web.util.StrUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("web")
public class ContentConvertController {

    @RequestMapping("/content")
    public ModelAndView content(HttpServletRequest request,
            HttpServletResponse response){

        String title = StrUtils.replaceBlank(request.getParameter("title"));
        String content = StrUtils.replaceBlank(request.getParameter("content"));
        String URL_LIST = StrUtils.replaceBlank(request.getParameter("URL_LIST"));
        String URL_POST = StrUtils.replaceBlank(request.getParameter("URL_POST"));
        HashMap<String, String> model = Maps.<String, String>newHashMap();


        model.put("title",title);
        model.put("xcontent",content);
        model.put("URL_POST",URL_POST);
        model.put("URL_LIST",URL_LIST);

        return new ModelAndView("content", model);

    }

    @RequestMapping("/convert")
    public @ResponseBody
    JSONObject convert(HttpServletRequest request,
                              HttpServletResponse response){

        String title = StrUtils.replaceBlank(request.getParameter("title"));
        String content = StrUtils.replaceBlank(request.getParameter("content"));
        String URL_LIST = StrUtils.replaceBlank(request.getParameter("URL_LIST"));
        String URL_POST = StrUtils.replaceBlank(request.getParameter("URL_POST"));

        title = StrUtils.escapeExprSpecialWord(title,StrUtils.pathEscape);
        content = StrUtils.escapeExprSpecialWord(content,StrUtils.pathEscape);
        URL_POST = StrUtils.escapeExprSpecialWord(URL_POST,StrUtils.pathEscape);
        URL_LIST = StrUtils.escapeExprSpecialWord(URL_LIST,StrUtils.pathEscape);

        JSONObject result = new JSONObject();
        result.put("URL_LIST","======");
        result.put("content", content);
        result.put("title",title);
        result.put("URL_POST",URL_POST);

        JsonResult jsonResult = new JsonResult().success().withData(result);
        return jsonResult;
    }
}
