<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/init.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/head.jsp"%>
</head>
<!-- 邮箱验证码验证JS -->
<script src="/public/js/user/user_email.js"></script>
<!-- 手机号验证码验证JS -->
<script src="/public/js/user/user_phone.js"></script>
<!-- 用户密码验证JS -->
<script src="/public/js/user/user_password.js"></script>

<body>
<div id="wrapper"><!-- WRAPPER -->
<!-- 导航栏 -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="brand">
		<a href="/CSMS/login.jsp"><img src="/CSMS/public/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
	</div>
	<div class="container-fluid">
		<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/CSMS/login.jsp">登录</a></li>
				<li><a href="/CSMS/SWZJ/user/logon.jsp">注册</a></li>
			</ul>
		</div>
	</div>
</nav>
<!-- END 导航栏 -->

<!-- 内容区域 -->
<!-- MAIN CONTENT -->
<div class="main-content">

<div class="container">
	<div class="col-md-8 col-md-offset-2" style="padding: 100px 0 0 0;">
		<div class="panel panel-default" style="text-align:center; vertical-align:middel;">
			<div class="panel-heading">
				<h3 class="panel-title">找回密码</h3>
			</div>
			<div class="panel-body">
				<!-- 填写账号 -->
				<form id="accountForm">
					<div class="form-info">
						<div class="info-field">
							<h3 style="text-align:center">
								第一步：输入用户账号
							</h3><hr>
							<!-- 用户账号输入框 -->
							<p>
								<input class="form-control" type="text"
									id="user_account" name="user_account" autocomplete="off" 
									placeholder="<fmt:message key="LostAccountTip"/>" 
									onkeypress="if(event.keyCode==13) {accountBtn.click();return false;}"/>
							</p>
							<!-- 查找按钮 -->
							<div>
								<button class="btn btn-primary btn-block" type="button" id="accountBtn">查找</button>
							</div>
						</div>
					</div>
				</form>
				
				<!-- 选择验证方式 -->
				<form id="modeForm" style="display:none">
					<div class="form-info">
						<div class="info-field">
							<h3 style="text-align:center">
								第二步：选择验证方式
							</h3><hr>
							<!-- 用户账号输入框 -->
							<select class="custom-select" id="selectMode">
								<option selected value="0" id="default_mode">选择验证方式</option>
								<option value="1" id="user_email_mode" style="display:none"></option>
								<option value="2" id="user_phone_mode" style="display:none"></option>
							</select>
						</div>
					</div>
				</form>
				
				<!-- 填写邮箱验证码 -->
				<form id="emailForm" style="display:none">
					<div class="form-info">
						<div class="info-field">
							<h3 style="text-align:center">
								第三步：填写邮箱验证码
							</h3><hr>
							<!-- 验证码输入框 -->
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="emailcode" name="emailcode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off" 
								id="email_code" name="email_code" disabled="disabled"
								placeholder="<fmt:message key="EmailCodeTip"/>"
								onkeypress="if(event.keyCode==13) {emailBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" 
							style="margin:-3px 0px 0px -20px;width:34%;font-size:15px" id="emailCodeBtn">免费获取验证码</button>
							<!-- 提交按钮 -->
							<div style="text-align:center">
								<button class="btn btn-primary" type="button" id="emailBtn">提交</button>
								<button class="btn btn-primary" type="button" id="modeBack1">返回</button>
							</div>
						</div>
					</div>
				</form>
				
				<!-- 填写手机短信验证码 -->
				<form id="phoneForm" style="display:none">
					<div class="form-info">
						<div class="info-field">
							<h3 style="text-align:center">
								第三步：填写手机验证码
							</h3><hr>
							<!-- 验证码输入框 -->
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="phonecode" name="phonecode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off" 
								id="phone_code" name="phone_code" disabled="disabled"
								placeholder="<fmt:message key="PhoneCodeTip"/>"
								onkeypress="if(event.keyCode==13) {phoneBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" 
							style="margin:-3px 0px 0px -20px;width:34%;font-size:15px" id="phoneCodeBtn">免费获取验证码</button>
							<!-- 提交按钮 -->
							<div style="text-align:center">
								<button class="btn btn-primary" type="button" id="phoneBtn">提交</button>
								<button class="btn btn-primary" type="button" id="modeBack2">返回</button>
							</div>
						</div>
					</div>
				</form>
				
				<!-- 填写新密码 -->
				<form id="passwordForm" style="display:none">
					<div class="form-info">
						<div class="info-field">
							<h3 style="text-align:center">
								第四步：填写新密码
							</h3><hr>
							<!-- 新密码输入框1 -->
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:93%">
								<input class="form-control" type="password"
									id="user_password" name="user_password"
									placeholder="<fmt:message key="PasswordLimit"/>" 
									onkeypress="if(event.keyCode==13) {passwordBtn.click();return false;}"/>
							</div><i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowNewPsw()"></i>
							<!-- 新密码输入框2 -->
							<p>
								<input class="form-control" type="password"
									id="user_passwords" name="user_passwords"
									placeholder="<fmt:message key="PasswordAgain"/>" 
									onkeypress="if(event.keyCode==13) {passwordBtn.click();return false;}"/>
							</p>
							<!-- 提交按钮 -->
							<div>
								<button class="btn btn-primary btn-block" type="button" id="passwordBtn">提交</button>
							</div>
						</div>
					</div>
				</form>
			</div>		
		</div>
	</div>
</div>

</div>
<!-- END MAIN CONTENT -->
<!-- END 内容区域 -->

<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/foot.jsp" %>
</div><!-- END WRAPPER -->

<!-- start-公共脚本 -->
<%@include file="/WEB-INF/view/common/javaScript.jsp"%>
<!-- end-公共脚本 -->
<script src="/public/js/jQuery.md5.js"></script>
<!-- 自定义脚本 -->
<script src="/public/js/user/user_lostPassword.js"></script>
</body>
</html>


