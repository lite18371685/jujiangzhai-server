<%@page import="cn.jujiangzhai.dao.impl.jdbc.CraftDao"%>
<%@page import="cn.jujiangzhai.entity.Handicraft"%>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>录入手工艺品记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="add handicrafts">

	<link rel="stylesheet" type="text/css" href="lib/bootstrap.min.css">
	<script src="lib/jquery.min.js"></script>
	<script src="lib/bootstrap.min.js"></script>

<body>

	<%
	     CraftDao dao = new CraftDao();
	     List<Handicraft> list = dao.queryAll();
	     if(list!=null){
	   	 pageContext.setAttribute("handicrafts", list);
	   	 pageContext.setAttribute("hasList", true);
	    }else{
	       	 pageContext.setAttribute("hasList", false);
	    
	    }
	%>

	<div class="container">
		<div class="jumbotron">
			<a href="manage.html"><button class="btn btn-success">返回</button></a>
			<h2 class="text-center">录入手工艺品记录</h2>
			<br>
			<br>
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<table class="table ">
						<caption>所有已录入记录</caption>
						<thead>
							<tr>
								
								<th>名字</th>
								<th>类型</th>
								<th>城市</th>
								<th>权重</th>
								<th>录入时间</th>	
								<th>店铺名称</th>
								<th>其他操作</th>
								
							</tr>
						</thead>
						<tbody>
						

						<c:if test="${hasList}" >
							
						<c:forEach var="h" items="${handicrafts}">
							
						<tr>
						<td>
							${ h.name}
						</td>
						<td>
							${h.type }
						</td>
						<td>
							${h.city} 
						</td>
						<td>
							${h.weight}
						</td>
						<td>
							${h.enterTime }
						</td>
						<td>
							${h.shopName }
						</td>
						<td>
						<a href="Query?id=${h.id }"><button class="btn  btn-sm btn-success">详情</button></a>
						<a href="Modify?id=${h.id }"><button class="btn  btn-sm btn-success">修改</button></a>
						<a href="Delete?id=${h.id }"><button class="btn btn-sm btn-success">删除</button></a>
						</td>
						</c:forEach>	
						</c:if>
			
						
					
					
						</tbody>
						
					</table>
				</div>
			</div>
			

			<br>
			<br>
			<form role="form" action="AddEntry"  enctype="multipart/form-data" method="post" class="form-horizontal">
				
				<div class="form-group">
					<label for="name" class="col-sm-2 col-sm-offset-1 control-label">名字:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="手工艺品的名字">
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
					<label for="city" class="col-sm-2 col-sm-offset-1 control-label">城市:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="city" name="city"
							placeholder="所在城市">
					</div>
					<div class="col-sm-2"></div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-2 col-sm-offset-1 control-label">地址:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="address" name="address"
							placeholder="所在地址">
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				<div class="form-group">
					<label for="weight" class="col-sm-2 col-sm-offset-1 control-label">权重:</label>
					<div class="col-sm-7">
						<select class="form-control" name="weight" id="weight">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
			
						</select> 
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				
				<div class="form-group">
					<label for="isRecommended" class="col-sm-2 col-sm-offset-1 control-label">是否今日推荐:</label>
					<div class="col-sm-7">
						<select class="form-control" name="isRecommended" id="isRecommended">
							<option value="true">是</option>
							<option value="false">否</option>
						</select> 
					</div>
					<div class="col-sm-2"></div>

				</div>
				<div class="form-group">
					<label for="cipher" class="col-sm-2 col-sm-offset-1 control-label">优惠暗号:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="cipher" name="cipher"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>


				<div class="form-group">
					<label for="discount" class="col-sm-2 col-sm-offset-1 control-label">优惠金额:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="discount" name="discount" placeholder="必须是纯数字,可以是小数"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>

				<div class="form-group">
					<label for="shopName" class="col-sm-2 col-sm-offset-1 control-label">店铺名称:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="shopName" name="shopName">
					</div>
					<div class="col-sm-2"></div>
				</div>				

				<div class="form-group">
					<label for="shopLink" class="col-sm-2 col-sm-offset-1 control-label">店铺链接:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="shopLink" name="shopLink" placeholder="店铺的淘宝链接,地址需要完整,如https://www.baidu.com">
					</div>
					<div class="col-sm-2"></div>
				</div>	
				<div class="form-group">
					<label for="description" class="col-sm-2 col-sm-offset-1 control-label">手工艺品描述:</label>
					<div class="col-sm-7">
						<textarea class="form-control" rows="5" id="description" name="description" placeholder="手工艺品的描述,用于详情页展示"></textarea> 
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
