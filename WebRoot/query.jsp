<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.jujiangzhai.entity.Handicraft"%>
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
	    if(request.getAttribute("handicraft")!=null){
	    	pageContext.setAttribute("hasParam", true);
	    	Handicraft h = (Handicraft)request.getAttribute("handicraft");
	    	
	    	pageContext.setAttribute("imgPath", "img/handicrafts/"+h.getId()+".jpg");
	    	
	    }else{
	    	pageContext.setAttribute("hasParam", false);
	    	
	    }
		
	 %>

	<div class="container">
	<div class="jumbotron">
	<a href="inputHandicraft.jsp"><button class="btn btn-success">返回</button></a> <br/>
	<h1>
	查询记录如下<br/><br/>
	</h1>
	<c:if test="${hasParam }">
	<h3>
	<span class="text-muted">id:</span> <span class="text-primary">${handicraft.id }</span> <br/><br/>
	<span class="text-muted">名称:</span><span class="text-primary"> ${handicraft.name }</span> <br/><br/>
	<span class="text-muted">种类:</span><span class="text-primary">${handicraft.type }</span> <br/><br/>
	<span class="text-muted">城市:</span><span class="text-primary">${handicraft.city }</span> <br/><br/>
	<span class="text-muted">地址:</span><span class="text-primary">${handicraft.address }</span> <br/><br/>
	<span class="text-muted">录入时间:</span><span class="text-primary">${handicraft.enterTime }</span> <br/><br/>
	<span class="text-muted">权重:</span><span class="text-primary">${handicraft.weight }</span> <br/><br/>
	<span class="text-muted">浏览次数:</span><span class="text-primary">${handicraft.views }</span> <br/><br/>
	<span class="text-muted">是否今日推荐:</span><span class="text-primary">${handicraft.isRecommended }</span> <br/><br/>
	<span class="text-muted">优惠暗号:</span><span class="text-primary">${handicraft.cipher }</span> <br/><br/>
	<span class="text-muted">优惠额度:</span><span class="text-primary">${handicraft.discount }</span> <br/><br/>
	<span class="text-muted">店铺名称:</span><span class="text-primary">${handicraft.shopName}</span> <br/><br/>
	<span class="text-muted">店铺链接:</span><a href="${handicraft.shopLink }">${handicraft.shopLink }</span> </a><br/><br/>
	<span class="text-muted">详情页描述:</span><br/><br/>
	${handicraft.description }<br><br><br>
	
	<span class="text-muted">图片:</span><br><br>
	<img src="${imgPath }" class="img-rounded">
	</h3>
	
	
	</c:if>

	</div>
	</div>

  </body>
</html>
