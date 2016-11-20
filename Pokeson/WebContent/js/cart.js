$('#deletecart').click(function(){
	
	$('#tablediv').empty().html("<h1>目前無任何商品</h1><br><a>回首頁繼續購物</a>");	
	$.get("deleteCart",{},function(data){$("#cartsapn").empty();});
});

$('.cartbtn').click(function(){
	var num = $(this).attr("name");
	$.get("deleteCartItem",{"CartItem":num},function(data){
		$('#cartsapn').empty().html(data);
		
		if(data==0){
			$("#tablediv").insertAfter("<h1>目前無任何商品</h1><br><a>回首頁繼續購物</a>");
		}
		else if(data!=0){
			location.replace("ShowCartContent.jsp");
		}
	});
	location.replace("ShowCartContent.jsp");
	$(this).parent().parent().remove();
})