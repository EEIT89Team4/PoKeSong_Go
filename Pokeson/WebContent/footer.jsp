<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<body>

<!-- <div id="footer-menu"> -->
<!-- 	<ul> -->
<!-- 		<li class="current_page_item"><a href="#">Homepage</a></li> -->
<!-- 		<li><a href="#">Blog</a></li> -->
<!-- 		<li><a href="#">Photos</a></li> -->
<!-- 		<li><a href="#">About</a></li> -->
<!-- 		<li><a href="#">Contact</a></li> -->
<!-- 	</ul> -->
<!-- </div> -->
<div id="footer">
	<p>&copy; PoKeSong股份有限公司 </p>
</div>
 <c:if test="${not empty mbr}"> 
 <script src="js/mqttws31.js" type="text/javascript"></script>
 <script src="js/config.js" type="text/javascript"></script>
 <script src="js/chat.js" type="text/javascript"></script>
 <script src="js/dynamic_chatbox.js" type="text/javascript"></script>
 <link href="css/chatbox.css" rel="stylesheet" />
 <div id="chat" onClick="open_chatbox();">線上客服</div> 
 <div id="chatBox"> 
 <div class="msg_head">"${mbr.member_name}"</div>
 <div onclick="closeChat()" id="close"><span class="glyphicon glyphicon-minus"></span></div><br> 
 <div >
 <input type="hidden" value="${mbr.member_name}" id="clientName"> 
 
 <textarea id="ws2" readonly="readonly" rows="15" cols="30">歡迎進入Pokesong線上客服...                 </textarea><br/>
 </div> 
 <div> 
 <input type="text" id="mqtt_text" size="25" placeholder="請在此輸入訊息..." />
 <button id="mqtt_pub" style="background-color:#00E3E3";color:white;">發送</button>
 </c:if> 
 </div> 
 </div>

</body>
