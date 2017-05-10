package cn.jujiangzhai.dao;

import cn.jujiangzhai.entity.User;

public interface IUserDao {

	public boolean insert(User user);
	
	public User queryById(String id);
	
	public User queryByPhone(String phone);

	public User queryByUserName(String name);
	
	public void view(String userId,String itemId);
	
	public void collect (String userId,String itemId);
	
	public void followUp(String userId,String shopId);
	
	public boolean updateCity(String id,String city);
	
	public boolean updateNickName(String id,String name);
	

	public void cancelCollect(String userId,String itemId);
	
	public void cancelFollowUp(String userId,String shopId);
	
	
}
