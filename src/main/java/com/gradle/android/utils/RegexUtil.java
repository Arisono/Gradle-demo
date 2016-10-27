package com.gradle.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**正则表达式工具类
 * Created by Arison on 2016/3/25.
 */
public class RegexUtil {
    
	public static void main(String[] args) {
		String email="784602719@q.com";
		System.out.println(checkEmail(email));
		String number="-1";
		System.out.println(checkDigit(number));//整数
		System.out.println(checkPositiveDigit(number));//正整数
		String blank="nihao jia" ;
		System.out.println(checkBlankSpace(blank));
	}

    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }


    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex, idCard);
    }


    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[3458]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }


    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

   /**
     * @desc:数字
     * @author：Arison on 2016/11/22
     */
    public static boolean checkDigit(String digit) {
        String regex = "^-?[1-9]\\d*$";
        return Pattern.matches(regex, digit);
    }
    
    
    /**
     * @desc:正整数
     * @param digit
     * @author Arison 
     */
    public static boolean checkPositiveDigit(String digit){
    	 String regex = "^[1-9]\\d*$";
         return Pattern.matches(regex, digit);
    }

    /**
      * @desc:小数
      * @author：Arison on 2016/11/22
      */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
      * @desc:空行
      * @author：Arison on 2016/11/22
      */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
      * @desc:中文
      * @author：Arison on 2016/11/22
      */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

   
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }


    public static boolean checkURL(String url) {
//        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        String regex = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?";
        return Pattern.matches(regex, url);
    }

    public static String getDomain(String url) {
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }


    public static boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }
}
