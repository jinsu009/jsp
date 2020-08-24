/**
 * 
 */
// jquery 플러그인 형태로 라이브러리 만들기
	$.fn.dynamicSelect = function(){
		this.each(function() {
			let url = $(this).data("url");
			let success = $(this).data("success");
			let obj = {
				url : url,
				success : success
			}
			dynamicElementGen(obj)
		});
		
		// 이벤트 드리블 방식을 이용해서 데이터 넘기기 
		this.on("renew",function(event,parameter){
			// 이벤트 호출 함수 사용시 파라미터값도 같이 불러오기 
			
// 			alert(parameter);
			// prod.buyerTag 호출
			let obj = {
				url : $(this).data("url"), // this : 이벤트 발생 target
				success : $(this).data("success"),
				data : parameter 
			}
			dynamicElementGen(obj);
		});
		
		function dynamicElementGen(obj) {
			$.ajax({
				url : obj.url,
				data : obj.data ? obj.data : {}, //데이터가 있으면 obj data 넘기고 아니면 빈 객체를 전달 
				dataType : "json",
				// Accept : 
				// html > text/html, Content-Type:text/html
				// json >  application/json 
				success : obj.success,
				error : function(errorResp) {
					console
							.log(errorResp.status + ":"
									+ errorResp.responseText);
				}
			});
		}
		return this; // 함수의 체인 형태 
	}