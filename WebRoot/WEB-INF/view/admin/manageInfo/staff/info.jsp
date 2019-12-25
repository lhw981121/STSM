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
			<h3 class="panel-title">待审核职位</h3>
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
							id="searchCDTopic" action="">
							<div class="input-group">
								<input class="form-control" name="queryStr" type="text"
									id="queryStr" value="" placeholder="企业名称、所在地"> <input
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
					</tr>
				</thead>
				<tbody id="waitAuditJobData">
				<c:forEach items="${staffinfo}" var="info">
					<tr>
						<td>${info.number}</td>
						<td>${info.name}</td>
						<c:if test="${info.sex == 1}">
							<td>男</td>
						</c:if>
						<c:if test="${info.sex == 2}">
							<td>女</td>
						</c:if>
						<td>${info.age}</td>
						<td>${info.position}</td>
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
</html>