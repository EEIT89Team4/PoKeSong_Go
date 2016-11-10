<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}
#orderDetail{
font-size:16px;
text-align:center;
}
#orderDetail tr td{
text-align:center;
}
</style>
</head>
<script src="js/jquery-1.9.1.js"></script>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->	
        <center>	
		<TABLE border="1" bordercolor="blue"  id="orderDetail">
	<tr height='50'>
		<th align="center" colspan="7" ><h3>${MemberVO.member_name}的訂單明細   </h3></th>
	</tr>
	<CENTER>
	<tr height='36'>
		<th width="300px" align="center">產品名稱</th>
		<th width="100px" align="center">產品數量</th>
		<th width="100px" align="center">單價</th>
		
	</tr>
	<c:set var="subtotal" value="0" />
	<c:forEach var="OrderDetailsVO" varStatus="stat" items="${OrderDetailsVO}">
        
		<tr bgColor="${aColor}" height='30'>
			<td  align="center">${OrderDetailsVO.productVO.product_name}</td>
			<td  align="left">${OrderDetailsVO.orderproduct_quantity}</td>
			<td  align="right">${OrderDetailsVO.productVO.product_price}&nbsp;</td>
			</tr>


	</c:forEach>
		</TABLE>

		
		
	<h4><a href="OrderList.jsp">回上一頁</a>&nbsp;&nbsp;<a href="productindex.jsp">回首頁</a></h4>	
	</center>	
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
