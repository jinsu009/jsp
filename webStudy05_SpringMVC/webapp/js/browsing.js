/**
 * 
 */
	
//------------------------
//var fileid ="";
//var radioval ="";
//$(".radio").hide(); 
//--------------------------
$.fn.browse=function(obj){
	const browsiongURL=obj.browsiongURL;
	const eleClass = obj.eleClass;
	
	const browseTag = this;
	
	const processURL = obj.processURL;
	var commandProcess = function(obj){
//		console.log("obj"+obj);
		$.ajax({
			url : processURL,
			data : {
				// command pattern : 명령에 대한 모든 정보를 가진 object : command object 
				command:obj.command
				, file : obj.file.prop("id")
				, dest : obj.dest?obj.dest.prop("id"):""
			},
			method : "post",
			dataType : "json",
			// Accept : 
			// html > text/html, Content-Type:text/html
			// json >  application/json 
			success : function(resp) {
				console.log(resp)
				if(resp.status==200)
				{// 처리가 정상적으로 이루어졌을 때 >> 폴더를 갱신
					obj.src.find("."+eleClass).trigger("refresh");
					if(obj.dest){
						obj.dest.find("."+eleClass).trigger("refresh");
					}
				}else{// 처리에 문제가 생겼을 때
					alert(resp.msg);
				}
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});
	}
	
	
	var toggler = $("#browser").on("click","."+eleClass,function(){
		
		let liTag = $(this).parent();	
  			let caretTag = $(this);
  			let base = liTag.prop("id");
  			let already = $(this).next("ul.nested:first");
  			
  			if(already && already.length>0){
  				liTag.find(".nested:first").toggleClass("active");
  				caretTag.toggleClass("caret-down");
  				return;
  			}
  				$.ajax({
  					//url :"ddit/contextBrowse.do",
  					url :browsiongURL, // 절대경로를 사용하기 위해 파라미터 값으로 받아온 변수를 넣어준다. 
					data : {
						base:base
					},
					method : "get",
					dataType : "json",
					// Accept : 
					// html > text/html, Content-Type:text/html
					// json >  application/json 
					success : function(resp) {
						console.log(resp);	
						if(!resp) return;
						let ulTag = $("<ul>").addClass("nested")
											 .sortable({
												items :"> li.file"
												, connectWith: ".fol>.nested"
												, receive : function(event, ui){
//													console.log(event); // ctrlkey(ctrl키를 눌렀는지 판단) : false(이동) > true(복사)
//													console.log(ui);
//													console.log(this); // ul 태그 
//													console.log($(this).parent("li").prop("id"));
													let dest = $(this).parent("li");
													let src = ui.sender.parent("li");
													let commandObj = {
															command : event.ctrlKey?"COPY":"MOVE"
															, file : ui.item
															, dest : dest
															, src : src
													}
													commandProcess(commandObj);
												}
											});	
						$(resp).each(function(idx, wrapper){
							 let liTag = $("<li>").prop({
								 	 "id":wrapper.id,
									 "class":wrapper.clzName});
							if(wrapper.directory){
								liTag.html($("<span>").addClass(eleClass).text(wrapper.name));
							}else{
								liTag.text(wrapper.name)
								.addClass("dropdown-item")
								.on("click", function(){
									if(!$(this).hasClass("active")){
										browseTag.find("li.file").removeClass("active");
									}
									$(this).toggleClass("active");
								});
							}
							ulTag.append(liTag);
						});
						caretTag.after(ulTag);
		  				liTag.find(".nested:first").toggleClass("active");
		  				caretTag.toggleClass("caret-down");
					},
					error : function(errorResp) {
						console.log(errorResp.status + ":"
								+ errorResp.responseText);
					}
				});  
  			}).on("refresh", "."+eleClass, function(){
  				//이동, 복사, 삭제가 완료되면  다시 비동기 요청을 발생시켜 특정한 폴더의 갱신된 목록을 받아온다. 
  				let already = $(this).next("ul.nested:first");
  				already.remove();
  				$(this).trigger("click");
  			});
	
	$(document).on("keyup",function(event){
//		console.log(event);
		if(event.keyCode==46){
			let file = browseTag.find("li.active");
			if(file.length>0 && confirm("삭제하시겠습니까?")){
				let commandObject={
						command : "DELETE"
						, file : file
						, src : file.parents("li.fol:first")
				}
				commandProcess(commandObject);
			}
		}
	});
	
	return this; // 체이닝소스에서는 return을 넘겨줘야 한다. 
}

//------------------------------------
/*
$("#browser").on("click",".file",function(){
		
		$("#MenuBtn").empty();
		fileid = $(this).prop("id");
		alert(fileid)
		let movebtn = $("<button>",{"text":"이동", "id":"moveBtn"}).css("margin","10px");
		let copybtn = $("<button>",{"text":"복사","id":"copyBtn"}).css("margin","10px");
		let deletebtn = $("<button>",{"text":"삭제","id":"deleteBtn"}).css("margin","10px");
		
		$("#MenuBtn").prepend(movebtn,copybtn,deletebtn);

		$(".radio").show();
		
	});	
		
$(document).on("click","#moveBtn",function(){
	
	radioval = $("input[name='folder']:checked").prop("value");
	alert(fileid)
	alert(radioval)
	
	$.ajax({
		url :"./FileMCD",
		method : "get",
		data : {
			fileid:fileid, 
			radioval:radioval
		},
	})	
}); 
*/