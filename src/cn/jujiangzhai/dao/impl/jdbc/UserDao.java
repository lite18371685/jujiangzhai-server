package cn.jujiangzhai.dao.impl.jdbc;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.jujiangzhai.dao.IUserDao;
import cn.jujiangzhai.entity.Article;
import cn.jujiangzhai.entity.User;
import cn.jujiangzhai.util.JdbcUtils;

public class UserDao implements IUserDao {

	public static final int MAX_RECENTVIEWED = 8;

	private QueryRunner qr = JdbcUtils.getQueryRunner();

	/**
	 * 插入用户记录
	 * 
	 * @return true:插入成功 false:插入失败
	 */
	@Override
	public boolean insert(User h) {

		// 保证传入的Article对象不为空,且主键id不为空.
		if (h == null) {
			return false;
		} else if (h.getId() == null || "".equals(h.getId().trim())) {
			return false;
		}

		// 共有10个占位符
		String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?);";

		try {
			qr.update(sql, h.getId(), h.getUserName(), h.getUserPassword(), h.getNickName(), h.getCity(), h.getPhone(),
					h.getLastLogin(), h.getRecentViewed(), h.getCollection(), h.getFollowUp());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	/**
	 * 根据ID值查询用户记录
	 */
	@Override
	public User queryById(String id) {

		if (id == null || "".equals(id.trim())) {
			return null;
		}

		String sql = "select * from users where id=?;";

		User h = null;

		try {
			h = qr.query(sql, new BeanHandler<User>(User.class), id);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return h;
	}

	/**
	 * 根据手机号查询记录
	 */
	@Override
	public User queryByPhone(String phone) {

		if (phone == null || "".equals(phone.trim())) {
			return null;
		}

		String sql = "select * from users where phone=?;";

		User h = null;

		try {
			h = qr.query(sql, new BeanHandler<User>(User.class), phone);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return h;
	}

	/**
	 * 根据用户名查询用户记录
	 */
	@Override
	public User queryByUserName(String name) {

		if (name == null || "".equals(name.trim())) {
			return null;
		}

		String sql = "select * from users where userName=?;";

		User h = null;

		try {
			h = qr.query(sql, new BeanHandler<User>(User.class), name);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return h;
	}

	/**
	 * 将用户浏览的手工艺品记录id存储到最近浏览中
	 * 
	 */
	@Override
	public void view(String userId, String itemId) {

		if (userId == null || "".equals(userId.trim())) {
			return;
		}

		if (itemId == null || "".equals(itemId.trim())) {
			return;
		}

		String sql1 = "select recentViewed from users where id=?;";

		String sql2 = "update users set recentViewed=? where id=?";
		try {
			String str = qr.query(sql1, new ScalarHandler<String>(), userId);

			str = processStr(str,itemId);
			
			qr.update(sql2, str, userId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		


	}

	/**
	 * 用户收藏手工艺品
	 */
	@Override
	public void collect(String userId, String itemId) {

		if (userId == null || "".equals(userId.trim())) {
			return;
		}

		if (itemId == null || "".equals(itemId.trim())) {
			return;
		}

		String sql1 = "select collection from users where id=?;";

		String sql2 = "update users set collection=? where id=?";
		try {
			String str = qr.query(sql1, new ScalarHandler<String>(), userId);
			
			str = processStr(str,itemId);
			
			qr.update(sql2, str, userId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 用户关注店铺
	 */
	@Override
	public void followUp(String userId, String itemId) {

		

		if (userId == null || "".equals(userId.trim())) {
			return;
		}

		if (itemId == null || "".equals(itemId.trim())) {
			return;
		}

		String sql1 = "select followUp from users where id=?;";

		String sql2 = "update users set followUp=? where id=?";
		try {
			String str = qr.query(sql1, new ScalarHandler<String>(), userId);
			
			str = processStr(str,itemId);
			
			qr.update(sql2, str, userId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 更新用户所在城市
	 */
	@Override
	public boolean updateCity(String userId, String city) {
		if (userId == null || "".equals(userId.trim())) {
			return false;
		}
		if (city == null || "".equals(city.trim())) {
			return false;
		}
		
		String sql = "update users set city=? where id=?;";
		
		try {
			int update = qr.update(sql, city,userId);
			System.out.println(update+" rows updated");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
		return true;
	}

	@Override
	public boolean updateNickName(String id, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cancelCollect(String userId, String itemId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelFollowUp(String userId, String shopId) {
		// TODO Auto-generated method stub

	}

	
	/**
	 * 对字段处理,itemId之间以#分隔,新加入的itemId将放在最前面
	 * @param str 字段原先记录值
	 * @param itemId 要插入的记录值
	 * @return 处理后的字段值
	 */
	private String processStr(String str, String itemId){
		String result;
		if (str == null || "".equals(str.trim())) {
			result = itemId;
		} else {
			String[] arr = str.split("#");
			String[] newArr = new String[arr.length + 1];
			newArr[0] = itemId;
			for (int i = 1, j = 0; i < MAX_RECENTVIEWED && j < arr.length; j++) {
				if (arr[j] != itemId) {
					newArr[i++] = arr[j];
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < newArr.length; i++) {
				sb.append(newArr[i]);
				sb.append("#");
			}
			// 刪除最后的'#'
			sb.deleteCharAt(sb.length() - 1);
			
			result = sb.toString();
		}
		
		return result;
	}
	
}




