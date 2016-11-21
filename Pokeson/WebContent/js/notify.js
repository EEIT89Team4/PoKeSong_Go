//$(function(){
//	$('.addnotify').click(function(){
//		alert(123);
//	})
//})

	$('.addnotify').click(function(){
		alert("123");
		var productid = $(this).attr("id");
	 	
		$.post("notifyMember",{"p_id":productid},function(data){
			if(data==0){
	 			alert("22");
			}
			if(data==1){
				alert("註冊成功");
			}
		})
	})
