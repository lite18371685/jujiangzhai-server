package cn.jujiangzhai.dao;

public interface ITokenDao {

	public String insert(String id);
	
	public void delete(String token);
	
	public void deleteById(String id);
	
	public String queryById(String id);
	
	public String queryByToken(String token);
	
}
