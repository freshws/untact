package com.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.untact.Util.Util;
import com.untact.dto.Article;


//@Controller는 초기 Project 생성 시 Spring Web을 체크 해야 사용가능
//@Controller는 브라우저로부터 통신을 받아 응답할 수 있다.
@Controller
public class UsrArticleController {
	
	private int articlesLastId;
	private List<Article> articles;
	
	//현재 날짜를 받아오는 메소드 호출
	String regDate = Util.getNowDateStr();
	
	
	public UsrArticleController() {
		
		articlesLastId = 0;
		String updateDate = regDate;
	
		articles = new ArrayList<>();
		
		articles.add(new Article(++articlesLastId, regDate, updateDate, "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, regDate, updateDate, "제목2", "내용2"));
		
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
		//http://localhost:8021/usr/article/doAdd?title=제목3&body=내용3
		public Map<String, Object> doAdd(String title, String body){
			
			String updateDate = regDate;
			
			articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));
			
			Map<String, Object> rs = new HashMap<>();
			
			rs.put("Result Code", "S-1");
			rs.put("Msg", "성공");
			rs.put("ID", articlesLastId);
			
			return rs;
			
		}


		/**
		 * 아래 메소드는 직접 당해 메소드에서 게시물을 삭제
		@RequestMapping("usr/article/doDelete")
		@ResponseBody
		//★해당 계시글이 지워져도 다음 글의 번호는 다음 번호를 부여받아야지 지워진 번호를 이어받으면 않됨
		//게시물을 지우는 메소드
		public Map<String, Object> doDelete(int id){
			
			articles.remove(id - 1);
			
			Map<String, Object> rs = new HashMap<>();
			
			rs.put("Result Code", "S-1");
			rs.put("Msg", "성공");
			rs.put("ID", articlesLastId);
			
			return rs;
			
		}
		**/

		
		//아래 메소드는 지우는 액션을 실제로 하는 메소드를 만들어 게시물 삭제
		@RequestMapping("usr/article/doDelete")
		@ResponseBody
		//★해당 계시글이 지워져도 다음 글의 번호는 다음 번호를 부여받아야지 지워진 번호를 이어받으면 않됨
		//게시물을 지우는 메소드
		//http://localhost:8021/usr/article/doDelete?id=1
		public Map<String, Object> doDelete(int id){
			
			boolean deleteArticleRs = deleteArticle(id);
			
			Map<String, Object> rs = new HashMap<>();
			
			if(deleteArticleRs == true) {
				
				rs.put("Result Code", "S-1");
				rs.put("Msg", "성공");
				
			}
			
			if(deleteArticleRs == false) {
				
				rs.put("Result Code", "F-1");
				rs.put("Msg", "게시물이 없습니다");
				
				
			}
			
			rs.put("ID", id);
			
			return rs;
			
		}
		
		private boolean deleteArticle(int id) {

			for(Article article : articles) {
				
				if(article.getId() == id) {
					
					articles.remove(article);
					
					return true;
					
				}
				
			}
			
			return false;
		}
		
		@RequestMapping("usr/article/doModify")
		@ResponseBody
		//브라우져 창에 아래와 같이 입력 하면 됨
		//http://localhost:8021/usr/article/doModify?id=1&title=제목3&body=내용3
		public Map<String, Object> doModify(int id, String title, String body){
			
			Article selArticle = null;
			
			for(Article article : articles) {
				
				if (article.getId() == id) {
					
					selArticle = article;
					
					break;
					
				}
				
				
			}
			
			Map<String, Object> rs = new HashMap<>();
			
			if (selArticle == null) {
				
				rs.put("Result Code", "F-1");
				rs.put("Msg", String.format("%d번 게시물은 없습니다.", id));
				
			} else {
				
				//setUpdateDate 메소드의 파라미터로 regDate를 넣으면 처음 생성할 때 시간이 입력된다.
				//별개 객체를 생성하는 별도 Util.getNowDateStr() 메소드를 매개변수로 넣어줘야함.
				selArticle.setUpdateDate(Util.getNowDateStr());
				selArticle.setTitle(title);
				selArticle.setBody(body);
				
				rs.put("Result Code", "S-1");
				rs.put("Msg", String.format("%d번 게시물이 수정되었습니다.", id));
				
			}
			
			
			rs.put("ID", id);
			
			return rs;
			
		}

}
