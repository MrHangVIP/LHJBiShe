package com.lhj.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {

	public static final int TOKEN_LEN=0x10;
	
	/**
	 * 更具mac获取token
	 * @param Mac
	 * @return
	 */
	public static String productToken(String Mac){
		
		return Mac+getRandomCharAndNumr(TOKEN_LEN);
	}
	
	
	/** 
	 * 获取随机字母数字组合 
	 *  
	 * @param length 
	 *            字符串长�? 
	 * @return 
	 */  
	public static String getRandomCharAndNumr(Integer length) {  
	    String str = "";  
	    Random random = new Random();  
	    for (int i = 0; i < length; i++) {  
	        boolean b = random.nextBoolean();  
	        if (b) { // 字符�?  
	            // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母  
	            str += (char) (65 + random.nextInt(26));// 取得大写字母  
	        } else { // 数字  
	            str += String.valueOf(random.nextInt(10));  
	        }  
	    }  
	    return str;  
	}  
	
	/**
	 * 验证当前字符串是否是邮箱
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		String reg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return checkPattern(reg, str);
	}
	
	/**
	 * 验证当前字符传是否满足当前的正则表达式
	 *
	 * @param reg 正则规则
	 * @param str 验证的字符串
	 * @return
	 */
	public static boolean checkPattern(String reg, String str) {
		if (isBlank(str)) {
			return false;
		}
		if (isBlank(reg)) {
			return true;
		}
		try {
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(str);
			return m.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断当前字符串是否是空或者空字符串
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}
}
