package cn.cjam.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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

    public static void main(String[] args){
        String html = "";
        String pureText = getPureText(html);
        System.out.println(pureText);
    }
}
