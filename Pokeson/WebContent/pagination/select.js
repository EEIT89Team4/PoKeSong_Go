//進階搜尋用
$(function() {
	$(".advclass").click(function() { // click event
		if ($(this).attr("value") == "true") {
			$(this).attr("value", "false");
		} else {
			$(this).attr("value", "true");
		} // 轉換value attribute 的值

		var url = window.location.toString();
//		if (url.indexOf("&") != -1) {
//			var indexnumber = url.indexOf("&");
//			url = url.substring(0, indexnumber);
//		}

		$("[value='true']").each(function() {
			if (!url.includes(encodeURI($(this).text()))) {
				url += "&adv=" + $(this).text();  //如果網址不包含商品 則在點選後加入商品
			}
//			else{   
//				alert('123');
//				url = url.replace('&adv='+$(this).text(),"");				
//			}
		});
		$("[value='false']").each(function() {  //網址已包含商品
			if (url.includes(encodeURI($(this).text()))) {
				url = url.replace("&adv="+encodeURI($(this).text()),"");
			}
		})
		
		//點選條件篩選後自動跳轉在第一頁
		var pos = url.indexOf("searchPage=");
		if(pos!=-1){
			var search='searchPage=';
			var sealenght = search.length;
			url = url.replace("searchPage="+url.charAt(pos+sealenght),"searchPage=1");
		}
		
		window.location.assign(url);
		
	})

})
$(function() {
	$("[value='false']").each(function() {
		if (window.location.toString().includes(encodeURI($(this).text()))) {
			$(this).attr("value", "true");
			$(this).css("background-color", "lightblue");  //改變被選取的種類背景顏色
		} else {
			$(this).attr("value", "false");
		}
	})      
	$(".orderclass").each(function(){
		if (window.location.toString().includes($(this).attr('value'))){
			$(this).css("background-color", "lightblue");   //改變被選取排序項目的背景顏色
		}
	})
})
// 排序用
$(function() {
	$('.orderclass').click(function() {
	var url = window.location.toString();
	
	//尚未排序
	if(!url.includes("&order=")){	
		window.location.assign(url+"&order="+$(this).attr("value"));
	}
	//之前已經排序
	else{
		if(url.includes($(this).attr('value'))){
			//相同排序 //do nothing
		}
		else{
			//相反排序
			var orderstr =$(this).siblings().attr('value');
//			alert($(this).attr('value'))
//			alert($(this).siblings().attr('value').trim());
//			alert(url);
			window.location.assign(url.replace(orderstr,$(this).attr('value')));
		}
			
	}
	})
})