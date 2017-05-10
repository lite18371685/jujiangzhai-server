package cn.jujiangzhai.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.dom4j.Element;

import cn.jujiangzhai.entity.Article;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.entity.User;

public class Utils {

	public static String getUUID(){
		return UUID.randomUUID().toString().substring(0,6);
	}

	
	public static void addHandicraft(Handicraft h,Element e) {
		
		e.addElement("id").setText(h.getId());
		
		e.addElement("name").setText(h.getName());
		
		e.addElement("type").setText(h.getType());
		
		e.addElement("city").setText(h.getCity());;
		
		e.addElement("address").setText(h.getAddress());

		e.addElement("enterTime").setText(h.getEnterTime());
		
		e.addElement("weight").setText(""+h.getWeight());
		
		e.addElement("views").setText(""+h.getViews());
		
		e.addElement("isRecommended").setText(h.getIsRecommended().toString());
		
		e.addElement("cipher").setText(h.getCipher());
		
		e.addElement("discount").setText(""+h.getDiscount());;
		
		e.addElement("shopName").setText(h.getShopName());
		
		e.addElement("shopLink").setText(h.getShopLink());
		
		e.addElement("description").setText(h.getDescription());;
	}
	
	public static Handicraft readHandicraft(Element e){
		
		Handicraft h = new Handicraft();
		
		h.setId(e.elementText("id"));
		
		h.setName(e.elementText("name"));
		
		h.setType(e.elementText("type"));
		
		h.setCity(e.elementText("city"));
		
		h.setAddress(e.elementText("address"));
		
		h.setEnterTime(e.elementText("enterTime"));
		
		h.setWeight(Integer.valueOf(e.elementText("weight")));
		
		h.setViews(Integer.valueOf(e.elementText("views")));
		
		h.setIsRecommended(Boolean.valueOf(e.elementText("isRecommended")));
		
		h.setCipher(e.elementText("cipher"));
		
		h.setDiscount(Double.valueOf(e.elementText("discount")));
		
		h.setShopName(e.elementText("shopName"));
		
		h.setShopLink(e.elementText("shopLink"));
		
		h.setDescription(e.elementText("description"));
		
	
		return h;
	}
	
	
//	public static void addUser(User user,Element e){
//		List<String> list = null;
//		e.addElement("id").setText(user.getId());
//		e.addElement("userName").setText(user.getUserName());
//		e.addElement("userPassword").setText(user.getUserPassword());
//		if (user.getNickName()!=null) {
//			e.addElement("nickName").setText(user.getNickName());
//		}else {
//			e.addElement("nickName");
//		}
//		if (user.getCity()!=null) {
//			e.addElement("city").setText(user.getCity());
//		}else {
//			e.addElement("city");
//		}
//		if (user.getPhone()!=null) {
//			e.addElement("phone").setText(user.getPhone());
//		}else {
//			e.addElement("phone");
//		}
//		if (user.getLastLogin()!=null) {
//			e.addElement("lastLogin").setText(user.getLastLogin());
//		}else {
//			e.addElement("lastLogin");
//		}
//
//	    list = user.getRecentViewed();
//		if(list!=null){
//		   
//			e.addElement("recentViewed").setText(f1(list));
//		}else{
//			e.addElement("recentViewed");
//		}
//
//		
//		list = user.getCollection();
//		if(list!=null){
//			   
//			e.addElement("collection").setText(f1(list));
//		}else{
//			e.addElement("collection");
//		}
//		
//	   list = user.getFollowUp();
//	   if(list!=null){
//		   
//			e.addElement("followUp").setText(f1(list));
//		}else{
//			e.addElement("followUp");
//		}
//
//	   
//	}
//	public static User readUser(Element e){
//		User u = new User();
//		u.setId(e.elementText("id"));
//		u.setUserName(e.elementText("userName"));
//		u.setUserPassword(e.elementText("userPassword"));
//		u.setNickName(e.elementText("nickName"));
//		u.setCity(e.elementText("city"));
//		u.setPhone(e.elementText("phone"));
//		u.setLastLogin(e.elementText("lastLogin"));
//		u.setRecentViewed(f2(e.elementText("recentViewed")));
//		u.setCollection(f2(e.elementText("collection")));
//		u.setFollowUp(f2(e.elementText("followUp")));
//		
//		return u;
//	}
	
	
	public static void addShop(Shop s,Element e) {
		e.addElement("id").setText(s.getId());
		e.addElement("shopName").setText(s.getShopName());
		e.addElement("shopLink").setText(s.getShopLink());
		e.addElement("city").setText(s.getCity());
		e.addElement("address").setText(s.getAddress());
		e.addElement("description").setText(s.getDescription());
		e.addElement("businessScope").setText(s.getBusinessScope());
	}
	public static Shop readShop(Element e){
		
		Shop shop = new Shop();
		shop.setId(e.elementText("id"));
		shop.setShopName(e.elementText("shopName"));
		shop.setShopLink(e.elementText("shopLink"));
		shop.setCity(e.elementText("city"));
		shop.setAddress(e.elementText("address"));
		shop.setDescription(e.elementText("description"));
		shop.setBusinessScope(e.elementText("businessScope"));
		shop.setImgPath(Path.SERVER_IP+"/img/shops/"+e.elementText("id")+".jpg");
		return shop;
	}
	
	public static void  addArticle(Article a,Element e) {
		e.addElement("id").setText(a.getId());
		e.addElement("title").setText(a.getTitle());
		e.addElement("type").setText(a.getType());
		e.addElement("description").setText(a.getDescription());
		
	}
	
	public static Article readArticle(Element e) {
		Article a = new Article();
		a.setId(e.elementText("id"));
		a.setType(e.elementText("type"));
		a.setTitle(e.elementText("title"));
		a.setDescription(e.elementText("description"));
		return a;
		
	}
	
	private static String f1(List<String> list){
			StringBuilder sb = new StringBuilder();
		    for(String str:list){
		    	sb.append(str);
		    	sb.append("#");
		    }
		    sb.deleteCharAt(sb.length()-1);
		    System.out.println(sb.toString());
		    return sb.toString();
	}
	
	private static List<String> f2(String str){
		if(str!=null){
			String[] arr = str.split("#");
			List<String> res = Arrays.asList(arr);
			return res;
		}
		return null;
	}
}
