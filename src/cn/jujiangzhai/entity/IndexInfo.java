package cn.jujiangzhai.entity;

import java.io.File;

import cn.jujiangzhai.util.Path;

/**
 * 消息实体,用于 产品列表页面的 消息封装
 * @author Wales
 *
 */
public class IndexInfo {

	String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String name;
	
	String views;
	
	String city;
	
	String imgPath;

	
	public static IndexInfo fromHc(Handicraft h) {
		
		IndexInfo info = new IndexInfo();
		
		info.id = h.getId();
		info.name = h.getName();
		info.views = ""+h.getViews();
		info.city = h.getCity();
		info.imgPath = Path.SERVER_IP+"/img/handicrafts/"+h.getId()+".jpg";
		
		return info;
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


	public IndexInfo(String id, String name, String views, String city, String imgPath) {
		super();
		this.id = id;
		this.name = name;
		this.views = views;
		this.city = city;
		this.imgPath = imgPath;
	}

	public IndexInfo() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IndexInfo [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", views=");
		builder.append(views);
		builder.append(", city=");
		builder.append(city);
		builder.append(", imgPath=");
		builder.append(imgPath);
		builder.append("]");
		return builder.toString();
	}


}
