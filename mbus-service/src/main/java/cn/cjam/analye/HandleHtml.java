package cn.cjam.analye;

import cn.cjam.util.HtmlUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Iterator;

/**
 * Created by cheng on 2016/6/15.
 */
public class HandleHtml {

    public static void  main(String[] args) throws Exception {


        int titleCount = 0;
        int h1Count = 0;
        int titleExist = 0;
        int fieldTitleCount = 0;


        File fileDir = new File("d:\\html");
        File[] files = fileDir.listFiles();
        for (File fileItem : files){
            System.out.println(fileItem.getAbsolutePath());
            String absolutePath = fileItem.getAbsolutePath();

            String encoding = get_charset(fileItem);
            String doc = readFileByLines(absolutePath, encoding);

            String clearDoc = HtmlUtil.clear(doc);
//            System.out.println(clearDoc);

            Document html = Jsoup.parse(clearDoc);
            String title = html.title();
            System.out.println(title);
            if (!StringUtils.isEmpty(title)){
                titleCount++;
            }


            String h1 = html.body().getElementsByTag("h1").text();
            String body = html.body().text();



            if (!StringUtils.isEmpty(h1)){
                h1Count++;
                System.out.println("===="+h1);
            } else {
                if (!StringUtils.isEmpty(body)){
                    int at = body.indexOf(title);
                    if (at >= 0){
                        System.out.println("====++"+title);
                        titleExist++;
                    } else {
                        if (!StringUtils.isEmpty(title)){
                            String[] split = title.split("-");
                            String fieldTitle = split[0];
                            if (!StringUtils.isEmpty(body)){
                                int atx = body.indexOf(fieldTitle);
                                if (atx >= 0){
                                    System.out.println("====**"+fieldTitle);
                                    fieldTitleCount++;
                                }
                            }
                        }
                    }
                }
            }


            Element eBody = html.body();
            JSONObject object = new JSONObject();
            JSONObject deep = deep(eBody, object);
            System.out.println(deep.toJSONString());

        }
        String format = String.format("title:%s/10, h1:%d/10, exist:%d/10,fieldTitleCount:%d/10",
                titleCount, h1Count, titleExist, fieldTitleCount);
        System.out.println(format);







    }


    public static JSONObject deep(Element root, JSONObject object){
        if (root == null){
            return null;
        }
        Elements childrens = root.children();
        if (childrens.size() <= 0){
            String rootName = root.tagName();
            object.put("name",rootName);
            return object;
        }

        Iterator<Element> iterator = childrens.iterator();
        String rootName = root.tagName();
        int size = childrens.size();
        JSONArray arr = new JSONArray();
        while (iterator.hasNext()){
            JSONObject item = new JSONObject();
            Element next = iterator.next();
            deep(next,item);
            arr.add(item);
        }
        object.put("name", rootName+":"+size);
        object.put("children",arr);
        return object;
    }


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName,String encoding) {

        File file = new File(fileName);
        StringBuilder sb = new StringBuilder("");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), encoding));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    public static String get_charset( File file ) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
            bis.mark( 0 );
            int read = bis.read( first3Bytes, 0, 3 );
            if ( read == -1 ) return charset;
            if ( first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE ) {
                charset = "UTF-16LE";
                checked = true;
            }
            else if ( first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF ) {
                charset = "UTF-16BE";
                checked = true;
            }
            else if ( first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF ) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if ( !checked ) {
                //    int len = 0;
                int loc = 0;

                while ( (read = bis.read()) != -1 ) {
                    loc++;
                    if ( read >= 0xF0 ) break;
                    if ( 0x80 <= read && read <= 0xBF ) // 单独出现BF以下的，也算是GBK
                        break;
                    if ( 0xC0 <= read && read <= 0xDF ) {
                        read = bis.read();
                        if ( 0x80 <= read && read <= 0xBF ) // 双字节 (0xC0 - 0xDF) (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else break;
                    }
                    else if ( 0xE0 <= read && read <= 0xEF ) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if ( 0x80 <= read && read <= 0xBF ) {
                            read = bis.read();
                            if ( 0x80 <= read && read <= 0xBF ) {
                                charset = "UTF-8";
                                break;
                            }
                            else break;
                        }
                        else break;
                    }
                }
                //System.out.println( loc + " " + Integer.toHexString( read ) );
            }

            bis.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return charset;
    }

}
