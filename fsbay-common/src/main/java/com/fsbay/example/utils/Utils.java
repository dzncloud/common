/**
 * 
 */
package com.fsbay.example.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author dengzhineng
 * @date 2016年8月28日
 */
public class Utils {
	public static String getCnfPath(String pkg) {
		String classPth = Utils.class.getClassLoader().getResource("")
				.getPath();
		classPth = classPth.replaceFirst("target/classes/",
				"src/main/resources");
		if (StringUtils.isNotBlank(pkg)) {
			classPth = classPth + pkg;
		}
		return classPth;
	}
}
