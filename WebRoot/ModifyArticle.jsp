<%@page
	import="cn.jujiangzhai.dao.impl.jdbc.*,cn.jujiangzhai.entity.*,cn.jujiangzhai.util.*"%>
<%@ page language="java" import="java.util.*,java.io.File"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>修改页面</title>
    
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
			if (id != null) {
		pageContext.setAttribute("hasId", true);
		File xmlPath = new File(Path.getArticlesPath(application));
		ArticleDao dao = new ArticleDao();
		Article a =dao.queryById(id);
		pageContext.setAttribute("article", a);
			} else {
		pageContext.setAttribute("hasId", false);
			}
	%>

	<div class="container">
		<div class="jumbotron">
			<a href="InputArticle.jsp"><button class="btn btn-success">返回</button></a>
			<br />
			<h1 class="text-center">修改记录</h1>
			<br /> <br />

			<c:if test="${hasId }">

				<form role="form" action="ModifyArticle" method="post"
					class="form-horizontal">


				<div class="form-group">
					<label for="title" class="col-sm-2 col-sm-offset-1 control-label">标题:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="title" name="title" value="${article.title }"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				<div class="form-group">
					<label for="type" class="col-sm-2 col-sm-offset-1 control-label">类型:<span class="text-danger">(需要重新选择)</span></label>
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
			

					<input type="hidden" name="id" value="${article.id }" />
					<p class="text-center">
						<button type="submit" class="btn btn-primary ">&nbsp;修改记录&nbsp;</button>
					</p>

				</form>

				<script type="text/javascript">
					var str = '${article.description}';
					document.getElementById("description").innerHTML = str;
				</script>

			</c:if>
  </body>
</html>
