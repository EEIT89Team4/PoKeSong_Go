
$('.addnotify').click(function() {
//	alert("123");
	var productid = $(this).attr("id");

	$.post("notifyMember", {
		"p_id" : productid
	}, function(data) {
		if (data == 0) {
			$("#myModal").modal({backdrop : false});
			$("#myModal").modal('show');
		}
		if (data == 1) {
			alert("註冊成功");
		}
	})
})

// $('#div5 p a[class="addnotify btn btn-primary"]').click(function(){
// var productid = $(this).attr("id");
// // alert(productid);
// $.post("notifyMember",{"p_id":productid},function(data){
// if(data==0){
// // sweetAlert("22","success");
// }
// if(data==1){
// sweetAlert("註冊成功", "", "success");
// }
// if(data==2){
// }
