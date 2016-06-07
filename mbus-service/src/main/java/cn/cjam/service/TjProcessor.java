package cn.cjam.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Service
public class TjProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private static String startUrl = "http://www.tjztb.gov.cn/zbgg/gczb/";
    private String targetUrlRegex = "(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/\\d+/t\\d+_\\d+\\.shtml)";
    private String titleRegex = "/html/body/div/div[5]/div[3]/text()";
    private String contentRegex = "/html/body/div/div[5]";

    private ResultItems resultItems = new ResultItems();
    private static JSONArray resultArr  = new JSONArray();


    @Override
    public void process(Page page) {

        List<String> all = page.getHtml().links().regex(targetUrlRegex).all();
        System.out.println(all);
        page.addTargetRequests(all);
        page.putField("name", page.getHtml().xpath(titleRegex).toString());
        page.putField("content", page.getHtml().xpath(contentRegex).toString());

        //
        if (page.getResultItems().get("name")==null){
            //skip this page
            System.out.println("+++++++++++");
            page.setSkip(true);
        } else {
            resultArr.add(JSON.toJSON(page.getResultItems()));
        }
        System.out.println("==============");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public JSONArray getResult(){
        Spider spider = Spider.create(new TjProcessor());
        spider.addUrl(startUrl)
                .addPipeline(new ConsolePipeline())
                .run();

        System.out.println("===========print total=================");
        System.out.println(resultArr.toString());
        return resultArr;
    }

    public static void main(String[] args) throws InterruptedException {


        Spider spider = Spider.create(new TjProcessor());
        spider.addUrl(startUrl)
                .addPipeline(new ConsolePipeline())
                .run();

        System.out.println("===========print total=================");
        System.out.println(resultArr.toString());
    }
}