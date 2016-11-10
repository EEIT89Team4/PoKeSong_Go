<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<script type="text/javascript">
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.getElementById("actionorder").value="CANCEL";
		return true;
	} else {
		return false;
	}
}
function reconfirmOrder() {
	if (confirm("確定送出此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "ORDER";
		return true;
	} else {
		return false;
	}
}
</script>
<!-- 取得今天的日期，今天的日期應當在最後確認時才取得 -->
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
</head>
<script src="js/jquery-1.9.1.js"></script>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->
        <BR>		
<h4>請確認訂單內容：</h4>
<CENTER>
<form action="ProcessOrder.do" method="post">
<!-- com.order.controller.ProcessOrderServlet -->
<table border='1' width="810" style="border-style: outset;font-size:16px">
      <TR >
         <TD style="text-align:left;  border-style: ridge;"><span style="color:#00BBFF;font-weight:bold;">會員編號：</span>${MemberVO.member_no}</TD>
         <TD style="text-align:left;  border-style: ridge;"><span style="color:#00BBFF;font-weight:bold;">客戶姓名：</span>${MemberVO.member_name}</TD>
         <TD style="text-align:left;  border-style: ridge;"><span style="color:#00BBFF;font-weight:bold;">訂單日期：</span><fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></TD>
      </TR>
      <TR>
      <c:if test="${MemberVO.member_address==null}">
           <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="color:#00BBFF;font-weight:bold;">會員地址：</span>未註冊地址</TD>
      </c:if>
      <c:if test="${MemberVO.member_address!=null}">
         <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="color:#00BBFF;font-weight:bold;">會員地址：</span>${MemberVO.member_address}</TD>
      </c:if>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left;  border-style: ridge;"><span style="width:50px;color:#00BBFF;font-weight:bold;">收貨人姓名：</span>
           <Input style="background:#DDDDDD;" size="30" type="text" 
                      name="ShippingName" value="${MemberVO.member_name}"><span style="color:red ;font-size:10px">${errorMsgs.shippingNameError}</span>
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:90px;color:#00BBFF;font-weight:bold;"> 收貨人電話：</span>
          <Input style="background:#DDDDDD;" size="30" type="text" 
                      name="ShippingPhone" value="${MemberVO.member_phone}"><span style="color:red;font-size:10px">${errorMsgs.shippingPhoneError}</span>
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:150px;color:#00BBFF;font-weight:bold;">出貨地址：</span>
           <Input style="background:#DDDDDD;" size="60" type="text" 
                      name="ShippingAddress" value="${MemberVO.member_address}"><span style="color:red;font-size:10px;">${errorMsgs.shippingAddressError}</span>
         </TD>
      </TR>
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:150px;color:#00BBFF;font-weight:bold;"> 統一編號：</span> -->
<!--           <Input style="background:#DDDDDD;" size="10" type="text"  -->
<!--                       name="BNO" value="12345678"> -->
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:150px;color:#00BBFF;font-weight:bold;">發票抬頭：</span> -->
<!--            <Input style="background:#DDDDDD;" size="50" type="text"  -->
<!--                       name="InvoiceTitle" value="寶可送股份有限公司台灣總公司" > -->
<!--          </TD> -->
<!--       </TR> -->
    
      <TR>
         <TD colspan='3'>
         
   <TABLE>
      
     <TR><TH style="text-align:center;font-size: 12pt; color:#666666;font-weight:bold" width="270;">產品名稱</TH>
         <TH style="text-align:center;font-size: 12pt;color:#666666;font-weight:bold" width="80">單價(元)</TH>
         <TH style="text-align:center;font-size: 12pt;color:#666666;font-weight:bold" width="80">數量</TH>
         <TH style="text-align:center;font-size: 12ptcolor:#666666;font-weight:bold;" width="110">小計</TH></TR>
     
     <c:forEach varStatus="vs" var="productList" items="${mycart.productMap}">
       <c:forEach var="eachproduct" items="${productList.value}" begin="0" end="0">
        <TR height='10'>
          <TD style="text-align:left  ;font-size: 11pt;">&nbsp&nbsp ${eachproduct.product_name}</TD>
          <TD style="text-align:center;font-size: 11pt;">${eachproduct.product_price}</TD>
          <TD style="text-align:center;font-size: 11pt;">${productList.value.size()}</TD>
<%--           <TD style="text-align:right ;font-size: 11pt;"><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount }" pattern="#,###" />元</TD> --%>
<%--           <TD style="text-align:right ;font-size: 11pt;"> ${anEntry.value.qty}   </TD> --%>
          <TD style="text-align:center ;font-size: 11pt;">${eachproduct.product_price*productList.value.size()}元</TD>
        </TR>
     </c:forEach>
     </c:forEach>
        <TR height='10' >
          <TD style="text-align:right;font-size: 11pt;" colspan='5' >小計總金額：</TD>
          <TD style="text-align:right;font-size: 11pt;" >${subtotal}元</TD>
                  
        </TR>
        <TR  >
          <TD colspan='5' style="text-align:right;font-size: 11pt;" >使用紅利：</TD>
<%--           <c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/> --%>
          <TD style="text-align:right;font-size: 11pt;" > ${usebouns}點</TD>
                   
        </TR  >
         <TR height='16' >
          <TD style="text-align:right;font-size: 11pt;" colspan='5' >運費：</TD>
          <TD style="text-align:right;font-size: 11pt;" >${freight}元</TD>
          <TD style="text-align:right;font-size: 11pt;color:red" >消費滿888, 免運費</TD>
          <TD style="text-align:right;font-size: 11pt;" ><a href="productindex.jsp">繼續購物</TD>
                  
        </TR>
        <TR  >
          <TD colspan='5' style="text-align:right;font-size: 11pt;" >總計金額：</TD>
          <TD style="text-align:right;font-size: 11pt;color:#AA0200;" >${alltotal+freight}元</TD>
                   
        </TR>
   </TABLE>
 
         </TD>
      </TR>
 
   </TABLE>
   <INPUT TYPE="hidden" name="finalDecision"  id="actionorder"value="">   
   <INPUT TYPE="hidden" name="total"  value="${alltotal+freight}">   
   <INPUT TYPE="hidden" name="freight"  value="${freight}">   
   <INPUT TYPE="hidden" name="usebouns"  value="${usebouns}">   
   <INPUT TYPE="hidden" name="subtotal"  value="${subtotal}">   
   <INPUT TYPE="hidden" name="today"  value="${today}">   
   <INPUT TYPE="SUBMIT" name="OrderBtn"  value="確定送出" onclick="return reconfirmOrder();">
   <INPUT TYPE="SUBMIT" name="CancelBtn" value="取消訂單" onclick="return cancelOrder();">

</CENTER>		
		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
