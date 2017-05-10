package cn.jujiangzhai.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import cn.jujiangzhai.dao.impl.xml.CraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.util.Path;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path=Path.getHandicraftsPath(this.getServletContext());
		
//		List<Handicraft> list1 = dao.queryType("吹糖人");
//		if(list1!=null){
//			System.out.println(list1.toString());
//
//		}else{
//			System.out.println("null");
//		}
		String type=  "吹糖人";
		Document doc = null;
		try {
			doc = new SAXReader().read(path);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xPath = "//isRecommended[text()='true']";

		List<Element> nodes = doc.selectNodes(xPath);
		for(Element e:nodes){
			System.out.println(e.getParent().elementText("id"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
