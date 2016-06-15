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
    static String scriptRegex = "<\\s*/?[sS]cript\\s*>";
    static String nbspRegex = "(\\s*&nbsp;\\s*)+\\s*";
    static String blankRegex = "\\s+";
    static String noteRegex = "<!--.*?-->";


    static Pattern compile = Pattern.compile(regex);
    static Pattern scriptCompile = Pattern.compile(scriptRegex);
    static Pattern nbspCompile = Pattern.compile(nbspRegex);
    static Pattern blankCompile = Pattern.compile(blankRegex);
    static Pattern noteCompile = Pattern.compile(noteRegex);


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
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


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
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


    public static String clearNBSP(String input){
        if (StringUtils.isBlank(input)){
            return "";
        }
        Matcher matcher = nbspCompile.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(stringBuffer,"&nbsp;");
//            System.out.println(matcher.group());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


    public static String clearBlank(String input){
        if (StringUtils.isBlank(input)){
            return "";
        }
        Matcher matcher = blankCompile.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(stringBuffer," ");
//            System.out.println("="+matcher.group()+"=");
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


    public static String clearNote(String input){
        if (StringUtils.isBlank(input)){
            return "";
        }
        Matcher matcher = noteCompile.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(stringBuffer,"");
//            System.out.println("="+matcher.group()+"=");
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


    /**
     * 清洗内容
     * @param input
     * @return
     */
    public static String clear(String input){
        input = clearField(input);
        input = clearNote(input);
        input = clearScript(input);
        input = clearNBSP(input);
        input = clearBlank(input);
        return input;
    }


    public static void main(String[] args){
//        String input = "<   script></Script   >";
//        String s = clearScript(input);
//        System.out.println("=="+s);


//        String input = "   &nbsp;    &nbsp;&nbsp;";
//        String s = clearNBSP(input);
//        System.out.println("=="+s);


//        String input = "   &nbsp;         &nbsp;&nbsp;";
//        String s = clearBlank(input);
//        System.out.println("=="+s);

        String input = "<div> \n" +
                " <!-- 正文内容显示 开始--> \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                " \n" +
                "        <p><span><span>《国家税务总局公报》印刷服务项目</span><span>竞争性谈判公告</span></span></p><p><span>&nbsp;</span></p><p><span>&nbsp;&nbsp;&nbsp; 项目名称：《国家税务总局公报》印刷服务项目</span></p><p><span>&nbsp;&nbsp;&nbsp; 项目编号：</span><span>GC-FJ160913</span></p><p><span>中央国家机关政府采购中心对下列货物或服务进行竞争性谈判，现邀请合格谈判供应商提交密封谈判响应文件。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;1、谈判内容</span></p><p><span>（1）谈判内容：</span></p><table><tbody><tr><td><p><strong><span>序号</span></strong></p></td><td><p><strong><span>印刷品名称</span></strong></p></td><td><p><strong><span>页数</span></strong></p></td><td><p><strong><span>技术要求</span></strong></p></td><td><p><strong><span>印量（册）</span></strong></p></td><td><p><strong><span>成品尺寸（长mm*宽mm）</span></strong></p></td><td><p><strong><span>装订形式</span></strong></p></td></tr><tr><td><p><strong><span>1</span></strong></p></td><td><p><strong><span>国家税务总局公报</span></strong></p></td><td><p><strong><span>平均6.15印张/期，总73.8个印张（正背单色印刷）</span></strong></p></td><td><p><strong><span>详见谈判文件第</span></strong><strong><span>四</span></strong><strong><span>部分</span></strong></p></td><td><p><strong><span>5.9万册/期*12期（每号留2000册年底做精装合订本）</span></strong></p></td><td><p><strong><span>297×210</span></strong></p></td><td><p><strong><span>骑马钉</span></strong></p></td></tr></tbody></table><p><span>（2）本次谈判共1包</span></p><p><span>（3）交付时间：详见谈判文件</span></p><p><span>（4）交付地点：详见谈判文件</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;2、预算金额</span></p><p><span>本采购项目的预算金额为：</span><span><span>&nbsp;&nbsp;114 &nbsp;</span></span><span>万元。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;3、需要落实的政府采购政策</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;本项目需落实的节能环保、中小微型企业扶持、融资担保等相关政府采购政策详见谈判文件。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;4、合格谈判供应商必须符合下列条件&nbsp;</span></p><p><span>（1）符合《中华人民共和国政府采购法》第二十二条的规定</span></p><p><span>（2）其余谈判人资质要求详见谈判文件第二部分。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;5、获取谈判文件的办法和时间</span></p><p><span>即日起至接受响应文件截止时间止，登录“中央政府采购网”(</span><a><span>http://www.zycg.gov.cn/</span><span>）下载竞争性谈判文件。</span></a></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;&nbsp;&nbsp;</span><span>6</span><span>、接受响应文件时间、接受响应文件截止时间及谈判开始时间</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;接受响应文件时间：2016年</span><span>6</span><span>月</span><span>21</span><span>日上午8：30-9：00（北京时间）</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;接受响应文件截止时间及谈判开始时间：2016年</span><span>6</span><span>月</span><span>21</span><span>日上午9：00（北京时间）</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;接受响应文件截止时间后送达的响应文件将被拒收。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;7、谈判地点</span></p><p><span>中央国家机关政府采购中心（北京市西城区西直门内大街西章胡同9号院）。</span></p><p><span>8、本项目其余相关信息均在“中国政府采购网”、“中央政府采购网”等媒体上发布。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;9、联系方式</span></p><p><span>采购人名称：国家税务总局</span></p><p><span>地址：北京市海淀区羊坊店西路5号&nbsp;</span></p><p><span>联系电话：010-63417040 &nbsp;</span></p><p><span>采购中心地址：北京市西城区西直门内大街西章胡同9号院</span></p><p><span>邮政编码：100035</span></p><p><span>项目联系人：</span><span>孙宝华</span><span>（项目负责人）、陈嘉（项目经办人）</span></p><p><span>联系电话：010-830849</span><span>65</span><span>、63099478</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;10、公告期限</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;本竞争性谈判公告自发布之日起公告期限为3个工作日。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中央国家机关政府采购中心</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2016年</span><span>6</span><span>月</span><span>15</span><span>日</span></p><p>&nbsp;</p>";
        String s = clear(input);
        System.out.println("=="+s);

    }
}
