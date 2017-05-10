package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jujiangzhai.dao.impl.xml.ShopDao;
import cn.jujiangzhai.entity.Shop;

/**
 * Servlet implementation class ModifyShop
 */
@WebServlet("/ModifyShop")
public class ModifyShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		if(id!=null){
			String shopName = request.getParameter("shopName");
			String shopLink = request.getParameter("shopLink");
			String description = request.getParameter("description");
			String city = request.getParameter("city");
			String businessScope = request.getParameter("businessScope");
			String address = request.getParameter("address");
			
			Shop shop = new Shop(id, shopName, shopLink, city, address, description, businessScope);
			
			File path = new File(this.getServletContext().getRealPath("/")+"db"+File.separator+"shops.xml");
			
			ShopDao dao = new ShopDao(path);
			
			dao.update(shop);
			
			request.getRequestDispatcher("inputShop.jsp").forward(request, response);

		}else{
			
			response.setHeader("refresh", "2;URL=inputShop.jsp");
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
