//员工今日考勤页面脚本

//员工上班打卡
function staffClockIn(){
	$.ajax({
		type:"post",
		url:"/STSM/StaffClock",
		datatype: "json", 
		async:false,
		data:{
			"mode":"clockIn",
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//操作成功
				swal({
					title: "打卡成功",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.reload();
				});
			}else{//操作失败
				swal({
					title: "打卡失败",
					text: r.errorMes,
					type: "error",
				},
				function(){
					window.location.reload();
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

//员工下班打卡
function staffClockOut(){
	$.ajax({
		type:"post",
		url:"/STSM/StaffClock",
		datatype: "json", 
		async:false,
		data:{
			"mode":"clockOut",
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//操作成功
				swal({
					title: "打卡成功",
					text: r.successMes,
					type: "success",
				},
				function(){
					window.location.reload();
				});
			}else{//操作失败
				swal({
					title: "打卡失败",
					text: r.errorMes,
					type: "error",
				},
				function(){
					window.location.reload();
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

//已打卡
function staffClocked(mode){
	swal({
		title : "已打卡",
		text : "你已完成"+mode+"打卡，不能重复打卡！",
		timer: 2000,
		type : "warning",
		showCancelButton : false,
		confirmButtonColor: "#00A0F0",
		confirmButtonText : "了解",
		closeOnConfirm : true
	});
}

//工作时间
function workTime(){
	swal({
		title : "工作时间",
		text : "当前是工作时间，请专心工作！",
		timer: 2000,
		type : "warning",
		showCancelButton : false,
		confirmButtonColor: "#DD6B55",
		confirmButtonText : "了解",
		closeOnConfirm : true
	});
}

//非工作时间
function notWorkTime(){
	swal({
		title : "休息时间",
		text : "当前是非工作时间，休息好才能开始新的一天的工作！",
		type : "success",
		showCancelButton : false,
		confirmButtonColor: "#469405",
		confirmButtonText : "好好休息",
		closeOnConfirm : true
	});
}
