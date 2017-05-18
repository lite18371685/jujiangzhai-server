package cn.jujiangzhai.dao.impl.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.jujiangzhai.dao.ICraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.util.JdbcUtils;


/**
 *  手工艺品数据访问层实现对象,实现ICraftDao接口
 *  JDBC实现
 * @author Wales
 *
 */
public class CraftDao implements ICraftDao {

	private QueryRunner qr = JdbcUtils.getQueryRunner();
	
	/**
	 * 插入手工艺品记录
	 * @return true:插入成功   false:插入失败
	 */
	@Override
	public boolean insert(Handicraft h) {
		
		// 保证传入的Handicraft不为空,且主键id不为空.
		if(h==null){
			return false;
		}else if(h.getId()==null||"".equals(h.getId().trim())){
			return false;
		}
		// 插入例子 :insert into handicrafts VALUES('001','Demo1','瓷器','南京','地址','输入时间','1','12',TRUE,'zxcc',20.2,'demoSHop',NULL,NULL);
		String sql = "insert into handicrafts values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			qr.update(sql,h.getId(),
					h.getName(),
					h.getType(),
					h.getCity(),
					h.getAddress(),
					h.getEnterTime(),
					h.getWeight(),
					h.getViews(),
					h.getIsRecommended(),
					h.getCipher(),
					h.getDiscount(),
					h.getShopName(),
					h.getShopLink(),
					h.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * 根据ID查询手工艺品记录
	 */
	@Override
	public Handicraft queryById(String id) {
		
		if(id==null||"".equals(id.trim())){
			return null;
		}
		
		String sql = "select * from handicrafts where id=?;";
		
		Handicraft h = null;
		
		try {
			h = qr.query(sql, new BeanHandler<Handicraft>(Handicraft.class), id);
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return h;
	}

	/**
	 * 查询全部的手工艺品记录
	 */
	@Override
	public List<Handicraft> queryAll() {
		
		String sql = "select * from handicrafts;";
		
		List<Handicraft> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Handicraft>(Handicraft.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/**
	 * 根据城市名查询手工艺品记录
	 */
	@Override
	public List<Handicraft> queryCity(String city) {
		
		String sql = "select * from handicrafts where city=?;";
		
		List<Handicraft> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Handicraft>(Handicraft.class),city);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	/**
	 * 根据类型查询手工艺品记录
	 */
	@Override
	public List<Handicraft> queryType(String type) {
		
		String sql = "select * from handicrafts where type=?;";
		
		List<Handicraft> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Handicraft>(Handicraft.class),type);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	/**
	 * 查询所有推荐的手工艺品记录
	 */
	@Override
	public List<Handicraft> queryRecommend() {
		
		String sql = "select * from handicrafts where isRecommended=1;";
		
		List<Handicraft> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Handicraft>(Handicraft.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;	
	}

	/**
	 * 根据店铺名称查询手工艺品记录
	 */
	@Override
	public List<Handicraft> queryByShopName(String shopName) {

		if(shopName==null||"".equals(shopName.trim())){
			return null;
		}
		
		String sql = "select * from handicrafts where shopName=?";
		
		List<Handicraft> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Handicraft>(Handicraft.class),shopName);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

	/**
	 * 更新手工艺品记录(根据Id)
	 */
	@Override
	public boolean update(Handicraft h) {
		
		// 保证传入的Handicraft不为空,且主键id不为空.
		if(h==null){
			return false;
		}else if(h.getId()==null||"".equals(h.getId().trim())){
			return false;
		}
		
		// 一共14个占位符,前13个为Handicraft对象的属性,最后一个为id值
		String sql = "update handicrafts set name=?, type=?, city=?, address=?, enterTime=?, weight=?, views=?, isRecommended=?, cipher=?, discount=?, shopName=?, shopLink=?, description=? where id=?";
		
		try {
			int update = qr.update(sql,
					h.getName(),
					h.getType(),
					h.getCity(),
					h.getAddress(),
					h.getEnterTime(),
					h.getWeight(),
					h.getViews(),
					h.getIsRecommended(),
					h.getCipher(),
					h.getDiscount(),
					h.getShopName(),
					h.getShopLink(),
					h.getDescription(),
					h.getId());
			System.out.println("修改手工艺品记录: "+update+" rows updated..");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据id值删除手工艺品记录
	 */
	@Override
	public boolean delete(String id) {
		
		if(id==null||"".equals(id.trim())){
			return false;
		}
		
		String sql = "delete from handicrafts where id=?;";
		
		try {
			int update = qr.update(sql, id);
			System.out.println("删除手工艺品记录: "+update+" rows updated..");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * 用户浏览后增加手工艺品的被浏览次数
	 */
	@Override
	public void viewed(String id) {

		if(id==null||"".equals(id.trim())){
			return ;
		}
		
		String sql = "update handicrafts set views=views+1 where id=?;";
		
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();		
		}
	}

	@Override
	public List<String> getAllCity() {
		
		String sql = "select distinct city from handicrafts;";
		
		List<String> list = null;
		
		try {
			list = qr.query(sql, new ColumnListHandler<String>());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
		
	}

}
