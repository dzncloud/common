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
	public static String getMainResPath(String pkg) {
		String bPath = getBasePath();
		String tPath = bPath + "src/main/resources";
		if (StringUtils.isNotBlank(pkg)) {
			tPath = tPath + pkg;
		}
		return tPath;
	}

	public static String getTestResPath(String pkg) {
		String bPath = getBasePath();
		String tPath = bPath + "src/test/resources";
		if (StringUtils.isNotBlank(pkg)) {
			tPath = tPath + pkg;
		}
		return tPath;
	}

	public static String getBasePath() {
		String bPth = Utils.class.getClassLoader().getResource("").getPath();
		bPth = bPth.replaceFirst("target/classes/", "");
		return bPth;
	}
}
