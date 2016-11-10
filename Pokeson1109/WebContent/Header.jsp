<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Vegetable by TEMPLATED</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script src="js/jquery-1.9.1.js"></script>
<script src="js/jquery.jqpagination.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/heroic-features.css" rel="stylesheet">
<style>
	body {
	margin: 0;
	padding: 0;
/* 	background: #2A2A2A; */
	font-family: Microsoft JhengHei;
	font-size: 12px;
	color: #878787;
	}
	
	h1, h2, h3 {
		margin: 0;
		padding: 0;
		font-weight: normal;
		color: #000000;
	}
	
	h1 {
		font-size: 2em;
	}
	
	h2 {
		font-size: 2.4em;
	}
	
	h3 {
		font-size: 1.6em;
	}
	
	p, ul, ol {
		margin-top: 0;
		line-height: 180%;
	}
	
	ul, ol {
	}
	
	a {
		text-decoration: none;
		color: #000000;
	}
	
	a:hover {
	}
	
	#wrapper {
		width: 980px;
		height: 60px;
		margin: 0 auto;
		padding: 0;
		background:-webkit-linear-gradient(top,#1a1a23,#121ded);
	}
	
	/* Header */
	
	#header {
		clear: both;
		width: 980px;
		height: 276px;
		margin: 0px auto;
		padding: 0px 0px 0px 0px;
		background: url(images/img01.jpg) no-repeat left top;
	}
	
	/* Logo */
	
	#logo {
		margin: 0;
		padding: 0px 0px 0px 60px;
		color: #000000;
	}
	
	#logo h1, #logo p {
		margin: 0;
		padding: 0;
	}
	
	#logo h1 {
		padding-top: 20px;
		letter-spacing: -1px;
		text-transform: lowercase;
		font-size: 3.8em;
	}
	
	#logo p {
		margin: 0;
		padding: 0px 0 0 10px;
		font: normal 14px Georgia, "Times New Roman", Times, serif;
		font-style: italic;
		color: #FFFFFF;
	} 
	
	#logo a {
		border: none;
		background: none;
		text-decoration: none;
		color: #FFFFFF;
	}
	
	/* Search */
	
	#search {
		float: right;
		width: 280px;
	}
	
	#search form {
		height: 41px;
		margin: 0;
		padding: 17px 0 0 20px;
	}
	
	#search fieldset {
		margin: 0;
		padding: 0;
		border: none;
	}
	
	#search-text {
		width: 220px;
		padding: 8px 5px 6px 5px;
		border: none;
		background: #FFFFFF url(images/img02.jpg) no-repeat left top;
		text-transform: lowercase;
		font: normal 11px Arial, Helvetica, sans-serif;
		color: #000000;
	}
	
	#search-submit {
		width: 62px;
		height: 22px;
		border: none;
		background: none;
		color: #000000;
	}
	
	/* Menu */
	#headcolor{
		width: 1300px;
		height:30px;
		background-color: #666666;
		padding: 10px;
		margin: 0;
	}
	#menu1 {
		float: right;
		width: 680px;
		height: 10px;
		padding: 0px;
			
	}
	#menuhead a{
		color:white;
		font-size:16px;
		
	}
	#menuhead td{
		width:100px;
		height:20px
	}
	
	#menu {
		float: left;
		width: 680px;
		height: 60px;
		padding: 0px;
		
	}
	
	#menu ul {
		margin: 0;
		padding: 0px;
		list-style: none;
		line-height: normal;
	}
	
	#menu li {
		float: left;
		border-right: 1px solid #1A1A18;
		
	}
	
	#menu a {
		display: block;
		height: 30px;
		padding: 25px 20px 0px 20px;
		text-decoration: none;
		text-align: center;
		text-transform: uppercase;
		font-family: Arial, Helvetica, sans-serif;
		font-size:16px; 
		font-weight: bold;
		color: #FFFFFF;
		border: none;
	}
	
	#menu a:hover, #menu .current_page_item a,#menu .current_page_item a{
		text-decoration: none;
		color: #FFFFFF;
	}
	
	#menu,#menu1 .current_page_item a {
	}
	
	/* Page */
	
	#page {
		width: 900px;
		margin: 0px auto;
		padding: 0px 40px;
		background: #FFFFFF;
	}
	
	/* Content */
	
	#content {
	/* 	float:middle; */
	/* 	width: 900px; */
		padding: 30px 0px 0px 0px;
	}
	
	.post {
		margin-bottom: 40px;
	}
	
	.post-bgtop {
	}
	
	.post-bgbtm {
	}
	
	.post .title {
		height: 38px;
		margin-bottom: 10px;
		padding: 12px 0 0 0px;
		letter-spacing: -.5px;
		color: #000000;
	}
	
	.post .title a {
		color: #000000;
		border: none;
	}
	
	.post .meta {
		margin-bottom: 30px;
		padding: 5px 0px 15px 0px;
		text-align: left;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 13px;
		font-weight: bold;
	}
	
	.post .meta .date {
		float: left;
	}
	
	.post .meta .posted {
		float: right;
	}
	
	.post .meta a {
	}
	
	.post .entry {
		padding: 0px 0px 20px 0px;
		padding-bottom: 20px;
		text-align: justify;
	}
	
	.more {
		float: left;
		display: block;
		width: 120px;
		height: 23px;
		margin-right: 10px;
		padding: 2px 0px 0px 0px;
		background: url(images/img04.jpg) no-repeat left top;
		text-align: center;
		text-transform: uppercase;
		font-size: 10px;
		color: #333333;		
	}
</style>
</head>
<body>
<div id="headcolor">
<div id="menu1">
<!-- <div class="collapse navbar-collapse" -->
<!-- 			id="bs-example-navbar-collapse-1"> -->
<%-- 	<%session.setAttribute("target", "/Pokeson/index.jsp");%> --%>
		<c:set var="target" value="${pageContext.servletContext.contextPath}${pageContext.request.servletPath}" scope="session"/>
		
		<table id="menuhead">
			<tr>
				<td><a href="index.jsp">首頁</a></td>
				<c:if test="${empty mbr}">
					<td><a data-toggle="modal" id="myBtn">登入</a></td>
				</c:if>
				<c:if test="${not empty mbr}">		 
					<td><a href="testJSP.jsp">登出</a></td>
				</c:if>
					<td><a href="addMbr.jsp">註冊</a></td>
<%-- 				<c:if test="${empty mbr}"> --%>
<%-- 					<c:set var="target" value="${pageContext.servletContext.contextPath}/mbrZone.jsp" scope="session"/> --%>
<!-- 					<td><a data-toggle="modal" id="myMbr">會員專區</a></td>	 -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${not empty mbr}"> --%>
					<td><a href="${pageContext.servletContext.contextPath}/mbrZone.jsp">會員專區</a></td>
<%-- 				</c:if> --%>
			</tr>
		</table>
	</div>
	</div>
<!-- 	</div> -->

<div id="header">
	<div id="logo">
		<h1><a href="#">PokeSong </a></h1>
	</div>
</div>
<!-- end #header -->
<div id="wrapper">
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="#">電子產品</a></li>
			<li><a href="#">生活家電</a></li>
			<li><a href="#">零食餅乾</a></li>
			<li><a href="#">生鮮食品</a></li>
<!-- 			<li><a href="#">居家生活</a></li> -->
		</ul>
	</div>
	<!-- end #menu -->
	<div id="search" >
		<form method="get" action="#">
			<div>
				<input type="text" name="s" id="search-text" value="" />
			</div>
		</form>
	</div>
</div>

	<!---------------------------------------彈跳視窗--------------------------->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content -->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-lock"></span> 會員登入
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form role="form" action="MemberServlet" method="post">
							<div class="form-group">
								<label for="usrname"><span class="glyphicon glyphicon-user"></span> 帳號</label> 
								<input type="text" class="form-control" id="usrname" name="member_id" placeholder="Enter id">
								<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>
							</div>
							<div class="form-group">
								<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> 密碼</label> 
								<input type="password" class="form-control" id="psw" name="member_password1" placeholder="Enter password">
								<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} ${errorMsgs.Err} </font>
							</div>
							<div class="form-group">
								<!-- 輸入驗證碼欄位 -->
								<label for="identity">輸入驗證碼:</label>
								<input type="text" class="form-control" id="identity" name="identity" placeholder="不分大小寫">
								<br><font color="red" size="-1">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</font>
							</div>
							<img src="IdentityServlet" id="pic" style="width:100px; height:50px"/>
								<input type="button" id ="btn" value="換個圖片" >
<!-- 							<div class="checkbox"> -->
<!-- 								<label><input type="checkbox" value="" checked>Remember	me</label> -->
<!-- 							</div> -->
							<button type="submit" class="btn btn-success btn-block">
								<input type="hidden" name="action" value="login">
								<span class="glyphicon glyphicon-off"></span> 登入
							</button>
							
							<button class="btn btn-success btn-block">
							<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&redirect_uri=http://localhost:8081/testGoogle/Login.do&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email">以Google帳戶登入</a>				
							</button>
						</form>
					</div>
					<div class="modal-footer">
						<p>
							<a href="addMbr.jsp">註冊</a>
						</p>
						<p>
							<a href="#">忘記密碼?</a>
						</p>
					</div>
					
				</div>
			</div>
		</div>
 	<!------------------------------------  ---------------------------------------->
	
	<!------------------------  我的最愛  -------------------------->
<div id="myfavorite" style="position:fixed;top:100px;right:15px;z-index:9;font-size:9pt;"><div style="width:100px;height:100px;cursor:pointer;background:url(images/myfavorite.png);background-repeat:no-repeat;text-align:center;" onclick="to_myfavorite();"><div style="padding:24px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white">我的最愛</div></div></div>	
	<!------------------------  購物車  -------------------------->
<div id="chkout" style="position:fixed;top:200px;right:15px;z-index:10;font-size:9pt;"><div style="width:100px;height:100px;cursor:pointer;background:url(images/ShoppingCart.png);background-repeat:no-repeat;text-align:center;" onclick="to_mycar1();"><div style="padding:2px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white">結 帳</div><div style="font-weight:bold;color:red;font-family:arial;margin-top:-3px;">共 <span id="buycount_all_f" style="font-size:11pt;">0</span>  件</div></div>
<!-- shopping car -->
<script>
function to_mycar1(){document.location.href="${pageContext.servletContext.contextPath}/ShowCartContent.jsp"}
function to_mycar1_delay(){setTimeout('to_mycar1()', 500);}
</script>
	<!-- -----------------------modal ----------------------------------->
	
	<script>
		//登入的登入
		$(document).ready(function() {
			$("#myBtn").click(function() {
				$("#myModal").modal({
					backdrop : false
				});
			});
		});
	</script>
	<script>
	// 		驗證碼換張圖
	  	$(document).ready(function(){
	  		$("#btn").click(function(){  
	  			$.get("IdentityServlet",{},function(data){
	  				$("#pic").attr("src","IdentityServlet");
	  			})
	  		});
	  	});
	</script>
	<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
<form style="display:inline;" name="form_into" method="post" action="/product/mycar1"><input type="hidden" name="fr1" value="/"></form></div>
<!-- end #footer -->
</body>
</html>
