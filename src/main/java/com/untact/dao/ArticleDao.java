package com.untact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.untact.Util.Util;
import com.untact.dto.Article;
import com.untact.dto.ResultData;

@Component
public class ArticleDao {

	private int articlesLastId;
	private List<Article> articles;

	public ArticleDao() {
		// 멤버변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();

		// 게시물 2개 생성
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목2", "내용2"));
	}

	public Article getArticle(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	
	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {

		if (searchKeyword == null) {

			return articles;
		}

		List<Article> filtered = new ArrayList<>();

		// contains는 매개변수로 들어온 키워드가 있는지 확인해서 True, False 리턴
		for (Article article : articles) {

			boolean contains = false;

			if (searchKeywordType.equals("title")) {

				contains = article.getTitle().contains(searchKeyword);
			}

			else if (searchKeywordType.equals("body")) {

				contains = article.getBody().contains(searchKeyword);

			}

			else {

				contains = article.getTitle().contains(searchKeyword);

				if (contains == false) {

					contains = article.getBody().contains(searchKeyword);
				}

			}
			if (contains) {
				filtered.add(article);
			}

		}

		return filtered;

	}

	public int addAticle(String title, String body) {
		int id = ++articlesLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		articles.add(new Article(id, regDate, updateDate, title, body));
		
		return id;
	}

	public boolean deleteArticle(int id) {
		
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				
				return true;
			}
		}
		return false;
	}

	public void modifyArticle(int id, String title, String body) {
		
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());
	}
	
}
