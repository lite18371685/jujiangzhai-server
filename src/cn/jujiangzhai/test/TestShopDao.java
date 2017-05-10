package cn.jujiangzhai.test;

import java.util.List;


import org.junit.Test;

import cn.jujiangzhai.dao.IShopDao;
import cn.jujiangzhai.dao.impl.jdbc.ShopDao;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.Utils;

public class TestShopDao {

	IShopDao dao = new ShopDao();
	
	@Test
	public void testInsert(){
		
		Shop shop = new Shop(Utils.getUUID(), "testInsert", "空连接", "温州", "龙湾", "嗯,这是描述", "皮革");
		
		System.out.println(dao.insert(shop));
	}
	
	@Test 
	public void testQueryById(){
		
		String id = "73929d";
		
		System.out.println(dao.queryById(id));
	}
	
	@Test
	public void testDelete(){
		
		String id ="73929d";
		
		System.out.println(dao.delete(id));
	}
	
	@Test
	public void testQueryCity(){
		
		String city = "南京市";
		
		System.out.println(dao.queryCity(city));
	}
	
	@Test
	public void testQueryType(){
		
		List<Shop> list = dao.queryByType("文玩");
		
		for(Shop h:list){
			System.out.println(h);
		}
	}
	
	@Test 
	public void testQueryName(){
		
		Shop h = dao.queryByName("观止");
		
		System.out.println(h);
	}
	
	@Test
	public void testQueryAll(){
		
		List<Shop> queryAll = dao.queryAll();
		
		for(Shop h : queryAll){
			System.out.println(h);
		}
	}
	
	@Test
	public void testUpdate(){
		
		Shop h = new Shop("41f707", "改名了", "baidu.com", "beijing", null, null, "陶瓷");
		dao.update(h);
		
	}
	

}
