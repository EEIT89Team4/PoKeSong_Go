<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-1.9.1.js"></script>
<script src="js/productindex.js"></script>
<!-- <script src="js/cart.js"></script> -->
<link href="css/bootstrap.css" rel="stylesheet">
<style>
.cartimg{
width:100px;
height:100px;
}
.table {
font-size:18px;
border:1px solid #ACD6FF;
border-collapse:collapse;
/* width:500px; */
}
.table tr th{
border:1px solid #ACD6FF;

width:130px;
}
.table tr td{
/*  font-size:20px; */
text-align:center;
border:1px solid #ACD6FF;
width:130px;
}
.checktable{
font-size:14px;
}

</style>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <c:if test="${empty mycart}">
		<H1 id="noproduct">親愛的${MemberVO.member_name}  ， 您尚未選購任何商品</H1>
	</c:if>
	<div id="tablediv">
	<div id="show">
		<c:if test="${not empty mycart}">
			<table id="carttable" class="table">
			
				<tr>
					<th>商品</th>
					<th>商品名稱</th>
					<th>價格</th>
					<th>數量</th>
					<th>小計</th>
					<th></th>
				</tr>
				<c:forEach var="productList" items="${mycart.productMap}">
					<tr>
						<c:forEach var="eachproduct" items="${productList.value}"
							begin="0" end="0">
							<td><img class="cartimg"
									src="${pageContext.servletContext.contextPath}/getImage?productid=${eachproduct.product_no}"></td>
							<td>${eachproduct.product_name}</td>
							<td>${eachproduct.product_price}</td>
							<td><select name="${eachproduct.product_no}" id="cartcount"
								class="CompareSel">

									<option value="${productList.value.size()}">${productList.value.size()}
										<c:forEach var="x" begin="1"
											end="${eachproduct.product_quantity}">
											<option value=${x}>${x}</option>
										</c:forEach>
									</option>


							</select></td>
							<td id="total">${eachproduct.product_price*productList.value.size()}</td>
							<td><a name="${eachproduct.product_no}" class="btn btn-primary cartbtn">刪除</a>
<%-- 							<a name="${eachproduct.product_no}" class="btn btn-primary cartbtnUpdate">修改</a> --%>
							</td>
						</c:forEach>
					
					
					</tr>
				</c:forEach>
				</table>
				<form name="form_name" action="checkout">  
<!--                                                           com.order.controller -->
				<table class="checktable">
						<tr><td>小計總金額:</td><td value="${mycart.subtotal}" name="subtotal">${mycart.subtotal}</td><td >元</td></tr>
			        <tr><td >目前紅利點數:</td><td>${MemberVO.member_bonus}點</td></tr>
			    <tr><td >選擇使用紅利點數:</td>
			    <td><select name="usebonus" id="member_bonus" 
								class="">
                               <c:choose >
                               <c:when test="${MemberVO.member_bonus > mycart.subtotal}">
                               <option >
                               <c:forEach var="x" begin="1" end="${mycart.subtotal}">
                               <option value=${x}>${x}</option> 
                               </c:forEach>
                               </option>
                               </c:when>
                               <c:otherwise>
                               <option >
                               <c:forEach var="x" begin="1" end="${MemberVO.member_bonus}">
                               <option value="${x}">${x}</option> 
                               </c:forEach>
                               </option>
                               </c:otherwise>
                               </c:choose>
							</select></td>
			<td></td></tr>
			<tr><td>扣除紅利總金額:</td><td id="alltotal" name="alltotal">${mycart.subtotal}</td><td>元</td></tr>
			</table>
			<a id="deletecart" class="btn btn-default">整批取消</a>
			<input type="hidden" name="subtotal" value="${mycart.subtotal}">
<%-- 			<input type="hidden" name="selectbonus" value="${param.subtotal-param.alltotal}"> --%>
					<input type="hidden" name="alltotal" value="${mycart.subtotal}">
			<input type="submit" value="確認訂購" style="width:82px;height:32px">
			</form>
		</c:if>
		</div>
	</div>
<!--   ------------------------------------小計扣除紅利加總金額-------------------------------------------- 	 -->
		<script>
	$(function(){
		$('select[name="usebonus"]').change(function(){
			var select=$(this).find('option:selected').val();
			var subtotal=${mycart.subtotal};
			$('#alltotal').empty().text(subtotal-select);
			var alltotal=subtotal-select;
			$('input[name="alltotal"]').val(alltotal);
		}
		)
	});
	</script>
<!--   ----------------------------------------結束-------------------------------------------------- 	 -->

<!--   ------------------------------------刪除購物車商品-------------------------------------------- 	 -->
<script>
$('.cartbtn').click(function(){
	var num = $(this).attr("name");
	$.get("deleteCartItem",{"CartItem":num},function(data){
		$('#cartsapn').empty().html(data);
		
		if(data==0){
// 			var tt=$('#show');
// 			tt.empty().after("<h1>親愛的${MemberVO.member_name}  ， 您尚未選購任何商品</h1>");
// 			var page=document.getElementById("page");
// 			page.removeChild(document.getElementById("tablediv"));
		}
		else if(data!=0){
			location.replace("ShowCartContent.jsp");
		}
	});
// 	location.replace("ShowCartContent.jsp");
	$(this).parent().parent().remove();
});
</script>
<!--   ----------------------------------------結束-------------------------------------------------- 	 -->
<!--   ------------------------------------修改購物車商品-------------------------------------------- 	 -->
<script>
var count=null;
var price=null;
// var aa=false;
$('.CompareSel').change(function(){
	var num = $(this).attr("name");
// 	 aa=true;
	 count=$(this).val();	
	 price=$(this).parent().prev().html()
	//alert(typeof(new Number(price)));
	$(this).parent().next().empty().html(new Number(count)*new Number(price));
	$.get("getCartNumber",{"productno":num,"count":count,"status":"Update"},function(data){
		if(data.match("0")){
			sweetAlert("加入購物車失敗，超過庫存量", "", "error");
		}
		else{
			sweetAlert("修改成功", "", "success");
			aa=false;
			$("#buycount_all_f").html(data);} //購物車後數字
});
	
			});
// $(".cartbtnUpdate").click(function(){
// 	var num = $(this).attr("name");
// 	if(aa){
// 		$.get("getCartNumber",{"productno":num,"count":count,"status":"Update"},function(data){
// 			if(data.match("0")){
// 				sweetAlert("加入購物車失敗，超過庫存量", "", "error");
// 			}
// 			else{
// 				sweetAlert("修改成功", "", "success");
// 				aa=false;
// 				$("#buycount_all_f").html(data);} //購物車後數字
// 	})}
// 	else{
// 		sweetAlert("沒有修改", "", "error");
// 		}
// 	});
</script>
<!--   ----------------------------------------結束-------------------------------------------------- 	 -->

<!--   ------------------------------------取消全部購物車商品-------------------------------------------- 	 -->
	<script>
	$('#deletecart').click(function(){
		$('#tablediv').empty().html("<h1>目前無任何商品</h1><br><a>回首頁繼續購物</a>");	
		$.get("deleteCart",{},function(data){
			$("#cartsapn").empty();
			document.location.reload();
			});
	});
	</script>
<!--   ----------------------------------------結束-------------------------------------------------- 	 -->
	
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
