//公共JS函数

/*获取员工职位  0:普通职员 1:组长 2:主管 3:部门经理 4:总监 5:副总经理 6:总经理 7:执行董事 8:副董事长 9:董事长*/
function getStaffPosition(state){
	switch(state){
	case 0:return "普通员工";
	case 1:return "组长";
	case 2:return "主管";
	case 3:return "部门经理";
	case 4:return "总监";
	case 5:return "副总经理";
	case 6:return "总经理";
	case 7:return "执行董事";
	case 8:return "副董事长";
	case 9:return "董事长"
	default:return "";
	}
}

//展示系统时间
function systemCurrentTime(){ 
var d=new Date(),str='';
str +=d.getFullYear()+'年'; //获取当前年份
str +=d.getMonth()+1+'月'; //获取当前月份（0——11）
str +=d.getDate()+'日';
str +=d.getHours()+'时';
str +=d.getMinutes()+'分';
str +=d.getSeconds()+'秒';
return str; } 
setInterval(function(){$("#systemCurrentTime").html(systemCurrentTime)},1000);

/*<!-- 语言切换脚本 -->*/
function changeLanguage(lan){
	$.ajax({
		type:"post",
		url:"/STSM/ChangeLanguage",
		datatype: "json",
		async:false,
		data:{
			"language":lan,
		},
		success:function(result) {
			location.reload();
		},
		error:function(){
			AjaxError();
		}
	});
}

/*<!-- 页面数据量切换脚本 -->*/
function changePageSize(size){
	$.ajax({
		type:"post",
		url:"/STSM/ChangePageSize",
		datatype: "json",
		async:true,
		data:{
			"pageSize":size,
		},
		success:function(result) {
			SelectPage(1);
		},
		error:function(){
			AjaxError();
		}
	});
}

//输入不能为空
function InputNotNull(value,str){
	if(value.val().length == 0){
		ErrorTipBottomLeft(str);
        return false;
	}else{ 
		return true;
	}
}

//判断输出是否为null
function IfNull(str){
	if(typeof(str) == "undefined"){
		return '';
	}else{
		return str;
	}
}

//Date格式文本格式化
function DateFormat(date, format) {
	if(date.length==0)	return '';
    date = new Date(date);
    date.setHours(date.getHours());
    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp('(' + k + ')').test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
    return format;
}

//将Date转换成距当前时间长度格式
function FromTodayFormat(date) {
	if(date.length==0)	return '';
	var timestamp = parseInt(new Date(date).getTime() / 1000);
	function zeroize(num) {
		return (String(num).length == 1 ? '0' : '') + num;
	}

	var curTimestamp = parseInt(new Date().getTime() / 1000); //当前时间戳
	var timestampDiff = curTimestamp - timestamp; // 参数时间戳与当前时间戳相差秒数

	var curDate = new Date(curTimestamp * 1000); // 当前时间日期对象
	var tmDate = new Date(timestamp * 1000); // 参数时间戳转换成的日期对象

	var Y = tmDate.getFullYear(),m = tmDate.getMonth() + 1,d = tmDate.getDate();
	var H = tmDate.getHours(),i = tmDate.getMinutes(),s = tmDate.getSeconds();

	if (timestampDiff < 60) { // 一分钟以内
		return language=="zh_CN"?"刚刚":"recently";
	} else if (timestampDiff < 3600) { // 一小时前之内
		return Math.floor(timestampDiff / 60) + (language=="zh_CN"?"分钟前":"minutes ago");
	} else if (curDate.getFullYear() == Y && curDate.getMonth() + 1 == m && curDate.getDate() == d) {
		return (language=="zh_CN"?"今天":"Today") + zeroize(H) + ':' + zeroize(i);
	} else {
		var newDate = new Date((curTimestamp - 86400) * 1000); // 参数中的时间戳加一天转换成的日期对象
		if (newDate.getFullYear() == Y && newDate.getMonth() + 1 == m && newDate.getDate() == d) {
			return (language=="zh_CN"?"昨天":"Yesterday") + zeroize(H) + ':' + zeroize(i);
		} else if (curDate.getFullYear() == Y) {
			if(language=="zh_CN"){
				return zeroize(m) + '月' + zeroize(d) + '日 ' + zeroize(H) + ':' + zeroize(i);
			}else{
				return zeroize(m) + '.' + zeroize(d) + ' ' + zeroize(H) + ':' + zeroize(i);
			}
		} else {
			if(language=="zh_CN"){
				return Y + '年' + zeroize(m) + '月' + zeroize(d) + '日 ' + zeroize(H) + ':' + zeroize(i);
			}else{
				return zeroize(m) + '/' + zeroize(d) + '/' + Y + ' ' + zeroize(H) + ':' + zeroize(i);
			}
		}
	}
}

//邮箱隐藏
function HideEmail(email){
	if (String (email).indexOf ('@') > 0) {
        let newEmail, str = email.split('@'), _s = '';

        if (str[0].length > 4) {
            _s = str[0].substr (0, 4);
            for (let i = 0; i < str[0].length - 4; i++) {
                _s += '*';
            }
        } else {
            _s = str[0].substr (0, 1);
            for (let i = 0; i < str[0].length - 1; i++) {
                _s += '*';
            }
        }
        newEmail = _s + '@' + str[1];
        return newEmail;
    } else {
        return email;
    }
}

//手机号隐藏
function HidePhone(phone){
	let newPhone = '';
　　if (phone.length > 7) {
		newPhone=phone.substr(0, 3) + '****' + phone.substr(7);
        return newPhone;
    } else {
        return phone;
    }
}

//获取URL参数
function GetUrlParam(paraName) {
　　var url = document.location.toString();
　　var arrObj = url.split("?");
　　if (arrObj.length > 1) {
　　　　var arrPara = arrObj[1].split("&");
　　　　var arr;
　　　　for (var i = 0; i < arrPara.length; i++) {
　　　　　　arr = arrPara[i].split("=");
　　　　　　if (arr != null && arr[0] == paraName) {
　　　　　　　　return arr[1];
　　　　　　}
　　　　}
　　　　return "";
　　}
　　else {
　　　　return "";
　　}
}

//发送验证码倒计时
function settime(obj,input) {
	if (countdown == 0) {
		input.attr('disabled',false);
		obj.attr('disabled', false);
		obj.html("免费获取验证码")
		countdown = 60;
		return;
	} else {
		input.attr('disabled',true);
		obj.attr('disabled', true);
		obj.html("重新发送("+countdown+")");
		countdown--;
	}
	setTimeout(function () {settime(obj,input)}, 1000)
}

//检测用户是否访问受限
function UserLimitedAccess(){
	var str;
	if(limitedAccess==1){//非管理员访问管理员页面
		str=language=='zh_CN'?"管理员页面拒绝访问！":"The admin page is denied access!";
	}
	if(limitedAccess.length != 0){
		swal({
			title: language=='zh_CN'?"访问受限":"Limited Access",
			text: str,
			type: "warning",
			confirmButtonText: language=='zh_CN'?"关闭":"Close",
			closeOnConfirm: false
		},
		function(){
			window.location.replace("/STSM/index");
		});
	}
}

//检测本机用户是否在线
function UserTestLogout(){
	$.ajax({
		url:"/STSM/UserTestLogout",
		async:true,
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isLogout==true){//用户已被挤下线
				swal({
				  title: language=='zh_CN'?"强制下线":"Forced Offline",
				  text: r.errorMes,
				  type: "warning",
				  confirmButtonText: language=='zh_CN'?"关闭":"Close",
				  closeOnConfirm: false
				},
				function(){
					location.reload();
				});
			}else{//用户在线
			}
		},
		error:function(){
		}
	});
	setTimeout(function () {UserTestLogout()}, 5000);
}

//刷新新消息
var messageType=["dot bg-info","dot bg-success","dot bg-danger","dot bg-warning","dot bg-primary"];//消息类型字符串数组
function RefreshNewMessage(){
	$.ajax({
		url:"/STSM/RefreshNewMessage",
		async:true,
		success:function(result) {
			var r = JSON.parse(result);
			if(r.count==0){//无新消息
				$('#message_count_1').html('');
				$('#message_count_2').html('');
				$('#message_count_3').html('');
				$('#message_menu').html('<li><a href="#" class="more">'+(language=='zh_CN'?"未收到消息":"No message received")+'</a></li>');
			}else{//有新消息
				$('#message_count_1').html(r.count);
				$('#message_count_2').html(r.count);
				$('#message_count_3').html(r.count);
				var newMes = r.newMesList;
				var messageMenuHtml = "";
				$.each(newMes, function (index, mes) {
					var mesTime = new Date(DateFormat(mes.created_at,"yyyy-MM-dd HH:mm:ss")).getTime();
					var nowTime = new Date().getTime();
					if(nowTime-mesTime<1000){
						NewMessageTip(mes.message_summary);
					}
					if(index==10){//只展示10条消息
						messageMenuHtml+='<li><a style="text-align:center">......</a></li>';		
						return false;
					}
					messageMenuHtml+='<li><a href="/STSM/message/unread_message?id='+mes.message_id+'" class="notification-item"><span class="'+messageType[mes.message_type]+'"></span>'+mes.message_summary+'</a></li>';		
				});
				messageMenuHtml+='<li><a href="/STSM/message/unread_message" class="more">'+(language=='zh_CN'?"查看所有消息":"View all messages")+'</a></li>';
				$('#message_menu').html(messageMenuHtml.toString());
			}
		},
		error:function(){
		}
	});
	setTimeout(function () {RefreshNewMessage()}, 1000);
}


//页面加载完毕
$(function () {
	//检测本机用户是否在线
	UserTestLogout();
	//刷新用户新消息
	RefreshNewMessage();
	//检测用户是否访问受限
	UserLimitedAccess();
	
})