<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/jquery.jqpagination.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/heroic-features.css" rel="stylesheet">
<body>
<jsp:include page="Header.jsp"/>
<div id="page">
	<div id="content">
		<div class="post">
			<div >
		<jsp:include page="/turnImg.jsp" />
		</div>
	
		</div>
		<hr>
		<div class="post">
			<h2 class="title"><a href="#">熱門商品</a></h2>
			<p class="meta"><span class="date">May 13, 2011</span><span class="posted">Posted by <a href="#">Someone</a></span></p>
			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
				<p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum vel, tempor at, varius non, purus. Mauris vitae nisl nec metus placerat consectetuer. Donec ipsum. Proin imperdiet est. Phasellus <a href="#">dapibus semper urna</a>. Pellentesque ornare, orci in consectetuer hendrerit, urna elit eleifend nunc, ut consectetuer nisl felis ac diam. Etiam non felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.  Mauris quam enim, molestie in, rhoncus ut, lobortis a, est.</p>
			</div>
			<p><a href="#" class="more">Read More</a><a href="#" class="comments">Comments</a></p>
		</div>
		<div class="post">
			<h2 class="title"><a href="#">Consecteteur hendrerit </a></h2>
			<p class="meta"><span class="date">May 10, 2011</span><span class="posted">Posted by <a href="#">Someone</a></span></p>
			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
				<p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum vel, tempor at, varius non, purus. Mauris vitae nisl nec metus placerat consectetuer. Donec ipsum. Proin imperdiet est. Phasellus <a href="#">dapibus semper urna</a>. Pellentesque ornare, orci in consectetuer hendrerit, urna elit eleifend nunc, ut consectetuer nisl felis ac diam. Etiam non felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.  Mauris quam enim, molestie in, rhoncus ut, lobortis a, est.</p>
			</div>
			<p><a href="#" class="more">Read More</a><a href="#" class="comments">Comments</a></p>
		</div>
		<div style="clear: both;">&nbsp;</div>
	</div>

	<jsp:include page="footer.jsp"/>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
