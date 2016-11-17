<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	MemberVO mbr = (MemberVO)request.getAttribute("mbr"); 
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>修改會員資料</title>
		<style >
			fieldset{
/* 				float:right; */
				width:550px;
				margin-top:50px;
				margin:0px auto;
				border:2px solid #ACD6FF;
				border-radius:10px;		
				text-align: left
			}
			label {
				float:left;
				width:8em;
				font-weight:bold;
/* 				margin-top:10px; */
				margin-bottom:2px;
				margin-right:10px;
				text-align: right;
			}
		 	.fieldWidth { 
/* 		 	    margin-top:10px;  */
		 		margin-bottom: 2px; 
		 		width: 200px; 
		 		font-size:18px; 
		 	} 
		 	font{
		 	margin-top:20px;
		 	}
		</style>
		<script src="js/jquery-3.1.1.min.js"></script>
		
	    <!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet">

	    <!-- Custom styles for this template -->
	    <link href="css/jumbotron-narrow.css" rel="stylesheet">
	 </head>
  <body>
	<jsp:include page="Header.jsp"/>
	<c:set var="target" value="${pageContext.request.servletPath}" scope="session" />
    <div class="container" style="font-size:18px">
      <div class="header clearfix">
        <h3 class="text-muted">親愛的 ${mbr.member_name} 你好!!</h3>
      </div>
      <center>
		<ul class="nav nav-tabs">
		  
		  <li role="presentation"><a href="MemberServlet?action=getMbr&member_no=${mbr.member_no}">修改會員資料</a></li>
		  <c:if test="${empty mbr.member_GoogleId}">
		  <li role="presentation"><a href="updatePwd.jsp">修改密碼</a></li>
		  </c:if>
		</ul>
      <div class="jumbotron">
        	<form method="post">
			<fieldset >
				<div >
					<label for="no">會員編號:</label>
					<font margin-top="10px">${mbr.member_no}</font>
					<hr>
					
					<label for="name">姓名:</label>
					<input type="text" id="name" name="member_name" value="${mbr.member_name}" class="fieldWidth">
					<span id="spanName" style="color:red"></span>
					<br><font color="red" size="-1" >${errorMsgs.ErrNameEmpty}  ${errorMsgs.ErrNameFormat}</font>
					<hr>
					<c:if test="${empty mbr.member_GoogleId}">
					<label for="account">帳號:</label>
					<font  margin-top="10px">${mbr.member_id}</font>	
					<hr>
					</c:if>		
					
					<label for="bdayNgender">生日與性別:</label>
					<input type="text" id="bday" name="member_birthday" value="${mbr.member_birthday}" class="fieldWidth">
	                
	                <c:if test="${empty mbr.member_GoogleId}">
					
					<font>${mbr.member_gender}</font>	
					
					</c:if>
					
					<span id="spanBday" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrDateEmpty} </font>
					<hr>
					
					<label for="phone">電話:</label>
					<input type="text" id="phone" name="member_phone" value="${mbr.member_phone}" class="fieldWidth">
					<span id="spanPhone" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPhoneEmpty}  ${errorMsgs.ErrPhoneFormat}</font>				
					<hr>
					
					<label for="email">E-mail:</label>
					<input type= "text" id="email" name="member_Email" value="${mbr.member_Email}" class="fieldWidth">
					<span id="spanEmail" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrEmailEmpty} ${errorMsgs.ErrEmailFormat}</font>
					<hr>
					
					<label for="address">地址:</label>
					<select name="city" id="city" >
						<option value="<%=mbr.getMember_address().substring(0,3)%>"><%=mbr.getMember_address().substring(0,3)%></option>
						<option value="基隆市" >基隆市</option>
						<option value="臺北市" >臺北市</option>
						<option value="新北市" >新北市</option>
						<option value="桃園市" >桃園市</option>
						<option value="新竹市" >新竹市</option>
						<option value="新竹縣" >新竹縣</option>
						<option value="苗栗縣" >苗栗縣</option>
						<option value="臺中市" >臺中市</option>
						<option value="彰化縣" >彰化縣</option>
						<option value="南投縣" >南投縣</option>
						<option value="雲林縣" >雲林縣</option>
						<option value="嘉義市" >嘉義市</option>
						<option value="嘉義縣" >嘉義縣</option>
						<option value="臺南市" >臺南市</option>
						<option value="高雄市" >高雄市</option>
						<option value="屏東縣" >屏東縣</option>
						<option value="臺東縣" >臺東縣</option>
						<option value="花蓮縣" >花蓮縣</option>
						<option value="宜蘭縣" >宜蘭縣</option>
						<option value="澎湖縣" >澎湖縣</option>
						<option value="金門縣" >金門縣</option>
						<option value="連江縣" >連江縣</option>
					</select>
					<input type="text" id="address" name="address" value="<%=mbr.getMember_address().substring(3)%>" class="fieldWidth" style="width:320px;">
					<span id="spanAddress" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrCityEmpty}  ${errorMsgs.ErrAdderssEmpty}  ${errorMsgs.ErrAddressFormat}</font>				
					<hr>
					
					<div align="center">
<!-- 						<input type="hidden" name="action" value="update"> -->
						<input type="hidden" id="no" name="member_no" value="${mbr.member_no}">
						<input type="hidden" id="idd" name="member_id" value="${mbr.member_id}">
						<input type="hidden" id="pwd" name="member_password1" value="${mbr.member_password}">
						<input type="hidden" id="pwd2" name="member_password2" value="${mbr.member_password}">
						<input type="hidden" id="gender" name="member_gender" value="${mbr.member_gender}">
						<input type="hidden" id="bonus" name="member_bonus" value="${mbr.member_bonus}">
						<input type="hidden" id="state" name="member_state" value="${mbr.member_state}">
						
			        	<p><input type="button" id="mbrUpdate" class="btn btn-lg btn-success" value="修改"></p>
					</div>
				</div>
			</fieldset>
		</form>        
      </div>

</center>
    </div> <!-- /container -->
<!------------------------------------------  Modal ------------------------------------------------>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
<!--         <h3 class="modal-title" ></h3> -->
      </div>
      <div class="modal-body">
        <h4 style="text-align:center;">會員資料更新成功!!</h4>
      </div>
      <div align="center" class="modal-footer">
        <button class="btn btn-lg btn-success">
        	<a href="${pageContext.servletContext.contextPath}/mbrZone.jsp">回會員專區</a>
        </button>
      </div>
    </div>
  </div>
</div>
<!------------------------------------------------------------------------------------------------------>
    <jsp:include page="footer.jsp"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
	<script type="text/javascript" src="js/jquery.plugin.js"></script>
	<script type="text/javascript" src="js/jquery.datepick.js"></script>
	<script>
			$('#bday,#defaultInline').datepick({dateFormat: 'yyyy-mm-dd'}); 
			
			$('.disablePicker').click(function() { 
			    var enable = $(this).text() === 'Enable'; 
			    $(this).text(enable ? 'Disable' : 'Enable'). 
			        siblings('.is-datepick').datepick(enable ? 'enable' : 'disable'); 
			}); 
			 
			$('#removePicker').click(function() { 
			    var destroy = $(this).text() === 'Remove'; 
			    $(this).text(destroy ? 'Re-attach' : 'Remove'); 
			    $('#bday,#defaultInline').datepick(destroy ? 'destroy' : {}); 
			});	
			</script>
	 <script> 
	 $(document).ready(function(){

	 		//註冊成功modal	
	 		$("#mbrUpdate").click(function() {
	 			var member_no= $("#no").val();
	 			var member_name = $("#name").val();
	 			var member_id = $("#idd").val();
	 			var member_password1 = $("#pwd").val();
	 			var member_password2 = $("#pwd2").val();
	 			var member_birthday = $("#bday").val();
	 			var member_phone = $("#phone").val();
	 			var member_Email = $("#email").val();
	 			var city = $("#city").val();
	 			var address = $("#address").val();
	 			var member_gender = $("#gender").val();
	 			var member_bonus = $("#bonus").val();
	 			var member_state = $("#state").val();
	 			
	 			$.post("MemberServlet",{"action":"update", "member_no":member_no, "member_name":member_name, "member_id":member_id, 
	 				"member_password1":member_password1, "member_password2":member_password2, "member_birthday":member_birthday,
	 				"member_gender":member_gender, "member_phone":member_phone, "member_Email":member_Email, "city":city, 
	 				"address":address, "member_bonus":0, "member_state":member_state}, function(data){
	 					if(data=="success"){
		 					$("#updateModal").modal({backdrop : false});	
		 					$("#updateModal").modal('show');
	 					}
	 				});
			});
	 		
	 		//姓名驗證
	 		$("#name").blur(function(){
	 			var regex = "^[(\u4E00-\u9FA5)(a-zA-Z)]{2,10}$";
	 			if($("#name").val()==""){
					$("#spanName").text("請填姓名");
	 			}else{
	 				if(!$("#name").val().match(regex)){
	 					$("#spanName").text("請輸入中文或英文");
	 				}else{
	 					$("#spanName").text("");
	 				}
	 			}
	 		});
	 		
	 		//驗證電話
	 		$("#phone").blur(function(){
	 			var regex = "^[0-9]{8,10}$";
	 			if($("#phone").val()==""){
					$("#spanPhone").text("請填電話");
	 			}else{
	 				if(!$("#phone").val().match(regex)){
	 					$("#spanPhone").text("請輸入數字，共8-10碼");
	 				}else{
	 					$("#spanPhone").text("");
	 				}
	 			}
	 		});
	 		//驗證email
	 		$("#email").blur(function(){
	 			var regex = /[-\w.]+@[\w-]+(\.[\w-]+)+/;
	 			if($("#email").val()==""){
					$("#spanEmail").text("請填email");
	 			}else{
	 				if(!$("#email").val().match(regex)){
	 					$("#spanEmail").text("請輸入正確email格式");
	 				}else{
	 					$("#spanEmail").text("");
	 				}
	 			}
	 		});
	 		//驗證地址
	 		$("#address").blur(function(){
	 			var regex = "^[(0-9)(\u4e00-\u9fa5)]+$";
	 			if($("#address").val()==""){
					$("#spanAddress").text("請填地址");
	 			}else{
	 				if(!$("#address").val().match(regex)){
	 					$("#spanAddress").text("請輸入中文或數字");
	 				}else{
	 					$("#spanAddress").text("");
	 				}
	 			}
	 		});
	 		
	 });
	 </script>	
  </body>
</html>
