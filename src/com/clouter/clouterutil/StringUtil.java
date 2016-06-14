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
		List<String> list = new ArrayList<String>();
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
}
