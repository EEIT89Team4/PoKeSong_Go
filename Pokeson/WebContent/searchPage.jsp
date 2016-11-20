<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> -->
<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->

<script src="js/jquery-1.9.1.js"></script>

<script src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/heroic-features.css" rel="stylesheet">

<body>

<jsp:include page="Header.jsp"/>

<div id="page">
	<div id="content">	
		<!-- 分類標籤 -->
		<div>
			<p>
			<h5>
				<a href="${pageContext.servletContext.contextPath}/index.jsp"
				style="color: #0F5DAC">首頁</a>/${searchText}
			</p>
		</h5>
	</div>
	
	
	
		
		<!------------------------------------商品陳列----------------------------->
		
			<div id="productframe" class="row text-center easyui-panel" border="false"
			data-options="href:'${pageContext.servletContext.contextPath}/searchData?search=${searchText}&pageNumber=${searchPage}&pageSize=${searchSize}&order=${ordertype}${advRequst}'">	
			</div>			
			
			<!------------------------------------pagination----------------------------->
	
			<div id="pptest" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;">
			</div>
		
	</div>
</div>

	
	
	
	<jsp:include page="footer.jsp"/>
	<script src="js/productindex.js"></script>
	
	<!--分頁coding(使用Easy-Ui Plugin)-->
			<script>	
			$(function(){
				
				var searchSize = ${searchSize}; // el運用
				var url = window.location.toString();
				
				$('#pptest').pagination({
					displayMsg:'顯示{from}到{to}筆,共{total}筆商品',
					onSelectPage:function(pageNumber,pageSize){
						if(url.includes("&order=")){
								if(searchSize != pageSize){
									window.location.assign("${pageContext.servletContext.contextPath}/searchPage?search=${searchText}"+"&searchPage=1&searchSize="+pageSize+"&order=${ordertype}${advRequst}");	
								}else{
									window.location.assign("${pageContext.servletContext.contextPath}/searchPage?search=${searchText}"+"&searchPage="+pageNumber+"&searchSize="+searchSize+"&order=${ordertype}${advRequst}");	
								}
						}else{
							if(searchSize != pageSize){
								window.location.assign("${pageContext.servletContext.contextPath}/searchPage?search=${searchText}"+"&searchPage=1&searchSize="+pageSize+"${advRequst}");	
							}else{
								window.location.assign("${pageContext.servletContext.contextPath}/searchPage?search=${searchText}"+"&searchPage="+pageNumber+"&searchSize="+searchSize+"${advRequst}");	
							}
							
						}
				    },
				});
			})
			</script>
</body>
</html>
