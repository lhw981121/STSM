//用户信息表单验证

//用户名验证JS
function checkUser_name(){
    var value = $('#user_name').val();
    //用户名的规则： 必填，只能为汉字、字母或数字，长度为2-20
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"用户名不能为空！":"The user name cannot be empty!");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"用户名输入不符合规范！":"Wrong user name format!");
	        return false;  
	    }else if(value.length<2||value.length>20){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"用户名长度非法！":"Wrong user name format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//用户账号验证JS
function checkUser_account(){
	var value = $('#user_account').val();
    //账号的规则：必填，6~18位字符，只能包含英文字母、数字、下划线
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("账号不能为空！");
        return false;
	}else{
		var reg = /^([_]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"账号只能包含英文字母、数字、下划线！":"Wrong account format!");
	        return false;  
	    }else if(value.length<6||value.length>18){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"账号必须为6~18位字符！":"Wrong account format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//用户密码验证JS
function checkUser_password(){
    var value = $('#user_password').val();
    //密码的规则：必填，6~18位字符，必须包含数字、字母或特殊字符其中两项及以上
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("密码不能为空！");
        return false;
	}else{
		var reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*./\-\+]+$)[a-zA-Z\d!@#$%^&*.\-\+]+$/;
	    if(!reg.test(value)){
	        //不符合规则
		    ErrorTipBottomLeft(language=='zh_CN'?"密码必须包含数字、字母或特殊字符其中两项及以上！":"Wrong password format!");
	        return false;  
	    }else if(value.length<6||value.length>18){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"密码必须为6~18位字符！":"Wrong password format!");
	        return false;  
	    }else{
			return true;
		}
	}
}

//用户邮箱验证
function checkUser_email(){
    var value = $('#user_email').val();
    //邮箱的规则：必填，邮箱正则表达式
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft(language=='zh_CN'?"邮箱不能为空！":"Email address cannot be empty!");
		return false;
	}else{
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"邮箱格式有误！":"Wrong email format!");
		    return false;  
	    }else{
			return true;
		}
	}
}


//邮箱验证码验证
function checkEmail_code(value,code){
	if(value.length == 0){
		ErrorTipBottomLeft(language=='zh_CN'?"验证码不能为空！":"The verification code cannot be empty!");
        return false;
	}else{
		var emailcode = code;
		if(emailcode!=null&&value!=emailcode){
		    ErrorTipBottomLeft(language=='zh_CN'?"验证码错误！":"Verification code error!");
	        return false;
		}else{
	        return true;
		}
	}
}

//用户手机号验证JS
function checkUser_phone(){
	var value = $('#user_phone').val();
    //手机号的规则： 必填，手机号标准
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("手机号不能为空！");
        return false;
	}else{
		var reg = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft(language=='zh_CN'?"手机号输入不规范！":"Wrong mobile phone format!");
	        return false;  
	    }else{
	        return true;  
		}
	}
}

//手机验证码验证
function checkPhone_code(value,code){
	if(value.length == 0){
		ErrorTipBottomLeft(language=='zh_CN'?"验证码不能为空！":"The verification code cannot be empty!");
        return false;
	}else{
		var phonecode = code;
		if(phonecode!=null&&value!=phonecode){
			ErrorTipBottomLeft(language=='zh_CN'?"验证码错误！":"Verification code error!");
	        return false;
		}else{ 
	        return true;
		}
	}
}

