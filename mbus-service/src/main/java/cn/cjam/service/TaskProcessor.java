package cn.cjam.service;

import cn.cjam.model.RunLog;
import cn.cjam.model.SeedTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
public class TaskProcessor implements Callable,PageProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private String startUrl = null;
    private Map<String,String> paramDict = null;
    private String URL_LIST = null;
    private String URL_POST = null;
    private JSONArray resultArr  = new JSONArray();
    private SeedTemplate seed = null;

    public TaskProcessor(){

    }

    public TaskProcessor(SeedTemplate seed){
        this.seed = seed;
    }

    @Override
    public void process(Page page) {

        // 列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            System.out.println("=============URL_LIST===============");
            System.out.println("=============URL_LIST all==============="+page.getHtml().links().regex(URL_LIST).all());
            return ;
        }
        System.out.println("=============URL_POST===============");
        // 详情页
        List<String> all = page.getHtml().links().regex(URL_POST).all();

        System.out.println(all);

        page.addTargetRequests(all);

        // extract 参数
        Iterator<Map.Entry<String, String>> iterator = paramDict.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> ele = iterator.next();
            String key = ele.getKey();
            String value = ele.getValue();
            page.putField(key, page.getHtml().xpath(value).toString());
            if (page.getResultItems().get(key)==null){
                page.setSkip(true);
                return;
            }
        }
        resultArr.add(JSON.toJSON(page.getResultItems()));
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
        Spider spider = Spider.create(this);

        if(isBrowse == 1 ){
            String path = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
            SeleniumDownloader seleniumDownloader = new SeleniumDownloader(path);
            spider.setDownloader(seleniumDownloader);
            System.out.println("====set==seleniumDownloader");
        }
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

        paramDict = Maps.newHashMap();
        Iterator<Map.Entry<String, Object>> iterator = conObj.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> ele = iterator.next();
            String key = ele.getKey();
            String value = String.valueOf(ele.getValue());
            if ("URL_POST".equals(key)){
                URL_POST = value;
            } else if ("URL_LIST".equals(key)){
                URL_LIST = value;
            } else {
                paramDict.put(key, value);
            }
        }
        if (StringUtils.isBlank(URL_LIST)){
            URL_LIST = "=======================";
        }
    }

    /**
     * 入口
     * @return
     */
    @Override
    public Object call() throws Exception {

        Integer isBrowse = seed.getIsBrowse();
        fillParameter(seed);
        Spider spider = Spider.create(this);

        if(isBrowse == 1 ){
            String path = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
            SeleniumDownloader seleniumDownloader = new SeleniumDownloader(path);
            spider.setDownloader(seleniumDownloader);
            System.out.println("====set==seleniumDownloader");
        }
        spider.addUrl(startUrl)
                .addPipeline(new ConsolePipeline())
                .run();

        RunLog runLog = new RunLog();
        return runLog;
    }
}