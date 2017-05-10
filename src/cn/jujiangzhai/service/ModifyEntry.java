package cn.jujiangzhai.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.ICraftDao;
import cn.jujiangzhai.dao.impl.xml.CraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.util.Utils;

/**
 * Servlet implementation class ModifyEntry
 */
@WebServlet("/ModifyEntry")
public class ModifyEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		if(id!=null){
			String name = request.getParameter("name");
			
			String type = request.getParameter("type");
			
			String city = request.getParameter("city");
			
			String address = request.getParameter("address");
			
			int weight = Integer.valueOf(request.getParameter("weight"));
			
			Boolean isRecommended = Boolean.valueOf(request.getParameter("isRecommended"));
			
			String cipher = request.getParameter("cipher");
			
			double discount = Double.valueOf(request.getParameter("discount"));
			
			String shopName = request.getParameter("shopName");
			
			String shopLink = request.getParameter("shopLink");
			
			String description = request.getParameter("description");
			
			String date = request.getParameter("enterTime");
			
			int views = Integer.valueOf(request.getParameter("views"));
			
			Handicraft handicraft = new Handicraft(id,name,type,description,city,address,date,weight,views,isRecommended,cipher,discount,shopName,shopLink);
			
			File path = new File(this.getServletContext().getRealPath("/")+"db/handicrafts.xml");
			
			CraftDao dao = new CraftDao(path);
			
			dao.update(handicraft);
			
			request.getRequestDispatcher("inputHandicraft.jsp").forward(request, response);
		}else{
			
			response.setHeader("refresh", "2;URL=inputHandicraft.jsp");
			response.getWriter().write("该ID值修改失败");
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
