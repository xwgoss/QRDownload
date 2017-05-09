package com.xwgoss.util;

import java.util.UUID;

public class StringUtil {
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
