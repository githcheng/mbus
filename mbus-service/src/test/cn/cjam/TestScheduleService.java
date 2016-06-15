package cn.cjam;

import cn.cjam.biz.ScheduleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cheng on 2016/6/9.
 */

public class TestScheduleService extends BaseTest{


    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void execute_Test(){
        scheduleService.execute();
    }

    @Test
    public void regex_Test(){
        String raw = "<   td class=\"text\" width=\"16%\" bgcolor=\"#E6F2FF\" align=\"center\"  >ddddd</td><div class=ooo>";

        String regex = "<\\s*([a-zA-Z-]+).*?>";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(raw);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            String group1 = "<"+matcher.group(1)+">";
            matcher.appendReplacement(stringBuffer,group1);

//            String group2 = matcher.group(2);
//            String group3 = matcher.group(3);
//            System.out.println(group1+","+group2);
        }
        System.out.println(stringBuffer);
    }

}
