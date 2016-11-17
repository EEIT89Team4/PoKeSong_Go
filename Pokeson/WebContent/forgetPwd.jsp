<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="js/jquery-1.9.1.js"></script>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/jumbotron-narrow.css" rel="stylesheet">

<style>
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
<c:set var="target" value="${pageContext.request.servletPath}" scope="session" />
<div id="page">
	<div id="content">

        <!-- 內容寫這 -->		
		<div class="container">
	      <div class="header clearfix">
	      </div>	
	      <div class="jumbotron">
		      <form method="post">
			      <fieldset>
			        <div class="lead">
			        	<label for="name">請輸入Email:</label>
			        	<input type="text" name="member_Email" id="email2" placeholder="請輸入Email" class="fieldWidth">
			        	<span id="spanEmail2" style="color:red"></span>
			        	<p style="color:red">${errorMsgs.ErrEmailEmpty} ${errorMsgs.ErrEmailFormat}${errorMsgs.ErrEmail}</p>
			        </div>
			        <div class="lead">
			        	<label for="name">請輸入驗證碼:</label>
			        	<input type="text" id="identity2" name="identity" placeholder="不分大小寫" class="fieldWidth">
			        	<span id="spanIdentity2" style="color:red"></span>
			        	<p style="color:red">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</p>
			        	<p align="center">
				        	<img src="IdentityServlet" id="pic2" style="width:100px; height:50px"/>
							<input type="button" id ="btn2" value="換個圖片" >
			        	</p>
			        </div>
			        <div align="center">
<!-- 				        <input type="hidden" name="action" value="forgetPwd"> -->
				        <p><input type="button" id="forget" class="btn btn-lg btn-success" value="查詢"></p>
			      	</div>
			      </fieldset>
		      </form>
	      </div>
    	</div> <!-- /container -->
		<!-- 結束 -->
	</div>
</div>
<!------------------------------------------  Modal ------------------------------------------------>
<div class="modal fade" id="forgetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
<!--         <h3 class="modal-title" ></h3> -->
      </div>
      <div class="modal-body">
        <h4 style="text-align:center;">密碼重置信已寄出，請至信箱收信!!</h4>
      </div>
      <div align="center" class="modal-footer">
        <button class="btn btn-lg btn-success">
        	<a href="${pageContext.servletContext.contextPath}/forgetPwd.jsp">OK</a>
        </button>
      </div>
    </div>
  </div>
</div>
<!------------------------------------------------------------------------------------------------------>

<script>
	 	$(document).ready(function(){
	 	//modal
	 		$("#forget").click(function(){
	 			var member_Email = $("#email2").val();
	 			var identity = $("#identity2").val();
	 			$.post("MemberServlet",{"action":"forgetPwd", "member_Email":member_Email, "identity":identity},
	 				function(data){
	 					if(data=="success"){
		 					$("#forgetModal").modal({backdrop : false});	
		 					$("#forgetModal").modal('show');
	 					}
	 			})
	 		})
		//驗證碼換張圖
	 		
	  		$("#btn2").click(function(){  
	  			$.get("IdentityServlet",{},function(data){
	  				$("#pic2").attr("src","IdentityServlet");
	  			})
	  		});
		  	
		//驗證email
			$("#email2").blur(function(){
				var regex = /[-\w.]+@[\w-]+(\.[\w-]+)+/;
		 			if($("#email2").val()==""){
						$("#spanEmail2").text("請填email");
		 			}else{
		 				if(!$("#email2").val().match(regex)){
		 					$("#spanEmail2").text("請輸入正確email格式");
		 				}else{
		 					$("#spanEmail2").text("");
		 				}
		 			}
			});
		//驗證碼
		$("#identity2").blur(function(){
			if($("#identity2").val()==""){
				$("#spanIdentity2").text("請填驗證碼");
			}else{
				$("#spanIdentity2").text("");
			}
		});
	});
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>
