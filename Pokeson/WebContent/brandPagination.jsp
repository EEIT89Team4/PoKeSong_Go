<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!------------------------------------商品陳列----------------------------->
<div>
	<div class="row">
		<div class="col-md-10"></div>
		<div class="col-md-1">
			<button id="gridlayout" type="button" class="btn btn-default btn-lg"
				aria-label="Left Align">
				<span class="glyphicon glyphicon-th-large"></span>
			</button>
		</div>
		<div class="col-md-1">
			<button id="linelayout" type="button" class="btn btn-default btn-lg"
				aria-label="Left Align">
				<span class="glyphicon glyphicon-th-list"></span>
			</button>


		</div>
	</div>

</div>


<div>

	<div class="row">

		<c:if test="${total==0}">
			<div>
				<h2 style="color: red">很抱歉，我們查不到"${searchText}"的相關的商品及資訊。</h2>
			</div>
		</c:if>

		<c:if test="${total>0}">

			<div class="col-md-2 col-sm-2">

				<span><strong><h4>依價格排序</h4></strong></span>
				<ul class="list-group">
					<li style="cursor: pointer;" class="list-group-item orderclass"
						value="asc">由低到高</li>
					<li style="cursor: pointer;" class="list-group-item orderclass"
						value="desc">由高到低</li>
				</ul>

			</div>



			<!-- 		productlayout2		 -->
			<div id="productlayout2" class="col-md-10 col-sm-10">
				<c:forEach var="product" items="${brandproducts}">

					<div id="${product.supplier_name}"
						class="col-md-4 col-sm-4 hero-feature">
						<a
							href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${product.product_no} "
							style="text-decoration: none;">

							<div class="thumbnail">
								<img
									src="${pageContext.servletContext.contextPath}/getImage?productid=${product.product_no}" />
								<div class="caption">
									<!-- 							商品名稱:$ -->
									<h6>${product.product_name}</h6>
									<p>售價:${product.product_price}</p>
									<p>
										<c:if test="${product.product_quantity eq 0}">
											<a id="${product.product_no}"
												class="addnotify btn btn-primary">貨到通知我</a>
										</c:if>
										<c:if test="${product.product_quantity gt 0}">
											<a id="${product.product_no}"
												class="addfavorite btn btn-primary">加入購物車</a>
										</c:if>

										<input type="button" style="width: 100px" name="INSERT"
											class="btn" id="${product.product_no}" value="追蹤商品">
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<!--productlayout2-->
			<c:if test="${layout eq 1}">
				<script>
				$("#productlayout2").css("display", "none");
			</script>
			</c:if>

			<!--productlayout1-->
			<div id="productlayout1" class="col-md-10 col-sm-10 text-center">
				<c:forEach var="product" items="${brandproducts}">
					<div class="col-md-12 col-sm-12 hero-feature well"
						style="height: 200px; margin: 3px">
						<a
							href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${product.product_no} ">

							<div class="col-md-4 col-sm-4 col-md-4 col-lg4">
								<div class="thumbnail" style="width: 180px">
									<img style="height: 150px; width: 150px"
										src="${pageContext.servletContext.contextPath}/getImage?productid=${product.product_no}" />
								</div>
							</div>
						</a>
						<div class="col-md-8 col-sm-8 col-md-8 col-lg8">
							<div class="caption">
								<a
									href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${product.product_no} "
									style="text-decoration: none;">

									<h2>商品名稱:${product.product_name}</h2>
								</a>
								<h2>
									售價:${product.product_price}
									<h2>
							</div>
							<c:if test="${product.product_quantity gt 0}">
								<a id="${product.product_no}"
									class="addfavorite btn btn-primary">加入購物車</a>
							</c:if>
							<c:if test="${product.product_quantity eq 0}">
								<a class="btn btn-primary">貨到通知我</a>
							</c:if>
							<input type="button" style="width: 100px" name="INSERT"
								class="btn" id="${product.product_no}" value="追蹤商品">
						</div>
					</div>


				</c:forEach>
			</div>
			<!--productlayout1-->
	</div>
</div>

<c:if test="${layout eq 1}">
	<script>
		$("#productlayout1").css("display", "block");
	</script>
</c:if>

</c:if>


<!------------------------------------商品陳列(二)----------------------------->
<script src="pagination/select.js"></script>
<script src="pagination/layout.js"></script>
<script>
			   $(function(){
					var num = ${total};
					
					if(num<10){ //如果總筆數小於10筆,第一頁就顯示全部筆數
						$('#pptest').pagination({
							total:num,
							pageList:[num],
							pageNumber:${pageNumber},
							pageSize:${pageSize}
						});
					}else if(num>15){ //如果總筆數大於15筆,可提供使用者3個顯示筆數的選項[5,10,15]	
						$('#pptest').pagination({
							total:num,
							pageList:[5,10,15],
							pageNumber:${pageNumber},
							pageSize:${pageSize}
						});	
					}else if(num>=10 && num<15){		//如果總筆數介於10-15筆,可提供使用者2個顯示筆數的選項[5,10]		
						$('#pptest').pagination({
							total:num,
							pageList:[5,10],
							pageNumber:${pageNumber},
							pageSize:${pageSize}
						});	
					}			
				})
				</script>

<!---------------------------------新增我的最愛--------------------------->
<input type="hidden" id="member_no" value="${MemberVO.member_no}" />
<script>
				$(function() {
					 
					$("input[name=INSERT]").one('click',function() {
						var member = $('input[id="member_no"]').val();
						var product_no = $(this).attr("id");
						$(this).parents("tr").remove();
						$.get("MyFavoriteServlet", {product_no : product_no,member_no : member,myfavoriteaction:"insert"}, function(data) {
							if(data==("新增成功")){
								alert('新增至我的最愛成功');
								}else {
									alert('請先登入會員');
								}
						})
					}
				)});
			</script>

<script src="js/productindex.js"></script>
<!-- 貨到通知 coding -->
<script type="text/javascript" src="js/notify.js"></script>
