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
					<label for="name">�m�W:</label>
					<input type="text" id="name" name="member_name" class="fieldWidth">
					<span id="spanName" style="color:red"></span>
					<br><font color="red" size="-1" >${errorMsgs.ErrNameEmpty}  ${errorMsgs.ErrNameFormat}</font>
					<hr>
					
					<label for="account">�b��:</label>
					<input type="text" id="account" name="member_id" class="fieldWidth">
					<span id="spanId" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>				
					<hr>
					
					<label for="pwd">�K�X:</label>
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
					
					<label for="pwd2">�A���T�{�K�X:</label>
					<input type="password" id="pwd2" name="member_password2" class="fieldWidth">
					<span id="spanPwd2" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPassword}</font>
					<hr>
					
					<label for="bdayNgender">�ͤ�P�ʧO:</label>
					<input type="text" id="bday" name="member_birthday" placeholder="�榡:1990-01-01" class="fieldWidth">
					<input type="radio" name="member_gender" value="�k">�k
					<input type="radio" name="member_gender" value="�k">�k
					<span id="spanBday" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrDateEmpty}  ${errorMsgs.ErrGenderEmpty}</font>
					<hr>
					
					<label for="phone">�q��:</label>
					<input type="text" id="phone" name="member_phone" placeholder="�榡:0912345678" class="fieldWidth">
					<span id="spanPhone" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPhoneEmpty}  ${errorMsgs.ErrPhoneFormat}</font>				
					<hr>
					
					<label for="email">E-mail:</label>
					<input type= "text" id="email" name="member_Email" class="fieldWidth">
					<span id="spanEmail" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrEmailEmpty} ${errorMsgs.ErrEmailFormat}</font>
					<hr>
					
					<label for="address">�a�}:</label>
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
					<input type="text" id="address" name="address" class="fieldWidth" Style="width:300px;">
					<span id="spanAddress" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrCityEmpty}  ${errorMsgs.ErrAdderssEmpty}  ${errorMsgs.ErrAddressFormat}</font>				
					<hr>
					
					<div align="center">
						<p><input  type="button" id="add" class="btn btn-lg btn-success" value="�[�J�|��"></p>
						<input type="checkbox" id="box" name="demo" value="demo">demo</input>

<!-- 						<input type="hidden" name="action" value="insert"> -->
						<input type="hidden" name="member_bonus" value="0">
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<!------------------------------------------ �[�J�|�����\ Modal ------------------------------------------------>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" >WELCOME!!</h3>
      </div>
      <div class="modal-body">
        <h4>�w��[�J�_�i�e</h4>
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
</script>
 <script> 
 	
 	$(document).ready(function(){
 		//�@���J
 		$("#box").change(function(){
 			var b = $(this).prop('checked');
 			if(b){
 				//alert("1234567");
 				$("#name").val("������");
 				$("#account").val("tiger");
 				$("#pwd").val("a1234567");
 				$("#pwd2").val("a1234567");
 				$("#bday").val("1991-01-01");
 				$("#phone").val("0912345678");
 				$("#email").val("yifang828@gmail.com");
 				$("option:eq(2)").prop("selected", b);
 				$("#address").val("�j�w�ϴ_���n��319��");
 				$('input[name="member_gender"]:eq(1)').prop("checked", b);
 			}
 		})
 		//���U���\modal	
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
		 			$.get("IdCheckServlet", {check:"member_id",member_id:id}, function(data){
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
 				}else if(!($("#pwd2").val().match($("#pwd").val()))){
 					$("#spanPwd2").text("�нT�{�K�X");
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
 		$("#bday").blur(function(){
 			var regex = /^([1-2]{1}[\d]{3})[-]([0-1]{1}\d)[-]([0-3]{1}\d)$/;
 			if($("#bday").val()==""){
				$("#spanBday").text("�ж�ͤ�");
 			}else{
 				if(!$("#bday").val().match(regex)){
 					$("#spanBday").text("�п�J�Ʀr");
 				}else{
 					$("#spanBday").text("");
 				}
 			}
 		});
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
		
		
  	});
 </script>	 
	<jsp:include page="footer.jsp"/>
</body>
</html>