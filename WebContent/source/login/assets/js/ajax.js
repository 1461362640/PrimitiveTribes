$(function() {
	var username="";
	var password="";
	var codenumber="";
	$('#sub').click(function() {
		$('#sub').attr("enable","enable");
		username = $('.username').val();
		password = $('.password').val();
		codenumber = $('.codenumber').val();
		if (username == '') {
			$('.username').find('.error').fadeOut('fast', function() {
				$('.username').css('top', '27px');
			});
			$('.username').find('.error').fadeIn('fast', function() {
				$('.username').parent().find('.username').focus();
			});
			return false;
		}
		if (password == '') {
			$('.password').find('.error').fadeOut('fast', function() {
				$('.password').css('top', '96px');
			});
			$('.password').find('.error').fadeIn('fast', function() {
				$('.password').parent().find('.password').focus();
			});
			return false;
		}
		if (codenumber == '') {
			$('.codenumber').find('.error').fadeOut('fast', function() {
				$('.codenumber').css('top', '165px');
			});
			$('.codenumber').find('.error').fadeIn('fast', function() {
				$('.codenumber').parent().find('.codenumber').focus();
			});
			return false;
		}

		//发送请求
		$.post("./login.do?check", {
			"userName" : username,
			"passWord" : password,
			"codeNumber" : codenumber
		}, function(data) {
			//console.log(data);
			 if(data.success=="true"){
				 window.location.replace("./forward.do?forward");
			 }else{
				 d = dialog({
						title : '警告',
						content :data.tip //'登陆失败！请检查用户名和密码重新登录！'
					});
					d.show();
			 }
		}, "json");
	});
	});
	
