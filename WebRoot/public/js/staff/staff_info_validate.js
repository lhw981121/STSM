//员工信息表单验证js


//员工信息表单验证
function cheekStaffAll(){
	if(checkStaffNumber($('#staff_number').val()) && checkStaffName($('#staff_name').val()) &&  cheakStaffSex(document.getElementsByName("staff_sex")) 
&& cheakStaffAge($('#staff_age').val()) && cheakStaffPosition(document.getElementsByName("staff_position")) && cheakStaffHouse($('#staff_house').val()) ){
		alter('')
		return true;
	}else{
		return false;
	}
}

//员工工号验证
function checkStaffNumber(value){
    //员工姓名的规则： 必填，只能为汉字、字母或数字，长度不超过10
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("员工工号不能为空！");
	    return false;
	}else{
		var reg = /^([0-9])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft("员工工号必须全为数字！");
	        return false;  
	    }else if(value.length!=11){
	        //不符合规则
			ErrorTipBottomLeft("员工工号长度必须为11位");
	        return false;
	    }else{
			return true;
		}
	}
}

//员工姓名验证
function checkStaffName(value){
    //员工姓名的规则： 必填，只能为汉字、字母或数字，长度不超过10
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("员工姓名不能为空！");
	    return false;
	}else{
		var reg = /^([\u0391-\uFFE5]|[0-9a-zA-Z])+$/;
	    if(!reg.test(value)){
	        //不符合规则
			ErrorTipBottomLeft("员工姓名输入不符合规范！");
	        return false;  
	    }else if(value.length>10){
	        //不符合规则
			ErrorTipBottomLeft("员工姓名长度超限！");
	        return false;
	    }else{
			return true;
		}
	}
}

//员工性别验证
function cheakStaffSex(value){
//	if($(":radio[name='staff_sex']:checked").val()==null){
//		alert("性别为空");
//	}
//	else
//		alert("性别不为空");
	if($('input[name="staff_sex"]:checked').val()==null){
		//不符合规则
		ErrorTipBottomLeft("员工性别不能为空！");
		return false;
	}else{
		return true;
	}
	/*
	//性别的规则：必填
	var flag = 0;
	for (var i=0;i<value.length;i++){
		if (value.item(i).checked == true){
			flag = 1;break;
		}
	}
	if (!flag){
        //不符合规则
		ErrorTipBottomLeft("员工性别不能为空！");
	    return false;
	}else{
        return true;  
	}*/
}

//员工年龄验证
function cheakStaffAge(value){
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("员工年龄不能为空！");
	    return false;
	}else if(value<16 || value>120){
		ErrorTipBottomLeft("请输入正确的员工年龄！");
		return false;
	}
	return true;
}

//员工职位验证
function cheakStaffPosition(value){
	var coun=$("#staff_position").val();
	if(coun==-1){
		//不符合规则
		ErrorTipBottomLeft("员工职位不能为空！");
		return false;
	}else
		return true;
}

//员工家庭住址验证
function cheakStaffHouse(value){
	if(value.length == 0){
		//不符合规则
		ErrorTipBottomLeft("员工家庭住址不能为空！");
	    return false;
	}else
		return true;
}

