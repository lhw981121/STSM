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

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">欢迎 ${user.getName()} 登录</div>
					<div class="panel-body">你是 ${user.getTypeStr()} ！
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-4">
			<!-- PANEL DEFAULT -->
			<div class="panel">
				<div class="panel-heading">
					<h3 class="panel-title">当前工作时间${atten.period}</h3>
					<div class="right">
						<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
						<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
					</div>
				</div>
				<div class="panel-body">
					<form action="/STSM/admin" method="post">
						<input class="form-control input-lg" placeholder="工作时间格式（XX时:XX分-XX时:XX分）" type="text" name="period" value="" required>
						<div class="panel-body">
							<p class="demo-button">
								<button type="submit" class="btn btn-danger" onclick="return confirm('确定修改工作时间?')">修改工作时间！</button>
							</p>
						</div>
					</form>
				</div>	
			</div>
			<!-- END PANEL DEFAULT -->
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