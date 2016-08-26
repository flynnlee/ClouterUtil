package com.clouter.clouterutil;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static boolean isEmpty(String str){
		return str == null || str.isEmpty();
	}

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
}
