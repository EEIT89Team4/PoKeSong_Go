<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="productclassdao" class="com.productclass.model.ProductClassDAO" scope="page" />
<head>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/productindex.js"></script>
<script src="js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script src="js/Popup.js"></script>
<!-- Pagination -->
<link rel="stylesheet" type="text/css" href="pagination/themes/material/easyui.css">
<link rel="stylesheet" type="text/css" href="pagination/themes/icon.css">
<link rel="stylesheet" type="text/css" href="pagination/demo.css">
<link rel="stylesheet" type="text/css" href="pagination/layout.css">
<script type="text/javascript" src="pagination/jquery.easyui.min.js"></script>
<script type="text/javascript" src="pagination/easyui-lang-zh_TW.js"></script>
<!-- Dropdown Menu 引用  註:須引用bootstrap -->
<script src="dropdown/dropdown.js"></script>
<link rel="stylesheet" href="dropdown/dropdown.css">
<!-- popicon -->
<link rel="stylesheet" href="css/popicon.css">

<!-- 最近流覽商品 -->
<link rel="stylesheet" type="text/css" href="history/history.css">


<style>
#membermenu {
   margin: auto; 
}
.main {
/*   margin-right: 30px; */
  position:absolute;
/*   background-color: 	#00BBFF; */
  color: white;
  font-family: 微軟正黑體;
  font-weight: bold;
  font-size: 20px;
  cursor: pointer;
  text-align: center;
  height: 100px;
  line-height: 35px;
  width: 100px;
  z-index:12;
}

.main:hover {
/*    background-color: #CCEEFF;  */
}

.sub {
  position:absolute;  
  cursor: pointer;
  background-color:#00AA55;
  color: white;
  font-family: 微軟正黑體;
/*   text-align: center; */
  font-weight: bold;
  width: 230px;
  z-index:11;
}
.sub ul {
  width:200px;
  list-style-type: none; 
  line-height: 100px;
}

.sub li {
  font-size: 20px;
  width:200px;
  color:white;
  list-style-type: none;
  padding-right:80px;
  line-height: 100px;
  height: 70px
}
.sub li a{
color:white;
/*   font-size: 16px; */
/*   width:200px; */
/*   list-style-type: none; */
/*    padding-right:100px;  */
/*   line-height: 100px; */
/*    height: 70px */
}
#div1{
position:relative;
z-index:9999;
width:400px;
}
</style>
</head>
<body>
<% Cookie[] cookiesarray = request.getCookies(); %>
  <% try{
  		for(int i = 0;i<cookiesarray.length;i++){ 
			 if(cookiesarray[i].getName().equals("user")){  
				 String name = cookiesarray[i].getValue(); 
				 session.setAttribute("user",name); 
			 }if(cookiesarray[i].getName().equals("password")){
				 String pwd = cookiesarray[i].getValue(); 
				 session.setAttribute("password",pwd);
			 }if(cookiesarray[i].getName().equals("rm")){
				 String rm = cookiesarray[i].getValue();
				 session.setAttribute("rm",rm);
			 }
		}
  	}catch(NullPointerException e){}
	%> 

<div id="headcolor">
<div id="menu1">
		<table id="menuhead">
			<tr>
				<td><a href="index.jsp" style="text-decoration:none;">首頁</a></td>
				<c:if test="${empty mbr}">
					<td><a  id="myBtn" style="text-decoration:none;">登入</a></td>
					
				</c:if>
				<c:if test="${not empty mbr}">		 
					<td><a href="#" id="signOut" style="text-decoration:none;">登出</a></td>
				</c:if>
					<td><a href="addMbr.jsp" style="text-decoration:none;">註冊</a></td>
<!-- 					<td><a href="mbrZone.jsp">會員專區</a></td>	 -->
				<c:if test="${not empty mbr}">		 
					<td><h4 style="color:#00BBFF;marign:20px">${mbr.member_name}  你好</a></h4></td>
				</c:if>
			</tr>
		</table>
		
		<c:if test="${not empty errorLogin}">
		<c:choose>
		<c:when test="${not empty errorLogin.ErrState }">
			<script>
				$(document).ready(function() {
// 					$("#myModal").modal({backdrop : false});
// 					$("#myModal").remove();
					alert("會員狀態異常,無法登入");
				});
			</script>
			</c:when>
		
		<c:otherwise>
			<script>
				$(document).ready(function() {
					$("#myModal").modal({backdrop : false});
					$("#myModal").modal('show');
				});
			</script>
			</c:otherwise>
			</c:choose>
		</c:if>
	</div>
	</div>
<div id="header">
	<div id="logo">
		<h1><a href="#">PokeSong </a></h1>
	</div>
</div>
<!-- end #header -->
<div id="wrapper">
	<!-- dropdown menu  -->
	<div style="display: inline-block">
		<button id="dropbtn" class="dropbtn">
			<span id="dropicon" class="glyphicon glyphicon-align-justify"></span>
			全站分類
		</button>
	</div>
	<!--搜尋  -->
	<div id="search">
		<form method="get" action="searchPage">
			<div>
				<input type="text" name="search" placeholder="請輸入商品關鍵字" id="search-text" value="" autocomplete="off">
				<span>
					<button id="vocalsearch" type="button">
					<span class="glyphicon glyphicon-bullhorn">
					</span>
					</button>
				</span>
				<div id="div1"></div>
			</div>
			
			<div id="search-submit">
				<button style="width:100%" type="button">
					<span>
					<img id="submitimg" src="images/search.png">
					</span>
					</button>
			</div>
			
		</form>
	</div>
	
	
	<script>
		var show;
		var xhr=null;
		var datas;
		var txt ;
		window.addEventListener("load", init, false);
		
		function init() {
			 txt = document.getElementById("search-text");
			txt.addEventListener("keyup", getData, false);
			show = document.getElementById("div1");
			
		}
		function getData() {
// 			alert("....");
			xhr=new XMLHttpRequest();
			if(xhr!=null){
				var url="JsonSimpleDemo?keyword="+txt.value;
				
				xhr.open('GET',url);
				xhr.addEventListener("readystatechange",callback);
				xhr.send();
			}else{
		 		alert("您的瀏覽器不支援Ajax功能!!");
		 	}
			show.style.display = "block";
			if (show.childNodes.length > 0) {
				show.removeChild(show.childNodes[0]);
			}
		}
		function callback(){
			if (xhr.readyState == 4){
		 		if(xhr.status == 200){
		 			 datas=JSON.parse(xhr.responseText);
		 			var eleDiv = document.createElement("div");
					eleDiv.className = "list-group";
                    if(txt.value!=""){
					for (var j = 0, max = datas.length; j < max; j++) {
						var txtBtn = document.createTextNode(datas[j]);
						var eleBtn = document.createElement("button");
						eleBtn.className = "list-group-item";
						eleBtn.setAttribute("type", "button");
						eleBtn.appendChild(txtBtn);

						eleBtn.addEventListener("click", function() {
							document.myData.keyword.value = this.firstChild.nodeValue;
							show.style.display = "none";
						}, false)
						eleDiv.appendChild(eleBtn);
					}
					show.appendChild(eleDiv);
                     }
		 			 
		 		}
			}
		 		}
		
	</script>
	

	<!--搜尋  -->
	<!--分類選單  -->
	<div class="dropdown-content">
			<c:forEach var="productclassitem" items="${productclassdao.all}">
				<a
					href="${pageContext.servletContext.contextPath}/classProduct?classno=${productclassitem.class_no}&class=${productclassitem.class_name}"
					value="${productclassitem.class_no}">${productclassitem.class_name}</a>
				<br>
			</c:forEach>			
	</div>
	
	<!--子分類選單  -->
	<!--搜尋coding(導向搜尋專有頁面)-->
			<script>	
			$(function(){
				$('#search-submit').click(function(){
					EleText = $('#search-text').val().trim();
// 					alert(EleText);
					if(EleText == "" || EleText == null){
						alert("請輸入搜尋關鍵字")
					}else{
						//預設搜尋後顯示為第1頁，每頁顯示10筆
						window.location.assign("${pageContext.servletContext.contextPath}/searchPage?search="+EleText+"&searchPage=1&searchSize=10");	
					}
				});
			})
			</script>
<!-- 	------------------------------------------		 -->
	<div id="rl" class="right-list">
	
<!-- 			<ul class="right-ul"> -->
<!-- 				<li><h3> -->
<!-- 						<a href="#筆記型電腦">筆記型電腦</a> -->
<!-- 					</h3></li> -->
<!-- 				<li><a href="#">子分類</a></li> -->
<!-- 				<li><a href="#">子分類</a></li> -->
<!-- 			</ul> -->

<!-- 			<ul class="right-ul"> -->
<!-- 				<li><h3> -->
<!-- 						<a href="#平板電腦">平板電腦</a> -->
<!-- 					</h3></li> -->
<!-- 				<li><a href="#">子分類</a></li> -->
<!-- 				<li><a href="#">子分類</a></li> -->
<!-- 				<li><a href="#">子分類</a></li> -->
<!-- 			</ul> -->
		</div>	
	
</div>
<!---------------------------------------登入彈跳視窗--------------------------->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content -->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px;">
<%-- 					<c:if test="${empty errorLogin}"> --%>
						<button type="button" class="close" data-dismiss="modal" id="close">&times;</button>
<%-- 					</c:if> --%>
						<h4>
							 會員登入
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form role="form" action="MemberServlet" method="post">
							<div class="form-group">
								<label for="usrname"><h3> 帳號</h3></label> 
								<input type="text" class="form-control" id="usrname" name="member_id" value="${sessionScope.user}"placeholder="Enter id">
								<br><font color="red" size="-1">${errorLogin.ErrIdEmpty}  ${errorLogin.ErrIdFormat}  ${errorLogin.ErrId} </font>
							</div>
							<div class="form-group">
								<label for="psw"><h3>密碼</h3></label> 
								<input type="password" class="form-control" id="psw" name="member_password1" value="${sessionScope.password}" placeholder="Enter password">
								<br><font color="red" size="-1">${errorLogin.ErrPasswordEmpty}  ${errorLogin.ErrPasswordFormat} ${errorLogin.Err} </font>
		
							</div>
							<div class="form-group">
								<!------------------- 輸入驗證碼欄位 ----------------->
								<label for="identity"><h3>輸入驗證碼:</h3></label>
								<input type="text" class="form-control" id="identity" name="identity" placeholder="不分大小寫">
								<br><font color="red" size="-1">${errorLogin.ErrIdentityEmpty} ${errorLogin.ErrIdentity}</font>
							</div>
							<img src="IdentityServlet" id="pic" style="width:100px; height:50px"/>
								<input type="button" id ="btn" value="換個圖片" >
							<div class="checkbox">
								<label><input type="checkbox" name="rm" 
										<c:if test='${sessionScope.rm == true}'>
											checked ='checked'
										</c:if>
										value="true">Remember me
								</label>
							</div>
							<button type="submit" class="btn btn-success btn-block">
								<input type="hidden" name="action" value="login">
								 登入
							</button>
							
							<button class="btn btn-success btn-block">
							<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&redirect_uri=http://localhost:8081/Pokeson/Login.do&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email">以Google帳戶登入</a>				
							</button>
						</form>
					</div>
					<div class="modal-footer">
						<p>
							<a href="addMbr.jsp">註冊</a>
						</p>
						<p>
							<a href="forgetPwd.jsp">忘記密碼?</a>
						</p>
					</div>
					
				</div>
			</div>
		</div>
 	<!------------------------------------登出  ---------------------------------------->
	<script>
	$(function(){
		$('#signOut').click(function(){
			$.get('MemberServlet',{action:"signOut"},function(data){
				if(data==("signOutOK")){
		        document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8081/Pokeson/index.jsp";
				}
			})
			
		})
		});
    </script>
    <!--------------------------------------------------------------------------------->
	    <!-----------------------------------登入視窗按x導回首頁---------------------------------------->
	
	<script>
	$(function(){
		$('#close').click(function(){
		
				document.location.href = "${pageContext.servletContext.contextPath}/index.jsp";
		}
		)
		});
    </script>
	
<div id="membermenu" style="position:fixed;top:260px;right:20px;font-size:9pt;"><div class="main"><div style="padding:0px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white;z-index:20"><div style="width:100px;height:100px;cursor:pointer;background:url(images/membermenu1.png);background-repeat:no-repeat;text-align:center;"">會員專區</div></div></div>
					<div class="sub" style="left:-70px;top:100px">
                    <ul id="memberzone">
                    <c:if test="${empty mbr}">
                    	<li><a href="#" name="myBtn">修改會員資料</a></li>
		                <li role="presentation"><a href="#" name="myBtn">修改密碼</a></li>
		                <li><a href="#" name="myBtn">查詢歷史訂單</a></li>
                    </c:if>
	                <c:if test="${not empty mbr}">
<%-- 		                <li><a href="${pageContext.servletContext.contextPath}/mbrZone.jsp">會員專區</a></li> --%>
                             <li role="presentation"><a href="MemberServlet?action=getMbr&member_no=${mbr.member_no}">修改會員資料</a></li>
		                <li role="presentation"><a href="updatePwd.jsp">修改密碼</a></li>
		                <li><a href="OrderList.jsp">查詢歷史訂單</a></li>
	                </c:if>
<!--                   <li></li> -->
                    </ul></div></div>
<!------------------------------------ myfavorite ---------------------------->
    	
<div id="myfavorite" style="position:fixed;top:160px;right:15px;z-index:9;font-size:9pt;"  ><div style="width:100px;height:100px;cursor:pointer;background:url(images/myfavorite.png);background-repeat:no-repeat;text-align:center;" onclick="to_myfavorite();">
<div style="padding:24px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white">我的最愛</div>
</div></div>	

<!------------------------------------ myfavorite ---------------------------->

<!------------------------------------ shopping car ---------------------------->

<div id="chkout" style="position:fixed;top:50px;right:15px;z-index:10;font-size:9pt;">
<div style="width:100px;height:100px;cursor:pointer;background:url(images/ShoppingCart.png);background-repeat:no-repeat;text-align:center;" onclick="to_mycar1();">
<div style="padding:2px 0px 0px 0px;font-size:11pt;font-family:微軟正黑體;color:white">結 帳</div>
<div style="font-weight:bold;color:yellow;font-family:arial;margin-top:15px;margin-right:-55px"><span id="buycount_all_f" style="font-size:18pt;">${mycart.productMap.size()}</span></div></div></div>

<!------------------------------------ shopping car ---------------------------->

<!------------------------------------ 瀏覽紀錄---------------------------------->
		
		
		<div id="slider_scroll">
		 	<div id="slider_tab">
			    <span>最</span>
			    <span>近</span>
			    <span>瀏</span>
			    <span>覽</span>
			    <span>商</span>
			    <span>品</span>
			</div>
			<div id="slider_content">
				
		    	<div  id="history" class="container" style="width:100%">
		    	
		    	<c:forEach var="proHistory" items="${proHistory}" varStatus='x'>
		    		<c:if test='${x.first}'>
		    			<div id="historyfirst" style="margin-top:20px;width:100%;">
			    			<a href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${proHistory.product_no}">
					    	<div class="thumbnail style="height:252px" >
							<img src="${pageContext.servletContext.contextPath}/getImage?productid=${proHistory.product_no}" />
							<div class="caption">
								<h6>${proHistory.product_name}</h6>
								<p>售價:${proHistory.product_price}</p>
							</div>
							</div>
							</a>	
				    	</div>
		    		</c:if>
		    		
		    	<c:if test='${x.count>1}'>
		    		
			    	<div style="margin:30px 0px;width:100%">
			    	
			    	<a href="${pageContext.servletContext.contextPath}/getOneProduct?productid=${proHistory.product_no}">
			    	<div class="thumbnail style="height:252px"">
					<img src="${pageContext.servletContext.contextPath}/getImage?productid=${proHistory.product_no}" />
					<div class="caption">
						<h6>${proHistory.product_name}</h6>
						<p>售價:${proHistory.product_price}</p>
						</div>
					</div>
			    	</a>
			    	</div> 	
		    	</c:if>
		    			
		    	</c:forEach>
		    	
		    	</div>
			</div>
		</div>
		
		<!-- 側邊固定slider code -->
<script>
$(function(){
	var w = $("#slider_content").width();
	
	
	if(document.getElementById("historyfirst")!=null){
		$("#slider_tab").css("display","block");	
	}
	
	$('#slider_content').css('height', ($(window).height() - 20) + 'px' ); //將區塊自動撐滿畫面高度
	
	$("#slider_tab").mouseover(function(){ //滑鼠滑入時
		if ($("#slider_scroll").css('left') == '-'+w+'px')
		{
			$("#slider_scroll").animate({ left:'0px' }, 600 ,'swing');
		}
	});
	
	//滑鼠離開後
	$("#slider_content").mouseleave(function(){
		$("#slider_scroll").animate( { left:'-'+w+'px' }, 600 ,'swing');	
	});	
});


</script>
<!------------------------------------ 瀏覽紀錄---------------------------------->

		
<input type="hidden" name="mbr" id="mbr" value="${mbr}"/>
<input type="hidden" name="mbr_no" id="mbr_no" value="${MemberVO.member_no}"/>
<script>
var mbr=$('#mbr').val();
var mbr_no=$('#mbr_no').val();
var flag=false;
function to_mycar1(){
	
// 	if( mbr=="" ){
// 		$("#myModal").modal({backdrop : false});
// 		$("#myModal").modal('show');
// }else{
	document.location.href="${pageContext.servletContext.contextPath}/ShowCartContent.jsp"
// 	}
}
function to_myfavorite(){

	if( mbr=="" ){
			$("#myModal").modal({backdrop : false});
			$("#myModal").modal('show');
			
	}else{
	
	document.location.href="${pageContext.servletContext.contextPath}/MyFavoriteServlet?myfavoriteaction=GET_ONE&member_no="+mbr_no+"" };
}
// function myfavoritesign(){
// 	document.location.href="${pageContext.servletContext.contextPath}/MyFavoriteServlet?myfavoriteaction=GET_ONE&member_no="+mbr_no+""
// }


</script>
<!-- -----------------------modal ----------------------------------->
	
	<script>
		$(document).ready(function() {
			$("#myBtn").click(function() {
				$("#myModal").modal({
					backdrop : false
				});
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			$("#membermenu li a[name='myBtn']").click(function() {
				$("#myModal").modal({
					backdrop : false
				});
			});
		});
	</script>
	<script>
	// 		驗證碼換張圖
	  	$(document).ready(function(){
	  		$("#btn").click(function(){  
	  			$.get("IdentityServlet",{},function(data){
	  				$("#pic").attr("src","IdentityServlet");
	  			})
	  		});
	  	});
	</script>
	<script>
	$(function(){
		var mbr1=$('#mbr').val();
	
		$(".sub").slideUp(0)
		  for (i = 0; i < $(".main").length; i++) {
		    $(".main:eq(" + i + ")").click({
		      id: i
		      
		      
		    }, function(e) {
		    	if(mbr1==""){
		    		$(document).ready(function() {
						$("#myModal").modal({backdrop : false});
						$("#myModal").modal('show');
					});
		    		
		    	}else{
		      n = e.data.id
// 		      $(".main:eq(" + n + ")").text(newtext[n])
		      $(".sub:eq(" + n + ")").slideToggle()
		      $(".sub:not(:eq(" + n + "))").slideUp()
		    	}
		    })
		    $(".main").mouseout({
		      id: i
		    }, function(e) {
		      n = e.data.id
		        // $(".sub").slideUp(200)
// 		      $(".main:eq(" + n + ")").text(oldtext[n])
		    })
		  }
		$("#membermenu").css("width", $(".main").length * 100)
	})
	
	</script>
	<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
	<script src="js/bootstrap.min.js"></script>
<form style="display:inline;" name="form_into" method="post" action="/product/mycar1"><input type="hidden" name="fr1" value="/"></form></div>
<!-- end #footer -->
</body>

