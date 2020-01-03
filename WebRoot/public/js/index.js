//主页页面脚本文件

/*判断当前考勤状态*/
var last_in = 0;
var last_out = 3;
var showState = 0;
if(attenIsStart){//今日考勤已开始
	if(!attenIsClockInEnd){//正在进行上班考勤，仅展示上班未打卡员工（1）
		last_in = 2;
		last_out = 0;
		showState = 1;
	}else if(attenIsWork){//上班考勤结束，当前为工作时间，仅展示上班未打卡员工（1）
		last_in = 2;
		last_out = 0;
		showState = 1;
	}else{//上班考勤结束，当前为非工作时间，已下班，展示上班或下班未打卡员工（2）
		last_in = 0;
		last_out = 3;
		showState = 2;
	}
}else{//今日考勤还未开始（0）
	last_in = 1;
	last_out = 1;
}

/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	$('#indexAbsenteeData').append('<td colspan="6"><h2 style="text-align:center">数据加载中。。。</h2></td>')
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/STSM/StaffPagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"staff_sex":3,
			"staff_position":10,
			"last_in":last_in,
			"last_out":last_out,
			"queryStr":"",
			"sortField":'updated_at',
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var staffs = r.staffs;
			$('#indexAbsenteeData').html('');
			if(staffs.length==0){$('#indexAbsenteeData').html('<td colspan="6"><h2 style="text-align:center">没有数据</h2></td>');}
			$.each(staffs, function (index, staff) {
				$('#indexAbsenteeData').append(
					'<tr>'
						+'<td>'+staff.number+'</td>'
						+'<td>'+staff.name+'</td>'
						+'<td>'+(staff.sex==1?"男":"女")+'</td>'
						+'<td>'+getStaffPosition(staff.position)+'</td>'
						+'<td>'//上班打卡状态
							+(showState==0?'<span class="label label-info">未开始</span>'://今日考勤还未开始
							'<span class="label label-'+(staff.isClockIn?"success":"danger")+'">'+(staff.isClockIn?"已打卡":"未打卡")+'</span>')//今日考勤已开始
						+'</td>'
						+'<td>'//下班打卡状态
							+(showState!=2?'<span class="label label-info">未开始</span>'://下班考勤还未开始
							'<span class="label label-'+(staff.isClockOut?"success":"danger")+'">'+(staff.isClockOut?"已打卡":"未打卡")+'</span>')//下班考勤已开始
						+'</td>'
					+'</tr>'
				);
			});
			/* 渲染页码 */
			RenderPagination(r.pagination);
		},
		error:function(){
			AjaxError();
		}
	});
}
SelectPage(page);