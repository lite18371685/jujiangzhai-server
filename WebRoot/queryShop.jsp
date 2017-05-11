<%@page import="cn.jujiangzhai.dao.impl.jdbc.ShopDao,cn.jujiangzhai.entity.*"%>
<%@ page language="java" import="java.util.*,java.io.File" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
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
		File xmlPath = new File(application.getRealPath("/")+"db"+File.separator+"shops.xml");
		ShopDao dao = new ShopDao();
		Shop p =dao.queryById(id);
		pageContext.setAttribute("shop", p);
			}else{
		pageContext.setAttribute("hasId", false);
			}
			
			pageContext.setAttribute("imgPath", "img"+File.separator+"shops"+File.separator+id+".jpg");
	%>
	<div class="container">
	<div class="jumbotron">
	<a href="inputShop.jsp"><button class="btn btn-success">返回</button></a> <br/>
	<h1>
	查询记录如下<br/><br/>
	</h1>
	<c:if test="${hasId }">
	<h3>
	<span class="text-muted">id:</span> <span class="text-primary">${shop.id }</span> <br/><br/>
	<span class="text-muted">店名:</span><span class="text-primary"> ${shop.shopName }</span> <br/><br/>
	<span class="text-muted">城市:</span><span class="text-primary">${shop.city }</span> <br/><br/>
	<span class="text-muted">地址:</span><span class="text-primary">${shop.address }</span> <br/><br/>
	<span class="text-muted">店铺链接:</span><a href="${shop.shopLink }">${shop.shopLink }</span> </a><br/><br/>
	<span class="text-muted">经营范围:</span><span class="text-primary">${shop.businessScope}</span> <br/><br/>
	
	<span class="text-muted">详情页描述:</span><br/><br/>
	${shop.description }<br><br><br>
	
	<span class="text-muted">图片:</span><br><br>
	<img src="${imgPath }" class="img-rounded">
	</h3>

	
	</c:if>

	</div>
	</div>


  </body>
</html>
