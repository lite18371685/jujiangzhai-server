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
import cn.jujiangzhai.entity.User;
import cn.jujiangzhai.entity.info.TokenInfo;
import cn.jujiangzhai.util.Path;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String  userName = request.getParameter("userName");
		
		String  password = request.getParameter("password");
		
		String token = null;

		if(userName!=null&&password!=null){
			
			UserDao dao = new UserDao();
			
			User user = dao.queryByUserName(userName);
			
			if(user!=null){
				if(userName.equals(user.getUserName())&&password.equals(user.getUserPassword())){
					TokenDao tokenDao = new TokenDao();
					token = tokenDao.insert(user.getId());
					TokenInfo info = new TokenInfo(1,token);
					response.getWriter().write(JSONObject.fromObject(info).toString());
				}else {
					TokenInfo info = new TokenInfo(0,null);
					response.getWriter().write(JSONObject.fromObject(info).toString());
				}
				
			}else{
				response.getWriter().write(JSONObject.fromObject(new TokenInfo(0,null)).toString());
			}
		
			
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
