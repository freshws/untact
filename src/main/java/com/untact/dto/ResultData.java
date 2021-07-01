package com.untact.dto;

import java.util.Map;
import com.untact.Util.Util;
import lombok.Data;

@Data
public class ResultData {

	private String resultCode;
	private String msg;
	private Map<String, Object> body;

	// "Object... args" 매개변수의 숫자나 형식에 상관없이 아무것나 받을 수 있는 구문
	public ResultData(String resultCode, String msg, Object... args) {

		this.resultCode = resultCode;
		this.msg = msg;
		this.body = Util.mapOf(args);

	}

	public boolean isSuccess() {

		// startsWith("S-")는 S-로 시작되는지 여부를 확인해서 리턴해줌
		return resultCode.startsWith("S-");

	}

	public boolean isFail() {

		return isSuccess() == false ;

	}

}
