<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->		
		<div class="row text-center">			     
				 <c:forEach var="product" items="${products}">
				 	<a
						href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${product.product_no} ">

						<div class="col-md-3 col-sm-6 hero-feature">
							<div class="thumbnail">
								<img
									src="${pageContext.servletContext.contextPath}/getImage?productid=${product.product_no}" />

								<div class="caption">
									<h6>商品名稱:${product.product_name}</h6>
									<p>售價:${product.product_price}</p>
									<p>
										<a id="${product.product_no}" class="addfavorite btn btn-primary">加入購物車</a> <a href="#"
											class="btn btn-default">我的最愛</a>
									</p>
								</div>
							</div>
						</div>
					</a>   
				</c:forEach>
			</div>
		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
