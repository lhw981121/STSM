//主页页面脚本文件


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
			"last_in":0,
			"last_out":3,
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
						+'<td><span class="label label-'+(staff.isClockIn?"success":"danger")+'">'+(staff.isClockIn?"已打卡":"未打卡")+'</span></td>'
						+'<td><span class="label label-'+(staff.isClockOut?"success":"danger")+'">'+(staff.isClockOut?"已打卡":"未打卡")+'</span></td>'
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