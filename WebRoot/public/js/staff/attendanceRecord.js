//员工考勤记录页面脚本



/*选择页码获取分页数据*/
var page = 1;
function SelectPage(pageNo){
	$('#attendanceRecordData').append('<td colspan="3"><h2 style="text-align:center">数据加载中。。。</h2></td>')
	page = pageNo;
	$.ajax({
		type:"post",
		url:"/STSM/StaffAttendancePagination",
		datatype: "json", 
		async:true,
		data:{
			"pageNo":pageNo,
			"staff_id":staff_id,
			"pastDay":$('#pastDay').val(),
		},
		success:function(result) {
			var r = JSON.parse(result);
			/* 渲染数据 */
			var attens = r.attens;
			$('#attendanceRecordData').html('');
			if(attens.length==0){$('#attendanceRecordData').html('<td colspan="3"><h2 style="text-align:center">没有数据</h2></td>');}
			$.each(attens, function (index, atten) {
				$('#attendanceRecordData').append(
					'<tr>'
						+'<td>'+DateFormat(IfNull(atten.date),"yyyy-MM-dd")+'</td>'
						+'<td>'+(atten.startTime.length==0?'<span class="label label-danger">未打卡</span>':
							'<span class="label label-success">已打卡</span>&emsp;打卡时间：'+DateFormat(IfNull(atten.startTime),"yyyy-MM-dd HH:mm:ss"))+'</td>'
						+'<td>'+(atten.endTime.length==0?'<span class="label label-danger">未打卡</span>':
							'<span class="label label-success">已打卡</span>&emsp;打卡时间：'+DateFormat(IfNull(atten.endTime),"yyyy-MM-dd HH:mm:ss"))+'</td>'
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