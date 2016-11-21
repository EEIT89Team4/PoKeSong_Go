<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>PoKeSon</title>
<script src="js/jquery-1.9.1.js"></script>
<!-- <script src="js/productindex.js"></script> -->
<!-- <link href="css/bootstrap.css" rel="stylesheet"> -->
<!-- <link href="style.css" rel="stylesheet" type="text/css" media="screen" /> -->
<!-- <script src="js/sweetalert.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="css/sweetalert.css"> -->
<style>
</style>
</head>
<c:if test="${not empty errorMsgs}">
<script>
$(function(){
alert("會員狀態異常,無法登入");
});
</script>
<% session.removeAttribute("errorMsgs"); %>
</c:if>
<jsp:include page="Header.jsp"/>
<body>

<c:set var="target" value="/index.jsp" scope="session" />

<div id="page">
<center>
	<div id="content">
		<div class="post">
			<div >
		<jsp:include page="/turnImg.jsp" />
		</div>
	
		</div>
<!-- 		<hr> -->
		<div class="post">
			<h2 class="title">熱門商品<span><img src="images/hot.jpg" style="width:70px;height:50px"/></span></h2>
			<div class="hotproduct">
			<jsp:include page="HotProduct.jsp"/>
			</div>
		</div>
<!-- 		<hr> -->
		<div class="post">
			<h2 class="title">新進商品<span><img src="images/new.jpg" style="width:50px;height:50px"/></span></h2>
			<div class="newproduct">
			<jsp:include page="NewProduct.jsp"/>
			</div>
		</div>
		
		<div style="clear: both;">&nbsp;</div>
	</div>

	<jsp:include page="footer.jsp"/>
	</center>
	</div>
<!-- 	<script type="text/javascript" src="js/jquery.min.js"></script> -->
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<!-- the jScrollPane script -->
<!-- 		<script type="text/javascript" src="js/jquery.mousewheel.js"></script> -->
		<script type="text/javascript" src="js/jquery.contentcarousel.js"></script>
		<script type="text/javascript">
			$('#ca-container').contentcarousel();
			$('#ca-containernew').contentcarousel();
		</script>
</body>
</html>
