package cn.jujiangzhai.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.jujiangzhai.dao.IUserDao;
import cn.jujiangzhai.dao.impl.jdbc.UserDao;
import cn.jujiangzhai.entity.User;
import cn.jujiangzhai.util.JdbcUtils;
import cn.jujiangzhai.util.Utils;

public class TestUserDao {

	
	private IUserDao dao = new UserDao();
	
	@Test
	public void test(){
		
		QueryRunner qr = JdbcUtils.getQueryRunner();
		
		String sql = "select collection from users where id=?;";
		
		try {
			String query = qr.query(sql, new ScalarHandler<String>(), "321e93");
			System.out.println(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testView(){
		dao.view("edcd74", "1231");
	}
	
	@Test
	public void testQueryById(){
		User queryById = dao.queryById("321e93");
		System.out.println(queryById);
		
	}
	
	@Test 
	public void testInsert(){
		User user = new User();
		user.setId(Utils.getUUID());
		user.setUserName("lite");
		user.setUserPassword("zxczxc");
		
		boolean res = dao.insert(user);
		System.out.println(res);
	}
	
	@Test
	public void testQueryByPhone(){
		User user = dao.queryByPhone("15077868750");
		System.out.println(user);
		
	}
	
	@Test
	public void testQueryByUserName(){
		String userName = "123456";
		System.out.println(dao.queryByUserName(userName));
	}
	

}
