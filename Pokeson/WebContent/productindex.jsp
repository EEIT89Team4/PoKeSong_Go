<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="productdao" class="product.ProductDAO" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="js/jquery-1.9.1.js"></script>
<!-- <script src="js/cloud-zoom.1.0.2.min.js"></script> -->
<script src="js/jquery.jqpagination.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/heroic-features.css" rel="stylesheet">
<script src="js/Popup.js"></script>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->		
<!-- 		<div class="container cantainer1"> -->
		<div class="row" style="z-index:-1;">
			<div id="productframe" class="row text-center" >
				<c:forEach var="productitem" items="${productdao.allProduct}">
					<a
						href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${productitem.product_no} ">

						<div class="col-md-3 col-sm-6 hero-feature">
						<!-- 小圖式 -->
<%-- 							<c:if test="${productitem.saleaccount_quantity>1000}"> --%>
<%-- 								<img src="${pageContext.servletContext.contextPath}/getIconImg" --%>
<!-- 									id="popicon" /> -->
<%-- 							</c:if> --%>
                        <!-- 小圖式 -->
							<div class="thumbnail" >
								<img
									src="${pageContext.servletContext.contextPath}/getImage?productid=${productitem.product_no}" />

								<div class="caption">
									<h6>商品名稱:${productitem.product_name}</h6>
									<p>售價:${productitem.product_price}</p>
									<p>

										<a id="${productitem.product_no}"
											class="addfavorite btn btn-primary">加入購物車</a> 
											<input type="button" style="width:100px" name="INSERT" class="btn"
											id="${productitem.product_no}" value="追蹤商品">
									</p>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
<!---------------------------------新增我的最愛--------------------------->
			<script>
				$(function() {
					$("input[name=INSERT]").one('click',function() {
						var member = ${MemberVO.member_no};
						var product_no = $(this).attr("id");
// 						alert(product_no);
						$(this).parents("tr").remove();
						$.get("MyFavoriteServlet", {product_no : product_no,member_no : member,myfavoriteaction:"insert"}, function(data) {
							alert('新增至我的最愛成功');
						})
					}
				)});
			</script>		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
	<
	<script src="js/bootstrap.min.js"></script>
	<script src="js/productindex.js"></script>
</body>
</html>
