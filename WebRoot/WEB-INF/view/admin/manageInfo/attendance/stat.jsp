<%@ page language="java" import="java.util.*,com.stsm.util.COMUtil" pageEncoding="utf-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 头部 -->
<%@include file="/WEB-INF/view/common/head.jsp"%>

</head>

<body>
<div id="wrapper">
<!-- WRAPPER -->
<!-- 导航栏 -->
<%@include file="/WEB-INF/view/common/navbar.jsp"%>
<!-- 左侧边栏 -->
<%@include file="/WEB-INF/view/common/adminLeftSidebar.jsp"%>

<!-- 内容区域 -->
<div class="main">
<!-- MAIN CONTENT -->
<div class="main-content">
	<!-- 待审核企业 -->
	<div class="panel" id="waitAuditJobPanel">
		<div class="panel-heading">
			<h3 class="panel-title">考勤信息</h3>

		</div>
		<div class="panel-heading">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-8 col-sm-8 col-lg-8">
						<form class="form-inline" id="searchForm" role="form"
							method="get" action="">
							<div class="form-group">
								<span class="panel-title">信息查询</span>
							</div>
						</form>
					</div>
					<div class="col-md-4 col-sm-4 col-lg-4">
						<form role="form" class="form-horizontal" method="get"
							id="searchStaff" action="/STSM/admin/manage_info/attendance/info">
							<div class="input-group">
								<input class="form-control" name="queryStr" type="text" id="queryStr" onfocus="this.value=''" onblur="this.value='${param.queryStr }'"
								value="${param.queryStr }" placeholder="考勤日期">
								<span class="input-group-btn"><a onclick="return searchStaff()" class="btn btn-primary">搜索</a></span>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>操作</th>
						<th>工号</th>
						<th>姓名</th>
						<th>上班打卡时间</th>
						<th>下班打卡时间</th>
					</tr>
				</thead>
				<tbody id="waitAuditJobData">
				<c:forEach items="startTime" var="st">
						<tr>
						<td>
							<a href="#">操作 </a>
						</td>
						<td>001xxx</td>
						<td>xx</td>
						<td>${st}</td>
						<td>  xx</td>
						</tr>
				</c:forEach>
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
<%@include file="/WEB-INF/view/common/foot.jsp"%>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/javaScript.jsp"%>
<!-- 自定义脚本 -->
</body>
<script>
//改变页码
$(document).ready(function(){
	//页面行数改变，刷新页面
	$("#pageSize").change(function(){
		window.location.reload();
	});
});
$("#pageSize option").each(function(){
	if($(this).val()=='${pageSize}'){
		$(this).prop('selected',true);
	}
});
//搜索框
function searchStaff() {
	if ($("#queryStr").val().length != 0) {
		$("#searchStaff").submit();
		return true;
	} else {
		return false;
	}
}
</script>
</html>