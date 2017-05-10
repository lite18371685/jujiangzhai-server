package cn.jujiangzhai.entity;

import java.util.List;

public class User {

	String id;
	
	String userName;
	
	String userPassword;
	
	String nickName;
	
	String city;
	
	String phone;
	
	String lastLogin;
	
	List<String> recentViewed;
	
	/**
	 * 用户收藏的手工艺品, 用手工工艺品的ID存储
	 */
	List<String> collection;
	
	List<String> followUp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}




	public List<String> getCollection() {
		return collection;
	}

	public void setCollection(List<String> collection) {
		this.collection = collection;
	}

	public List<String> getFollowUp() {
		return followUp;
	}

	public void setFollowUp(List<String> followUp) {
		this.followUp = followUp;
	}

	public User() {
		super();
	}



	public User(String id, String userName, String userPassword, String nickName, String city, String phone,
			String lastLogin, List<String> recentViewed, List<String> collection, List<String> followUp) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.nickName = nickName;
		this.city = city;
		this.phone = phone;
		this.lastLogin = lastLogin;
		this.recentViewed = recentViewed;
		this.collection = collection;
		this.followUp = followUp;
	}

	public List<String> getRecentViewed() {
		return recentViewed;
	}

	public void setRecentViewed(List<String> recentViewed) {
		this.recentViewed = recentViewed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userPassword=");
		builder.append(userPassword);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", city=");
		builder.append(city);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append(", recentViewed=");
		builder.append(recentViewed);
		builder.append(", collection=");
		builder.append(collection);
		builder.append(", followUp=");
		builder.append(followUp);
		builder.append("]");
		return builder.toString();
	}
	
	
}
