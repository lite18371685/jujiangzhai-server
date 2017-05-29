package cn.jujiangzhai.entity.info;

import cn.jujiangzhai.entity.Handicraft;

/**
 * 消息实体类, 用于封装 产品详情页的信息
 * @author Wales
 *
 */
public class DetailsInfo {

	 String id;
	 String name;
	 String views;
	 String address;
	 String description;
	 String cipher;
	 String discount;
	 String shopName;
	 Boolean isCollected;
	 Boolean isViewed;
	 String shopLink;
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
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
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
	public String getCipher() {
		return cipher;
	}
	public void setCipher(String cipher) {
		this.cipher = cipher;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Boolean getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}
	public Boolean getIsViewed() {
		return isViewed;
	}
	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}
	public String getShopLink() {
		return shopLink;
	}
	public void setShopLink(String shopLink) {
		this.shopLink = shopLink;
	}
	public DetailsInfo(String id, String name, String views, String address, String description, String cipher,
			String discount, String shopName, Boolean isCollected, Boolean isViewed, String shopLink) {
		super();
		this.id = id;
		this.name = name;
		this.views = views;
		this.address = address;
		this.description = description;
		this.cipher = cipher;
		this.discount = discount;
		this.shopName = shopName;
		this.isCollected = isCollected;
		this.isViewed = isViewed;
		this.shopLink = shopLink;
	}
	public DetailsInfo() {
		super();
	}
	 
	public static DetailsInfo fromHc(Handicraft h){
		DetailsInfo info = new DetailsInfo();
		info.setId(h.getId());
		info.setAddress(h.getAddress());
		info.setCipher(h.getCipher());
		info.setDescription(h.getDescription());
		info.setDiscount(""+h.getDiscount());
		info.setIsCollected(false);
		info.setIsViewed(false);
		info.setName(h.getName());
		info.setShopName(h.getShopName());
		info.setShopLink(h.getShopLink());
		
		info.setViews(""+h.getViews());
		
		
		return info;
	}
	
}
