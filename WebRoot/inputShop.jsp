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
    
    <title>录入店铺记录</title>
    
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
    	File xmlPath = new File(pageContext.getServletContext().getRealPath("/")+"db/shops.xml");
        ShopDao dao = new ShopDao();
        List<Shop> list = dao.queryAll();
        if(list!=null){
       	 pageContext.setAttribute("shopList", list);
       	 pageContext.setAttribute("hasList", true);
        }else{
           	 pageContext.setAttribute("hasList", false);
        
        }
    %>
    
    
	<div class="container">
		<div class="jumbotron">
			<a href="manage.html"><button class="btn btn-success">返回</button></a>
			<h2 class="text-center">录入店铺记录</h2>
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
								<th>店名</th>
								<th>城市</th>
								<th>地址</th>
								<th>经营范围</th>	
								<th>店铺链接</th>
								<th>其他操作</th>
								
							</tr>
						</thead>
						<tbody>
						

						<c:if test="${hasList}" >
							
						<c:forEach var="h" items="${shopList}">
							
						<tr>
						<td>
							${ h.id}
						</td>
						<td>
							${h.shopName }
						</td>
						<td>
							${h.city} 
						</td>
						<td>
							${h.address}
						</td>
						<td>
							${h.businessScope }
						</td>
						<td>
							${h.shopLink }
						</td>
						<td>
						<a href="queryShop.jsp?id=${h.id }"><button class="btn  btn-sm btn-success">详情</button></a>
						<a href="modifyShop.jsp?id=${h.id } "><button class="btn  btn-sm btn-success">修改</button></a>
						<a href="DeleteShop?id=${h.id } "><button class="btn btn-sm btn-success">删除</button></a>
						</td>
						</c:forEach>	
						</c:if>
			
						
					
					
						</tbody>
						
					</table>
				</div>
			</div>
			
			<br>
			<br>
			<form role="form" action="AddShop"  enctype="multipart/form-data" method="post" class="form-horizontal">
			
		
				<div class="form-group">
					<label for="shopName" class="col-sm-2 col-sm-offset-1 control-label">店名:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="shopName" name="shopName"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
				
					
				<div class="form-group">
					<label for="city" class="col-sm-2 col-sm-offset-1 control-label">城市:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="city" name="city"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
								
				<div class="form-group">
					<label for="address" class="col-sm-2 col-sm-offset-1 control-label">地址:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="address" name="address"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
					<div class="form-group">
					<label for="shopLink" class="col-sm-2 col-sm-offset-1 control-label">店铺链接:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="shopLink" name="shopLink"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
					<div class="form-group">
					<label for="businessScope" class="col-sm-2 col-sm-offset-1 control-label">经营范围:</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="businessScope" name="businessScope" placeholder="经营的手工艺品分类,用英文逗号分隔"
							>
					</div>
					<div class="col-sm-2"></div>
				</div>
				
				<div class="form-group">
					<label for="description" class="col-sm-2 col-sm-offset-1 control-label">店铺描述:</label>
					<div class="col-sm-7">
						<textarea class="form-control" rows="5" id="description" name="description" placeholder="店铺的描述,用于店铺详情页展示"></textarea> 
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
