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
		<c:set var="target" value="${pageContext.request.servletPath}" scope="session" />
		<script>
			$(document).ready(function() {
				$("#myModal").modal({backdrop : false});
				$("#myModal").modal('show');
			});
		</script>
	</c:if>
    <div class="container" style="font-size:18px">
      <div class="header clearfix">
        <h3 class="text-muted">親愛的 ${mbr.member_name} 你好!!</h3>
      </div>
		<ul class="nav nav-tabs">
		  <li role="presentation"><a href="MemberServlet?action=getMbr&member_no=${mbr.member_no}">修改會員資料</a></li>
		    <c:if test="${empty mbr.member_GoogleId}">
		  <li role="presentation"><a href="updatePwd.jsp">修改密碼</a></li>
		  </c:if>
		</ul>
      <div class="jumbotron">
<!--         <h1>Jumbotron heading</h1> -->
<!--         <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p> -->
        <p><a class="btn btn-lg btn-success" href="productindex.jsp" role="button">Go Shopping</a></p>
      </div>

    </div> <!-- /container -->
	<jsp:include page="footer.jsp"/>
  </body>
</html>
