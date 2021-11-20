package org.caseor.common.core.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {

    public static final String UNDERLINE = "_";
    public static final String COMMA = ",";


    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    public static final Joiner COMMA_JOINER = Joiner.on(",");
    public static final Splitter COMMA_SPLITTER = Splitter.on(",");
    public static final Joiner COLON_JOINER = Joiner.on(":");
    public static final Splitter COLON_SPLITTER = Splitter.on(":");


    /**
     * 下划线转驼峰
     */
    public static String underlineToHump(String str) {
        Matcher matcher = LINE_PATTERN.matcher(str.toLowerCase());
        StringBuilder stringBuffer = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * 驼峰转下划线
     */
    public static String humpToUnderline(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuilder stringBuffer = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, UNDERLINE + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * 获取参数不为空值
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * * 判断一个字符串是否为空串
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return ObjectUtil.isNull(str) || "".equals(str.trim());
    }


    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 判断是否为空，并且不是空白字符
     * @param str 要判断的value
     * @return 结果
     */
    public static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

}
