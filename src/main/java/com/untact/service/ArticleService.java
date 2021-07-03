package com.untact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.Util.Util;
import com.untact.dao.ArticleDao;
import com.untact.dto.Article;
import com.untact.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public Article getArticle(int id) {

		return articleDao.getArticle(id);

	}

	public ResultData addAticle(String title, String body) {

		int id = articleDao.addAticle(title, body);

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {

		boolean rs = articleDao.deleteArticle(id);

		if (rs == true) {

			return new ResultData("S-1", "삭제하였습니다.", "id", id);
		} else {

			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.", "id", id);
		}

	}

	public ResultData modifyArticle(int id, String title, String body) {
		
		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {

		return articleDao.getArticles(searchKeywordType, searchKeyword);
	}
}