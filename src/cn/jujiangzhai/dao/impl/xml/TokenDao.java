package cn.jujiangzhai.dao.impl.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.ITokenDao;
import cn.jujiangzhai.util.TokenProcessor;
import cn.jujiangzhai.util.Utils;

public class TokenDao implements ITokenDao {

	File path = null;
	
	public  TokenDao(File p) {
		// TODO Auto-generated constructor stub
		path=p;
	}
	
	@Override
	public String insert(String id) {
		
		if(id==null){
			return null;
		}		
		
		String token = TokenProcessor.makeToken();
		
		Document doc = null;

		Element element = null;
		if(path.exists()){
			try {
				doc = new SAXReader().read(path);
				element = doc.getRootElement().addElement("token");
			
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else {
			
			doc = DocumentHelper.createDocument();
			element = doc.addElement("tokens").addElement("token");		
		}
		
		element.addElement("userId").setText(id);
		element.addElement("tokenStr").setText(token);
		
		
		
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
		
		
		
		return token;
	}

	@Override
	public void delete(String token) {
		
		if(token==null||!path.exists()){
			return;
		}
		
		try {
			Document doc = new SAXReader().read(path);
			Element element = (Element)doc.selectSingleNode("//tokenStr[text()='"+token+"']");
			if(element!=null){
				element.getParent().detach();
			}
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
			
			writer.write(doc);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void deleteById(String id) {

		if(id==null||!path.exists()){
			return;
		}
		
		try {
			Document doc = new SAXReader().read(path);
			List<Element> list = doc.selectNodes("//id[text()='"+id+"']");
			if(list!=null){
				for(Element e:list){
					e.detach();
				}
			}
			FileOutputStream outputStream = new FileOutputStream(path);
			
			OutputFormat outFormat = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(outputStream,outFormat);
			
			writer.write(doc);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}
	@Override
	public String queryById(String id) {
		if(id==null||!path.exists()){
			return null;
		}
		
		String token = null;
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//userId[text()='"+id+"']";

			Node node = doc.selectSingleNode(xPath);
			token = node.getParent().elementText("tokenStr");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return token;
		
	}

	@Override
	public String queryByToken(String token) {
		if(token==null||!path.exists())
			return null;
		
		String id = null;
		
		try{
			Document doc = new SAXReader().read(path);
			String xPath = "//tokenStr[text()='"+token+"']";

			Node node = doc.selectSingleNode(xPath);
			id = node.getParent().elementText("userId");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return id;
	}

}
