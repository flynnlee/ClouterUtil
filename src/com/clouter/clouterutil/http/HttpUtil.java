package com.clouter.clouterutil.http;

/**
 * Created by flynn on 2016/9/4 0004.
 */
public class HttpUtil {
	public static String getLastUrl(String url, int deep){
		String[] values = url.split("/");
		StringBuilder builder = new StringBuilder();
		for(int i = values.length - deep; i < values.length; i++){
			builder.append(values[i]);
			if(i != values.length - 1){
				builder.append("/");
			}
		}
		return builder.toString();
	}
}
