package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jujiangzhai.dao.impl.jdbc.TokenDao;
import cn.jujiangzhai.dao.impl.jdbc.UserDao;
import cn.jujiangzhai.util.Path;

/**
 * Servlet implementation class Collect
 */
@WebServlet("/Collect")
public class Collect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String token = request.getParameter("token");
		String itemId = request.getParameter("itemId");
		String type = request.getParameter("type");
		String action=request.getParameter("action");
		String msg=null;
		TokenDao tokenDao = new TokenDao();
		UserDao userDao = new UserDao();
		if ("cancel".equals(action)) {		//取消收藏接口
			int status = 0;
			if(token!=null&&type!=null&&itemId!=null){
				String id = tokenDao.queryByToken(token);
				if(id!=null){
					if("handicraft".equals(type)){
						userDao.cancelCollect(id, itemId);
						status=1;
						msg="取消收藏手工艺品成功";
					}else if("shop".equals(type)){
						userDao.cancelFollowUp(id, itemId);
						status=1;
						msg="取消收藏店铺成功";
					}
				}else{
					msg="该token找不到用户";
				}
			}else{
				msg="参数错误";
			}
			response.getWriter().write(""+status+":"+msg);
			
		}else{			//收藏接口
			int status = 0;
			if(token!=null&&type!=null&&itemId!=null){
//				System.out.println(token+type+itemId);
				String id = tokenDao.queryByToken(token);
				if(id!=null){
					if("handicraft".equals(type)){
						
						
						userDao.collect(id, itemId);
						status=1;
						msg="收藏手工艺品成功";
					
					}else if ("shop".equals(type)) {
						userDao.followUp(id, itemId);
						status=1;
						msg="收藏店铺成功";
					}
				}else{
					msg="该token找不到用户";
				}

				
				
			}else{
				msg="参数错误";
			}

			response.getWriter().write(""+status+":"+msg);
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
