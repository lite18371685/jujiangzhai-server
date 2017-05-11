package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jujiangzhai.dao.impl.jdbc.CraftDao;
import cn.jujiangzhai.dao.impl.jdbc.ShopDao;

/**
 * Servlet implementation class DeleteShop
 */
@WebServlet("/DeleteShop")
public class DeleteShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		if(id!=null){
			
			File path = new File(this.getServletContext().getRealPath("/")+"db"+File.separator+"shops.xml");
			

			ShopDao dao = new ShopDao();
			
			dao.delete(id);
				
				
			File imgPath = new File(this.getServletContext().getRealPath("/")+"img"+File.separator+"shops"+File.separator+id+".jpg");
			if(imgPath.exists()){
				if(imgPath.isFile()){
					imgPath.delete ();
				}
			}
				
				
				request.getRequestDispatcher("inputShop.jsp").forward(request, response);
			
			
		}else{
			response.setHeader("refresh", "2;URL=inputShop.jsp");
			response.getWriter().write("ID为空");
			
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
