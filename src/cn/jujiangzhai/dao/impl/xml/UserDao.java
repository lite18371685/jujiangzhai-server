package cn.jujiangzhai.dao.impl.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.IUserDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.entity.User;
import cn.jujiangzhai.util.Utils;

public class UserDao implements IUserDao {

	File path = null;
	
	public static final int MAX_RECENTVIEWED = 6;
	
	public UserDao(File path) {
		super();
		this.path = path;
	}

	@Override
	public boolean insert(User user) {

		if(user==null){
			return false;
		}
		Document doc = null;

		Element element = null;
		if(path.exists()){
			try {
				doc = new SAXReader().read(path);
				element = doc.getRootElement().addElement("user");
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else {
			
			doc = DocumentHelper.createDocument();
			element = doc.addElement("users").addElement("user");		
		}
		
		Utils.addUser(user, element);
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
			
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true; 
		
	}

	@Override
	public User queryById(String id) {
		if(id==null||!path.exists()){
			return null;
		}
		
		User u = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "/users/user[id='"+id+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			u = Utils.readUser(e);
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User queryByUserName(String name) {
		if(name==null||!path.exists()){
			return null;
		}
		
		User u = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//userName[text()='"+name+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			if(e==null){
				return null;
			}
			u = Utils.readUser(e.getParent());
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public User queryByPhone(String phone) {
		if(phone==null||!path.exists()){
			return null;
		}
		
		User u = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "/users/user[phone='"+phone+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			u = Utils.readUser(e);
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public void view(String userId, String itemId) {

		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+userId+"']");
			}else {
				return;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return;
		}
		String str = element.elementText("recentViewed");
		if(str==""||str==null){
			element.element("recentViewed").setText(itemId);
		}else {
			String[] arr = str.split("#");
			String[] newArr = new String[arr.length+1];
			newArr[0]=itemId;
			for(int i=1,j=0;i<MAX_RECENTVIEWED&&j<arr.length;j++){
				if(arr[j]!=itemId){
					newArr[i++]=arr[j];
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<newArr.length;i++){
				sb.append(newArr[i]);
				sb.append("#");
			}
			sb.deleteCharAt(sb.length()-1);
			element.element("recentViewed").setText(sb.toString());
		}
		
	}
	
	@Override
	public void cancelCollect(String userId, String itemId) {
		// TODO Auto-generated method stub
		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+userId+"']");
			}else {
				return;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return;
		}
		String str = element.elementText("collection");
		if(str==""||str==null){
			return;
		}else {
			if(str.contains(itemId)){
				int index = str.indexOf(itemId);
				int len = itemId.length();	
				if(index==0){
					str=str.substring(len+1);
					
				}else{
					str=str.substring(0, index-1)+str.substring(index+len);
				}
			}
			
		}
		element.element("collection").setText(str);
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
				
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void cancelFollowUp(String userId, String shopId) {
		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+userId+"']");
			}else {
				return;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return;
		}
		String str = element.elementText("followUp");
		if(str==""||str==null){
			return;
		}else {
			if(str.contains(shopId)){
				int index = str.indexOf(shopId);
				int len = shopId.length();	
				if(index==0){
					str=str.substring(len+1);
					
				}else{
					str=str.substring(0, index-1)+str.substring(index+len);
				}
			}
			
		}
		element.element("followUp").setText(str);
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
				
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void collect(String userId, String itemId) {
		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+userId+"']");
			}else {
				return;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return;
		}
		String str = element.elementText("collection");
		if(str==""||str==null){
			str=itemId;
		}else {
			if(!str.contains(itemId)){
				str = str +"#"+itemId;
			}
			
		}
		element.element("collection").setText(str);
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
				
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void followUp(String userId, String shopId) {
		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+userId+"']");
			}else {
				return;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return;
		}String str = element.elementText("followUp");
		if(str==""||str==null){
			str=shopId;
		}else {
			if(!str.contains(shopId)){
				str = str +"#"+shopId;
			}
		}
		element.element("followUp").setText(str);
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
				
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean updateCity(String id, String city) {

		if(!path.exists()||id==null){
			return false;
		}
		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+id+"']");

			}else{
				return false;
			}
		
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		element.element("city").setText(city);
		
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
				
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateNickName(String id, String name) {
		if(!path.exists()||id==null){
			return false;
		}
		Document doc = null;
		Element element = null;	
		try {
			doc = new SAXReader().read(path);
			if(doc!=null){
				element = (Element)doc.selectSingleNode("/users/user[id='"+id+"']");

			}else{
				return false;
			}
		
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		element.element("nickName").setText(name);
		
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
				
			writer.write(doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
