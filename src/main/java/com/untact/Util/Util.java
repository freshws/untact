package com.untact.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	//현재 날짜를 받아오는 메소드
	public static String getNowDateStr() {
		
		//format1은 yyyy-MM-dd HH:mm:ss 이런 형태를 같는다는 것
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//현재 날짜를 받아옴
		Date time = new Date();
		//현재 날짜를 format1 형태로 만들어 주는 것
		return format1.format(time);
		
	}

}
