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
	String userId = "321e93";
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
	
	@Test
	public void testCollect(){
		String userId = "4ae335";
		String itemId = "asczxc";
		
		dao.collect(userId, itemId);
		
	}
	
	@Test
	public void testFollowUp(){
		String userId = "4ae335";
		String shopId = "azxc1212c";
		
		dao.followUp(userId, shopId);
	}
	
	@Test
	public void testUpdateCity(){
		String city = "南京";
		String userId = "4ae335";
		
		dao.updateCity(userId, city);
	}
	
	@Test
	public void testUpdateNickName(){

		String nickName = "wales";
		dao.updateNickName(userId, nickName);
	}
	
	@Test
	public void testCancelCollection(){
		String itemId = "f8212e";
		
		dao.cancelCollect(userId, itemId);
	}
	
	@Test
	public void testCancelFollowUp(){
		String itemId = "788dc9";
		dao.cancelFollowUp(userId, itemId);
	}

	@Test 
	public void testChangePwd(){
		
		System.out.println(dao.changePwd("eadaac", "qqqqzzzz", "qqqqzzzz1"));
	}
	
	@Test
	public void testChangeNickName(){
		
	}
}
