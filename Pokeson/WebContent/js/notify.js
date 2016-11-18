$('.addnotify').click(function(){
	var productid = $(this).attr("id");
// 	alert(productid);
	$.post("notifyMember",{"p_id":productid},function(data){
		if(data==0){
// 			sweetAlert("22","success");
		}
		if(data==1){
			sweetAlert("註冊成功", "", "success");
		}
		if(data==2){
			
		}
	})
})