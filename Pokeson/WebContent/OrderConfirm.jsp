<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<style>
.checkOrderRule{
float:left;
margin:0;
padding:10px;
width:100%;
}
.scroll-boxes{
max-height:200px;
overflow-x:hidden;
overflow-y:scroll;
}
.commerce-agreement{
text-align:left;
margin-top:0px;
padding:15px 10px;
font-size:13px;
border:1px solid #bababa;
}
</style>
<script type="text/javascript">
function cancelOrder() {

	confirm("確定取消此份訂單 ?",function(aa){
		if(aa){
			document.getElementById("actionorder").value="CANCEL";
			$('#orderform').submit();
			}else{
				alert("訂單取消失敗");}})
// 	    if (confirm("確定取消此份訂單 ?",function(aa){ aa=true;}) {
// 	    	if(aa){
// 	    	document.getElementById("actionorder").value="CANCEL";
// 	    	return true;
// 	    	}
// 	    }else {
// 	    	return false;
// 	    }
	}
	

function reconfirmOrder() {
	if($('input[id="boughtCar"]').prop("checked")||$('input[id="boughtCash"]').prop("checked")){
   if($('#checkOrderRlue').prop("checked")){
	   confirm("確定送出此份訂單 ?",function(aa){
		   if(aa){
			   document.getElementById("actionorder").value="ORDER";
			   $('#orderform').submit();
		   }else{
			   alert("確認訂單失敗");
		   }
	   })
			// 接收此資料的Servlet會使用 finalDecision 參數的值
	}else{
		alert("請勾選同意");
		return false;
	}
	}else{
		alert("請勾選付款方式");
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
<form action="ProcessOrder.do" method="post" id="orderform">
<!-- com.order.controller.ProcessOrderServlet -->
<table border='1' width="100%" padding-right="100px" style="border-style: outset;font-size:16px;margin:10px">
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
         <c:if test="${empty ShippingName}">
           <Input style="background:#DDDDDD;" size="30" type="text" 
                      name="ShippingName" value="${MemberVO.member_name}"><span style="color:red ;font-size:10px">${errorMsgs.shippingNameError}</span>
            </c:if>
             <c:if test="${not empty ShippingName}">
           <Input style="background:#DDDDDD;" size="30" type="text" 
                      name="ShippingName" value="${ShippingName}"><span style="color:red ;font-size:10px">${errorMsgs.shippingNameError}</span>
            </c:if>        
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:90px;color:#00BBFF;font-weight:bold;"> 收貨人電話：</span>
          <c:if test="${empty ShippingPhone}">
          <Input style="background:#DDDDDD;" size="30" type="text" id="newPhone"
                      name="ShippingPhone" value="${MemberVO.member_phone}">
<!--                       <input type="checkbox" id="newPhone"  value="member_phone">加入常用電話</input> -->
                      <span style="color:red;font-size:10px">${errorMsgs.shippingPhoneError}</span>
         </c:if>
         <c:if test="${not empty ShippingPhone}">
          <Input style="background:#DDDDDD;" size="30" type="text" 
                      name="ShippingPhone" value="${ShippingPhone}"><span style="color:red;font-size:10px">${errorMsgs.shippingPhoneError}</span>
         </c:if>
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:150px;color:#00BBFF;font-weight:bold;">出貨地址：</span>
         <c:if test="${empty ShippingAddress}">
           <Input style="background:#DDDDDD;" size="50" type="text" 
                      name="ShippingAddress" value="${MemberVO.member_address}">
                      <input type="checkbox" id="newAddress" value="ShippingAddress">加入常用資訊</input>
                      <span style="color:red;font-size:10px;">${errorMsgs.shippingAddressError}</span>
         </c:if>
         <c:if test="${not empty ShippingAddress}">
           <Input style="background:#DDDDDD;" size="50" type="text" 
                      name="ShippingAddress" value="${ShippingAddress}"><span style="color:red;font-size:10px;">${errorMsgs.shippingAddressError}</span>
         </c:if>
         </TD>
      </TR>
            <TR>
         <TD colspan='3' style="text-align:left; border-style: ridge;"><span style="width:150px;color:#00BBFF;font-weight:bold;"> 付款方式：</span>
          <input type="radio" id="boughtCash" name="bought" value="boughtCash">貨到付款
          <input type="radio" id="boughtCar" name="bought" value="boughtCar">線上刷卡
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
   <h2>服務相關條款</h2>
   <div class="checkOrderRule">
		<div class="commerce-agreement scroll-boxes">
		<p>
		訂購聲明 : <br>
1. 我已確認訂單內的所有商品及數量並提供收貨人相關資料。<br>
2. 收貨人的各項資料同時依據PoKeson「隱私權聲明 (最近更新日: 2016年11月01日)」及「會員權益個人資料保護聲明 (最近更新日: 2016年11月01日)」進行與商品配送相關的蒐集、處理及利用。
若您所填寫之送貨地址、收件人、聯絡人、聯絡方式等非您本人之個人資料，您同意已取得各該當事人之同意提供予本公司，並已代本公司就前開法定告知事項予以告知。<br>
<br>
<!-- 付款方式：<br> -->
<!-- 1. 本人同意離開好市多網站進入國泰世華銀行網站進行線上付款作業，於完成後回到好市多網站，並瞭解可以自行先至該銀行網站詳閱該銀行的隱私權政策。 -->
<!-- 2. 進入國泰世華網站付款程序後請勿跳離網頁，以免造成申請中斷。 -->
<!-- 3. 進入國泰世華網站後請依指示逐一完成各步驟，請勿於等待時間多次按壓確認鍵，避免重覆扣款。 -->

廢四機回收權益須知：
新購公告指定規格之電視機、洗衣機、電冰箱或冷、暖氣機時，販賣業者應提供同項目、數量、時間及同送達地址之廢四機回收(搬、載運)無償服務。
但不包含為搬運、拆卸而動用大型機具、工程或危險施工等。
請於交付廢四機時向業者索取回收聯單，本人確認已被告知並知悉上述權益。
相關資訊可向環保署免付費資源回收專線：0800-085-717查詢
		</p>
		</div>
		</div>
		<input type="checkbox" id="checkOrderRlue"  >同意</input>
   <INPUT TYPE="hidden" name="finalDecision"  id="actionorder"value="">   
   <INPUT TYPE="hidden" name="total"  value="${alltotal+freight}">   
   <INPUT TYPE="hidden" name="alltotal"  value="${alltotal}">   
   <INPUT TYPE="hidden" name="freight"  value="${freight}">   
   <INPUT TYPE="hidden" name="usebouns"  value="${usebouns}">   
   <INPUT TYPE="hidden" name="subtotal"  value="${subtotal}">   
   <INPUT TYPE="hidden" name="addShippingPhone"  value="${ShippingPhone}">   
   <INPUT TYPE="hidden" name="addShippingAddress"  value="${ShippingAddress}">   
   <INPUT TYPE="hidden" name="today"  value="${today}">   
<%--    <INPUT TYPE="hidden" name="shippingName"  value="${ShippingName}">    --%>
<%--    <INPUT TYPE="hidden" name="shippingPhone"  value="${ShippingPhone}">    --%>
<%--    <INPUT TYPE="hidden" name="shippingAddress"  value="${ShippingAddress}">    --%>
   <INPUT TYPE="button" name="OrderBtn" id="Add" value="確定送出" onclick="reconfirmOrder()">
   <INPUT TYPE="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()">
  
<script>
// Google新增常用電話跟地址
$(function(){
	$('input[id="Add"]').click(function(){
		if($('#newAddress').prop("checked")){
			var address=$("input:checked[id='newAddress']").prev().val();
			var phone=$("input[name='ShippingPhone']").val();
			$('input[name="addShippingAddress"]').empty().val(address);
			$('input[name="addShippingPhone"]').empty().val(phone);
			$.get('googlesign',{"action":"UPDATA","Address":address,"Phone":phone},function(data){})
		}
	});	
	
	$('#boughtCar').click(function(){
		
			$("#aaaaa").modal({
				backdrop : false
			});
		
	})
	
});

</script>
<div class="modal fade" id="aaaaa" role="dialog">
			<div class="modal-dialog" 　>
				<div class="modal-content">
					<div class="modal-header" style="padding: 20px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3>
							 輸入信用卡資料
						</h3>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form role="form" action="MemberServlet" method="post">
							<div >
								<label for="usrname"><h3>信用卡卡號:</h3></label> 
								<table><tr><td style="width:10px"><input type="text" class="" id="" name="" placeholder="" size="4" maxlength="4"></td><td>&nbsp&nbsp-&nbsp&nbsp</td>
								<td ><input type="text" class="" id="" name="" placeholder="" size="4" maxlength="4"></td><td>&nbsp&nbsp-&nbsp&nbsp</td>
								<td ><input type="text" class="" id="" name="" placeholder="" size="4" maxlength="4"></td><td>&nbsp&nbsp-&nbsp&nbsp</td>
								<td ><input type="text" class="" id="" name="" placeholder="" size="4" maxlength="4"></td>
								</tr></table>
<%-- 								<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font> --%>
							</div>
							<div >
								<label for="psw"><h3>有效期限:</h3></label>
								<select name="mounth">
								<c:forEach var="mounth" begin="1" end="12">
								<option value="${mounth}">${mounth}</option>
								</c:forEach> 
								</select><span>月</span><span>
								<select name="year">
								<c:forEach var="year" begin="2016" end="2027">
								<option value="${year}">${year}</option>
								</c:forEach> 
								</select><span>年</span></span>
<%-- 								<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} ${errorMsgs.Err} </font> --%>
							</div>
							<div class="form-group">
								<!-- 輸入驗證碼欄位 -->
								<label for="identity"><h3>信用卡背面最後三碼：</h3></label>
								<input type="text" class="" id="" name="" placeholder="" size="3" maxlength="3">
<%-- 								<br><font color="red" size="-1">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</font> --%>
							</div>
							<button type="button"  data-dismiss="modal" class="btn btn-success btn-block">
								<input type="hidden" name="" value="">
								</span> 確定
							</button>
							
						</form>
					</div>
					
				</div>
			</div>
		</div>
</form>
</CENTER>		
		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
