package cn.jujiangzhai.dao;

import java.util.List;

import cn.jujiangzhai.entity.Article;

public interface IArticleDao {
	public boolean insert(Article a);
	
	public Article queryById(String id);
	
	public List<Article> queryAll();
	
	public boolean update(Article a);
	
	public boolean delete (String id);
	
}
