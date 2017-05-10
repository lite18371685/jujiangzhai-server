package cn.jujiangzhai.test;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.jujiangzhai.dao.ICraftDao;
import cn.jujiangzhai.dao.impl.jdbc.CraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.JdbcUtils;
import cn.jujiangzhai.util.Utils;

public class TestCraftDao {

	QueryRunner qr = JdbcUtils.getQueryRunner();
	
	ICraftDao dao = new CraftDao();
	
	@Test
	public void test1(){
		
	
		
		String sql = "select * from shops ";
		
		try {
			List<Shop> list = qr.query(sql, new BeanListHandler<Shop>(Shop.class));
			
			for(Shop h : list){
				System.out.println(h);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2(){
		
		String sql = "select * from handicrafts where name=?;";
		
		try {
			Handicraft h = qr.query(sql, new BeanHandler<Handicraft>(Handicraft.class), "testInsert1");
			System.out.println(h);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testInsert(){
		
		Handicraft h = new Handicraft(Utils.getUUID(), "testInsert1", "其他", "没什么好写的描述", "南京", "没写的地址", "2017-4-25 22:33", 1, 22, false, "helloCipher!", 11.22, "test Shop", "empty link");
		dao.insert(h);	
	}
	
	@Test
	public void testQueryById(){
		
		Handicraft h = dao.queryById("ed7044");
		System.out.println(h);
	}
	

	
	@Test
	public void testQueryAll(){
		List<Handicraft> list = dao.queryAll();
		for(Handicraft h:list){
			System.out.println(h.toString());
		}
	}
	
	
	@Test
	public void testRecommended(){
		
		List<Handicraft> list = dao.queryRecommend();
		
		for(Handicraft h:list){
			System.out.println(h.toString());
		}
		
	}
	
	@Test
	public void testQueryCity(){
		
		List<Handicraft> list = dao.queryCity("南京市");
		
		for(Handicraft h:list){
			System.out.println(h.toString());
		}
	}
	
	@Test
	public void testQueryType(){
		
		List<Handicraft> list = dao.queryType("陶瓷");
		
		for(Handicraft h:list){
			System.out.println(h.toString());
		}
	}
	
	@Test
	public void testQueryByShopName(){
		
		List<Handicraft> list = dao.queryByShopName("吹糖人小铺");
		
		for(Handicraft h:list){
			System.out.println(h.toString());
		}
	}
	
	@Test
	public void testUpdate(){
		
		Handicraft h = new Handicraft("95b420", "修改后的testInsert1", "玉石", "描述", "南京", "没写的地址", "17-4-25 22:33", 1, 22, false, "helloCipher!", 11.22, "test Shop", "empty link");
		
		dao.update(h);
	}
	
	@Test
	public void testView(){
		
		dao.viewed("f8212e");
	}
	
}
