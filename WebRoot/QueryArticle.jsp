<%@page import="cn.jujiangzhai.dao.impl.xml.ArticleDao"%>
<%@page import="cn.jujiangzhai.dao.impl.xml.ShopDao,cn.jujiangzhai.entity.*,cn.jujiangzhai.util.*"%>
<%@ page language="java" import="java.util.*,java.io.File" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="lib/bootstrap.min.css">
	<script src="lib/jquery.min.js"></script>
	<script src="lib/bootstrap.min.js"></script>
  </head>
  
  <body>
   
	<%
   		String id = request.getParameter("id");
   			if(id!=null){
   		pageContext.setAttribute("hasId", true);
   		File xmlPath = new File(Path.getArticlesPath(pageContext.getServletContext()));
   		ArticleDao dao = new ArticleDao(xmlPath);
   		Article a =dao.queryById(id);
   		pageContext.setAttribute("article", a);
   			}else{
   		pageContext.setAttribute("hasId", false);
   			}
   			
   			pageContext.setAttribute("imgPath", "img"+File.separator+"articles"+File.separator+id+".jpg");
   	%>
	 
	 <div class="container">
	<div class="jumbotron">
	<a href="InputArticle.jsp"><button class="btn btn-success">返回</button></a> <br/>
	<h1>
	查询记录如下<br/><br/>
	</h1>
	<c:if test="${hasId }">
	<h3>
	<span class="text-muted">id:</span> <span class="text-primary">${article.id }</span> <br/><br/>
	<span class="text-muted">标题:</span><span class="text-primary"> ${article.title }</span> <br/><br/>
	<span class="text-muted">类型:</span><span class="text-primary">${article.type }</span> <br/><br/>
	
	
	<span class="text-muted">详情页描述:</span><br/><br/>
	${article.description }<br><br><br>
	
	<span class="text-muted">图片:</span><br><br>
	<img src="${imgPath }" class="img-rounded">
	</h3>

	
	</c:if>

	</div>
	</div>
	 
  </body>
</html>
