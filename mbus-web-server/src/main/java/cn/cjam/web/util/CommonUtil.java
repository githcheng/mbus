package cn.cjam.web.util;

import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cheng on 2016/2/26.
 */
public class CommonUtil {
    public static boolean isPhone(String phone){

        if (StringUtils.isEmpty(phone) || phone.length() != 11){
            return false;
        }
        String regExp = "^[1][3-9][0-9]{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if (m.find()){
            return true;
        }
        return false;
    }


    public static String generatePictureCode(){
        int length = 6;
        String authCode = "";
        Random random = new Random();
        for (int i=0;i<length;i++){
            int s = random.nextInt(9);
            authCode += s;
        }
        return authCode;
    }

    public static String generateSmsCode(){
        int length = 4;
        String authCode = "";
        Random random = new Random();
        for (int i=0;i<length;i++){
            int s = random.nextInt(9);
            authCode += s;
        }
        return authCode;
    }

    public static void main(String args[]){
        System.out.println("isPhone: "+isPhone("133456789101"));
    }
}
