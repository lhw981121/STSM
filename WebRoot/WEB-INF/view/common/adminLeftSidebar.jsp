<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
			<ul class="nav">

				<li><a href="/STSM/admin" id="admin" class=""><i class="lnr lnr-home"></i> <span>主页</span></a></li>
				
				<li>
					<a href="#staff_attendance" data-toggle="collapse" id="#staff_attendance" class="collapsed">
					<i class="fa fa-clipboard"></i> <span>员工考勤</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="staff_attendance" class="collapse">
						<ul class="nav">
							<c:choose>
								<c:when test="${empty param.date}">
									<li><a href="/STSM/admin/staff_attendance/yes" id="attendance_yes" class="">已考勤员工</a></li>
									<li><a href="/STSM/admin/staff_attendance/no" id="attendance_no" class="">未考勤员工</a></li>
									<li><a href="/STSM/admin/staff_attendance/stat" id="attendance_stat" class="">考勤统计</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/STSM/admin/staff_attendance/yes?date=${param.date }" id="attendance_yes" class="">已考勤员工</a></li>
									<li><a href="/STSM/admin/staff_attendance/no?date=${param.date }" id="attendance_no" class="">未考勤员工</a></li>
									<li><a href="/STSM/admin/staff_attendance/stat?date=${param.date }" id="attendance_stat" class="">考勤统计</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</li>

				<li>
					<a href="#manageInfo" data-toggle="collapse" id="#manageInfo" class="collapsed"> 
					<i class="lnr lnr-database"></i> <span>信息管理</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="manageInfo" class="collapse">
						<ul class="nav">
							<li><a href="/STSM/admin/manage_info/staff/info" id="staff">员工信息管理</a></li>
							<li><a href="/STSM/admin/manage_info/attendance/info" id="attendance">考勤信息管理</a></li>
							<li><a href="###" id="###">###</a></li>
						</ul>
					</div>
				</li>

			</ul>
		</nav>
	</div>
</div>
<!-- END LEFT SIDEBAR -->

<!-- 侧边栏选中保持 -->
<script>
	var pathname = window.location.pathname;
	
	//主页
	if(pathname.indexOf("STSM/admin/") == -1){
		document.getElementById("admin").className = "active";
	}

	//管理员员工考勤菜单
	if (pathname.indexOf("staff_attendance") != -1) {
		document.getElementById("#staff_attendance").className = "active";
		document.getElementById("staff_attendance").className = "collapse in";
		if (pathname.indexOf("yes") != -1) {
			document.getElementById("attendance_yes").className = "active";
		} else if (pathname.indexOf("no") != -1) {
			document.getElementById("attendance_no").className = "active";
		} else if (pathname.indexOf("stat") != -1) {
			document.getElementById("attendance_stat").className = "active";
		}
	}

	//管理员信息管理菜单
	if (pathname.indexOf("manage_info") != -1) {
		document.getElementById("#manageInfo").className = "active";
		document.getElementById("manageInfo").className = "collapse in";
		if (pathname.indexOf("staff") != -1) {
			document.getElementById("staff").className = "active";
		} else if (pathname.indexOf("attendance") != -1) {
			document.getElementById("attendance").className = "active";
		}
	}
</script>