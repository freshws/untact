package com.untact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.untact.dto.Article;
import com.untact.dto.ResultData;
import com.untact.service.ArticleService;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {

		Article article = articleService.getArticle(id);

		return article;

	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(String searchKeywordType, String searchKeyword) {

		// 아래와 같이 하지 않고 매개변수에 @RequestParam 이란 어노테이션을 쓰면됨

		if (searchKeywordType != null) {

			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {

			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {

			searchKeyword = null;

		}

		if (searchKeyword != null) {

			searchKeyword = searchKeyword.trim();

		}

		return articleService.getArticles(searchKeywordType, searchKeyword);

	}

	// 아래 매소드의 매개변수 중 title와 body는 String 타입 즉 클래스 타입이다.
	// 만약 int 같은 원시 타입의 경우 초기에 값을 입력해주지 않으면 에러 발생
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {

		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}

		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}

		return articleService.addAticle(title, body);

	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}

		return articleService.deleteArticle(id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}

		return articleService.modifyArticle(id, title, body);

	}

}
