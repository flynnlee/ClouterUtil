package com.clouter.clouterutil;

import com.clouter.clouterutil.math.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static boolean isEmpty(String str){
		return str == null || str.isEmpty();
	}

	/**
	 * 按指定字符串拆分字符串
	 * 不支持正则表达式，提升效率
	 * 若以分隔符结尾则忽略最后一个分隔符
	 * @param str - 原字符串
	 * @param delim - 分隔符
	 * @return
	 */
	public static String[] split(String str, String delim){
		if(str.isEmpty()) return new String[0];
		int lastIndexOfDelim = str.lastIndexOf(delim);
		int delimLength = delim.length();
		if(lastIndexOfDelim == (str.length() - delimLength)){
			str = str.substring(0, lastIndexOfDelim);
		}
		List<String> list = new ArrayList<>();
		int fromIndex = 0;
		while(true){
			int index = str.indexOf(delim, fromIndex);
			if(index < 0){
				list.add(str.substring(fromIndex));
				break;
			}
			list.add(str.substring(fromIndex, index));
			fromIndex = index + delimLength;
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 自定义格式输出文本{0} {1}
	 * @param s - 字符串模板
	 * @param objects - 要填充的参数列表
	 * @return - 结果文本
	 */
	public static String format(String s,Object ...objects){
		if(objects!=null&&objects.length>0){
			StringBuilder sb = new StringBuilder();
			int i=0;
			sb.append("{").append(i).append("}");
			int j = s.indexOf(sb.toString());
			while(j>=0){
				if(i<objects.length){
					s=s.replace(sb.toString(), objects[i]==null?"":objects[i].toString());
				}
				i++;
				sb = new StringBuilder();
				sb.append("{").append(i).append("}");
				j = s.indexOf(sb.toString());
			}
		}
		return s;
	}

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstChar(String str){
		if(str.isEmpty()) return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String lowerCaseFirstChar(String str){
		if(str.isEmpty()) return str;
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 判断字符串str是否以suffix结尾
	 * @param str
	 * @param suffix
	 * @return
	 */
	public static boolean isSuffix(String str, String suffix){
		if(str.lastIndexOf(suffix) < 0) return false;
		return str.substring(str.length() - suffix.length()).equals(suffix);
	}

	public static boolean isPrefix(String str, String prefix){
		if(str.lastIndexOf(prefix) < 0) return false;
		return str.substring(0, prefix.length()).equals(prefix);
	}

	private static final String RANDOM_STR="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 返回一个指定长度的随机字符串(只包括数字大小写英文字母)
	 * @param len
	 * @return
	 */
	public static String randomStr(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, strL = RANDOM_STR.length(); i < len; i++) {
			int index = MathUtil.randomInt(strL);
			sb.append(RANDOM_STR.charAt(index));
		}
		return sb.toString();
	}
}
