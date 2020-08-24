/**
 * 
 */
$.fn.sessionTimer = function (timeout){
	console.log(this);		// const : 상수 , 변하지 않는값
	const self = this;
		const initTimeout = timeout;
		var msgOutId = null;
		
			function formatting(timeValue){
				let min = Math.trunc(timeValue/60);
				let sec = timeValue%60;
				return min+":"+sec;
			}
			function init(){
				msgArea = self.find("#msgArea").hide();
				timeValue = initTimeout;
				
				if(msgOutId){
					// msgOutId == null || not defiend 인경우 새로 시작 
					clearTimeout(msgOutId);
				}
				
				msgOutId = setTimeout(function(){
					// 4분에서 1분 남은 시점에 실행해야할 함수 작성 	
					// timeout은 끝나면 자동으로 clear;
						msgArea.show();
					}, (initTimeout - 60)*1000 ); // millisecond
			}
			
			init(); // 상수결정후 초기화 함수 호출 
			
			var timerArea = self.find("#timerArea");
			
			if(!timerArea || timerArea.length==0){
				//객체가 아직 존재하지 않거나 lenght == 0 일 때 , div 태그 생성 
				timerArea = $("<div>").prop({id:"timerArea"});
				self.prepend(timerArea);
			}
			
			var msgBtn = msgArea.find(".msgBtn").on("click",function(){
				let btnId = $(this).prop("id");
				if(btnId == "yesBtn"){
					init(); 
				}
				msgArea.hide();
			});
			
			var timerId = setInterval(function(){
				if(--timeValue <= 0 ){
					clearInterval(timerId);
				}else{
					timerArea.text(formatting(timeValue));
				}
			}, 1000); // second
}
