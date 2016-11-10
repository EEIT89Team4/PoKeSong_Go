<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<script src="js/jquery-3.1.1.min.js"></script> 
<script src="js/jquery-1.9.1.js"></script> 
<script src="js/jquery-3.1.1.js"></script> 
<script type="text/javascript">
$(function(){
	// �����o���n�������å� jQuery �]��
	// �A�Ө��o $block �����פγ]�w�ʵe�ɶ�
	var $block = $('#abgneBlock'),
		$slides = $('ul.list', $block),
		_width = $block.width(),
		$li = $('li', $slides),
		_animateSpeed = 600,
		// �[�J�p�ɾ�, �����ɶ��α���}��
		timer, _showSpeed = 3000, _stop = false;
 
	// ���� li �ﶵ
	var _str = '';
	for(var i=0, j=$li.length;i<j;i++){
		// �C�@�� li �����ۤv�� className = playerControl_���X
		_str += '<li class="playerControl_' + (i+1) + '"></li>';
	}
 
	// ���� ul �ç� li �ﶵ�[��䤤
	var $playerControl = $('<ul class="playerControl"></ul>').html(_str).appendTo($slides.parent()).css('left', function(){
		// �� .playerControl ����m������m
		return (_width - $(this).width()) / 2;
	});
 
	// �� li �[�W click �ƥ�
	var $playerControlLi = $playerControl.find('li').click(function(){
		var $this = $(this);
		$this.addClass('current').siblings('.current').removeClass('current');
 
		clearTimeout(timer);
		// ���ʦ�m��۹��������X
		$slides.stop().animate({
			left: _width * $this.index() * -1
		}, _animateSpeed, function(){
			// ��s�i���ʨ쥿�T��m��, �̧P�_�ӱҰʭp�ɾ�
			if(!_stop) timer = setTimeout(move, _showSpeed);
		});
 
		return false;
	}).eq(0).click().end();
 
	// �p�G�ƹ����J $block ��
	$block.hover(function(){
		// �����}���έp�ɾ�
		_stop = true;
		clearTimeout(timer);
	}, function(){
		// �p�G�ƹ����X $block ��
		// �}�Ҷ}���έp�ɾ�
		_stop = false;
		timer = setTimeout(move, _showSpeed);
	});
 
	// �p�ɾ��ϥ�
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
			<li><a target="_blank" href="#"><img src="images/img09.jpg"></a></li>
			<li><a target="_blank" href="#"><img src="images/img10.jpg"></a></li>
			<li><a target="_blank" href="#"><img src="images/img11.jpg"></a></li>
			<li><a target="_blank" href="#"><img src="images/img12.jpg"></a></li>
		</ul>
	</div>
</body>
</html>