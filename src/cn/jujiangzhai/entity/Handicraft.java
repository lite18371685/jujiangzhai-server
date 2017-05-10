package cn.jujiangzhai.entity;

import java.util.Date;

public class Handicraft {
	
	/**
	 * 手工艺品的ID,随机生成,6位.
	 */
	private String id;
	
	/**
	 * 手工艺品的名称
	 */
	private String name;
	
	/**
	 * 手工艺品的种类
	 * {草编,吹糖人,瓷器,刺绣,雕刻,风筝,剪纸,面塑,皮影戏,宋锦,中国结,其他}
	 */
	private String type;
	
	/**
	 * 手工艺品的描述,用于详情页
	 */
	private String description;
	
	/**
	 * 手工艺品所在城市
	 */
	private String city;
	
	/**
	 * 手工艺品的地址
	 */
	private String address;
	
	/**
	 * 录入时间
	 */
	private String enterTime;
	
	/**
	 * 手工艺品的权重,数值为整数,[1,2,3,4,5],数值越小权重越大
	 */
	private int weight=5;
	
	/**
	 * 浏览次数
	 */
	private int views=0;
	
	/**
	 * 是否今日推荐
	 */
	private Boolean isRecommended = false;
	
	/**
	 * 优惠暗号
	 */
	private String cipher;
	
	/**
	 * 优惠额度
	 */
	private double discount = 0;
	
	/**
	 * 店铺名称
	 */
	private String shopName;
	
	/**
	 * 店铺连接
	 */
	private String shopLink;


	
	
	public Handicraft(String id, String name, String type, String description, String city, String address,
			String enterTime, int weight, int views, Boolean isRecommended, String cipher, double discount,
			String shopName, String shopLink) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.city = city;
		this.address = address;
		this.enterTime = enterTime;
		this.weight = weight;
		this.views = views;
		this.isRecommended = isRecommended;
		this.cipher = cipher;
		this.discount = discount;
		this.shopName = shopName;
		this.shopLink = shopLink;
	}

	public Handicraft() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Boolean getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLink() {
		return shopLink;
	}

	public void setShopLink(String shopLink) {
		this.shopLink = shopLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Handicraft other = (Handicraft) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Handicraft [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", description=");
		builder.append(description);
		builder.append(", city=");
		builder.append(city);
		builder.append(", address=");
		builder.append(address);
		builder.append(", enterTime=");
		builder.append(enterTime);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", views=");
		builder.append(views);
		builder.append(", isRecommended=");
		builder.append(isRecommended);
		builder.append(", cipher=");
		builder.append(cipher);
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", shopLink=");
		builder.append(shopLink);
		builder.append("]");
		return builder.toString();
	}
	public String toIndexJsonString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"id\":\"").append(id).append("\",");
		sb.append("\"name\":\"").append(name).append("\",");
		sb.append("\"views\":\"").append(views).append("\",");
		sb.append("\"city\":\"").append(city).append("\"");
		sb.append("}");
		return sb.toString();
	}
	

}


