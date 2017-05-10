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
	    if(request.getAttribute("handicraft")!=null){
	    	pageContext.setAttribute("hasParam", true);
	    	

	    	
	    }else{
	    	pageContext.setAttribute("hasParam", false);
	    	
	    }
	    
		
	 %>
	 
	<div class="container">
	<div class="jumbotron">
	<a href="inputHandicraft.jsp"><button class="btn btn-success">返回</button></a> <br/>
	<h1 class="text-center">修改记录</h1> <br/><br/>

	<c:if test="${hasParam }">
	
	<form role="form" action="ModifyEntry" method="post"
				class="form-horizontal">
				
				<div class="form-group">
					<label for="name" class="col-sm-2 col-sm-offset-1 control-label">名字:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="name" name="name"
							value="${handicraft.name }" >
					</div>
					<div class="col-sm-2"></div>
				</div>
				<div class="form-group">
					<label for="views" class="col-sm-2 col-sm-offset-1 control-label">浏览次数:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="views" name="views"
							value="${handicraft.views }" >
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
					<label for="city" class="col-sm-2 col-sm-offset-1 control-label">城市:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="city" name="city"
							value="${handicraft.city }">
					</div>
					<div class="col-sm-2"></div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-2 col-sm-offset-1 control-label">地址:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="address" name="address"
							value="${handicraft.address }">
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				<div class="form-group">
					<label for="weight" class="col-sm-2 col-sm-offset-1 control-label">权重:</label>
					<div class="col-sm-7">
						<select class="form-control" name="weight" id="weight">
							<option value="1" <c:if test="${handicraft.weight eq 1}">selected="selected"</c:if>>1</option>
							<option value="2" <c:if test="${handicraft.weight eq 2}">selected="selected"</c:if>>2</option>
							<option value="3" <c:if test="${handicraft.weight eq 3}">selected="selected"</c:if>>3</option>
							<option value="4" <c:if test="${handicraft.weight eq 4}">selected="selected"</c:if>>4</option>
							<option value="5" <c:if test="${handicraft.weight eq 5}">selected="selected"</c:if>>5</option>
			
						</select> 
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				
				<div class="form-group">
					<label for="isRecommended" class="col-sm-2 col-sm-offset-1 control-label">是否今日推荐:</label>
					<div class="col-sm-7">
						<select class="form-control" name="isRecommended" id="isRecommended">
							<option value="false" >否</option>
							<option value="true" <c:if test="${handicraft.isRecommended }">selected="selected"</c:if>>是</option>							
						</select> 
					</div>
					<div class="col-sm-2"></div>

				</div>
				<div class="form-group">
					<label for="cipher" class="col-sm-2 col-sm-offset-1 control-label">优惠暗号:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="cipher" name="cipher" value="${handicraft.cipher }">
					</div>
					<div class="col-sm-2"></div>
				</div>


				<div class="form-group">
					<label for="discount" class="col-sm-2 col-sm-offset-1 control-label">优惠金额:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="discount" name="discount"  value="${handicraft.discount }"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>

				<div class="form-group">
					<label for="shopName" class="col-sm-2 col-sm-offset-1 control-label">店铺名称:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="shopName" name="shopName" value="${handicraft.shopName }">
					</div>
					<div class="col-sm-2"></div>
				</div>				

				<div class="form-group">
					<label for="shopLink" class="col-sm-2 col-sm-offset-1 control-label">店铺链接:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="shopLink" name="shopLink" value="${handicraft.shopLink }">
					</div>
					<div class="col-sm-2"></div>
				</div>	
				<div class="form-group">
					<label for="description" class="col-sm-2 col-sm-offset-1 control-label">手工艺品描述:</label>
					<div class="col-sm-7">
						<textarea class="form-control" rows="5" id="description" name="description" value="${handicraft.description }"></textarea> 
					</div>
					<div class="col-sm-2"></div>
				</div>	

	
				<input type="hidden"  name="id" value="${handicraft.id }"/>
				<input type="hidden" name="enterTime" value="${ handicraft.enterTime}"/>
				
	
				<p class="text-center">
					<button type="submit" class="btn btn-primary ">&nbsp;修改记录&nbsp;</button>
				</p>
			</form>
	
	

	<script type="text/javascript">
	var str = '${handicraft.description}';
	document.getElementById("description").innerHTML=str;
	</script>

	
	</c:if>
	
	
	
	
	</div>
	</div>
	
  </body>
</html>
