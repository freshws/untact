package com.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.untact.dto.Article;


//@Controller는 초기 Project 생성 시 Spring Web을 체크 해야 사용가능
//@Controller는 브라우저로부터 통신을 받아 응답할 수 있다.
@Controller
public class UsrArticleController {
	
	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		
		articlesLastId = 0;
	
		articles = new ArrayList<>();
		
		articles.add(new Article(++articlesLastId, "2020-12-12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-12", "제목2", "내용2"));
		
	}
	

	//@RequestMapping은 "/usr/home/main" 요청과 showMain() 메소드를 매칭시켜주는 것
		@RequestMapping("/usr/article/detail")
		//@ResponseBody는 이 함수를 return한 값이 브라우저에 전달 된다는 것
		@ResponseBody
		public Article showDetail(int id) {
			
			return articles.get(id - 1);
			
		}
		

		//@RequestMapping은 "/usr/home/main" 요청과 showMain() 메소드를 매칭시켜주는 것
		@RequestMapping("/usr/article/list")
		//@ResponseBody는 이 함수를 return한 값이 브라우저에 전달 된다는 것
		@ResponseBody
		public List<Article> showList() {
			
			//전체 리스트가 나오도록 해줌
			return articles;
			
		}
		
		@RequestMapping("usr/article/doAdd")
		@ResponseBody
		//브라우져 창에 아래와 같이 입력 하면 됨
		//http://localhost:8021/usr/article/doAdd?regDate=20210629&title=제목3&body=내용3
		public Map<String, Object> doAdd(String regDate, String title, String body){
			
			articles.add(new Article(++articlesLastId, "regDate", "title", "body"));
			
			Map<String, Object> rs = new HashMap<>();
			
			rs.put("Result Code", "S-1");
			rs.put("Msg", "성공");
			rs.put("ID", articlesLastId);
			
			return rs;
			
		}
	

}
