/**
 * 
 */
var topHeader = $("#topHeader");
var bodyContent = $("#bodyContent").on("submit", "form", function(){
	let action = $(this).attr("action");
	let method = $(this).attr("method");
	let data = $(this).serialize();
	$.ajax({
		url:action,
		method:method,
		data:data,
		dataType:"html",
		success:function(resp){
			bodyContent.html(resp);
			history.pushState({html:resp}, '', '');
		}		
	});
	return false;
});
$(document).ajaxError(function(resp){
	console.log(resp);
});
$("body, .unfold").on("click", "a.byAjax", function(){
	let url = $(this).attr("href");
	
	if(url=='#') return false;
	else if($(this).hasClass("noAjax")) return true;
	$.ajax({
		url:url,
		method:"get",
		dataType:"html",
		success:function(resp){
			bodyContent.html(resp);
			history.pushState({
				html:resp
				, url:url
			}, '', '');
			sidebar.find("li").removeClass("active");
			sidebar.find("a[href='"+url+"']").parent("li:first").addClass("active");
			topHeader.find(".header-content>div>a").removeClass("active");
			topHeader.find("a[href='"+url+"']").addClass("active");
		}
	});
	return false;
});
var sidebar = $("#sidebar").on("click", "li.side-nav-menu-item", function(){
	sidebar.find(".side-nav-menu-second-level>li.side-nav-menu-item").removeClass("side-nav-opened");
});
$(window).on("popstate", function(event){
	
	if(event.originalEvent.state){
		let state = event.originalEvent.state;
		bodyContent.html(state.html);
		if(state.url){
			sidebar.find("li").removeClass("active");
			sidebar.find("a[href='"+state.url+"']").parent("li:first").addClass("active");
		}
	}
});
history.pushState({html:bodyContent.html()}, '', '');