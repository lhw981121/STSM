<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

	<!-- ERROR TIP -->
	<!-- END ERROR TIP -->

	<div class="container-fluid" style="padding:200px 0px;width:50%;text-align:center">
		<div class="panel panel-default" >
			<div class="panel-heading">修改今日工作时间</div>
			<div class="panel-body">
				<form action="/STSM/admin" method="post">
					<input class="form-control input-lg" placeholder="工作时间格式（XX:XX-XX:XX）" type="text" name="period" value="" required>
					<div>
						<button style="margin-top:20px" type="submit" class="btn btn-danger" onclick="return confirm('确定修改工作时间?')">修改工作时间！</button>
					</div>
				</form>
			</div>	
		</div>
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

</body>
</html>