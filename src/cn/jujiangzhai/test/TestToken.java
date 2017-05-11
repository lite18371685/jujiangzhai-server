package cn.jujiangzhai.test;
import org.junit.Test;

import cn.jujiangzhai.dao.ITokenDao;
import cn.jujiangzhai.dao.impl.jdbc.TokenDao;
public class TestToken {

	
	ITokenDao dao = new TokenDao();
	
	@Test
	public void testInsert(){
		
		System.out.println(dao.insert("ssss"));;
	}
	
	@Test
	public void testDelete(){
		
//		dao.delete("e0480f0f-4c7a-4217-8ecf-50b70eac363a-1494510508222");
		
		dao.deleteById("edcd74");
	}
	
	@Test
	public void testQuery(){
		
		System.out.println(dao.queryById("321e93"));
		
		System.out.println(dao.queryByToken("1f5932a4-7d54-45ff-910d-bff75aaac556-1492129729977"));
	}
}
