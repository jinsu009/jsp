/**
 * 
 */
$.fn.browse=function(obj){
	const browsingURL = obj.browsingURL;
	const eleClass = obj.eleClass;
	const processURL = obj.processURL;
	const browseTag = this;
	
	var commandProcess = function(obj){
//		console.log(obj);
		$.ajax({
			url : processURL,
			data : {
				command:obj.command
				, file:obj.file.prop("id")
				, dest: obj.dest?obj.dest.prop("id"):""
			},
			method : "post",
			dataType : "json", // Accept:application/json, Content-Type:application/json
			success : function(resp) {
				console.log(resp);
				if(resp.status==200){
					obj.src.find("."+eleClass).trigger("refresh");
					if(obj.dest){
						obj.dest.find("."+eleClass).trigger("refresh");
					}
				}else{
					alert(resp.message);
				}
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});
	}
	
	var toggler = this.on("click", "."+eleClass, function(){
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
			url:browsingURL,
			data : {
				base:base
			},
			method : "get",
			dataType : "json", // Accept:application/json, Content-Type:application/json
			success : function(resp) {
				console.log(resp);
				if(!resp) return;
				let ulTag = $("<ul>").addClass("nested")
									 .sortable({
										 items:"> li.file"
										 , connectWith:".folder>.nested"
										 , receive:function(event, ui){
//											 console.log(event);
//											 console.log(ui);
//											 console.log($(this).parent("li").prop("id"));
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
									"class":wrapper.clzName
								});
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
		let already = $(this).next("ul.nested:first");
		already.remove();
		$(this).trigger("click");
	});
	$(document).on("keyup", function(event){
//		console.log(event);
		if(event.keyCode==46){
			let file = browseTag.find("li.active");
			if(file.length>0 && confirm("삭제하시겠습니까?")){
				let commandObject = {
					command:"DELETE"
					, file : file
					, src : file.parents("li.folder:first")
				};
				commandProcess(commandObject);
			}
		}
	});
	
	return this;
}












