<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>�[�J�|��</title>
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
				<label class="title" for="name">�m�W:</label>
				<input type="text" id="name" name="member_name">
				<span id="spanName" style="color:red"></span>
				<br><font class="s1" color="red" size="-1" >${errorMsgs.ErrNameEmpty}  ${errorMsgs.ErrNameFormat}</font>
				<hr>
				<label class="title" for="account">�b��:</label>
				<input type="text" id="account" name="member_id">
				<span id="spanId" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>				
				<hr>
				<label class="title" for="pwd">�K�X:</label>
				<input type="password" id="pwd" name="member_password1">
				<span id="spanPwd" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} </font>
				<br>	
				<label class="title" id="span1" for="pwd" size="-3"></label>
				<div class="progress" style=" text-align:center;width:178px">
				  <div id="pwdBar" class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" 
				  	aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%" ></div>				  
				</div>
				<label class="title" for="pwd2">�A���T�{�K�X:</label>
				<input type="password" id="pwd2" name="member_password2">
				<span id="spanPwd2" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrPassword}</font>
				<hr>
				<label class="title" for="bdayNgender">�ͤ�P�ʧO:</label>
				<input type="text" id="bday" name="member_birthday" placeholder="�榡:1990-01-01">
				<input type="radio" name="member_gender" value="�k">�k
				<input type="radio" name="member_gender" value="�k">�k
				<span id="spanBday" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrDateEmpty}  ${errorMsgs.ErrGenderEmpty}</font>
				<hr>
				<label class="title" for="phone">�q��:</label>
				<input type="text" id="phone" name="member_phone" placeholder="�榡:0912345678">
				<span id="spanPhone" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrPhoneEmpty}  ${errorMsgs.ErrPhoneFormat}</font>				
				<hr>
				<label class="title" for="email">E-mail:</label>
				<input type= "text" id="email" name="member_Email">
				<span id="spanEmail" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrEmailEmpty} </font>
				<hr>
				<label class="title" for="address">�a�}:</label>
				<select name="city" id="city" >
					<option value="�п��" >�п��</option>
					<option value="�򶩥�" >�򶩥�</option>
					<option value="�O�_��" >�O�_��</option>
					<option value="�s�_��" >�s�_��</option>
					<option value="��饫" >��饫</option>
					<option value="�s�˥�" >�s�˥�</option>
					<option value="�s�˿�" >�s�˿�</option>
					<option value="�]�߿�" >�]�߿�</option>
					<option value="�O����" >�O����</option>
					<option value="���ƿ�" >���ƿ�</option>
					<option value="�n�뿤" >�n�뿤</option>
					<option value="���L��" >���L��</option>
					<option value="�Ÿq��" >�Ÿq��</option>
					<option value="�Ÿq��" >�Ÿq��</option>
					<option value="�O�n��" >�O�n��</option>
					<option value="������" >������</option>
					<option value="�̪F��" >�̪F��</option>
					<option value="�O�F��" >�O�F��</option>
					<option value="�Ὤ��" >�Ὤ��</option>
					<option value="�y����" >�y����</option>
					<option value="���" >���</option>
					<option value="������" >������</option>
					<option value="�s����" >�s����</option>
				</select>
				<input type="text" id="address" name="address">
				<span id="spanAddress" style="color:red"></span>
				<br><font color="red" size="-1">${errorMsgs.ErrCityEmpty}  ${errorMsgs.ErrAdderssEmpty}  ${errorMsgs.ErrAddressFormat}</font>				
				<hr>
				<div align="center">
					<input  type="submit" value="�[�J�|��">
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
 		//�m�W����
 		$("#name").blur(function(){
 			var regex = "^[(\u4E00-\u9FA5)(a-zA-Z)]{2,10}$";
 			if($("#name").val()==""){
				$("#spanName").text("�ж�m�W");
 			}else{
 				if(!$("#name").val().match(regex)){
 					$("#spanName").text("�п�J����έ^��");
 				}else{
 					$("#spanName").text("");
 				}
 			}
 		});
 		
 		$("#account").blur(function(){
 			var regex = "^[a-zA-Z0-9_]{1,20}$";
 			var id = $(this).val();
 			if($("#account").val()==""){
				$("#spanId").text("�ж�b��");
 			}else{
 				if(!$("#account").val().match(regex)){
 					$("#spanId").text("�п�J�^��μƦr");
 				}else{
		 			$.get("MemberServlet", {action:"insert",check:"member_id",member_id:id, member_password1:"", member_password2:""
		 				, member_name:"", member_birthday:"", member_gender:""
		 				, member_phone:"", member_Email:"", city:"", address:""}, 
		 				function(data){
		 				if(data=="�b���w�s�b"){
		 					$("#spanId").text("�b���w�s�b");
		 				}else{
		 					$("#spanId").text("");
		 				}
		 			});
 				}
 			}
 		});

 		//���ұK�X
 		$("#pwd").blur(function(){
 			var regex = "^[A-Za-z0-9]{6,20}$";
 			if($("#pwd").val()==""){
				$("#spanPwd").text("�ж�K�X");
 			}else{
 				if(!$("#pwd").val().match(regex)){
 					$("#spanPwd").text("�п�J�^��j�p�g�B�Ʀr�A�@6-20�X");
 				}else{
 					$("#spanPwd").text("");
 				}
 			}
 		});
 		$("#pwd2").blur(function(){
 			var regex = "^[A-Za-z0-9]{6,20}$";
 			if($("#pwd2").val()==""){
				$("#spanPwd2").text("�ж�K�X");
 			}else{
 				if(!$("#pwd2").val().match(regex)){
 					$("#spanPwd2").text("�п�J�^��j�p�g�B�Ʀr�@6-20�X");
 				}else{
 					$("#spanPwd2").text("");
 				}
 			}
 		});
 		//���ҥͤ�
//  		$("#bday").blur(function(){
//  			var regex = /^([1-2]{1}[\d]{3})[-]([0-1]{1}\d)[-]([0-3]{1}\d)$/;
//  			if($("#bday").val()==""){
// 				$("#spanBday").text("�ж�ͤ�");
//  			}else{
//  				if(!$("#bday").val().match(regex)){
//  					$("#spanBday").text("�п�J�Ʀr");
//  				}else{
//  					$("#spanBday").text("");
//  				}
//  			}
//  		});
 		//���ҩʧO
 		//���ҹq��
 		$("#phone").blur(function(){
 			var regex = "^[0-9]{8,10}$";
 			if($("#phone").val()==""){
				$("#spanPhone").text("�ж�q��");
 			}else{
 				if(!$("#phone").val().match(regex)){
 					$("#spanPhone").text("�п�J�Ʀr�A�@8-10�X");
 				}else{
 					$("#spanPhone").text("");
 				}
 			}
 		});
 		//����email
 		$("#email").blur(function(){
 			var regex = /[-\w.]+@[\w-]+(\.[\w-]+)+/;
 			if($("#email").val()==""){
				$("#spanEmail").text("�ж�email");
 			}else{
 				if(!$("#email").val().match(regex)){
 					$("#spanEmail").text("�п�J���Temail�榡");
 				}else{
 					$("#spanEmail").text("");
 				}
 			}
 		});
 		//���Ҧa�}
 		$("#address").blur(function(){
 			var regex = "^[(0-9)(\u4e00-\u9fa5)]+$";
 			if($("#address").val()==""){
				$("#spanAddress").text("�ж�a�}");
 			}else{
 				if(!$("#address").val().match(regex)){
 					$("#spanAddress").text("�п�J����μƦr");
 				}else{
 					$("#spanAddress").text("");
 				}
 			}
 		});
		//�K�X�j��	
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
	 			$('#span1').text("�j");
	 			$('#pwdBar').attr("style", "width: 100%");
	 		}else if(strength==3){
	 			$('#span1').text("��");
	 			$('#pwdBar').attr("style", "width: 60%");
	 		}else if(strength==0){
	 			$('#span1').text("");
	 			$('#pwdBar').attr("style", "width: 0%");
	 		}else{
	 			$('#span1').text("�z"); 		
	 			$('#pwdBar').attr("style", "width: 30%");
	 		}
	 	});
		
	 	//���plugin
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