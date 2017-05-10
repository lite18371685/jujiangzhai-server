package cn.jujiangzhai.util;



import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;




/**
 *  JDBC工具类
 *  1. 初始化C3P0连接池
 *  2. 创建DbUtils核心工具类对象
 * @author Wales
 *
 */
public class JdbcUtils {

	/**
	 * 1. 初始化C3P0连接池
	 */
	private static DataSource dataSource;
	
	static{
		dataSource = new ComboPooledDataSource();
	}
	
	public static QueryRunner getQueryRunner(){
		
		// 创建QueryRunner对象,传入连接池对象
		// 在创建QueryRunner对象的时候,传入了数据源对象,那么在使用方法的时候,就不需要传入连接对象
		// 会自动从数据源中获取连接,也不需要关闭连接了.
		return new QueryRunner(dataSource);
		
	}
	
}
