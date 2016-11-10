<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
#orderlist{
font-size:16px;
}
th{
text-align:center;
color:	#00BBFF;
}
#table{
width:1000px;
}
#content{
margin-left:-35px;
}
</style>
</head>
<script src="js/jquery-1.9.1.js"></script>
<jsp:useBean id="OrderlistService" class="order.OrderlistService"
	scope="page" />
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->		
		<center id="orderlist">
    <h3>${MemberVO.member_name}的訂購紀錄</h3>
	<table border="2" bordercolor="blue" style="background:#EFEFFB;" id="table">

		<tr height='36' >
			<th>訂單編號</th>
			<th>訂購人姓名</th>
<!-- 			<th>訂購人電話</th> -->
<!-- 			<th>訂購人地址</th> -->
			<th>訂購日期</th>
			<th>收貨人姓名</th>
			<th>收貨人電話</th>
			<th>收貨人地址</th>
			<th style="width:100px">小計金額</th>
			<th>使用紅利</th>
			<th>運費</th>
			<th>總金額</th>
			<th>訂單狀態</th>
		</tr>
		<c:forEach var="OrderlistVO" varStatus="stat" 	items="${OrderlistService.allOrderlist}">
				<c:if test="${OrderlistVO.member_no==MemberVO.member_no}">
						<TR height='30'>
							<TD width="86" align="center">
							    <a  href="orderDetail.do?member_no=${MemberVO.member_no}&order_no=${OrderlistVO.order_no}" />
							         ${OrderlistVO.order_no}
							    </a>
							  </TD>
							<TD width="130" align="center">${OrderlistVO.order_name}</TD>
<%-- 							<TD width="80" align="center">${OrderlistVO.order_phone}</TD> --%>
<%-- 							<TD width="400" align="center">${OrderlistVO.order_address}</TD> --%>
							<TD width="100" align="center">${OrderlistVO.order_date}</TD>
							<TD width="130" align="center">${OrderlistVO.receipt_name}</TD>
							<TD width="80" align="center">${OrderlistVO.receipt_phone}</TD>
							<TD width="400" align="center">${OrderlistVO.receipt_address}</TD>
							<TD width="50" align="center">${OrderlistVO.subtotal}</TD>
							<TD width="50" align="center">${OrderlistVO.usebonus}</TD>
							<TD width="50" align="center">${OrderlistVO.freight}</TD>
							<TD width="100" align="center">${OrderlistVO.total}</TD>
							<TD width="100" align="center">${OrderlistVO.orderState}</TD>
<%-- 							<TD width="400" align="left">&nbsp;${anOrderBean.shippingAddress}</TD> --%>
							<!--
							<%-- 
							<TD width="100" align="center"><input type="hidden"
								value="${anOrderBean.orderNo}" name="OrderNo"> <input
								type="hidden" value="${anOrderBean.totalAmount}"
								name="totalAmount"> <input type="hidden"
								value="${anOrderBean.orderDate}" name="orderDate"> <input
								type="submit" value="詳細資料"></TD>
								--%>
  						 -->
						</TR>
				</c:if>

			

		</c:forEach>
<!-- 		<tr height='36'> -->
<%-- 			<td align="center" colspan="4"><a href="<c:url value='../productindex.jsp' />">回首頁</a></td> --%>
<!-- 		</tr> -->
	</TABLE>
	<a href="index.jsp">回首頁</a>
	</center>
		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
