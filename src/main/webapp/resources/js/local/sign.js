$(function() {
	// 登录验证的controller url
	var signUrl = '/foodShop/local/insertLocalAuth';

	$('#submit').click(function() {
		// 获取输入的帐号
		var userName = $('#username').val();
		// 获取输入的密码
		var password = $('#psw').val();
		// 获取验证码信息
		var verifyCodeActual = $('#j_captcha').val();

		$.ajax({
			url : signUrl,
			type : "post",
			dataType : 'json',
			data : {
				userName : userName,
				password : password,
				verifyCodeActual : verifyCodeActual,
			},
			success : function(data) {
				if (data.success) {
					$.toast('注册成功！');
						// 若用户在前端展示系统页面则自动链接到前端展示系统首页
						window.location.href = '/foodShop/frontend/index';
				} else {
					$.toast('注册失败！' + data.errMsg);
				}
			}
		});

/*		var html = '<p>你好,  ' + userName + '</p>';
        $('#localAuth').html(html);*/

    /*   var localAuth = document.getElementById("localAuth");
       localAuth.innerHTML = localAuth.innerText + "你好" +userName;*/


/*        $.ajax({
		  	url : '/foodShop/local/Cookie',
			type : "get",
            dataType : 'json',
            data : {
                userName : userName,
                password : password,
            },
            success : function(data) {
                if (data.success) {
                    $.toast('保存成功');
                } else {
                    $.toast('保存失败！' + data.errMsg);
                }
            }
		});*/


	});
});