<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>PoKeSon</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Vegetable by TEMPLATED</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/productindex.js"></script>
<script src="js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style>
#membermenu {
   margin: auto; 
}
.main {
/*   margin-right: 30px; */
  position:absolute;
/*   background-color: 	#00BBFF; */
  color: white;
  font-family: 微軟正黑體;
  font-weight: bold;
  font-size: 20px;
  cursor: pointer;
  text-align: center;
  height: 100px;
  line-height: 35px;
  width: 100px;
  z-index:12;
}

.main:hover {
/*    background-color: #CCEEFF;  */
}

.sub {
  position:absolute;  
  cursor: pointer;
  background-color:#00BBFF;
  color: white;
  font-family: 微軟正黑體;
/*   text-align: center; */
  font-weight: bold;
  width: 200px;
  z-index:11;
}
.sub ul {
  width:200px;
  list-style-type: none; 
  line-height: 100px;
}

.sub li {
  font-size: 16px;
  width:220px;
  color:white;
  list-style-type: none;
  padding-right:80px;
  line-height: 100px;
  height: 70px
}
.sub li a{
color:white;
/*   font-size: 16px; */
/*   width:200px; */
/*   list-style-type: none; */
/*    padding-right:100px;  */
/*   line-height: 100px; */
/*    height: 70px */
}
</style>
</head>
<body>
<c:set var="target" value="${pageContext.servletContext.contextPath}${pageContext.request.servletPath}" scope="session"/>
<div id="headcolor">
<div id="menu1">
		<table id="menuhead">
			<tr>
				<td><a href="index.jsp">首頁</a></td>
				<c:if test="${empty mbr}">
					<td><a href="#" id="myBtn">登入</a></td>
				</c:if>
				<c:if test="${not empty mbr}">		 
					<td><a href="#" id="signOut">登出</a></td>
				</c:if>
					<td><a href="addMbr.jsp">註冊</a></td>
<!-- 					<td><a href="mbrZone.jsp">會員專區</a></td>	 -->
						
				<c:if test="${not empty mbr}">		 
					<td><h4 style="color:#00BBFF">${mbr.member_name}  你好</a></h4></td>
				</c:if>
			</tr>
		</table>
	</div>
	</div>
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
<!---------------------------------------登入彈跳視窗--------------------------->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
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
								<input type="text" class="form-control" id="usrname" name="member_id" placeholder="Enter email">
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
							<div class="checkbox">
								<label><input type="checkbox" value="" checked>Remember	me</label>
							</div>
							<button type="submit" class="btn btn-success btn-block">
								<input type="hidden" name="action" value="login">
								<span class="glyphicon glyphicon-off"></span> 登入
							</button>
							
							<button class="btn btn-success btn-block">
							<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&redirect_uri=http://localhost:8081/Pokeson/Login.do&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email">以Google帳戶登入</a>				
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
 	<!------------------------------------登出  ---------------------------------------->
	<script>
	$(function(){
		$('#signOut').click(function(){
			$.get('MemberServlet',{action:"signOut"},function(){
				document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8081/Pokeson/productindex.jsp";
			})
			
		})
		});
    </script>
    <!--------------------------------------------------------------------------------->
	
	
<div id="membermenu" style="position:fixed;top:260px;right:20px;font-size:9pt;"><div class="main"><div style="padding:0px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white;z-index:20"><div style="width:100px;height:100px;cursor:pointer;background:url(images/membermenu1.png);background-repeat:no-repeat;text-align:center;"">會員專區</div></div></div>
					<div class="sub" style="left:-60px;top:100px">
                    <ul>
                  <li><a href="#">修改/查詢會員資料</a></li>
                  <li><a href="OrderList.jsp">查詢歷史訂單</a></li>
<!--                   <li></li> -->
                    </ul></div></div>
    	
<div id="myfavorite" style="position:fixed;top:160px;right:15px;z-index:9;font-size:9pt;"><div style="width:100px;height:100px;cursor:pointer;background:url(images/myfavorite.png);background-repeat:no-repeat;text-align:center;" onclick="to_myfavorite();"><div style="padding:24px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white">我的最愛</div></div></div>	
<div id="chkout" style="position:fixed;top:50px;right:15px;z-index:10;font-size:9pt;"><div style="width:100px;height:100px;cursor:pointer;background:url(images/ShoppingCart.png);background-repeat:no-repeat;text-align:center;" onclick="to_mycar1();"><div style="padding:2px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white">結 帳</div><div style="font-weight:bold;color:yellow;font-family:arial;margin-top:15px;margin-right:-55px"><span id="buycount_all_f" style="font-size:18pt;">${mycart.productMap.size()}</span></div></div>
<!-- shopping car -->

<script>
function to_mycar1(){document.location.href="${pageContext.servletContext.contextPath}/ShowCartContent.jsp"}
function to_myfavorite(){document.location.href="${pageContext.servletContext.contextPath}/MyFavoriteServlet?myfavoriteaction=GET_ONE&member_no="+${MemberVO.member_no}+"" }
</script>
<!-- -----------------------modal ----------------------------------->
	
	<script>
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
	//   			alert("hi");
	  			$.get("IdentityServlet",{},function(data){
	//   				$("#pic").attr("src","${pageContext.servletContext.contextPath}/IdentityServlet");
	  				$("#pic").attr("src","IdentityServlet");
	  			})
	  		});
	  	});
	</script>
	<script>
	$(function(){
		$(".sub").slideUp(0)
		  for (i = 0; i < $(".main").length; i++) {
		    $(".main:eq(" + i + ")").click({
		      id: i
		    }, function(e) {
		      n = e.data.id
// 		      $(".main:eq(" + n + ")").text(newtext[n])
		      $(".sub:eq(" + n + ")").slideToggle()
		      $(".sub:not(:eq(" + n + "))").slideUp()
		    })
		    $(".main").mouseout({
		      id: i
		    }, function(e) {
		      n = e.data.id
		        // $(".sub").slideUp(200)
// 		      $(".main:eq(" + n + ")").text(oldtext[n])
		    })
		  }
		$("#membermenu").css("width", $(".main").length * 100)
	})
	
	</script>
	<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
	<script src="js/bootstrap.min.js"></script>
<form style="display:inline;" name="form_into" method="post" action="/product/mycar1"><input type="hidden" name="fr1" value="/"></form></div>
<!-- end #footer -->
</body>
</html>
