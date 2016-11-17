<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
<title>登入</title>
<style >
	fieldset{
		width:500px;
		margin:0 auto;
		border-radius:10px;
		
	}
	.s1{
 		text-align:center 
 		margin:0 auto;
	}
	.title{
		width:150px;
		float:left;
		text-align:right;
		padding-right:3px;
	}
</style>
</head>
<body>
	<form action="MemberServlet" method="post">
		<fieldset >
			<div ><label class="title" for="account">帳號:</label>
				<input type="text" id="account" name="member_id">
				<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>				
				<hr>
				<label class="title" for="pwd">密碼:</label>
				<input type="password" id="pwd" name="member_password1">
				<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} ${errorMsgs.Err} </font>
				<hr>
			<!-- 輸入驗證碼欄位 -->
				<label class="title" for="pwd">輸入驗證碼:</label>
				<input type="text" id="identity" name="identity">
			<!-- 驗證碼本人 -->	
<!-- 				<div align="center"><img src="IdentityServlet" id="pic" onload="btn.disabled=false;"/></div> -->
<%-- 				<br><font color="red" size="-1">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</font> --%>
<!-- 				<input type="button" id ="btn" value="換個圖片" onclick="reloadImage()" > -->
<!-- 試用jQuery -->				
				<div align="center"><img src="IdentityServlet" id="pic" /></div>
				<br><font color="red" size="-1">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</font>
				<input type="button" id ="btn" value="換個圖片" >
				
				<div align="center">
					<input  type="submit" value="登入">
					<input type="hidden" name="action" value="login">					
				</div>
				
			</div>
			<div>
			<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&redirect_uri=http://localhost:8081/testGoogle/Login.do&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email">以Google帳戶登入</a>
			</div>
						
  <script src="js/jquery-3.1.1.min.js"></script>
  <script>	
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
	
  <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
		</fieldset>
	</form>
</body>
</html>