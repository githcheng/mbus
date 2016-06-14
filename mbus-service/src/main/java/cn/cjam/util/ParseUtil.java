package cn.cjam.util;

import cn.cjam.model.ShowResultTemplate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jam on 2016/6/14.
 */
public class ParseUtil {

    public static List<ShowResultTemplate> parse(JSONArray result){

        ArrayList<ShowResultTemplate> templates = new ArrayList<ShowResultTemplate>();
        if (CollectionUtils.isEmpty(result)){
            return templates;
        }
        for (int i=0;i<result.size();i++){
            JSONObject obj = result.getJSONObject(i);
            try {
                ShowResultTemplate element = new ShowResultTemplate();
                element.title = obj.getJSONObject("all").getString("title");
                element.content = obj.getJSONObject("all").getString("content");
                element.contentSize = StringUtils.length(element.content);
                element.url = obj.getJSONObject("request").getString("url");
                element.statusCode = obj.getJSONObject("request").getJSONObject("extras").getInteger("statusCode");
                templates.add(element);
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return templates;
    }
}
