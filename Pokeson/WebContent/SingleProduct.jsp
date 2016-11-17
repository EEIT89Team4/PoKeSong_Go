<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="productdao" class="product.ProductDAO" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>【PoKeSon】${oneproduct.product_name}</title>
<script src="js/jquery-1.9.1.js"></script>
<!-- <script src="js/productindex.js"></script> -->
<!-- <script src="js/sweetalert.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="css/sweetalert.css"> -->
<script src="js/Popup.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->		
<!----------- Facebook SKD ------------------->
<div id="fb-root"></div>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.8";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>
<div class="col-md-5 col-sm-6 hero-feature">
	<div class="thumbnail">
		<img
			src="${pageContext.servletContext.contextPath}/getImage?productid=${oneproduct.product_no}" />
		<div class="fb-share-button"
			data-href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${productitem.product_no}"
			data-size="large" data-mobile-iframe="true">
			<a class="fb-xfbml-parse-ignore" target="_blank"
				href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse">分享</a>
		</div>
	</div>
</div>
<div class="col-md-7 col-sm-6 hero-feature">
	<div class="thumbnail">
		<div class="caption">
			<ul class="prdPrice">
				<li type="font-size:20px">售價${oneproduct.product_price}元</li>
			</ul>
			<ul class="prdPriceDetail">
				<li><b>結帳方式<i>:</i></b>
					<dt>信用卡</dt>
					<dd>貨到付款</dd>
					<dd title="使用ATM付款金額在99,999元(含)以下方可轉帳付款">ATM</dd>
					</dl></li>
				<!--配送方式-->
				<li><b>配送方式<i>:</i></b> <label><input type="radio"
						value="shopcart" name="dely_work" id="shopcart" checked>宅配
				</label> <label><input type="radio" value="shopcart"
						name="dely_work" id="shopcart">超商取貨 </label></li>

				<li><b>數量<i>:</i></b> <c:if
						test="${oneproduct.product_quantity != 0}">
						<select name="count" id="count" class="CompareSel">
							<!-- --------------------------------產品數量迴圈---------------------------------- -->
							<c:forEach var="x" begin="1" end="${oneproduct.product_quantity}">
								<option class="opt" value="${x}">${x}</option>
							</c:forEach>
							<!-- --------------------------------產品數量迴圈---------------------------------- -->
						</select>
					</c:if> <!-- --------------------------------商品存貨讀取結束---------------------------------- -->
					<div class="oneProductbtn">
						<a id="${oneproduct.product_no}" class="addfavorite1 btn btn-primary">加入購物車</a>
						 <a href="#"
							class="btn btn-default">我的最愛</a>
					</div>
		</div>
	</div>
</div>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">商品特色</a></li>
		<li><a href="#tabs-2">退換貨須知</a></li>
		<li><a href="#tabs-3">商品類別</a></li>
	</ul>
	<div id="tabs-1">
		<p>${oneproduct.product_description}</p>
	</div>
	<div id="tabs-2">
		<p>退換貨須知：
			商品到貨享十天猶豫期之權益（注意！猶豫期非試用期），辦理退貨商品必須是全新狀態且包裝完整，否則將會影響退貨權限。各類商品退換貨限制說明
			個人衛生用品除商品本身有瑕疵外，未拆封商品仍享有十天猶豫期之退貨權利。但已拆封
			(如剪標、下水等情形…)，依據《通訊交易解除權合理例外情事適用準則》第二條規定，本公司無法接受退換貨。
			※個人衛生用品項目：內衣褲(含隱形胸罩、胸扥、胸貼、透明肩帶、水餃墊/美胸墊、襯裙)、塑身衣(含馬甲、束褲、束腿、腰夾、內搭)、泳裝、襪子
			運送服務： 我們所提供的產品配送區域範圍目前僅限台灣本島。 商品之實際配貨日期、退換貨日期，依我們向您另行通知之內容為準。
			針對大型商品(包括：大型家電、家具床墊、健身按摩器材、車類...等)，我們將於完成收款確認後，兩天內〈不含例假日〉將會有專人與您確認相關配送細節等的聯繫。偏遠地區若無法配送，廠商將保留出貨與否的權利。
			售後服務： 如您收到商品，請依正常程序儘速檢查商品，若商品發生新品瑕疵之情形，您可申請更換新品或退貨，請直接點選聯絡我們。
			若您對於購買流程、付款方式、退貨及商品運送方式有疑問，你可直接點選會員中心。 特別說明：
			本公司對於所販售具遞延性之商品或服務，消費者權益均受保障。如因合作廠商無法提供商品或服務，請與本公司聯繫辦理退貨或換成等值商品。</p>
	</div>
	<div id="tabs-3">
		<p>類別待完成(MOMO)</p>
	</div>
</div>		
		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
<script>
$(".addfavorite1").click(function(){
	var tempno=$(this).attr("id");
	var count=$('select[name="count"]').val();
	$.get("getCartNumber",{"productno":tempno,"count":count,"status":"Insert"},function(data){
		if(data==0){
			alert("加入購物車失敗，超過庫存量");
// 			sweetAlert("加入購物車失敗，超過庫存量", "", "error");
		}
		else{
			alert("加入購物車成功");
// 			sweetAlert("加入購物車成功", "", "success");
			$("#buycount_all_f").html(data);} //購物車後數字
		
	})
});</script>
<script src="js/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs({
			event : "mouseover"
		});
	});
</script>
	<jsp:include page="footer.jsp"/>
</body>
</html>
