var mqtt;
		var reconnectTimeout = null; //重新連接暫停時間
        
		function MQTTconnect() {
			document.getElementById("mqtt_pub").addEventListener('click',
					publish_message);
					
	       document.getElementById("mqtt_text").addEventListener('keyup', function (event) {
           //check to see if the enter key was pressed
           if (event.which === 13) { 
           //if so, run the addTask function
           publish_message();
                   }
            });
					

			if (typeof path == "undefined") {
				path = '/mqtt';
			}
			mqtt = new Paho.MQTT.Client(host, port, path, "web_"
					+ parseInt(Math.random() * 100, 10));
			var options = {
				timeout : 3,
				useSSL : useTLS,
//				cleanSession : cleansession,
				onSuccess : onConnect,
				onFailure : function(message) {
					$('#status').val(
							"Connection failed: " + message.errorMessage
									+ "Retrying");
					setTimeout(MQTTconnect, reconnectTimeout);
				}
			};

			mqtt.onConnectionLost = onConnectionLost;
			mqtt.onMessageArrived = onMessageArrived;

			if (username != null) {
				options.userName = username;
				options.password = password;
			}
//			console.log("Host=" + host + ", port=" + port + ", path=" + path
//					+ " TLS = " + useTLS + " username=" + username
//					+ " password=" + password);
			mqtt.connect(options);
		}

		function onConnect() {
			$('#status').val('Connected to ' + host + ':' + port + path);
			// Connection succeeded; subscribe to our topic
			mqtt.subscribe(topic, {
				qos : 0
			});
			$('#topic').val(topic);
		}

		function onConnectionLost(response) {
			setTimeout(MQTTconnect, reconnectTimeout);
			$('#status').val(
					"connection lost: " + responseObject.errorMessage
							+ ". Reconnecting");

		};

		function onMessageArrived(message) {
            
			
			var topic = message.destinationName;
			var payload = message.payloadString;
            var ws2=$("#ws2").value
// 			$('#ws').prepend('<li>' +payload + '</li>');
			var textarea = $("#ws2").append(payload+"\n");
			
            textarea.scrollTop(textarea[0].scrollHeight - textarea.height());
		};
		
	    function publish_message() {
			var inputName = document.getElementById("clientName");
			var cName = inputName.value;
			
			var input_text = document.getElementById("mqtt_text");
			var payload = input_text.value;
         
		    var words = cName+" : "+payload;
		
			var message = new Paho.MQTT.Message(words);
			message.destinationName = topic;
			mqtt.send(message);
			input_text.value = '';
		}
		
		
		$(document).ready(function() {
			MQTTconnect();
		});