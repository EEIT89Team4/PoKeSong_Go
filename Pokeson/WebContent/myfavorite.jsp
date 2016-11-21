<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
#myfavorite{
font-size:26px;
}
#myfavorite img{
width:100px;
height:100px
}
</style>
</head>
<script src="js/jquery-1.9.1.js"></script>
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
        <!-- 內容寫這 -->		
		<th><h2 style="color:#00BBFF">喜愛商品:</h2></th>
		<div id="myfavorite">
			<table>
				<c:forEach var="myfavoriteVO" items="${myfavorites}">
					<tr>
					<td style="width:120px">
					<a href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${myfavoriteVO.productVO.product_no} ">
					<img src="${pageContext.servletContext.contextPath}/getImage?productid=${myfavoriteVO.productVO.product_no}" /></a>
					</td>
					<td style="width:600px">
					<a href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${myfavoriteVO.productVO.product_no} ">
					${myfavoriteVO.productVO.product_name}</a>
				      </td>
				      <td><input type="button"  name="DELETE"  class="btn" id="${myfavoriteVO.productVO.product_no}" value="刪除"></td>    
					</tr>
				</c:forEach>
				<script>
                $(function(){
                	$("input").click(function(){
                		var member=${MemberVO.member_no};
                		var product_no=$(this).attr("id");
//                 		alert(product_no);
                	$(this).parents("tr").remove();	
                	$.get("MyFavoriteServlet",{product_no:product_no,member_no:member,myfavoriteaction:"delete"},function(data){
//                 		alert(data);
//                 		if(data.equals("deleteOK")){
                			
//                 		sweetAlert("刪除成功", "", "success");
//                 		}
                	})
                	})
	});
				
				</script>
				
				</table>
				<center>
				<h4><a href="${header['referer']}">回上一頁</a>&nbsp;&nbsp;|<a href="index.jsp">&nbsp;&nbsp;  回首頁</a></h4>
			</center>
			</div>
		
		
		
		
		
		
		 <!-- 結束 -->
	</div>
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
