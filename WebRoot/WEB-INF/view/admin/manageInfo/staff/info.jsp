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
			<h3 class="panel-title">员工信息</h3>
			<div class="right">
				<a href="/STSM/admin/manage_info/staff/create"><span class="label label-primary"><i class="fa fa-plus-square"></i>&nbsp;新增员工</span></a>
			</div>
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
							id="searchCDTopic" action="/STSM/admin/manage_info/staff/info">
							<div class="input-group">
								<input class="form-control" name="queryStr" type="text"
									id="queryStr" value="" placeholder="员工姓名、职位、工号"> <input
									type="hidden" name="selectPages" value="">
								<span class="input-group-btn"><a onclick="return searchCDTopic()" class="btn btn-primary">搜索</a></span>
							</div>
							<script type="text/javascript">
								function searchCDTopic() {
									if (document.getElementById("queryStr").value.length != 0) {
										document.getElementById("searchCDTopic").submit();
										return true;
									} else {
										return false;
									}
								}
							</script>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>工号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>职位</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="waitAuditJobData">
				<c:forEach items="${staffinfo}" var="staff">
					<tr>
						<!-- 工号 -->
						<td>${staff.number}</td>
						<!-- 姓名 -->
						<td>${staff.name}</td>
						<!--性别 -->
						<c:if test="${staff.sex == 1}">
							<td>男</td>
						</c:if>
						<c:if test="${staff.sex == 2}">
							<td>女</td>
						</c:if>
						<c:if test="${staff.sex == 0}">
							<td>未知</td>
						</c:if>
						<!--年龄 -->
						<c:choose>
							<c:when test="${staff.age>0}">
								<td>${staff.age}</td>
							</c:when>
							<c:otherwise>
								<td>未知</td>
							</c:otherwise>
						</c:choose>
						<!--职位 -->
						<c:choose>
							<c:when test="${staff.position==0}">
								<td>普通职员</td>
							</c:when>
							<c:when test="${staff.position==1}">
								<td>组长</td>
							</c:when>
							<c:when test="${staff.position==2}">
								<td>主管</td>
							</c:when>
							<c:when test="${staff.position==3}">
								<td>部门经理</td>
							</c:when>
							<c:when test="${staff.position==4}">
								<td>总监</td>
							</c:when>
							<c:when test="${staff.position==5}">
								<td>副总经理</td>
							</c:when>
							<c:when test="${staff.position==6}">
								<td>总经理</td>
							</c:when>
							<c:when test="${staff.position==7}">
								<td>执行董事</td>
							</c:when>
							<c:when test="${staff.position==8}">
								<td>副董事长</td>
							</c:when>
							<c:when test="${staff.position==9}">
								<td>董事长</td>
							</c:when>
							<c:otherwise>
								<td>未知</td>
							</c:otherwise>
						</c:choose>
						<!--操作 -->
						<td>
							<a href="/STSM/admin/manage_info/staff/details?StaffID=${staff.ID}">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="update?StaffID=${staff.ID}" onclick="return confirm('确定修改该员工信息?')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/STSM/StaffDelete?StaffID=${staff.ID}" onclick="return confirm('确定删除该员工信息?')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
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
</script>
</html>