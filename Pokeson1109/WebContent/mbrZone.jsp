<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>會員專區</title>
	
	    <!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet">

	    <!-- Custom styles for this template -->
	    <link href="css/jumbotron-narrow.css" rel="stylesheet">

  <body>
	<jsp:include page="Header.jsp"/>
	<c:if test="${empty mbr}">
		<c:set var="target" value="${pageContext.servletContext.contextPath}${pageContext.request.servletPath}" scope="session"/>
		<script>
			$(document).ready(function() {
				$("#myModal").modal({backdrop : false});
				$("#myModal").modal('show');
			});
		</script>
	</c:if>
	<!---------------------------------------彈跳視窗--------------------------->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content -->
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
								<input type="text" class="form-control" id="usrname" name="member_id" placeholder="Enter id">
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
<!-- 							<div class="checkbox"> -->
<!-- 								<label><input type="checkbox" value="" checked>Remember	me</label> -->
<!-- 							</div> -->
							<button type="submit" class="btn btn-success btn-block">
								<input type="hidden" name="action" value="login">
								<span class="glyphicon glyphicon-off"></span> 登入
							</button>
							
							<button class="btn btn-success btn-block">
							<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&redirect_uri=http://localhost:8081/testGoogle/Login.do&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email">以Google帳戶登入</a>				
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
 	<!------------------------------------  ---------------------------------------->
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
        <p><a class="btn btn-lg btn-success" href="productindex.jsp" role="button">Go Shopping</a></p>
      </div>

    </div> <!-- /container -->
	<!-- -----------------------modal ----------------------------------->	
	<script>
// 		$(document).ready(function() {
// 			$("#myBtn").click(function() {
// 				$("#myModal").modal({
// 					backdrop : false
// 				});
// 			});
// 		});
// 		$(document).ready(function() {
// 			$("#myModal").modal({backdrop : false});
// 			$("#myModal").modal('show');
// 		});
 	</script>
	<jsp:include page="footer.jsp"/>
  </body>
</html>
