package cn.jujiangzhai.util;

import java.io.File;

import javax.servlet.ServletContext;

public class Path {

	public static final String SERVER_IP = "35.185.145.60";
	
	private static final String HANDICRAFTSXML_PATH="db"+File.separator+"handicrafts.xml";
	
	private static final String USERSXML_PATH="db"+File.separator+"users.xml";
	
	private static final String ARTICLESXML_PATH="db"+File.separator+"articles.xml";
	
	private static final String SHOP_PATH="db"+File.separator+"shops.xml";
	
	private static final String TOKEN_PATH="db"+File.separator+"tokens.xml";
	
	public static String getTokensPath(ServletContext context) {
		
		return context.getRealPath("/")+TOKEN_PATH;
		
	}
	
	public static String getHandicraftsPath(ServletContext context){
		
		return context.getRealPath("/")+HANDICRAFTSXML_PATH;
		
	}
	
	public static String getUsersPath(ServletContext context) {
		
		return context.getRealPath("/")+USERSXML_PATH;
		
	}
	
	public static String  getArticlesPath(ServletContext context) {
		
		
		return context.getRealPath("/")+ARTICLESXML_PATH;
	}

	public static String getShopPath(ServletContext context) {
		return context.getRealPath("/")+SHOP_PATH;
	}
}
