package com.mi.util;

public class StringUtil {
	public static boolean checkNull(String ... strs) {
		if (strs==null||strs.length<=0) {
			return true;
		}
		for (String str:strs) {
			if (str==null||str.equals("")) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkNull(String author, Integer tid, String title, String content) {
		return false;
	}

	public static boolean checkNull(Integer tid, String tname) {
		return false;
	}

	public static boolean checkNull(Integer tid) {
		return false;
	}

}
