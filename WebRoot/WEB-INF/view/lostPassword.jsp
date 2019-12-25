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
<script src="/STSM/public/js/user/user_email.js"></script>
<!-- 手机号验证码验证JS -->
<script src="/STSM/public/js/user/user_phone.js"></script>
<!-- 用户密码验证JS -->
<script src="/STSM/public/js/user/user_password.js"></script>

<body>
<div id="wrapper"><!-- WRAPPER -->
<!-- 导航栏 -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="brand">
		<a href="/STSM/login"><img src="/STSM/public/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
	</div>
	<div class="container-fluid">
		<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/STSM/login">登录</a></li>
			</ul>
		</div>
	</div>
</nav>
<!-- END 导航栏 -->

<!-- 内容区域 -->
<!-- MAIN CONTENT -->
<div class="main-content">

<div class="container">
	<div class="col-md-8 col-md-offset-2" style="padding: 100px">
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
							<div class="form-group">
								<input class="form-control" type="text"
									id="user_account" name="user_account" autocomplete="off" 
									placeholder="员工工号、邮箱或手机号" 
									onkeypress="if(event.keyCode==13) {accountBtn.click();return false;}"/>
							</div>
							<br>
							<div class="form-group">
								<button class="btn btn-primary" type="button" id="accountBtn">查找</button>
								<a class="btn btn-primary" href="#" onClick="history.back(-1)">取消</a>
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
							<div class="form-group">
								<select class="form-control" id="selectMode">
									<option selected value="0" id="default_mode">选择验证方式</option>
									<option value="1" id="user_email_mode" style="display:none"></option>
									<option value="2" id="user_phone_mode" style="display:none"></option>
								</select>
							</div>
							<br>
							<div class="form-group">
								<a class="btn btn-primary" href="#" onClick="history.back(-1)">取消</a>
							</div>
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
							<div class="form-group">
								<div class="input-group">
									<input id="emailcode" name="emailcode" type="hidden"/>
									<input class="form-control" type="text" autocomplete="off" 
									id="email_code" name="email_code" disabled="disabled"
									placeholder="输入验证码"
									onkeypress="if(event.keyCode==13) {emailBtn.click();return false;}"/>
									<span class="input-group-btn"><button type="button" class="btn btn-primary" id="emailCodeBtn">免费获取验证码</button></span>
								</div>
							</div>
							<br>
							<div class="form-group">
								<button type="button" class="btn btn-primary" id="emailBtn">提交</button>
								<button type="button" class="btn btn-primary" id="modeBack1">返回</button>
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
							<div class="form-group">
								<div class="input-group">
									<input id="phonecode" name="phonecode" type="hidden"/>
									<input class="form-control" type="text" autocomplete="off" 
									id="phone_code" name="phone_code" disabled="disabled"
									placeholder="输入验证码"
									onkeypress="if(event.keyCode==13) {phoneBtn.click();return false;}"/>
									<span class="input-group-btn"><button type="button" class="btn btn-primary" id="phoneCodeBtn">免费获取验证码</button></span>
								</div>
							</div>
							<br>
							<div class="form-group">
								<button type="button" class="btn btn-primary" id="phoneBtn">提交</button>
								<button type="button" class="btn btn-primary" id="modeBack2">返回</button>
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
								<div class="form-group">
									<div class="col-md-11">
										<input class="form-control" type="password"
										id="user_password" name="user_password"
										placeholder="6~18位字符,必须包含数字、字母或特殊字符其中两项及以上" 
										onkeypress="if(event.keyCode==13) {passwordBtn.click();return false;}"/>
									</div>
									<i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowNewPsw()"></i>
								</div>
								<!-- 新密码输入框2 -->
								<div class="form-group">
									<div class="col-md-12">
										<input class="form-control" type="password"
										id="user_passwords" name="user_passwords"
										placeholder="请再次输入密码" 
										onkeypress="if(event.keyCode==13) {passwordBtn.click();return false;}"/>
									</div>
								</div>
							<div class="form-group">
								<button style="margin-top:20px" type="button" class="btn btn-primary" id="passwordBtn">重置密码</button>
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
<script src="/STSM/public/js/common/jQuery.md5.js"></script>
<!-- 自定义脚本 -->
<script src="/STSM/public/js/user/user_lostPassword.js"></script>
</body>
</html>


