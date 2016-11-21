<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<!-- <script src="js/jquery-3.1.1.min.js"></script>  -->
<!-- <script src="js/jquery-1.9.1.js"></script>  -->
<!-- <script src="js/jquery-3.1.1.js"></script>  -->
<script type="text/javascript">
$(function(){
	// 先取得必要的元素並用 jQuery 包裝
	// 再來取得 $block 的高度及設定動畫時間
	var $block = $('#abgneBlock'),
		$slides = $('ul.list', $block),
		_width = $block.width(),
		$li = $('li', $slides),
		_animateSpeed = 600,
		// 加入計時器, 輪播時間及控制開關
		timer, _showSpeed = 3000, _stop = false;
 
	// 產生 li 選項
	var _str = '';
	for(var i=0, j=$li.length;i<j;i++){
		// 每一個 li 都有自己的 className = playerControl_號碼
		_str += '<li class="playerControl_' + (i+1) + '"></li>';
	}
 
	// 產生 ul 並把 li 選項加到其中
	var $playerControl = $('<ul class="playerControl"></ul>').html(_str).appendTo($slides.parent()).css('left', function(){
		// 把 .playerControl 移到置中的位置
		return (_width - $(this).width()) / 2;
	});
 
	// 幫 li 加上 click 事件
	var $playerControlLi = $playerControl.find('li').click(function(){
		var $this = $(this);
		$this.addClass('current').siblings('.current').removeClass('current');
 
		clearTimeout(timer);
		// 移動位置到相對應的號碼
		$slides.stop().animate({
			left: _width * $this.index() * -1
		}, _animateSpeed, function(){
			// 當廣告移動到正確位置後, 依判斷來啟動計時器
			if(!_stop) timer = setTimeout(move, _showSpeed);
		});
 
		return false;
	}).eq(0).click().end();
 
	// 如果滑鼠移入 $block 時
	$block.hover(function(){
		// 關閉開關及計時器
		_stop = true;
		clearTimeout(timer);
	}, function(){
		// 如果滑鼠移出 $block 時
		// 開啟開關及計時器
		_stop = false;
		timer = setTimeout(move, _showSpeed);
	});
 
	// 計時器使用
	function move(){
		var _index = $('.current').index();
		$playerControlLi.eq((_index + 1) % $playerControlLi.length).click();
	}
});

</script>

</script>


<style>
#abgneBlock {
	width: 900px;
	height: 279px;
	position: relative;
	overflow: hidden;
	border: 1px solid #ccc;
	-webkit-box-sizing: content-box; 
      -moz-box-sizing: content-box; 
           box-sizing: content-box; 
}
#abgneBlock ul.list {
	padding: 0;
	margin: 0;
	list-style: none;
	position: absolute;
	width: 9999px;
	height: 100%;
}
#abgneBlock ul.list li {
	float: left;
	width: 900px;
	height: 100%;
}
#abgneBlock .list img{
	width: 100%;
	height: 100%;
	border: 0;
}
#abgneBlock ul.playerControl {
	margin: 0;
	padding: 0;
	list-style: none;
	position: absolute;
	bottom: 5px;
	right: 5px;
	height: 14px;
}
#abgneBlock ul.playerControl li {
	float: left;
	width: 10px;
	height: 10px;
	cursor: pointer;
	margin: 0px 2px;
	background: url(images/cir_ctrl.png) no-repeat -10px 0;
}
#abgneBlock ul.playerControl li.current {
	background-position: 0 0;
}
</style>
</head>
<body>
	<div id="abgneBlock">
		<ul class="list">
			<li><a href="#">
			<img src="${pageContext.servletContext.contextPath}/getindexImage?cm_no=1" />
			</a></li>
			<li><a href="#">
			<img src="${pageContext.servletContext.contextPath}/getindexImage?cm_no=2" />
			</a></li>
			<li><a href="#">
			<img src="${pageContext.servletContext.contextPath}/getindexImage?cm_no=3" />
			</a></li>
			<li><a href="#">
			<img src="${pageContext.servletContext.contextPath}/getindexImage?cm_no=4" />
			</a></li>
<!-- 			<li><a target="_blank" href="#"><img src="images/cola.jpg"></a></li> -->
<!-- 			<li><a target="_blank" href="#"><img src="images/img10.jpg"></a></li> -->
<!-- 			<li><a target="_blank" href="#"><img src="images/img11.jpg"></a></li> -->
<!-- 			<li><a target="_blank" href="#"><img src="images/img12.jpg"></a></li> -->
		</ul>
	</div>
</body>
