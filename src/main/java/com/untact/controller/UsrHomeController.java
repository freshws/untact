package com.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.untact.dto.Article;


//@Controller는 초기 Project 생성 시 Spring Web을 체크 해야 사용가능
//@Controller는 브라우저로부터 통신을 받아 응답할 수 있다.
@Controller
public class UsrHomeController {
	
	
	//@RequestMapping은 "/usr/home/main" 요청과 showMain() 메소드를 매칭시켜주는 것
	@RequestMapping("/usr/home/detail")
	//@ResponseBody는 이 함수를 return한 값이 브라우저에 전달 된다는 것
	@ResponseBody
	public Article showDetail(int id) {
		
		Article article = new Article(1, "2020-12-12", "제목1", "내용1");
		
		return article;
		
	}
	

	//@RequestMapping은 "/usr/home/main" 요청과 showMain() 메소드를 매칭시켜주는 것
	@RequestMapping("/usr/home/list")
	//@ResponseBody는 이 함수를 return한 값이 브라우저에 전달 된다는 것
	@ResponseBody
	public String showList() {
		
		return "ㅋㅋㅋ";
		
	}
	
	
}
