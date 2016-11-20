var EleSpan = document.getElementById("search");
				var EleText = document.getElementById("searchbar");
				EleSpan.addEventListener("click", function() {
					if (!('webkitSpeechRecognition' in window)) {
						alert("do not support")
					} else {
						var recognition = new webkitSpeechRecognition();
						recognition.continuous = false;//自動停止辨識
						recognition.interimResults = false;//是否立刻產生辨識字體
						recognition.lang = "cmn-Hant-TW";//辨識語言
						recognition.start();//開始辨識
						recognition.onresult = function(event) {
							var i = event.resultIndex;
							var j = event.results[i].length - 1;
							var speechtext = event.results[i][j].transcript;
							if (speechtext.match("我要去Yahoo")) {
								window.location
										.replace("http://www.yahoo.com.tw");
						
							} 
							else if(speechtext.match("搜尋")){
								 var searchtext= ducument.getElementById("searchbar").value;
									window.location.replace("/getSomeProduct?"+searchtext);
							}
							else {
								EleText.value = speechtext;
							}
						};

					}
				})