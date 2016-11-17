//var EleSpan = document.getElementById("search");
//				var EleText = document.getElementById("searchbar");
//				EleSpan.addEventListener("click", function() {
//					if (!('webkitSpeechRecognition' in window)) {
//						alert("do not support")
//					} else {
//						var recognition = new webkitSpeechRecognition();
//						recognition.continuous = false;//自動停止辨識
//						recognition.interimResults = false;//是否立刻產生辨識字體
//						recognition.lang = "cmn-Hant-TW";//辨識語言
//						recognition.start();//開始辨識
//						recognition.onresult = function(event) {
//							var i = event.resultIndex;
//							var j = event.results[i].length - 1;
//							var speechtext = event.results[i][j].transcript;
//							if (speechtext.match("我要去Yahoo")) {
//								window.location
//										.replace("http://www.yahoo.com.tw");
//						
//							} 
//							/*else if(speechtext.match("搜尋")){
//								 var searchtext= ducument.getElementById("searchbar").value;
//									window.location.replace("/getSomeProduct?"+searchtext);
//							}*/
//							else {
//								EleText.value = speechtext;
//							}
//						};
//
//					}
//				})
//	      加入購物車並顯示目前購物車數量
	$(".addfavorite").click(function(){
		var tempno=$(this).attr("id");
		$.get("getCartNumber",{"productno":tempno,"count":"1","status":"Insert"},function(data){
			if(data==0){
				
				alert("加入購物車失敗，超過庫存量");
//				sweetAlert("加入購物車失敗，超過庫存量", "", "error");
			}
			else{
				$('img[src="/Pokeson/getImage?productid='+tempno+'"]').animate({
					"opacity":"0.1"					
				    },200).animate({
					"opacity":"1"					
				    },200);
			$("#buycount_all_f").html(data);
			
			}
			
		})
	})		
//	     每次刷新頁面更新購物車數量
		$("#cartsapn").ready(function(){
				$.get("PageChangeCartNumber",{},function(data){
					$("#cartsapn").html(data);
				})
			})

	//單個產品頁面加入購物車
			$(".addfavorite1").click(function(){
				alert(1);
		var tempno=$(this).attr("id");
		var count=$('select[name="count"]').val();
		alert(count);
		$.get("getCartNumber",{"productno":tempno,"count":count},function(data){
			if(data.match("0")){
				alert("加入購物車失敗，超過庫存量");
//				sweetAlert("加入購物車失敗，超過庫存量", "", "error");
			}
			else{$("#buycount_all_f").html(data);} //購物車後數字
			
		})
	})		
	//購物車改變數量
    $('.CompareSel').change(function(){
    	var count=$(this).val();	
//    	alert(count);
    	var price=$(this).parent().prev().html()
    	//alert(typeof(new Number(price)));
    	$(this).parent().next().empty().html(new Number(count)*new Number(price));
				})
    
