
//���һ����ӰƬ��Ч��
$(document).ready(function() {
	$(".frist").mousemove(function() {
		//$(this).children("a").children("span").addClass("shadow");
		$(this).children("span").addClass("shadow");
		   
	});
	$(".frist").mouseleave(function() {
		$(this).children("span").removeClass("shadow");
	});

});