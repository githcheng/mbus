package cn.cjam.web.util;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cheng on 2016/6/10.
 */
public class StrUtils {

    private static Pattern p = Pattern.compile("\\s*|\t|\r|\n");


    public static String[] urlEscape = { "\'" ,"\"", ".","\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
    public static String[] pathEscape = {"."};

    public static String replaceBlank(String str) {
        String dest = "";
        if (StringUtils.isNotBlank(str)) {
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 转义正则特殊字符
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword,String[] fbsArr) {
        if (StringUtils.isNotBlank(keyword)) {
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
