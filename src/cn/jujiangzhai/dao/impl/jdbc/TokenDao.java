package cn.jujiangzhai.dao.impl.jdbc;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.jujiangzhai.dao.ITokenDao;
import cn.jujiangzhai.util.JdbcUtils;
import cn.jujiangzhai.util.TokenProcessor;

public class TokenDao implements ITokenDao {

	private QueryRunner qr = JdbcUtils.getQueryRunner();

	/**
	 *  向tokens表插入一条记录，由传入的用户id和随机生成的token组成，并返回token值
	 *  不考虑多端登陆，因此不需要一个userId拥有多个token，所以插入前删除已经存在的token
	 */
	@Override
	public String insert(String id) {
		
		if(id == null || "".equals(id.trim()))
			return null;
		
		// 删除表中可能存在的该id所拥有的token
		deleteById(id);
		
		
		String tokenStr = TokenProcessor.makeToken();
		
		String sql = "insert into tokens values(?,?);";
		
		try {
			qr.update(sql, id, tokenStr);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return tokenStr;
	}

	/**
	 * 删除指定token记录
	 */
	@Override
	public void delete(String token) {

		String sql = "delete from tokens where tokenStr=?;";
		
		try {
			qr.update(sql, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 删除指定用户id在表中 的所有token记录
	 */
	@Override
	public void deleteById(String id) {

		String sql = "delete from tokens where userId=?;";
		
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String queryById(String id) {
		
		String sql = "select tokenStr from tokens where userId=?;";
		
		String res = null;
		try {
			res = qr.query(sql, new ScalarHandler<String>(), id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return res;
		
		
	}

	@Override
	public String queryByToken(String token) {
		
		String sql = "select userId from tokens where tokenStr=?;";
		
		String res = null;
		try {
			res = qr.query(sql, new ScalarHandler<String>(), token);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return res;
		
	}

}
