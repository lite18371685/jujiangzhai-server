package cn.jujiangzhai.dao.impl.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.jujiangzhai.dao.IArticleDao;
import cn.jujiangzhai.entity.Article;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.JdbcUtils;

public class ArticleDao implements IArticleDao {

	private QueryRunner qr = JdbcUtils.getQueryRunner();

	@Override
	public boolean insert(Article h) {

		// 保证传入的Article对象不为空,且主键id不为空.
		if (h == null) {
			return false;
		} else if (h.getId() == null || "".equals(h.getId().trim())) {
			return false;
		}

		// 共有4个占位符
		String sql = "insert into articles values(?,?,?,?);";

		try {
			qr.update(sql, h.getId(), h.getTitle(), h.getType(), h.getDescription());

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Article queryById(String id) {

		if (id == null || "".equals(id.trim())) {
			return null;
		}

		String sql = "select * from articles where id=?;";

		Article h = null;

		try {
			h = qr.query(sql, new BeanHandler<Article>(Article.class), id);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return h;

	}

	@Override
	public List<Article> queryAll() {

		String sql = "select * from articles;";

		List<Article> list = null;

		try {
			list = qr.query(sql, new BeanListHandler<Article>(Article.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public boolean update(Article h) {

		// 保证传入的Shop对象不为空,且主键id不为空.
		if (h == null) {
			return false;
		} else if (h.getId() == null || "".equals(h.getId().trim())) {
			return false;
		}

		String sql = "update articles set title=?, type=?, description=? where id=?;";

		try {
			qr.update(sql, h.getTitle(), h.getType(), h.getDescription(), h.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(String id) {

		if (id == null || "".equals(id.trim())) {
			return false;
		}

		String sql = "delete from articles where id=?;";

		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
