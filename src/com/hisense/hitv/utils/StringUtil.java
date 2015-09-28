package com.hisense.hitv.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 * @author wangheping
 */
public class StringUtil {

    private StringUtil() {

    }

    /**
     * @param path 路径
     * @return String
     */
    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf("\\") + 1, path.length());
    }

    /**
     * @param inputStr 源字符串
     * @return String
     */
    public String null2blank(String inputStr) {
        return inputStr == null ? "" : inputStr;
    }

    /**
     * @param chineseStr 汉字
     * @return String
     */
    public static String chineseToUTF8(String chineseStr) {
        String utf8String = null;
        if (null != chineseStr && !chineseStr.equals("")) {
            try {
                byte[] stringBytesISO = chineseStr.getBytes("ISO-8859-1");
                utf8String = new String(stringBytesISO, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // As we can't translate just send back the best guess.
                System.out.println("UnsupportedEncodingException is: "
                    + e.getMessage());
                utf8String = chineseStr;
            }
        } else {
            utf8String = chineseStr;
        }
        return utf8String;
    }

    /**
     * @param str 源字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {
        if (str.equals("") || str == null) {
            return true;
        }
        return false;
    }

    /**
     * 验证email格式
     * @param email 邮箱
     * @return 结果码
     */
    public static boolean checkEmailPattern(String email) {
        if (email == null || email.length() < 5 || email.length() > 40) {
            return false;
        }

        Pattern pattern1 =
            Pattern.compile("^([a-z]|[A-Z]|[0-9])[0-9a-zA-Z_@\\-\\.]+$");
        Matcher mat1 = pattern1.matcher(email);
        if (!mat1.find()) {
            return false;
        }
        Pattern pattern =
            Pattern.compile("^[\\-\\.\\w]+@[\\.\\-\\w]+(\\.\\w+)+$");
        Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            return false;
        }
        Pattern pattern3 = Pattern.compile("[a-zA-Z]+$");
        Matcher mat3 = pattern3.matcher(email);
        if (!mat3.find()) {
            return false;
        }
        return true;
    }
}
