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
<!-- Custom styles for this template -->
<link href="css/jumbotron-narrow.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<style>
	fieldset{
/* 		float:right; */
		width:550px;
		margin-top:50px;
		margin:0px auto;
		border-radius:10px;		
		text-align: left
	}
	label {
		float:left;
		width:8em;
		font-weight:bold;
		margin-top:10px;
		margin-bottom:2px;
		margin-right:10px;
		text-align: right;
	}
 	.fieldWidth { 
 	    margin-top:10px; 
 		margin-bottom: 2px; 
 		width: 200px; 
 		font-size:18px; 
 	} 
</style>

<body>
<jsp:include page="Header.jsp"/>

<div class="container">
	<div class="jumbotron">
<!-- 		<form action="MemberServlet" method="post" style="font-size:18px"> -->
		<form method="post" style="font-size:18px">
			<fieldset>
				<div>
					<label for="name">姓名:</label>
					<input type="text" id="name" name="member_name" class="fieldWidth">
					<span id="spanName" style="color:red"></span>
					<br><font color="red" size="-1" >${errorMsgs.ErrNameEmpty}  ${errorMsgs.ErrNameFormat}</font>
					<hr>
					
					<label for="account">帳號:</label>
					<input type="text" id="account" name="member_id" class="fieldWidth">
					<span id="spanId" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>				
					<hr>
					
					<label for="pwd">密碼:</label>
					<input type="password" id="pwd" name="member_password1" class="fieldWidth">
					<span id="spanPwd" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} </font>
					<hr>	
					
					<label id="span1" for="pwd" size="-3"></label>
					<div class="progress" style="width:196px">
					  <div id="pwdBar" class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" 
					  	aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%" >
					  </div>				  
					</div>
					<hr>
					
					<label for="pwd2">再次確認密碼:</label>
					<input type="password" id="pwd2" name="member_password2" class="fieldWidth">
					<span id="spanPwd2" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPassword}</font>
					<hr>
					
					<label for="bdayNgender">生日與性別:</label>
					<input type="text" id="bday" name="member_birthday" placeholder="格式:1990-01-01" class="fieldWidth">
					<input type="radio" name="member_gender" value="女">女
					<input type="radio" name="member_gender" value="男">男
					<span id="spanBday" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrDateEmpty}  ${errorMsgs.ErrGenderEmpty}</font>
					<hr>
					
					<label for="phone">電話:</label>
					<input type="text" id="phone" name="member_phone" placeholder="格式:0912345678" class="fieldWidth">
					<span id="spanPhone" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPhoneEmpty}  ${errorMsgs.ErrPhoneFormat}</font>				
					<hr>
					
					<label for="email">E-mail:</label>
					<input type= "text" id="email" name="member_Email" class="fieldWidth">
					<span id="spanEmail" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrEmailEmpty} ${errorMsgs.ErrEmailFormat}</font>
					<hr>
					
					<label for="address">地址:</label>
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
					<input type="text" id="address" name="address" class="fieldWidth" Style="width:300px;">
					<span id="spanAddress" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrCityEmpty}  ${errorMsgs.ErrAdderssEmpty}  ${errorMsgs.ErrAddressFormat}</font>				
					<hr>
					
					<div align="center">
						<p><input  type="button" id="add" class="btn btn-lg btn-success" value="加入會員"></p>
						<input type="checkbox" id="box" name="demo" value="demo">demo</input>

<!-- 						<input type="hidden" name="action" value="insert"> -->
						<input type="hidden" name="member_bonus" value="0">
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<!------------------------------------------ 加入會員成功 Modal ------------------------------------------------>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" >WELCOME!!</h3>
      </div>
      <div class="modal-body">
        <h4>歡迎加入寶可送</h4>
      </div>
      <div align="center" class="modal-footer">
        <button class="btn btn-lg btn-success">
        	<a href="${pageContext.servletContext.contextPath}/index.jsp">SHOP NOW</a>
        </button>
      </div>
    </div>
  </div>
</div>
<!------------------------------------------------------------------------------------------------------>
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script>
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
</script>
 <script> 
 	
 	$(document).ready(function(){
 		//一鍵輸入
 		$("#box").change(function(){
 			var b = $(this).prop('checked');
 			if(b){
 				//alert("1234567");
 				$("#name").val("跳跳虎");
 				$("#account").val("tiger");
 				$("#pwd").val("a1234567");
 				$("#pwd2").val("a1234567");
 				$("#bday").val("1991-01-01");
 				$("#phone").val("0912345678");
 				$("#email").val("yifang828@gmail.com");
 				$("option:eq(2)").prop("selected", b);
 				$("#address").val("大安區復興南路319號");
 				$('input[name="member_gender"]:eq(1)').prop("checked", b);
 			}
 		})
 		//註冊成功modal	
 		$("#add").click(function() {
 			var member_name = $("#name").val();
 			var member_id = $("#account").val();
 			var member_password1 = $("#pwd").val();
 			var member_password2 = $("#pwd2").val();
 			var member_birthday = $("#bday").val();
 			var member_phone = $("#phone").val();
 			var member_Email = $("#email").val();
 			var city = $("#city").val();
 			var address = $("#address").val();
 			var member_gender = $('input[name="member_gender"]').val();
 			
 			$.post("MemberServlet",{"action":"insert", "member_name":member_name, "member_id":member_id, 
 				"member_password1":member_password1, "member_password2":member_password2, "member_birthday":member_birthday,
 				"member_gender":member_gender, "member_phone":member_phone, "member_Email":member_Email, "city":city, 
 				"address":address, "member_bonus":0}, function(data){
 					if(data=="success"){
	 					$("#addModal").modal({backdrop : false});	
	 					$("#addModal").modal('show');
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
 		
 		$("#account").blur(function(){
 			var regex = "^[a-zA-Z0-9_]{1,20}$";
 			var id = $(this).val();
 			if($("#account").val()==""){
				$("#spanId").text("請填帳號");
 			}else{
 				if(!$("#account").val().match(regex)){
 					$("#spanId").text("請輸入英文或數字");
 				}else{
		 			$.get("IdCheckServlet", {check:"member_id",member_id:id}, function(data){
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
 				}else if(!($("#pwd2").val().match($("#pwd").val()))){
 					$("#spanPwd2").text("請確認密碼");
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
 		$("#bday").blur(function(){
 			var regex = /^([1-2]{1}[\d]{3})[-]([0-1]{1}\d)[-]([0-3]{1}\d)$/;
 			if($("#bday").val()==""){
				$("#spanBday").text("請填生日");
 			}else{
 				if(!$("#bday").val().match(regex)){
 					$("#spanBday").text("請輸入數字");
 				}else{
 					$("#spanBday").text("");
 				}
 			}
 		});
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
		
		
  	});
 </script>	 
	<jsp:include page="footer.jsp"/>
</body>
</html>