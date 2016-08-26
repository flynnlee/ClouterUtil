package com.clouter.clouterutil.clazz;

import com.clouter.clouterutil.StringUtil;

public final class ClassUtil {
	public static String getClassFullName(Object object, int packageDeep){
		return getClassFullName(object.getClass(), packageDeep);
	}

	public static String getClassFullName(Class<?> clazz, int packageDeep){
		String str = clazz.getName();
		if(packageDeep == -1){
			return str;
		}
		String[] vars = StringUtil.split(str, ".");
		packageDeep = Math.min(vars.length - 1, packageDeep);
		String result = vars[vars.length - 1];
		for(int i = 0; i < packageDeep; i++){
			result = vars[vars.length - i - 2] + "." + result;
		}
		return result;
	}
}
