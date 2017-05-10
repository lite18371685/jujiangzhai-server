package cn.jujiangzhai.entity;

import cn.jujiangzhai.util.Path;

/**
 * 消息实体,用于封装 科普文章列表的单个文章所需消息
 * @author Wales
 *
 */
public class ArticleListInfo {
	String id;
	
	String title;
	
	String type;
	
	String description;

	String imgPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public ArticleListInfo(String id, String title, String type, String description, String imgPath) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.description = description;
		this.imgPath = imgPath;
	}

	public ArticleListInfo() {
		super();
	}
	
	public static ArticleListInfo fromArticle(Article a){
		ArticleListInfo info = new ArticleListInfo();
		
		info.setId(a.getId());
		info.setTitle(a.getTitle());
		info.setType(a.getType());
		info.setDescription(a.getDescription());
		info.setImgPath(Path.SERVER_IP+"/img/articles/"+a.getId()+".jpg");
		return info;
	}
}
