package cn.cjam.service;

import cn.cjam.model.SeedTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Service
public class TestProcessor implements PageProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private String startUrl = null;
    private String targetUrlRegex = null;
    private String titleRegex = null;
    private String contentRegex = null;
    private JSONArray resultArr  = null;

    public TestProcessor(){

    }

    public TestProcessor(SeedTemplate seed){

    }

    @Override
    public void process(Page page) {

        List<String> all = page.getHtml().links().regex(targetUrlRegex).all();
        System.out.println(all);
        page.addTargetRequests(all);
        page.putField("title", page.getHtml().xpath(titleRegex).toString());
        page.putField("content", page.getHtml().xpath(contentRegex).toString());

        //
        if (page.getResultItems().get("title")==null){
            //skip this page
            page.setSkip(true);
        } else {
            resultArr.add(JSON.toJSON(page.getResultItems()));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 入口
     * @param seed
     * @return
     */
    public JSONArray test(SeedTemplate seed){

        resultArr  = new JSONArray();
        Integer isBrowse = seed.getIsBrowse();
        fillParameter(seed);
        logger.info("{},{},{},{}",startUrl,targetUrlRegex,titleRegex,contentRegex);

        Spider spider = Spider.create(this);
        spider.addUrl(startUrl)
                .addPipeline(new ConsolePipeline())
                .run();
        return resultArr;

    }

    /**
     * 解析内容字段
     * @param seed
     */
    private void fillParameter(SeedTemplate seed) {
        this.startUrl = seed.getStartUrl();
        JSONObject conObj = JSONObject.parseObject(seed.getContent());
        this.targetUrlRegex = conObj.getString("targetUrlRegex");
        this.titleRegex = conObj.getString("titleRegex");
        this.contentRegex = conObj.getString("contentRegex");

//        this.targetUrlRegex = "(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/\\d+/t\\d+_\\d+\\.shtml)";
//        this.titleRegex = "/html/body/div/div[5]/div[3]/text()"; ///html/body/div/div[5]/div[3]
//        this.contentRegex = "/html/body/div/div[5]";
    }
}