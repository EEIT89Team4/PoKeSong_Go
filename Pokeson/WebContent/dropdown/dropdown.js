	//全站分類按鈕
 	$(function(){
 		var btnFlag = false;
		
		$('#dropbtn').click(function(){

		if(btnFlag==false){
				$('#dropicon').removeClass("glyphicon-align-justify");
				$('#dropicon').addClass("glyphicon-menu-down");
				
				$('.dropdown-content').slideDown(200)
				$(this).css({
					'background-color': '#888888'
				})
				
				btnFlag = true;
			}else{
				$('#dropicon').removeClass("glyphicon-menu-down");
				$('#dropicon').addClass("glyphicon-align-justify");
				
				$('.dropdown-content').slideUp(200)
				
				$(this).css({
					'background-color': '#000000'
				});
				
				btnFlag = false;
			}
		});
	})

	//子分類跳出
$(function(){
		

	var classno = $('.dropdown-content > a').attr('value');	
	
	var timer;
	
	$(".dropdown-content > a").on("mouseover",showRList);
	
	$(".right-list").on("mouseover",function(){
		if(timer){clearTimeout(timer);}
	    $(".right-list").show();	
	});
	
	$(".dropdown-content > a").on("mouseout",hideRList);
	
	$(".right-list").on("mouseout",function(){
		timer=setTimeout(function(){
	        $(".right-list").fadeOut(250);
	        console.log("infadeOut");
	    },100);
	});
	
	function showRList(){
	    if(timer){clearTimeout(timer);}
	    	$(".right-list").fadeIn(250);    	
	}
	function hideRList(){
		if(timer){clearTimeout(timer);}
	    timer=setTimeout(function(){
	        $(".right-list").fadeOut(250);
	        console.log("fadeOut");
	    },100);
	}
	
	})
	
	//子分類 用JSON呈現
	$(function(){
		var class_no;
		
		$('.dropdown-content > a').mouseover(function(){
			class_no = $(this).attr('value');
			
		$('#rl').empty();
		
			
			if(class_no!=4){
			$.getJSON("rightList?classno=" + class_no, {} , function(data){
				$.each(data,function(){
//					console.log("子分類each");
					
					var classdetail_name = this.classdetail_name;
					var classdetail_no = this.classdetail_no;
					
					var div = $("<div></div>");
					
					var row = $("<div></div>");
					
					var ul = $("<ul></ul>").addClass("right-ul");
					
					var a = $("<a></a>")
						.attr("href" ,"/Pokeson/detailProduct?detailno=" + classdetail_no + "&detail=" + classdetail_name)
						.attr("value" , classdetail_no).text(classdetail_name);
					
					var h3 = $("<h3></h3>");
					h3.append(a);
					
					var li = $("<li></li>").append(h3);
					
					ul.append(li);
					
					$.each(this.supplier_name, function(index,value){
//						console.log("進入品牌each="+index);
//						console.log(value);
						var suppliera = $("<a></a>")
							.attr("value" , value).text(value)
							.attr("href","/Pokeson/brandProduct?detailno=" + classdetail_no
									+ "&brand=" + value);
						
					
						
						var supplierli = $("<li></li>").append(suppliera);
						
						ul.append(supplierli);
					});
					
					div.append(ul)
		
					row.append(div);
					
					$('#rl').append(row);
				});
			})	
			}
		})		
	})
	
	