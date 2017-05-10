package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jujiangzhai.dao.impl.xml.TokenDao;
import cn.jujiangzhai.dao.impl.xml.UserDao;
import cn.jujiangzhai.entity.TokenInfo;
import cn.jujiangzhai.entity.User;
import cn.jujiangzhai.util.Path;
import cn.jujiangzhai.util.Utils;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		
		String  userName = request.getParameter("userName");
		
		String  password = request.getParameter("password");
		
		UserDao userDao = new UserDao(new File(Path.getUsersPath(this.getServletContext())));
		
		if(userName!=null&&password!=null){
			if(userDao.queryByUserName(userName)==null){	//若数据库中不存在重名用户,则添加
				User newUser = new User();
				newUser.setId(Utils.getUUID());
				newUser.setUserName(userName);
				newUser.setUserPassword(password);
				
				userDao.insert(newUser);
				
				TokenDao tokenDao = new TokenDao(new File(Path.getTokensPath(this.getServletContext())));
				
				String token = tokenDao.insert(newUser.getId());
				
				response.getWriter().write(JSONObject.fromObject(new TokenInfo(1,token)).toString());
				
			}else{	//重名则返回错误
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
