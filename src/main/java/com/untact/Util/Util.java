package com.untact.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
	
	//일렬로 입력받는 값을 key와 value로 나누어 저장하는 메소드
	//메소드의 매개변수 "Object... args" 는 매개변수의 숫자에 관계없이 인자가 들어오면 배열로 만들어줌
	public static Map<String, Object> mapOf(Object... args){
		
		if(args.length % 2 !=0) {
			
			throw new IllegalArgumentException("인자를 짝수개로 입력 해주세요.");
			
		}
		
		int size = args.length / 2;
		
		Map<String, Object> map = new LinkedHashMap();
		
		for(int i=0; i < size; i++) {
			
			int keyIndex = i * 2;
			int valueIndex = keyIndex +1;
			
			String key;
			Object value;
			
			try { 
				
				key = (String)args[keyIndex];
				
			} catch (ClassCastException e) {
				
				throw new IllegalArgumentException("키는 String으로 입력해야 합니다." + e.getMessage());
				
			}
			
			value = args[valueIndex];
			
			map.put(key, value);
			
		}
		
		return map;
		
	}
	

}
