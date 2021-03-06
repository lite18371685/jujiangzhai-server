package cn.jujiangzhai.dao;

import java.util.List;

import cn.jujiangzhai.entity.*;

public interface IShopDao {

	public boolean insert(Shop shop);
	
	public Shop queryById(String id);
	
	public List<Shop> queryCity(String city);
	
	public List<Shop> queryByType(String type);
	
	public Shop queryByName(String shopName);
	
	
	public List<Shop> queryAll();
	
	public boolean update(Shop h);
	
	public boolean delete (String id);
	
	// 获取所有产品所在城市的列表(不重复)
	public List<String> getAllCity();
}
