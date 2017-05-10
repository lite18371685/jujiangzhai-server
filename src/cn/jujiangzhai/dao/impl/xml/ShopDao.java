package cn.jujiangzhai.dao.impl.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.IShopDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.Utils;

public class ShopDao implements IShopDao {
	File path = null;

	public ShopDao(File path) {
		super();
		this.path = path;
	}

	@Override
	public Shop queryByName(String shopName) {
		// TODO Auto-generated method stub
		if(shopName==null||!path.exists()){
			return null;
		}
		Shop shop = null;
		
		try {
			Document doc = new SAXReader().read(path);
			String xPath = "//shopName[text()='"+shopName+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			shop = Utils.readShop(e.getParent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return shop;
	}
	@Override
	public List<Shop> queryByType(String type) {

		if(type==null||!path.exists()){
			return null;
		}
		
		List<Shop> list = new ArrayList<Shop>();
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//shop[contains(businessScope,'"+type+"')]";
			List<Element> list2 = (List<Element>)doc.selectNodes(xPath);
			for(Element e:list2){
				list.add(Utils.readShop(e));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	@Override
	public List<Shop> queryCity(String city) {
		if(city==null||!path.exists()){
			return null;
		}
		
		List<Shop> list  = new ArrayList<Shop>();
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//city[text()='"+city+"']";
			List<Element> list2 = (List<Element>)doc.selectNodes(xPath);
			for(Element e:list2){
				list.add(Utils.readShop(e.getParent()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean insert(Shop shop) {
		if(shop==null){
			return false;
		}
		Document doc = null;

		Element element = null;
		if(path.exists()){
			try {
				doc = new SAXReader().read(path);
				element = doc.getRootElement().addElement("shop");
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else {
			
			doc = DocumentHelper.createDocument();
			element = doc.addElement("shops").addElement("shop");		
		}
		
		Utils.addShop(shop, element);
		
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
	public Shop queryById(String id) {
		
		if(id==null||!path.exists()){
			return null;
		}
		
		Shop shop = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "/shops/shop[id='"+id+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			shop = Utils.readShop(e);
		}catch(Exception e){
			e.printStackTrace();
		}
		return shop;
	}

	@Override
	public List<Shop> queryAll() {

		if(!path.exists()){
			return null;
		}
		
		List<Shop> list = new ArrayList<Shop>();
		
		try {
			 Document doc = new SAXReader().read(path);
			 if(doc!=null){
				List<Element> elements = doc.getRootElement().elements("shop");
				for(Element e:elements){
					list.add(Utils.readShop(e));
				}
				 
			 }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean update(Shop shop) {
		if(!path.exists()||shop==null){
			return false;
		}
		Document doc = null;
		String id = shop.getId();
		Element element = null;
			try {
				doc = new SAXReader().read(path);
				if(doc!=null){
					element = (Element)doc.selectSingleNode("/shops/shop[id='"+id+"']");

				}else{
					return false;
				}
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			if(element!=null){			
				element.detach();
			}
			
			try {
				Element newElement=null;
				
				newElement = doc.getRootElement().addElement("shop");
				
				Utils.addShop(shop, newElement);

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
	public boolean delete(String id) {
		if(!path.exists()||id==null){
			//删除失败
			return false;
		}
		
		try {
			Document doc = new SAXReader().read(path);
			Element element = (Element)doc.selectSingleNode("/shops/shop[id='"+id+"']");
			if(element!=null){
				element.detach();
			}
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
			
			writer.write(doc);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
