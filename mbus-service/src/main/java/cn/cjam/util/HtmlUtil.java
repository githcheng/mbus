package cn.cjam.util;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cheng on 2016/2/23.
 */
public class HtmlUtil {

    // 只有纯文本可以通过
    public static String getPureText(String html) {
        if (html == null){
            return null;
        }
        return Jsoup.clean(html, Whitelist.none());
    }


    static String regex = "<\\s*([a-zA-Z-]+).*?>";
    static Pattern compile = Pattern.compile(regex);

    public static String clearField(String input){
        if (StringUtils.isBlank(input)){
            return "";
        }
        Matcher matcher = compile.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            String group1 = "<"+matcher.group(1)+">";
            matcher.appendReplacement(stringBuffer,group1);
        }
        return stringBuffer.toString();
    }


    static String scriptRegex = "<\\s*/?[sS]cript\\s*>";
    static Pattern scriptCompile = Pattern.compile(scriptRegex);
    public static String clearScript(String input){
        if (StringUtils.isBlank(input)){
            return "";
        }
        Matcher matcher = scriptCompile.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(stringBuffer,"");
//            System.out.println(matcher.group());
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args){
        String input = "<   script></Script   >";
        String s = clearScript(input);
        System.out.println("=="+s);
    }
}
