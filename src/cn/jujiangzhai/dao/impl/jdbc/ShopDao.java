package cn.jujiangzhai.dao.impl.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.jujiangzhai.dao.IShopDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.entity.Shop;
/**
 *  店铺数据访问层实现对象,实现ICraftDao接口
 *  JDBC实现
 * @author Wales
 *
 */
import cn.jujiangzhai.util.JdbcUtils;
public class ShopDao implements IShopDao {

	private QueryRunner qr = JdbcUtils.getQueryRunner();
	
	@Override
	public boolean insert(Shop h) {
		
		// 保证传入的Shop对象不为空,且主键id不为空.
		if(h==null){
			return false;
		}else if(h.getId()==null||"".equals(h.getId().trim())){
			return false;
		}
		
		// 共有7个占位符
		String sql = "insert into shops values(?,?,?,?,?,?,?);";
		
		try {
			qr.update(sql, 
						h.getId(),
						h.getShopName(),
						h.getShopLink(),
						h.getCity(),
						h.getAddress(),
						h.getDescription(),
						h.getBusinessScope());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * 根据ID值查询记录
	 */
	@Override
	public Shop queryById(String id) {
		
		if(id==null||"".equals(id.trim())){
			return null;
		}
		
		String sql = "select * from shops where id=?;";
		
		Shop h = null;
		
		try {
			h = qr.query(sql, new BeanHandler<Shop>(Shop.class), id);
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return h;
	}

	/**
	 * 根据城市名查询店铺记录
	 */
	@Override
	public List<Shop> queryCity(String city) {
		
		String sql = "select * from shops where city=?;";
		
		List<Shop> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Shop>(Shop.class),city);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	/**
	 * 根据类型查询店铺记录
	 */
	@Override
	public List<Shop> queryByType(String type) {
		
		String sql = "SELECT * from shops where businessScope like ?;";
		
		List<Shop> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Shop>(Shop.class),"%"+type+"%");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	@Override
	public Shop queryByName(String shopName) {
		
		if(shopName==null||"".equals(shopName.trim())){
			return null;
		}
		
		String sql = "select * from shops where shopName=?;";
		
		Shop h = null;
		
		try {
			h = qr.query(sql, new BeanHandler<Shop>(Shop.class), shopName);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return h;
	}

	@Override
	public List<Shop> queryAll() {
		
		String sql = "select * from shops;";
		
		List<Shop> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Shop>(Shop.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public boolean update(Shop h) {
		
		// 保证传入的Shop对象不为空,且主键id不为空.
		if(h==null){
			return false;
		}else if(h.getId()==null||"".equals(h.getId().trim())){
			return false;
		}
		
		// 共有7个占位符 前6个为Shop对象的属性,最后一个为id值
		String sql = "update shops set shopName=?, shopLink=?, city=?, address=?, description=?, businessScope=? where id=?;";
		
		try {
			qr.update(sql, 						
						h.getShopName(),
						h.getShopLink(),
						h.getCity(),
						h.getAddress(),
						h.getDescription(),
						h.getBusinessScope(),
						h.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(String id) {
		
		if(id==null||"".equals(id.trim())){
			return false;
		}
		
		String sql = "delete from shops where id=?;";
		
		try {
			int update = qr.update(sql, id);
			System.out.println("删除店铺记录: "+update+" rows updated..");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;

	}

}
