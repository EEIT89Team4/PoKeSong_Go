<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
<title>Insert title here</title>
</head>
<body>
<% session.invalidate(); %>
 ${member.member_name}謝謝您的光臨
 <a href="#" onclick="logout();">Sign out</a>
 <a href="productindex.jsp">回去購物</a>
 <script>

 function logout(){
	document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8081/testGoogle/productindex.jsp";
	
 }
</script>
 
 

</body>
</html>