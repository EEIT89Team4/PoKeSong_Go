<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	MemberVO mbr = (MemberVO)request.getSession().getAttribute("mbr"); 
	//有這行EL才取得到值,加上getSession()才能取得地址欄
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>修改會員資料</title>
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
		<script src="js/jquery-3.1.1.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
		<script type="text/javascript" src="js/jquery.plugin.js"></script>
		<script type="text/javascript" src="js/jquery.datepick.js"></script>
		
	    <!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet">

	    <!-- Custom styles for this template -->
	    <link href="css/jumbotron-narrow.css" rel="stylesheet">
	
	</head>
  <body>
	<jsp:include page="Header.jsp"/>
    <div class="container">
      <div class="header clearfix">
        <h3 class="text-muted">親愛的 ${mbr.member_name} 你好!!</h3>
      </div>
		<ul class="nav nav-tabs">
		  <li role="presentation"><a href="MemberServlet?action=getMbr&member_no=${mbr.member_no}">修改會員資料</a></li>
		  <li role="presentation"><a href="updatePwd.jsp">修改密碼</a></li>
		</ul>
      <div class="jumbotron">
<!--         <h1>Jumbotron heading</h1> -->
<!--         <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p> -->
        	<form action="MemberServlet" method="post">
			<fieldset >
				<div >
					<label class="title" for="no">會員編號:</label>
					<font>${mbr.member_no}</font>
					<hr>
<!-- 					<label class="title" for="name">姓名:</label> -->
<%-- 					<input type="text" id="name" name="member_name" value="${mbr.member_name}" > --%>
<%-- 					<br><font class="s1" color="red" size="-1" >${errorMsgs.ErrNameEmpty}  ${errorMsgs.ErrNameFormat}</font> --%>
<!-- 					<hr> -->
<!-- 					<label class="title" for="account">帳號:</label> -->
<%-- 					<font>${mbr.member_id}</font> --%>
<!-- 					<hr> -->
					<label class="title" for="pwd">請輸入新密碼:</label>
					<input type="password" id="pwd" name="member_password1">
					<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} </font>
					<hr>
					<label class="title" for="pwd2">再次確認新密碼:</label>
					<input type="password" id="pwd2" name="member_password2">
					<br><font color="red" size="-1">${errorMsgs.ErrPassword}</font>
					<hr>
<!-- 					<label class="title" for="bdayNgender">生日與性別:</label> -->
<!-- 		<!---------------------------------生日選單選擇-------------------------------- -->
<%-- 					<input type="text" id="bday" name="member_birthday" value="${mbr.member_birthday}"> --%>
	
<%-- 					<font>${mbr.member_gender}</font> --%>
<!-- 					<hr> -->
<!-- 					<label class="title" for="phone">電話:</label> -->
<%-- 					<input type="text" id="phone" name="member_phone" value="${mbr.member_phone}"> --%>
<%-- 					<br><font color="red" size="-1">${errorMsgs.ErrPhoneEmpty}  ${errorMsgs.ErrPhoneFormat}</font>				 --%>
<!-- 					<hr> -->
<!-- 					<label class="title" for="email">E-mail:</label> -->
<%-- 					<input type= "text" id="email" name="member_Email" value="${mbr.member_Email}"> --%>
<%-- 					<br><font color="red" size="-1">${errorMsgs.ErrEmailEmpty} </font> --%>
<!-- 					<hr> -->
<!-- 					<label class="title" for="address">地址:</label> -->
<!-- 					<select name="city" id="city" > -->
<%-- 						<option value="<%=mbr.getMember_address().substring(0,3)%>"><%=mbr.getMember_address().substring(0,3)%></option> --%>
<!-- 						<option value="基隆市" >基隆市</option> -->
<!-- 						<option value="臺北市" >臺北市</option> -->
<!-- 						<option value="新北市" >新北市</option> -->
<!-- 						<option value="桃園市" >桃園市</option> -->
<!-- 						<option value="新竹市" >新竹市</option> -->
<!-- 						<option value="新竹縣" >新竹縣</option> -->
<!-- 						<option value="苗栗縣" >苗栗縣</option> -->
<!-- 						<option value="臺中市" >臺中市</option> -->
<!-- 						<option value="彰化縣" >彰化縣</option> -->
<!-- 						<option value="南投縣" >南投縣</option> -->
<!-- 						<option value="雲林縣" >雲林縣</option> -->
<!-- 						<option value="嘉義市" >嘉義市</option> -->
<!-- 						<option value="嘉義縣" >嘉義縣</option> -->
<!-- 						<option value="臺南市" >臺南市</option> -->
<!-- 						<option value="高雄市" >高雄市</option> -->
<!-- 						<option value="屏東縣" >屏東縣</option> -->
<!-- 						<option value="臺東縣" >臺東縣</option> -->
<!-- 						<option value="花蓮縣" >花蓮縣</option> -->
<!-- 						<option value="宜蘭縣" >宜蘭縣</option> -->
<!-- 						<option value="澎湖縣" >澎湖縣</option> -->
<!-- 						<option value="金門縣" >金門縣</option> -->
<!-- 						<option value="連江縣" >連江縣</option> -->
<!-- 					</select> -->
	<%-- 				<input type="text" id="address" name="address" value="${mbr.member_address}"> --%>
<%-- 					<input type="text" id="address" name="address" value="<%=mbr.getMember_address().substring(3)%>"> --%>
<%-- 					<br><font color="red" size="-1">${errorMsgs.ErrCityEmpty}  ${errorMsgs.ErrAdderssEmpty}  ${errorMsgs.ErrAddressFormat}</font>				 --%>
<!-- 					<hr> -->
					<div align="center">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="member_no" value="${mbr.member_no}">
						<input type="hidden" name="member_name" value="${mbr.member_name}">
						<input type="hidden" name="member_id" value="${mbr.member_id}">
						<input type="hidden" name="member_birthday" value="${mbr.member_birthday}">
						<input type="hidden" name="member_gender" value="${mbr.member_gender}">
						<input type="hidden" name="member_phone" value="${mbr.member_phone}">
						<input type="hidden" name="member_Email" value="${mbr.member_Email}">
						<input type="hidden" name="city" value="<%=mbr.getMember_address().substring(0,3)%>">
						<input type="hidden" name="address" value="<%=mbr.getMember_address().substring(3)%>">
						<input type="hidden" name="member_bonus" value="${mbr.member_bonus}">
			       		<p><input type="submit" class="btn btn-lg btn-success" value="修改"></p>
					</div>
				</div>
			</fieldset>
		</form>
<!--         <p><a class="btn btn-lg btn-success" href="MemberServlet?action=update" role="button">修改</a></p> -->
        
      </div>

    </div> <!-- /container -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>
