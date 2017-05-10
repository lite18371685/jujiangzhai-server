package cn.jujiangzhai.dao.impl.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Sides;
import javax.servlet.ServletContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.ICraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.util.Utils;

public class CraftDao implements ICraftDao {
	
	File path ;
	
	public CraftDao(File path) {
		super();
		this.path = path;
	}

	@Override
	public List<Handicraft> queryByShopName(String shopName) {
		List<Handicraft> list = new ArrayList<Handicraft>();
		
		try {
			Document doc = new SAXReader().read(path);
			String xPath = "//shopName[text()='"+shopName+"']";
			List<Element> nodes = doc.selectNodes(xPath);
			if (nodes==null) {
				System.out.println("nodelist is null");
				return null;
				
			}
			for(Element e:nodes){
				list.add(Utils.readHandicraft(e.getParent()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Handicraft> queryRecommend() {
		 List<Handicraft> list = new ArrayList<Handicraft>();
			
			try{
				Document doc = new SAXReader().read(path);
				String xPath = "//isRecommended[text()='true']";
				List<Element> nodes = doc.selectNodes(xPath);
				if (nodes==null) {
					System.out.println("nodelist is null");
					return null;
					
				}
				for(Element e:nodes){
					list.add(Utils.readHandicraft(e.getParent()));
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
	}
	
	@Override
	public List<Handicraft> queryCity(String city) {
		
		if(city==null||!path.exists()){
			return null;
		}
		
		 List<Handicraft> list = new ArrayList<Handicraft>();
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//city[text()='"+city+"']";
			List<Element> nodes = doc.selectNodes(xPath);
			if (nodes==null) {
				System.out.println("nodelist is null");
				return null;
				
			}
			
			for(Element e:nodes){
				list.add(Utils.readHandicraft(e.getParent()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Handicraft> queryType(String type) {
		if(type==null||!path.exists()){
			return null;
		}
		
		 List<Handicraft> list =  new ArrayList<Handicraft>();
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//type[text()='"+type+"']";
			List<Element> nodes = doc.selectNodes(xPath);
			if(nodes==null){
				System.out.println("nodes  is null");
				return null;
			}
			
			for(Element e:nodes){
				list.add(Utils.readHandicraft(e.getParent()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void viewed(String id) {
		if(!path.exists()||id==null){
			return;
		}
		Document doc = null;
		Element element = null;
			try {
				doc = new SAXReader().read(path);
				if(doc!=null){
					element = (Element)doc.selectSingleNode("/handicrafts/handicraft[id='"+id+"']");

				}else{
					return ;
				}
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		Integer i = Integer.valueOf(element.elementText("views"));
		i++;
		element.element("views").setText(id.toString());
					
	}
	
	@Override
	public boolean insert(Handicraft h) {
		// TODO Auto-generated method stub
		if(h==null){
			return false;
		}
		Document doc = null;

		Element element = null;
		if(path.exists()){
			try {
				doc = new SAXReader().read(path);
				element = doc.getRootElement().addElement("handicraft");
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else {
			
			doc = DocumentHelper.createDocument();
			element = doc.addElement("handicrafts").addElement("handicraft");		
		}
		
		Utils.addHandicraft(h, element);
		
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
	public Handicraft queryById(String id) {
		
		if(id==null||!path.exists()){
			return null;
		}
		
		Handicraft h = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "/handicrafts/handicraft[id='"+id+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			h = Utils.readHandicraft(e);
		}catch(Exception e){
			e.printStackTrace();
		}
		return h;
	}

	@Override
	public List<Handicraft> queryAll() {

		if(!path.exists()){
			return null;
		}
		
		List<Handicraft> list = new ArrayList<Handicraft>();
		
		try {
			 Document doc = new SAXReader().read(path);
			 if(doc!=null){
				List<Element> elements = doc.getRootElement().elements("handicraft");
				for(Element e:elements){
					list.add(Utils.readHandicraft(e));
				}
				 
			 }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean update(Handicraft h) {
		// TODO Auto-generated method stub
		if(!path.exists()||h==null){
			return false;
		}
		Document doc = null;
		String id = h.getId();
		Element element = null;
			try {
				doc = new SAXReader().read(path);
				if(doc!=null){
					element = (Element)doc.selectSingleNode("/handicrafts/handicraft[id='"+id+"']");

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
				
				newElement = doc.getRootElement().addElement("handicraft");
				
				Utils.addHandicraft(h, newElement);

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
		// TODO Auto-generated method stub
		if(!path.exists()||id==null){
			//删除失败
			return false;
		}
		
		try {
			Document doc = new SAXReader().read(path);
			Element element = (Element)doc.selectSingleNode("/handicrafts/handicraft[id='"+id+"']");
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
