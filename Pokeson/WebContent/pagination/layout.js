$('#linelayout').click(function(){
	$.get("LayoutServlet",{"layout":"line"},function(datas){
	});
	$("#productlayout2").css("display","none");
	$("#productlayout1").css("display","block");
})
$('#gridlayout').click(function(){
$.get("LayoutServlet",{"layout":"grid"},function(datas){
	});
	$("#productlayout1").css("display","none");
	$("#productlayout2").css("display","block");
})