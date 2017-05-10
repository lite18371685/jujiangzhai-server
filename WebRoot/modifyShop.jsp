<%@page
	import="cn.jujiangzhai.dao.impl.xml.ShopDao,cn.jujiangzhai.entity.Shop"%>
<%@ page language="java" import="java.util.*,java.io.File"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
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
		File xmlPath = new File(application.getRealPath("/") + "db" + File.separator + "shops.xml");
		ShopDao dao = new ShopDao(xmlPath);
		Shop p = dao.queryById(id);
		pageContext.setAttribute("shop", p);
			} else {
		pageContext.setAttribute("hasId", false);
			}
	%>


	<div class="container">
		<div class="jumbotron">
			<a href="inputShop.jsp"><button class="btn btn-success">返回</button></a>
			<br />
			<h1 class="text-center">修改记录</h1>
			<br /> <br />

			<c:if test="${hasId }">

				<form role="form" action="ModifyShop" method="post"
					class="form-horizontal">


					<div class="form-group">
						<label for="shopName"
							class="col-sm-2 col-sm-offset-1 control-label">店名:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="shopName"
								name="shopName" value="${shop.shopName }">
						</div>
						<div class="col-sm-2"></div>
					</div>


					<div class="form-group">
						<label for="city" class="col-sm-2 col-sm-offset-1 control-label">城市:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="city" name="city"
								value="${shop.city }">
						</div>
						<div class="col-sm-2"></div>
					</div>

					<div class="form-group">
						<label for="address"
							class="col-sm-2 col-sm-offset-1 control-label">地址:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="address"
								name="address" value="${shop.address }">
						</div>
						<div class="col-sm-2"></div>
					</div>
					<div class="form-group">
						<label for="shopLink"
							class="col-sm-2 col-sm-offset-1 control-label">店铺链接:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="shopLink"
								value="${shop.shopLink }" name="shopLink">
						</div>
						<div class="col-sm-2"></div>
					</div>
					<div class="form-group">
						<label for="businessScope"
							class="col-sm-2 col-sm-offset-1 control-label">经营范围:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="businessScope"
								name="businessScope" value="${shop.businessScope }">
						</div>
						<div class="col-sm-2"></div>
					</div>

					<div class="form-group">
						<label for="description"
							class="col-sm-2 col-sm-offset-1 control-label">店铺描述:</label>
						<div class="col-sm-7">
							<textarea class="form-control" rows="5" id="description"
								name="description" placeholder="店铺的描述,用于店铺详情页展示"></textarea>
						</div>
						<div class="col-sm-2"></div>
					</div>


					<input type="hidden" name="id" value="${shop.id }" />
					<p class="text-center">
						<button type="submit" class="btn btn-primary ">&nbsp;修改记录&nbsp;</button>
					</p>

				</form>

				<script type="text/javascript">
					var str = '${shop.description}';
					document.getElementById("description").innerHTML = str;
				</script>

			</c:if>
</body>
</html>
