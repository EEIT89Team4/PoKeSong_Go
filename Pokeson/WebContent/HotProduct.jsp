<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <head>
<!--         <link rel="stylesheet" type="text/css" href="csss/demo.css" /> -->
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/jquery.jscrollpane.css" media="all" />
    <style>
    .hotproduct{}
    </style>
    </head>
   <jsp:useBean id="productdao" class="product.ProductService" scope="page" />
		<div >
			<div id="ca-container" class="ca-container">
				<div class="ca-wrapper">
                   <c:forEach var="productitem" items="${productdao.hotProduct}" begin="0" end="5">
					<div class="ca-item ca-item-1">
					<a
						href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${productitem.product_no} ">

						<div class="hotproduct">
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

<%-- 										<a id="${productitem.product_no}" --%>
<!-- 											class="addfavorite btn btn-primary">加入購物車</a>  -->
<!-- 											<input type="button" style="width:100px" name="INSERT" class="btn" -->
<%-- 											id="${productitem.product_no}" value="追蹤商品"> --%>
									</p>
								</div>
							</div>
						</div>
					</a>
					</div>
				         </c:forEach>
				</div>
			</div>
		</div>
		
  
