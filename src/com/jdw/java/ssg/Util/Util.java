package com.jdw.java.ssg.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	//클래스로 만들어서 가져다가 쓰기위해 static String으로 만들고 함수화해야한다(void아님)
		public static String getNowTimeStr() {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			return sdf1.format(now);
		}
	}
