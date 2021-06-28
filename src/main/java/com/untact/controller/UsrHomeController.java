package com.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//@Controller는 초기 Project 생성 시 Spring Web을 체크 해야 사용가능
//@Controller는 브라우저로부터 통신을 받아 응답할 수 있다.
@Controller
public class UsrHomeController {
	
	//@RequestMapping은 "/usr/home/main" 요청과 showMain() 메소드를 매칭시켜주는 것
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		
		return "안녕";
		
	}
	

}
