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

<!-- ERROR TIP -->
<!-- END ERROR TIP -->

	<div class="container-fluid">
		<h3 class="page-title">公告栏</h3>
		<div class="row">
			<!-- 公告栏 -->
			<div class="col-md-8">
				<div class="panel panel-headline">
					<div class="panel-heading">
						<h3 class="panel-title">今日工作时间</h3>
						<p style="font-size:25px;color:rgb(0, 170, 255);" class="panel-subtitle">${atten.getPeriod() }</p>
						<span><script src="/STSM/public/js/common/randomText.js"></script></span>
					</div>
					<div class="panel-body">
						<h4>公司制度</h4>
						<p>
							1、公司员工上下班必须严格按照作息时间执行，不迟到、早退；<br>
							2、上班考勤实行实时签到制度，不得故意误签上班时间，必须本人亲自签到确认；<br>
							3、工作期间内不得窜岗、闲聊、玩网络游戏、网络聊天、利用公司电话打私人电话、吃食物、听音乐等与工作无关的事情；<br>
							4、员工必须服从上级管理人员领导，不得工作怠慢；<br>
							5、上班前30分钟和下班后30分为当日两次考勤时间段，期间未打卡者算作当日未考勤处理。<br>
						</p>
					</div>
				</div>
			</div>
			<!-- END 公告栏 -->
			<!-- 考勤 -->
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">今日考勤</h3>
						<div class="right">
							<button type="button" class="btn-toggle-collapse">
								<i class="lnr lnr-chevron-up"></i>
							</button>
							<button type="button" class="btn-remove">
								<i class="lnr lnr-cross"></i>
							</button>
						</div>
					</div>
					<div class="panel-body no-padding bg-primary text-center">
						<div class="padding-top-30 padding-bottom-30">
							<c:choose>
								<c:when test="${staff.getIsClockIn() }">
									<i class="fa fa-thumbs-o-up fa-5x"></i>
									<h3>已打卡</h3>
								</c:when>
								<c:otherwise>
									<i class="fa fa-frown-o fa-5x"></i>
									<h3>未打卡</h3>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<!-- END 考勤 -->
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<!-- 未考勤人员展示 -->
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">今日未考勤人员展示</h3>
						<div class="right">
							<button type="button" class="btn-toggle-collapse">
								<i class="lnr lnr-chevron-up"></i>
							</button>
							<button type="button" class="btn-remove">
								<i class="lnr lnr-cross"></i>
							</button>
						</div>
					</div>
					<div class="panel-body no-padding">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>工号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>职位</th>
									<th>上班打卡</th>
									<th>下班打卡</th>
								</tr>
							</thead>
							<tbody id="indexAbsenteeData">
								<!-- <tr>
									<td><a href="#">763648</a></td>
									<td>Steve</td>
									<td>$122</td>
									<td>Oct 21, 2016</td>
									<td><span class="label label-success">COMPLETED</span></td>
									<td><span class="label label-success">COMPLETED</span></td>
								</tr> -->
							</tbody>
						</table>
						<!-- 页尾 -->
						<%@include file="/WEB-INF/view/common/pagination.jsp" %>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-md-6">
								<span class="panel-note"><i class="fa fa-clock-o"></i> Last 24 hours</span>
							</div>
						</div>
					</div>
				</div>
				<!-- END 未考勤人员展示 -->
			</div>
		</div>
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
<script src="/STSM/public/js/index.js?t=Math.radom()"></script>
</body>
</html>