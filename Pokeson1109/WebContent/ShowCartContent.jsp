<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/cart.js"></script>
<body>
<jsp:include page="Header.jsp"/>
	<c:if test="${empty mbr}">
		<c:set var="target" value="${pageContext.servletContext.contextPath}${pageContext.request.servletPath}" scope="session"/>
		<script>
			$(document).ready(function() {
				$("#myModal").modal({backdrop : false});
				$("#myModal").modal('show');
			});
		</script>
	</c:if>
	<!---------------------------------------彈跳視窗--------------------------->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content -->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-lock"></span> 會員登入
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form role="form" action="MemberServlet" method="post">
							<div class="form-group">
								<label for="usrname"><span class="glyphicon glyphicon-user"></span> 帳號</label> 
								<input type="text" class="form-control" id="usrname" name="member_id" placeholder="Enter id">
								<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>
							</div>
							<div class="form-group">
								<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> 密碼</label> 
								<input type="password" class="form-control" id="psw" name="member_password1" placeholder="Enter password">
								<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} ${errorMsgs.Err} </font>
							</div>
							<div class="form-group">
								<!-- 輸入驗證碼欄位 -->
								<label for="identity">輸入驗證碼:</label>
								<input type="text" class="form-control" id="identity" name="identity" placeholder="不分大小寫">
								<br><font color="red" size="-1">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</font>
							</div>
							<img src="IdentityServlet" id="pic" style="width:100px; height:50px"/>
								<input type="button" id ="btn" value="換個圖片" >
<!-- 							<div class="checkbox"> -->
<!-- 								<label><input type="checkbox" value="" checked>Remember	me</label> -->
<!-- 							</div> -->
							<button type="submit" class="btn btn-success btn-block">
								<input type="hidden" name="action" value="login">
								<span class="glyphicon glyphicon-off"></span> 登入
							</button>
							
							<button class="btn btn-success btn-block">
							<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&redirect_uri=http://localhost:8081/testGoogle/Login.do&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email">以Google帳戶登入</a>				
							</button>
						</form>
					</div>
					<div class="modal-footer">
						<p>
							<a href="addMbr.jsp">註冊</a>
						</p>
						<p>
							<a href="#">忘記密碼?</a>
						</p>
					</div>
					
				</div>
			</div>
		</div>
 	<!------------------------------------  ---------------------------------------->
<div id="page">
	<div id="content">
     <c:if test="${empty mycart}">
		<H1 id="noproduct">親愛的${mbr.member_name}  ， 您尚未選購任何商品</H1>
	</c:if>
	<div id="tablediv">
		<c:if test="${not empty mycart}">
			<table id="carttable" class="table table-bordered">
			
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
							<td class="carttd"><img class="cartimg"
									src="${pageContext.servletContext.contextPath}/getImage?productid=${eachproduct.product_no}"></td>
							<td>${eachproduct.product_name}</td>
							<td>${eachproduct.product_price}</td>
							<td><select name="cartcount" id="cartcount"
								class="CompareSel">

									<option value="${productList.value.size()}">${productList.value.size()}
										<c:forEach var="x" begin="1"
											end="${eachproduct.product_quantity}">
											<option value=${x}>${x}</option>
										</c:forEach>
									</option>


							</select></td>
							<td id="total">${eachproduct.product_price*productList.value.size()}</td>
							<td><a name="${eachproduct.product_no}" class="btn btn-primary cartbtn">刪除</a></td>
						</c:forEach>
					
					
					</tr>
				</c:forEach>
				</table>
				<form name="form_name" action="checkout">
				<table>
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
			<input type="submit" value="確認訂購">
<!-- 			<a id="comfirmcart" class="btn btn-default">確認訂購</a> -->
			</form>
		</c:if>
		<script>
// 	--------------小計扣除紅利加總金額-------------------
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
	//-------------------------------------------------
	</script>
	</div>
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
