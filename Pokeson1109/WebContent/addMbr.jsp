<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>加入會員</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<style>
	fieldset{
		width:550px;
		margin-top:50px;
		margin:0px auto;
		border-radius:10px;		
	}
	.s1{
 		text-align:center ;
 		margin:0px auto;
	}
	.title{
		width:150px;
		float:left;
		text-align:right;
		padding-right:3px;
	}
	.s2{
		margin-top:50px;
 		margin:0px auto;
	}
</style>
<!-- <link rel="stylesheet" type="text/css" href="css/jquery.datepick.css"> -->
<!-- <script type="text/javascript" src="js/jquery.plugin.js"></script> -->
<!-- <script type="text/javascript" src="js/jquery.datepick.js"></script> -->
<body>
<jsp:include page="Header.jsp"/>
	<form action="MemberServlet" method="post" class="s2">
		<fieldset class="s2">
			<div>
				<label class="title" for="name">姓名:</label>
				<input type="text" id="name" name="member_name">
				<span id="spanName" style="color:red"></span>
				<br><font class="s1" color="red" size="-1" >${errorMsgs.ErrNameEmpty}  ${errorMsgs.ErrNameFormat}</font>
				<hr>
				<label class="title" for="account">帳號:</label>
				<input type="text" id="account" name="member_id">
				<span id="spanId" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>				
				<hr>
				<label class="title" for="pwd">密碼:</label>
				<input type="password" id="pwd" name="member_password1">
				<span id="spanPwd" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} </font>
				<br>	
				<label class="title" id="span1" for="pwd" size="-3"></label>
				<div class="progress" style=" text-align:center;width:178px">
				  <div id="pwdBar" class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" 
				  	aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%" ></div>				  
				</div>
				<label class="title" for="pwd2">再次確認密碼:</label>
				<input type="password" id="pwd2" name="member_password2">
				<span id="spanPwd2" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrPassword}</font>
				<hr>
				<label class="title" for="bdayNgender">生日與性別:</label>
				<input type="text" id="bday" name="member_birthday" placeholder="格式:1990-01-01">
				<input type="radio" name="member_gender" value="女">女
				<input type="radio" name="member_gender" value="男">男
				<span id="spanBday" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrDateEmpty}  ${errorMsgs.ErrGenderEmpty}</font>
				<hr>
				<label class="title" for="phone">電話:</label>
				<input type="text" id="phone" name="member_phone" placeholder="格式:0912345678">
				<span id="spanPhone" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrPhoneEmpty}  ${errorMsgs.ErrPhoneFormat}</font>				
				<hr>
				<label class="title" for="email">E-mail:</label>
				<input type= "text" id="email" name="member_Email">
				<span id="spanEmail" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrEmailEmpty} </font>
				<hr>
				<label class="title" for="address">地址:</label>
				<select name="city" id="city" >
					<option value="請選擇" >請選擇</option>
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
				<input type="text" id="address" name="address">
				<span id="spanAddress" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrCityEmpty}  ${errorMsgs.ErrAdderssEmpty}  ${errorMsgs.ErrAddressFormat}</font>				
				<hr>
				<div align="center">
					<input  type="submit" value="加入會員">
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="member_bonus" value="0">
				</div>
			</div>
		</fieldset>
	</form>
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
 <script> 
 	
 	$(document).ready(function(){
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
 		
 		$("#account").blur(function(){
 			var regex = "^[a-zA-Z0-9_]{1,20}$";
 			var id = $(this).val();
 			if($("#account").val()==""){
				$("#spanId").text("請填帳號");
 			}else{
 				if(!$("#account").val().match(regex)){
 					$("#spanId").text("請輸入英文或數字");
 				}else{
		 			$.get("MemberServlet", {action:"insert",check:"member_id",member_id:id, member_password1:"", member_password2:""
		 				, member_name:"", member_birthday:"", member_gender:""
		 				, member_phone:"", member_Email:"", city:"", address:""}, 
		 				function(data){
		 				if(data=="帳號已存在"){
		 					$("#spanId").text("帳號已存在");
		 				}else{
		 					$("#spanId").text("");
		 				}
		 			});
 				}
 			}
 		});

 		//驗證密碼
 		$("#pwd").blur(function(){
 			var regex = "^[A-Za-z0-9]{6,20}$";
 			if($("#pwd").val()==""){
				$("#spanPwd").text("請填密碼");
 			}else{
 				if(!$("#pwd").val().match(regex)){
 					$("#spanPwd").text("請輸入英文大小寫、數字，共6-20碼");
 				}else{
 					$("#spanPwd").text("");
 				}
 			}
 		});
 		$("#pwd2").blur(function(){
 			var regex = "^[A-Za-z0-9]{6,20}$";
 			if($("#pwd2").val()==""){
				$("#spanPwd2").text("請填密碼");
 			}else{
 				if(!$("#pwd2").val().match(regex)){
 					$("#spanPwd2").text("請輸入英文大小寫、數字共6-20碼");
 				}else{
 					$("#spanPwd2").text("");
 				}
 			}
 		});
 		//驗證生日
//  		$("#bday").blur(function(){
//  			var regex = /^([1-2]{1}[\d]{3})[-]([0-1]{1}\d)[-]([0-3]{1}\d)$/;
//  			if($("#bday").val()==""){
// 				$("#spanBday").text("請填生日");
//  			}else{
//  				if(!$("#bday").val().match(regex)){
//  					$("#spanBday").text("請輸入數字");
//  				}else{
//  					$("#spanBday").text("");
//  				}
//  			}
//  		});
 		//驗證性別
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
		//密碼強度	
	 	$('#pwd').keyup(function(){
	 		var strength = 0;
	 		var arr = [/.{10,}/, /[A-Z]+/, /[a-z]+/, /[0-9]+/];
	 		$.map(arr,function(regex){
	//  			alert("strong");
				if($('#pwd').val().match(regex)){
	 				strength++; 
				}
	 		});
			
	 		if(strength==4){
	 			$('#span1').text("強");
	 			$('#pwdBar').attr("style", "width: 100%");
	 		}else if(strength==3){
	 			$('#span1').text("中");
	 			$('#pwdBar').attr("style", "width: 60%");
	 		}else if(strength==0){
	 			$('#span1').text("");
	 			$('#pwdBar').attr("style", "width: 0%");
	 		}else{
	 			$('#span1').text("弱"); 		
	 			$('#pwdBar').attr("style", "width: 30%");
	 		}
	 	});
		
	 	//日曆plugin
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
		
  	});
 </script>	 
	<jsp:include page="footer.jsp"/>
</body>
</html>