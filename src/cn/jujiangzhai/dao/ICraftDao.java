/**
 * 
 */
package cn.jujiangzhai.dao;

import java.util.List;

import cn.jujiangzhai.entity.Handicraft;

/**
 * @author Wales
 *
 */
public interface ICraftDao {

	public boolean insert(Handicraft h);
	
	public Handicraft queryById(String id);
	
	public List<Handicraft> queryAll();
	
	public List<Handicraft> queryCity(String city);
	
	public List<Handicraft> queryType(String type);
	
	public List<Handicraft> queryRecommend();
	
	public List<Handicraft> queryByShopName(String shopName);
	
	public boolean update(Handicraft h);
	
	public boolean delete (String id);
	
	public void viewed(String id);
	
	// 获取所有产品所在城市的列表(不重复)
	public List<String> getAllCity();
	
}
