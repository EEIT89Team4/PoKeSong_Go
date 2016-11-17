<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="js/jquery-1.9.1.js"></script>
<style>

</style>
<script>
		$(document).ready(function() {
			$("#bbbbb").click(function() {
				$("#aaaaa").modal({
					backdrop : false
				});
			});
		});
	</script>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->		
		
		<a href="#" id="bbbbb">登入</a>
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
								<br><font color="red" size="-1">${errorMsgs.ErrIdEmpty}  ${errorMsgs.ErrIdFormat}  ${errorMsgs.ErrId}</font>
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
								<br><font color="red" size="-1">${errorMsgs.ErrPasswordEmpty}  ${errorMsgs.ErrPasswordFormat} ${errorMsgs.Err} </font>
							</div>
							<div class="form-group">
								<!-- 輸入驗證碼欄位 -->
								<label for="identity"><h3>信用卡背面最後三碼：</h3></label>
								<input type="text" class="" id="" name="" placeholder="" size="3" maxlength="3">
								<br><font color="red" size="-1">${errorMsgs.ErrIdentityEmpty} ${errorMsgs.ErrIdentity}</font>
							</div>
							<button type="submit" class="btn btn-success btn-block">
								<input type="hidden" name="action" value="login">
								</span> 確定
							</button>
							
						</form>
					</div>
					
				</div>
			</div>
		</div>
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
