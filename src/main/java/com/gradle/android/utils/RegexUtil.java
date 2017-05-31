package com.gradle.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.android.retrofit.demo.OkhttpUtils;

/**
 * 正则表达式工具类 Created by Arison on 2016/3/25.
 * 这里记录平时积累的正则表达式
 */
@SuppressWarnings("unused")
public class RegexUtil {

	/**
	 * 正实数(限制位数)
	 */
	private static String regex1 = "^[0-9]+(.[0-9]{1,2})?$";
	/**
	 * 正实数(不限制位数)
	 */
	private static String regex2 = "([1-9]\\d*|0)(\\.\\d+)?";
	/**
	 * 只能输入大小写英文字符，数字，中文（限制长度)
	 */
	private static String regex3 = "^[\u4e00-\u9fa5_a-zA-Z0-9_\n]{0,10}$";
	/**
	 * 只能输入大小写英文字符，数字，中文（不限制长度)
	 */
	private static String regex3_1 = "^[\u4e00-\u9fa5_a-zA-Z0-9_\n]+$";
	/**
	 * 字符前，字符后限制空格
	 */
	private static String regex4 = "^\\s+.*\\s+$";
	/**
	 * 字符前限制空格
	 */
	private static String regex5 = "^\\s+.*$";
	/**
	 * 字符后限制空格
	 */
	private static String regex6 = "^.*\\s+$";

	/**
	 * 只能输入大小写英文，数字(不限制长度)
	 */
	private static String regex7 = "^[A-Za-z0-9]+$";

	/**
	 * 只能输入大小写英文，数字(限制长度)
	 */
	private static String regex8 = "^[A-Za-z0-9]{0,10}+$";

	/**
	 * 验证邮箱
	 */
	private static String regex9 = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	
	/**
	 * 全字符验证
	 */
	private static String regex10="^.{1,300}$";
	
	/**
	 * 支持中，英，数字，下划线，括号，小数点
	 */
	private static String regex11="^[\u4e00-\u9fa5_a-zA-Z0-9.()]{0,30}$";

	
	
	
	public static void main(String[] args) {
		
	  OkhttpUtils.println(checkRegex("(123ijkjfd_821.)",regex11));	
	
    	
	}

	
	public static boolean checkRegex(String text, String regex) {
		return Pattern.matches(regex, text);
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 限制输入表情符号
	 * @param string
	 * @return
	 */
	public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }
	
	
	
	
	/**
	 * 以下方法可以作废
	 *
	 */
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
	public static boolean checkPositiveDigit(String digit) {
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
		String regex = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?";
		return Pattern.matches(regex, url);
	}

	public static String getDomain(String url) {
		Pattern p = Pattern.compile(
				"(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",
				Pattern.CASE_INSENSITIVE);
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
