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
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.IArticleDao;
import cn.jujiangzhai.entity.Article;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.Utils;

public class ArticleDao implements IArticleDao {

	File path = null;
	
	
	public ArticleDao(File path) {
		super();
		this.path = path;
	}

	@Override
	public boolean insert(Article a) {
		if(a==null){
			return false;
		}
		Document doc = null;

		Element element = null;
		if(path.exists()){
			try {
				doc = new SAXReader().read(path);
				element = doc.getRootElement().addElement("article");
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else {
			
			doc = DocumentHelper.createDocument();
			element = doc.addElement("articles").addElement("article");		
		}
		
		Utils.addArticle(a, element);
		
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
	public Article queryById(String id) {
		if(id==null||!path.exists()){
			return null;
		}
		
		Article a = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "/articles/article[id='"+id+"']";
			Element e = (Element)doc.selectSingleNode(xPath);
			a = Utils.readArticle(e);
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Article> queryAll() {
		if(!path.exists()){
			return null;
		}
		
		List<Article> list = new ArrayList<Article>();
		
		try {
			 Document doc = new SAXReader().read(path);
			 if(doc!=null){
				List<Element> elements = doc.getRootElement().elements("article");
				for(Element e:elements){
					list.add(Utils.readArticle(e));
				}
				 
			 }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean update(Article a) {
		if(!path.exists()||a==null){
			return false;
		}
		Document doc = null;
		String id = a.getId();
		Element element = null;
			try {
				doc = new SAXReader().read(path);
				if(doc!=null){
					element = (Element)doc.selectSingleNode("/articles/article[id='"+id+"']");

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
				
				newElement = doc.getRootElement().addElement("article");
				
				Utils.addArticle(a, newElement);

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
			Element element = (Element)doc.selectSingleNode("/articles/article[id='"+id+"']");
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
