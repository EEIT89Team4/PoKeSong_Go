<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="productdao" class="product.ProductDAO"
	scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>【PoKeSon】${oneproduct.product_name}</title>
<script src="js/jquery-1.9.1.js"></script>

<!-- jquery-ui tabs -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">

<!-- <script src="js/productindex.js"></script> -->
<!-- <script src="js/sweetalert.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="css/sweetalert.css"> -->

</head>
<body>
	<jsp:include page="Header.jsp" />
	<div id="page">

		<div id="content">
			<!-- 內容寫這 -->

			<!-- 分類標籤 -->
			<div style="height: 80px">
				<p>
					<h5>
						<a href="${pageContext.servletContext.contextPath}/index.jsp"
							style="color:#0F5DAC">首頁</a>/ <a
							href="${pageContext.servletContext.contextPath}/classProduct?classno=${oneproduct.productClassVO.class_no}&class=${oneproduct.productClassVO.class_name}"
							value="${oneproduct.productClassVO.class_no}" style="color:#0F5DAC">${oneproduct.productClassVO.class_name}</a>/
						<a
							href="${pageContext.servletContext.contextPath}/detailProduct?detailno=${onedetail.classdetail_no}&detail=${onedetail.classdetail_name}"
							value="${onedetail.classdetail_no}" style="color:#0F5DAC">${onedetail.classdetail_name}</a>
				</h5>
				</p>
			</div>



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
			<!-- --------------------------------圖片區---------------------------------- -->

			<!-- CloudZoom Plugin -->
			<link rel="stylesheet" type="text/css" href="cloudzoom/cloudzoom.css">
			<script type="text/javascript" src="cloudzoom/cloudzoom.js"></script>
			<script type="text/javascript">
				CloudZoom.quickStart();
			</script>

			<div class="container">

				<div class="col-md-5 col-sm-6 hero-feature">
					<div class="thumbnail" style="border: 1px solid #cacaca;">

						<!-- CloudZoom效果 -->
						<img style="height: 220px" class="cloudzoom"
							src="${pageContext.servletContext.contextPath}/getImage?productid=${oneproduct.product_no}"
							data-cloudzoom="zoomImage:'${pageContext.servletContext.contextPath}/getBigImg?productid=${oneproduct.product_no}' ,tintOpacity:'0.1' ,zoomPosition:'12'">
						<!-- CloudZoom效果 -->

						<div class="fb-share-button"
							data-href="http://192.168.43.208:8081/Supermaket-Project1023/getOneProduct?productid=${oneproduct.product_no}"
							data-size="large" data-mobile-iframe="true">
							<a class="fb-xfbml-parse-ignore" target="_blank"
								href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse">分享</a>
						</div>
					</div>
				</div>
				<!-- --------------------------------產品描述區---------------------------------- -->
				<div class="col-md-6 col-sm-6 hero-feature">
					<div class="thumbnail">
						<div class="caption">
							<div class="prodetail1">
								<h2>${oneproduct.product_name}</h2>
								<h3>售價${oneproduct.product_price}元</h3>
								<c:if test="${not empty requestScope.getdollar}">
								<c:if test="${oneproduct.product_price<requestScope.getdollar}">
				 				<div class="prodetail2">
			   					<img id="carreful"
									src=" ${pageContext.servletContext.contextPath}/GetCarrefulImg" />
									<span><b>家樂福原價:${requestScope.getdollar} 便宜  ${requestScope.getdollar-oneproduct.product_price} 元 </b></span>
			  					</div>
			  					</c:if>
			    				</c:if>
								<h5>結帳方式:</h5>
								<h5>
									<span>信用卡 </span><span> 貨到付款</span><span> ATM</span>
									<h5>
										<!--配送方式-->
										<b>配送方式<i>:</i></b> <label><input type="radio"
											value="shopcart" name="dely_work" id="shopcart" checked>宅配
										</label> <label><input type="radio" value="shopcart"
											name="dely_work" id="shopcart">超商取貨 </label>
										</li> <br> <b>數量<i>:</i></b>
										<c:if test="${oneproduct.product_quantity != 0}">
											<select name="count" id="count" class="CompareSel">
												<!-- --------------------------------產品數量迴圈---------------------------------- -->
												<c:forEach var="x" begin="1"
													end="${oneproduct.product_quantity}">
													<option class="opt" value="${x}">${x}</option>
												</c:forEach>
												<!-- --------------------------------產品數量迴圈---------------------------------- -->
											</select>
										</c:if>
										<!-- --------------------------------商品存貨讀取結束---------------------------------- -->
										<div style="margin: 15px 0px" class="oneProductbtn">
											<a id="${oneproduct.product_no}"
												class="addfavorite1 btn btn-primary">加入購物車</a> <input
												type="button" style="width: 100px" name="INSERT" class="btn"
												id="${productitem.product_no}" value="追蹤商品" />

										</div>
							</div>

						</div>
					</div>

				</div>
				<!-- containerdiv7777 -->
			</div>

			<hr style="border-top: 1px solid #9c9c9c">

			<!-- containerdiv -->
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
					<p>
						<a href="${pageContext.servletContext.contextPath}/allProduct"
							style="color: blue">商品首頁</a>/ <a
							href="${pageContext.servletContext.contextPath}/classProduct?classno=${oneproduct.productClassVO.class_no}"
							value="${oneproduct.productClassVO.class_no}" style="color: blue">${oneproduct.productClassVO.class_name}</a>/
						<a
							href="${pageContext.servletContext.contextPath}/detailProduct?detailno=${onedetail.classdetail_no}"
							value="${onedetail.classdetail_no}" style="color: blue">${onedetail.classdetail_name}</a>
					</p>
				</div>
			</div>

			<hr style="border-top: 1px solid #9c9c9c">


			<!-- RECOMMENDED PRODUCTS -->
			<!-- Head Line -->
			<div
					style="font-weight: bold; font-size: 25px; margin: 10px 0; color: #0F5DAC; font-weight: 300">
					<h4>推薦商品</h4>
				</div>
			<div class="container" style="width:100%">
				
				<div class="row">


					<div>
						<c:forEach var="recom" items="${recomList}">
							<a
								href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${recom.product_no}">
								<div class="col-md-3 col-sm-6 hero-feature">

									<!-- 熱銷商品 -->
									<c:if test="${recom.saleaccount_quantity>1000}">
										<img
											src="${pageContext.servletContext.contextPath}/getIconImg"
											id="popicon" />
									</c:if>
									<!-- 熱銷商品 -->
									<div class="thumbnail">
										<img
											src="${pageContext.servletContext.contextPath}/getImage?productid=${recom.product_no}" />

										<div class="caption">
											<h6>${recom.product_name}</h6>
											<p>售價:${recom.product_price}</p>
										</div>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- end of content -->
	</div>
	<!-- end of page -->


	<script>
		$(".addfavorite1").click(function() {
			var tempno = $(this).attr("id");
			var count = $('select[name="count"]').val();
			$.get("getCartNumber", {
				"productno" : tempno,
				"count" : count,
				"status" : "Insert"
			}, function(data) {
				if (data == 0) {
// 					sweetAlert("加入購物車失敗，超過庫存量", "", "error");
					alert("加入購物車失敗，超過庫存量");
				} else {
// 					sweetAlert("加入購物車成功", "", "success");
					alert("加入購物車成功");
					$("#buycount_all_f").html(data);
				} //購物車後數字

			})
		});
	</script>

	<!-- jquery-ui tabs -->
	<script src="js/jquery-ui.min.js"></script>

	<script>
		$(function() {
			$("#tabs").tabs({
				event : "mouseover"
			});
		});
	</script>
	<jsp:include page="footer.jsp" />
</body>
</html>
