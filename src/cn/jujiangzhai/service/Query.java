package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.jujiangzhai.dao.impl.xml.CraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.util.Utils;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		File path = new File(this.getServletContext().getRealPath("/")+"db/handicrafts.xml");
		
		String id = request.getParameter("id");
		
//		if(id==null||(!path.exists())){			
//			response.getWriter().write("查询失败");
//		}else{
//			Document doc = null;
//			try {
//				doc = new SAXReader().read(path);
//			} catch (DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String xPath = "/handicrafts/handicraft[id='"+id+"']";
//			Element e = (Element)doc.selectSingleNode(xPath);
//			Handicraft h = Utils.readHandicraft(e);
//			System.out.println(h);
//			request.setAttribute("handicraft", h);
//			request.getRequestDispatcher("query.jsp").forward(request, response);
//		}
		
		CraftDao dao = new CraftDao(path);
		Handicraft h = dao.queryById(id);
		request.setAttribute("handicraft", h);
		request.getRequestDispatcher("query.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
