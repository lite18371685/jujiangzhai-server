package cn.jujiangzhai.entity.info;

import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.Path;
import cn.jujiangzhai.util.Utils;

public class ShopListInfo {
	String id;
	String shopName;

	String city;
	String imgPath;
	String businessScope;
	String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public ShopListInfo(String id, String shopName, String city, String imgPath, String businessScope,
			String description) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.city = city;
		this.imgPath = imgPath;
		this.businessScope = businessScope;
		this.description = description;
	}
	public ShopListInfo() {
		super();
	}
	public static ShopListInfo fromShop(Shop z){
		
		ShopListInfo info = new ShopListInfo();
		info.setId(z.getId());
		info.setBusinessScope(z.getBusinessScope());
		info.setCity(z.getCity());
		info.setDescription(z.getDescription());
		info.setImgPath(Path.SERVER_IP+"/img/shops/"+z.getId()+".jpg");
		info.setShopName(z.getShopName());
		
		return info;
		
	}
	
	
}
