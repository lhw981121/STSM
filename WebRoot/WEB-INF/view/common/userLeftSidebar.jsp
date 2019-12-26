<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
			<ul class="nav">

				<li><a href="/STSM/index" id="index" class=""><i class="lnr lnr-home"></i> <span>主页</span></a></li>
				
				<li>
					<a href="#attendance" data-toggle="collapse" id="#attendance" class="collapsed">
					<i class="fa fa-wpforms"></i> <span>员工考勤</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="attendance" class="collapse">
						<ul class="nav">
							<li><a href="/STSM/staff/attendance_today" id="attendance_today" class="">今日考勤</a></li>
							<li><a href="/STSM/staff/attendance_record" id="attendance_record" class="">考勤记录</a></li>
						</ul>
					</div>
				</li>
				
				<li><a href="/STSM/staff/salary_check" id="salary_check" class=""><i class="fa fa-money"></i><span>薪水核实</span></a></li>

			</ul>
		</nav>
	</div>
</div>
<!-- END LEFT SIDEBAR -->

<!-- 侧边栏选中保持 -->
<script>
	var pathname = window.location.pathname;
	
	//主页
	if(pathname.indexOf("index") != -1){
		document.getElementById("index").className = "active";
	}

	//员工考勤菜单
	if (pathname.indexOf("attendance") != -1) {
		document.getElementById("#attendance").className = "active";
		document.getElementById("attendance").className = "collapse in";
		if (pathname.indexOf("attendance_today") != -1) {
			document.getElementById("attendance_today").className = "active";
		} else if (pathname.indexOf("attendance_record") != -1) {
			document.getElementById("attendance_record").className = "active";
		}
	}
	
	//员工薪水核实
	if(pathname.indexOf("salary_check") != -1){
		document.getElementById("salary_check").className = "active";
	}
	
</script>
