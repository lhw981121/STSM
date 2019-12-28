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

	<div style="padding: 80px 0px;text-align: center">
		<h1>员工今日考勤</h1><hr>
		<h3><span style="color:red">注意事项</span><br></h3>
		<h4>上班前30分钟和下班后30分钟为当日两次考勤时间段，期间未打卡者算作当日未考勤处理。</h4>
		<h4>如发现员工代他人进行当日考勤，<span style="color:red">违者重罚！</span></h4>
		<p>
			<span style="color:#E308E4;font-size:25px">考勤状态：</span><span style="font-size:25px" id="attenStateStr">${atten.getStateStr() }</span>
		</p>
		<c:choose>
			<c:when test="${atten.state==1 }"><!-- 上班打卡 -->
				<c:if test="${staff.isClockIn }">
					<button type="button" class="btn btn-default" onclick="staffClocked('上班')" style="margin:20px">
						<span class="fa fa-thumbs-o-up" style="font-size:150px" title="上班已打卡"></span>
					</button>
				</c:if>
				<c:if test="${!staff.isClockIn }">
					<button type="button" class="btn btn-default" onclick="staffClockIn()" style="margin:20px">
						<span class="fa fa-sign-in" style="font-size:150px" title="上班打卡"></span>
					</button>
				</c:if>
			</c:when>
			<c:when test="${atten.state==2 }"><!-- 下班打卡 -->
				<c:if test="${staff.isClockOut }">
					<button type="button" class="btn btn-default" onclick="staffClocked('下班')" style="margin:20px">
						<span class="fa fa-thumbs-o-up" style="font-size:150px" title="下班已打卡"></span>
					</button>
				</c:if>
				<c:if test="${!staff.isClockOut }">
					<button type="button" class="btn btn-default" onclick="staffClockOut()" style="margin:20px">
						<span class="fa fa-sign-in" style="font-size:150px" title="下班打卡"></span>
					</button>
				</c:if>
			</c:when>
			<c:when test="${atten.isWork }"><!-- 工作时间 -->
				<button type="button" class="btn btn-default" onclick="workTime()" style="margin:20px">
					<p><span class="fa fa-rocket" style="font-size:150px"></span></p>
				</button>
			</c:when>
			<c:otherwise><!-- 非工作时间 -->
				<button type="button" class="btn btn-default" onclick="notWorkTime()" style="margin:20px">
					<span class="fa fa-cutlery" style="font-size:150px"></span>
				</button>
			</c:otherwise>
		</c:choose>
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
<script src="/STSM/public/js/staff/attendanceToday.js"></script>

</body>
</html>