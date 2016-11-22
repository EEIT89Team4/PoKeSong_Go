<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.*"%>

<%
	MemberVO mbr = (MemberVO)request.getAttribute("mbr"); 
	//有這行EL才取得到值,加上getSession()才能取得地址欄
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="js/jquery-1.9.1.js"></script>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/jumbotron-narrow.css" rel="stylesheet">
<style >
	fieldset{
		float:right;
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
<div id="page">
	<div class="container" style="font-size:18px">
        <!-- 內容寫這 -->		
		<div class="jumbotron">
		  <form method="get">
			<fieldset >
				<div >

					<label for="pwd">請輸入新密碼:</label>
					<input type="password" id="pwd" name="member_password1" class="fieldWidth">
					<span id="spanPwd" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} </font>
					<hr>
					
					<label for="pwd2">再次確認新密碼:</label>
					<input type="password" id="pwd2" name="member_password2" class="fieldWidth">
					<span id="spanPwd2" style="color:red"></span>
					<br><font color="red" size="-1">${errorMsgs.ErrPassword}</font>
					<hr>
					
					<div align="center">
<!-- 						<input type="hidden" name="action" value="update"> -->
						<input type="hidden" id="no" name="member_no" value="${mbr.member_no}">
						<input type="hidden" id="name" name="member_name" value="${mbr.member_name}">
						<input type="hidden" id="idd" name="member_id" value="${mbr.member_id}">
						<input type="hidden" id="bday" name="member_birthday" value="${mbr.member_birthday}">
						<input type="hidden" id="gender" name="member_gender" value="${mbr.member_gender}">
						<input type="hidden" id="phone" name="member_phone" value="${mbr.member_phone}">
						<input type="hidden" id="email" name="member_Email" value="${mbr.member_Email}">
						<input type="hidden" id="city" name="city" value="<%=mbr.getMember_address().substring(0,3)%>">
						<input type="hidden" id="address" name="address" value="<%=mbr.getMember_address().substring(3)%>">
						<input type="hidden" id="bonus" name="member_bonus" value="${mbr.member_bonus}">
						<input type="hidden" id="state" name="member_state" value="${mbr.member_state}">

			       		<p><input type="button" id="reset" class="btn btn-lg btn-success" value="修改"></p>
					</div>
				</div>
			</fieldset>
		</form>        
      </div>	
		<!-- 結束 -->
	</div>
</div>
<!------------------------------------------  Modal ------------------------------------------------>
<div class="modal fade" id="reModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
<!--         <h3 class="modal-title" ></h3> -->
      </div>
      <div class="modal-body">
        <h4 style="text-align:center;">會員密碼更新成功!!</h4>
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

<script>
	$("#reset").click(function() {
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
					$("#reModal").modal({backdrop : false});	
					$("#reModal").modal('show');
				}
				var e = JSON.parse(data);//因為用get不會得到json物件
				$("#spanPwd").text(e.ErrPasswordEmpty);
				$("#spanPwd").text(e.ErrPasswordFormat);
				$("#spanPwd2").text(e.ErrPassword2Empty);
				$("#spanPwd2").text(e.ErrPassword);
			});
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
			}else if(!($("#pwd2").val().match($("#pwd").val()))){
				$("#spanPwd2").text("請確認密碼");
			}else{
				$("#spanPwd2").text("");
			}
		}
	});
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>
