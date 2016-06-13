package cn.cjam;

import cn.cjam.biz.SeedTemplateService;
import cn.cjam.model.SeedTemplate;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * Created by cheng on 2016/6/9.
 */

public class TestDao extends BaseTest{


    @Autowired
    private SeedTemplateService seedTemplateService;

    @Test
    public void SeedTemplateDao_getSeedTemplateListByState_Test(){
        List<SeedTemplate> seedTemplateList = seedTemplateService.getSeedTemplateListByState(0,1);
        System.out.println(seedTemplateList);

        System.out.println(seedTemplateList.size());
    }

    @Test
    public void SeedTemplateDao_insert_Test(){
        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setContent(sampleSeed().toJSONString());
        seedTemplate.setOperator("程国江");
        seedTemplate.setState(0);
        seedTemplate.setIsBrowse(0);
        seedTemplate.setStartUrl("http://www.tjztb.gov.cn/zbgg/gczb/");
        seedTemplate.setType(new Random().nextInt(4)+1);

        Integer insert = seedTemplateService.insert(seedTemplate);
        System.out.println(insert);
    }

    public JSONObject sampleSeed(){
        JSONObject object = new JSONObject();
        object.put("URL_POST", "(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/\\d+/t\\d+_\\d+\\.shtml)");
        object.put("URL_LIST", "(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/index_\\[1-3]\\.shtml)");
        object.put("title", "/html/body/div/div[5]/div[3]/text()");
        object.put("content", "/html/body/div/div[5]");
        return object;
    }

    public JSONObject sampleSeed3(){
        JSONObject object = new JSONObject();
        object.put("URL_POST", "(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/\\d+/t\\d+_\\d+\\.shtml)");
        object.put("URL_LIST", "(http://www\\.tjztb\\.gov\\.cn/zbgg/gczb/index_\\[1-3]\\.shtml)");
        object.put("title", "/html/body/div/div[5]/div[3]/text()");
        object.put("content", "/html/body/div/div[5]");
        object.put("test", "/html/body/div/div[5]");
        return object;
    }

    @Test
    public void update_Test(){

        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setContent(sampleSeed3().toJSONString());
        seedTemplate.setOperator("test");
        seedTemplate.setState(0);
        seedTemplate.setIsBrowse(0);
        seedTemplate.setStartUrl("www.test.com");
        seedTemplate.setType(new Random().nextInt(4)+1);

        Long id = 17L;
        seedTemplate.setId(id);
        seedTemplateService.update(seedTemplate);
    }


    @Test
    public void confirm(){
        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setContent(sampleSeed3().toJSONString());
        seedTemplate.setOperator("test");
        seedTemplate.setState(1);
        seedTemplate.setIsBrowse(0);
        seedTemplate.setStartUrl("www.test.com");
        seedTemplate.setType(new Random().nextInt(4)+1);

        Long id = 17L;
        seedTemplate.setId(id);
       seedTemplateService.confirm(seedTemplate);
    }

    @Test
    public void delete(){
        Long id = 16L;
        seedTemplateService.delete(id);
    }


    @Test
    public void SeedTemplateDao_insert2_Test(){
        SeedTemplate seedTemplate = new SeedTemplate();
        seedTemplate.setContent(sampleSeed2().toJSONString());
        seedTemplate.setOperator("jam");
        seedTemplate.setState(0);
        seedTemplate.setIsBrowse(1);
        seedTemplate.setStartUrl("http:test.com");
        seedTemplate.setType(new Random().nextInt(4) + 1);

        Integer insert = seedTemplateService.insert(seedTemplate);
        System.out.println(insert);
    }
    public JSONObject sampleSeed2(){
        JSONObject object = new JSONObject();
        object.put("URL_POST", "(http://www\\.fjgpc\\.cn/Modules/ShowBidAffiche\\.aspx\\?DisplayType=PublicAffiche&Id=\\S+)");
        object.put("title", "//*[@id=\"ctl00_ContentPlaceHolder1_FormView1_Label1\"]");
        object.put("content", "//*[@id=\"ctl00_ContentPlaceHolder1_FormView1\"]/tbody/tr/td/table");
        return object;
    }
}
