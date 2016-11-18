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
			style="color: #0F5DAC">首頁</a>/${classname}
			</p>
		</h5>
	</div>
	
	<!--分類圖-->
	
	<div style="margin:auto">
		<img style="width:100%" src="${pageContext.servletContext.contextPath}/classImg?classno=${classno}"/>
	</div>
	
		
		<!------------------------------------商品陳列----------------------------->
		<div id="productframe" class="row text-center easyui-panel" border="false"
		data-options= "href:'${pageContext.servletContext.contextPath}/classData?classno=${classno}&pageNumber=${pageNumber}&pageSize=${pageSize}&order=${ordertype}'">	
		</div>
			
		<!------------------------------------pagination----------------------------->
		<div id="pptest" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;">
		</div>
		
	</div>
</div>

	<jsp:include page="footer.jsp"/>
	<script src="js/productindex.js"></script>
			<!--分頁coding(Easy-Ui Plugin)-->
			<script>	
			$(function(){
				
				var pageSize = ${pageSize};
				var url = window.location.toString();
				
				$('#pptest').pagination({
					displayMsg:'顯示{from}到{to}筆,共{total}筆商品',
				    onSelectPage:function(pageNumber,changeSize){
				    	if(url.includes("&order=")){
				    		if(pageSize != changeSize){
						    	window.location.assign("${pageContext.servletContext.contextPath}/classProduct?classno=${classno}&class=${classname}&pageNumber=1&pageSize="+ changeSize +"&order=${ordertype}");
						    	}else{
						    		window.location.assign("${pageContext.servletContext.contextPath}/classProduct?classno=${classno}&class=${classname}&pageNumber="+pageNumber+"&pageSize="+pageSize+"&order=${ordertype}");	
						    	}
				    	}else{
				    		if(pageSize != changeSize){
						    	window.location.assign("${pageContext.servletContext.contextPath}/classProduct?classno=${classno}&class=${classname}&pageNumber=1&pageSize="+ changeSize);
						    	}else{
						    		window.location.assign("${pageContext.servletContext.contextPath}/classProduct?classno=${classno}&class=${classname}&pageNumber="+pageNumber+"&pageSize="+pageSize);	
						    	}
				    	}
				    },
				});
			})	
			</script>
	
</body>
</html>
