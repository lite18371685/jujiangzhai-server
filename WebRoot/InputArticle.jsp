<%@page import="cn.jujiangzhai.dao.impl.xml.ArticleDao"%>

<%@page import="cn.jujiangzhai.util.Path"%>
<%@page import="cn.jujiangzhai.dao.impl.xml.ShopDao,cn.jujiangzhai.entity.*"%>
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
    
    <title>录入科普文章</title>
    
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
  	File xmlPath = new File(Path.getArticlesPath(pageContext.getServletContext()));
      ArticleDao dao = new ArticleDao(xmlPath);
      List<Article> list = dao.queryAll();
      if(list!=null){
     	 pageContext.setAttribute("list", list);
     	 pageContext.setAttribute("hasList", true);
      }else{
         	 pageContext.setAttribute("hasList", false);
      
      }
  %>
     
       
	<div class="container">
		<div class="jumbotron">
			<a href="manage.html"><button class="btn btn-success">返回</button></a>
			<h2 class="text-center">录入科普文章</h2>
			<br>
			<br>
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<table class="table ">
						<caption>所有已录入记录</caption>
						<thead>
							<tr>
								
								<th>id</th>
								<th>标题</th>
								<th>类型</th>
								<th>其他操作</th>
								
							</tr>
						</thead>
						<tbody>
						

						<c:if test="${hasList}" >
							
						<c:forEach var="h" items="${ list}">
							
						<tr>
						<td>
							${ h.id}
						</td>
						<td>
							${h.title }
						</td>
						<td>
							${h.type} 
						</td>
						
						<td>
						<a href="QueryArticle.jsp?id=${h.id }"><button class="btn  btn-sm btn-success">详情</button></a>
						<a href="ModifyArticle.jsp?id=${h.id } "><button class="btn  btn-sm btn-success">修改</button></a>
						<a href="DeleteArticle?id=${h.id } "><button class="btn btn-sm btn-success">删除</button></a>
						</td>
						</c:forEach>	
						</c:if>
			
						
					
					
						</tbody>
						
					</table>
				</div>
			</div>
			
			<br>
			<br>
			<form role="form" action="AddArticle"  enctype="multipart/form-data" method="post" class="form-horizontal">
			
		
				<div class="form-group">
					<label for="title" class="col-sm-2 col-sm-offset-1 control-label">标题:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="title" name="title"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				<div class="form-group">
					<label for="type" class="col-sm-2 col-sm-offset-1 control-label">类型:</label>
					<div class="col-sm-7">
						<select class="form-control" name="type" id="type">
   						    <option value="其他">其他</option>
							<option value="茶具">茶具</option>
							<option value="蜡艺">蜡艺</option>
							<option value="字画">字画</option>
							<option value="玉石">玉石</option>
							<option value="陶瓷">陶瓷</option>
							<option value="铜艺">铜艺</option>
							<option value="服饰">服饰</option>
							<option value="文玩">文玩</option>
							<option value="家具">家具</option>
							<option value="小食">小食</option>
							<option value="饰品">饰品</option>
							<option value="塑品">塑品</option>
							<option value="戏剧">戏剧</option>
							<option value="编织">编织</option>
							<option value="布艺">布艺</option>
							<option value="风筝">风筝</option>
						</select> 
					</div>
					<div class="col-sm-2"></div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 col-sm-offset-1 control-label">文章详情:</label>
					<div class="col-sm-7">
						<textarea class="form-control" rows="10" id="description" name="description" placeholder="科普文章的内容，详情页显示"></textarea> 
					</div>
					<div class="col-sm-2"></div>
				</div>	
			
				
				<div class="row">
					<div class="col-sm-3"></div>
						
					<p class="col-sm-8">
					
					<span>上传jpg格式的图片,体积不要过大</span>
					<input type="file"  id="uploadImg" name="uploadImg" >	
						
				
				</p>
				
					
				</div>
				<p class="text-center">
					<button type="submit" class="btn btn-primary ">&nbsp;录入记录&nbsp;</button>
				</p>
			
			</form>
			
			
</div>
</div>
      </body>
</html>
