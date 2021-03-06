//用户找回密码页面脚本

/*<!-- 密码是否可见 -->*/
function hideShowNewPsw(){
	var eye = $('#eye');
	var passwordInput1 = $('#user_password');
	var passwordInput2 = $('#user_passwords');
    if (passwordInput1.attr("type") == "password") {
        passwordInput1.attr("type","text");
        passwordInput2.attr("type","text");
        eye.attr("class","fa fa-eye fa-2x");
    }else {
        passwordInput1.attr("type","password");
        passwordInput2.attr("type","password");
        eye.attr("class","fa fa-eye-slash fa-2x");
    }
}

//判断账号是否存在
$("#accountBtn").click(function() {
	if(!InputNotNull($('#user_account'),"账号不能为空！"))	return;
	$.ajax({
		type:"post",
		url:"/STSM/UserIsAccountExist",
		datatype: "json",
		async:false,
		data:{
			"user_account":$('#user_account').val(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isExist==true){//账号存在
				var user = r.user;
				if((user.email==null||user.email.length==0)&&(user.phone==null||user.phone.length==0)){
					$("#default_mode").html("您没有可用的验证方式！请联系管理员。");
				}else{
					if(user.email!=null&&user.email.length!=0){
						$("#user_email_mode").show();
						$("#user_email_mode").html(("邮箱验证&emsp;&emsp;&emsp;&emsp;")+HideEmail(user.email));
						$("#emailcode").val(user.email);
					}
					if(user.phone!=null&&user.phone.length!=0){
						$("#user_phone_mode").show();
						$("#user_phone_mode").html(("手机短信验证&emsp;&emsp;")+HidePhone(user.phone));
						$("#phonecode").val(user.phone);
					}
				}
				$("#accountForm").slideToggle();
				$("#modeForm").slideToggle();
			}else{//账号不存在。
				ErrorTipBottomCenter(r.errorMes);
			}
		},
		error:function(){
			AjaxError();
		}
	});
});

//判断选择的验证方式
$("#selectMode").change(function(){
    if($("#selectMode").val()==1){
    	$("#modeForm").slideToggle();
		$("#emailForm").slideToggle();
    }
    if($("#selectMode").val()==2){
    	$("#modeForm").slideToggle();
		$("#phoneForm").slideToggle();
    }
});
//返回验证方式选择框
$("#modeBack1").click(function(){
	$("#modeForm").slideToggle();
	$("#emailForm").slideToggle();
	$("#selectMode").val(0);
});
$("#modeBack2").click(function(){
	$("#modeForm").slideToggle();
	$("#phoneForm").slideToggle();
	$("#selectMode").val(0);
});

/* 发送验证码 */
var countdown = 60;
/* 发送邮箱验证码 */
$('#emailCodeBtn').click(function(){
	settime($('#emailCodeBtn'),$('#user_email'));
	InfoTipBottomRight("邮件正在发送中。。。");
	var user_email = $("#emailcode").val();
	$.ajax({
		type:"post",
		url:"/STSM/UserSendEmailCode",
		datatype: "json",
		async:true,
		data:{
			"user_email":user_email.toLowerCase(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//邮箱验证码发送成功
				SuccessTipBottomRight(r.successMes);
				$("#emailcode").val(r.code);
				$("#email_code").attr('disabled', false);
			}else{//发送失败
				ErrorTipBottomCenter(r.errorMes);
				countdown=0;
			}
		},
		error:function(){
			AjaxError();
		}
	});
})
/* 发送手机验证码 */
$('#phoneCodeBtn').click(function(){
	settime($('#phoneCodeBtn'),$('#user_phone'));
	InfoTipBottomRight("短信正在发送中。。。");
	var user_phone = $("#phonecode").val();
	$.ajax({
		type:"post",
		url:"/STSM/UserSendPhoneCode",
		datatype: "json",
		async:true,
		data:{
			"user_phone":user_phone,
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//短信发送成功
				SuccessTipBottomRight(r.successMes);
				$("#phonecode").val(r.code);
				$("#phone_code").attr('disabled', false);
			}else{//发送失败
				ErrorTipBottomCenter(r.errorMes);
				countdown=0;
			}
		},
		error:function(){
			AjaxError();
		}
	});
});

//判断邮箱验证码是否正确
$("#emailBtn").click(function() {
	if(!checkEmail_code($('#email_code').val(),$('#emailcode').val()))	return;
	$("#emailForm").slideToggle();
	$("#passwordForm").slideToggle();
});
//判断手机验证码是否正确
$("#phoneBtn").click(function() {
	if(!checkPhone_code($('#phone_code').val(),$('#phonecode').val()))	return;
	$("#phoneForm").slideToggle();
	$("#passwordForm").slideToggle();
});

/*<!-- 验证两次密码是否相同 -->*/
function checkUser_passwords(){
	var password = $('#user_password').val();
	var passwords = $('#user_passwords').val();
	if(password!=passwords){
		ErrorTipBottomCenter("两次密码不相同！");
		return false;
	}else{
		return true;
	}
}

/*<!-- 密码表单验证 -->*/
function checkPassword(){ 
	if(checkUser_password()&&checkUser_passwords()){
		return true;
	}else{  
		return false;  
	}
}

//判断是否成功重置密码
$("#passwordBtn").click(function(){
	if(!checkPassword()) return;
	$.ajax({
		type:"post",
		url:"/STSM/UserUpdatePassword",
		datatype: "json",
		async:false,
		data:{
			"user_account":$("#user_account").val(),
			"user_password":$.md5($("#user_password").val()),
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//重置密码成功
				swal({
					title: "重置密码成功",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.href="/STSM/user/mycenter";
				});
				setTimeout(function () {window.location.href="/STSM/user/mycenter";}, 3000);
			}else{//重置密码失败
				swal({
					title: "重置密码失败",
					text: r.errorMes,
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
});
