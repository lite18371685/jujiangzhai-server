package cn.jujiangzhai.entity;

import cn.jujiangzhai.util.Path;

public class Shop {

	String id  ;
	
	String shopName;
	
	String shopLink;
	
	String city;
	
	String address;
	
	String description;
	
	String businessScope;
	
	String imgPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public Shop(String id, String shopName, String shopLink, String city, String address, String description,
			String businessScope) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.shopLink = shopLink;
		this.city = city;
		this.address = address;
		this.description = description;
		this.businessScope = businessScope;
		this.imgPath = Path.SERVER_IP+"/img/shops/"+id+".jpg";
	}

	public Shop() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shop [id=");
		builder.append(id);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", shopLink=");
		builder.append(shopLink);
		builder.append(", city=");
		builder.append(city);
		builder.append(", address=");
		builder.append(address);
		builder.append(", description=");
		builder.append(description);
		builder.append(", businessScope=");
		builder.append(businessScope);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
