<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 头部 -->
<%@include file="/WEB-INF/view/common/head.jsp" %>

</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
<!-- 导航栏 -->
<%@include file="/WEB-INF/view/common/navbar.jsp" %>
<!-- 左侧边栏 -->
<%@include file="/WEB-INF/view/common/userLeftSidebar.jsp" %>

<!-- 内容区域 -->
<div class="main">
<!-- MAIN CONTENT -->
<div class="main-content">

	<!-- 员工考勤记录 -->
	<div class="panel" id="waitAuditJobPanel">
		<div class="panel-heading">
			<h3 class="panel-title">考勤记录</h3>
			<div class="right">
				<select id="pastDay" class="form-control" onchange="SelectPage(page)">
					<option value="1">今天</option>
					<option value="7">过去一周</option>
					<option value="30" selected>过去一月</option>
					<option value="90">过去三月</option>
					<option value="180">过去半年</option>
					<option value="999999">全部记录</option>
				</select>
			</div>
		</div>
		
		<div class="panel-body">
			<table class="table table-hover">
				<colgroup>
					<col style="width:20%">
					<col style="width:40%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>日期</th>
						<th>上班打卡</th>
						<th>下班打卡</th>
					</tr>
				</thead>
				<tbody id="attendanceRecordData">
					
				</tbody>
			</table>
		</div>

		<!-- 分页 -->
		<%@include file="/WEB-INF/view/common/pagination.jsp"%>
	</div>

</div>
<!-- END MAIN CONTENT -->
</div>
<!-- END 内容区域 -->

<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/foot.jsp" %>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/javaScript.jsp" %>
<!-- 自定义脚本 -->
<script>
var staff_id = ${staff.ID};
</script>
<script src="/STSM/public/js/staff/attendanceRecord.js"></script>

</body>
</html>