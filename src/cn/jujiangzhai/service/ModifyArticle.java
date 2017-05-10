package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jujiangzhai.dao.impl.xml.ArticleDao;
import cn.jujiangzhai.dao.impl.xml.ShopDao;
import cn.jujiangzhai.entity.Article;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.util.Path;

/**
 * Servlet implementation class ModifyArticle
 */
@WebServlet("/ModifyArticle")
public class ModifyArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		if(id!=null){
			String title = request.getParameter("title");
			String type = request.getParameter("type");
			String description = request.getParameter("description");
			
			
			Article article = new Article(id, title, type, description);
			
			File path = new File(Path.getArticlesPath(this.getServletContext()));
			
			ArticleDao dao = new ArticleDao(path);
			
			dao.update(article);
			
			request.getRequestDispatcher("InputArticle.jsp").forward(request, response);

		}else{
			
			response.setHeader("refresh", "2;URL=InputArticle.jsp");
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
