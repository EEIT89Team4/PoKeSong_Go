<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員資料</title>
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
</head>
<body>
<c:set var="member_no" value="${mbr.member_no}" scope="session"/>
<FORM METHOD="post" ACTION=MemberServlet>
<table border='1' bordercolor='#CCCCFF' >
	<tr>
		<td>會員編號</td>
		<td>${mbr.member_no}</td>
	</tr>
	<tr>
		<td>姓名</td>
		<td>${mbr.member_name}</td>
	</tr>
	<tr>
		<td>帳號</td>
		<td>${mbr.member_id}</td>
	</tr>
	<tr>
		<td>密碼</td>
		<td>${mbr.member_password}</td>
	</tr>
	<tr>
		<td>生日與性別</td>
		<td>${mbr.member_birthday}  ${mbr.member_gender}</td>
	</tr>
	<tr>
		<td>電話</td>
		<td>${mbr.member_phone}</td>
	</tr>
	<tr>
		<td>Email</td>
		<td>${mbr.member_Email}</td>
	</tr>
	<tr>
		<td>地址</td>
		<td>${mbr.member_address}</td>
	</tr>
	<tr>
		<td>紅利</td>
		<td>${mbr.member_bonus}</td>
	</tr>
</table>
<div>
	<input type="submit" value="修改會員資料">
	<input type="hidden" name="member_no" value="${mbr.member_no}">
	<input type="hidden" name="action" value="getMbr">
</div>
<div>
<%-- <a href="<jsp:forward page="productindex.jsp"> --%>
<%-- 	<jsp:param  name="mbr.member_no" value="${mbr.member_no}"/> --%>
<%-- 	</jsp:forward>">GO Shoping</a> --%>
<%-- 	<jsp:forward page="productindex.jsp"> --%>
	
	<a href="productindex.jsp">GO Shoping</a>
<%-- 	</jsp:forward> --%>
</div>	

</FORM>
<form action="MemberServlet" method="post">
<!-- <div> -->
<!-- 	<input type="submit" value="刪除會員資料"> -->
<%-- 	<input type="hidden" name="member_no" value="${mbr.member_no}"> --%>
<!-- 	<input type="hidden" name="action" value="delete"> -->
<!-- </div> -->
</form>
</body>
</html>