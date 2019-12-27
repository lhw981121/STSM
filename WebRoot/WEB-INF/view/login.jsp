<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/init.jsp"%>

<!DOCTYPE html>
<html class="fullscreen-bg">
<head>
<!-- 头部 -->
<%@include file="/WEB-INF/view/common/head.jsp" %>
<!-- 用户信息验证JS -->
<script src="/STSM/public/js/user/user_info_validate.js"></script>

</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
	<div class="vertical-align-wrap">
		<div class="vertical-align-middle">
			<div class="auth-box ">
				<div class="left">
					<div class="content">
						<div class="header">
							<div class="logo text-center">
								<img src="/STSM/public/assets/img/logo-dark.png" alt="Klorofil Logo">
							</div>
							<!-- 登录方式选择 -->
							<div class="mode-left">
                        <span id="mode_account" style="color:rgb(0,170,255)">账号密码登录</span>
                     </div>
                     <div class="mode-right" >
                        <span id="mode_phone">手机验证登录</span>
                     </div>
						</div>
						<form class="form-auth-small">
							<!-- 账号密码登录方式 -->
							<div id="account_input">
								<div class="form-group">
									<input class="form-control" type="text" value="${account}"
										id="user_account" name="user_account"
										placeholder="员工工号、邮箱或手机号"
										onkeypress="if(event.keyCode==13) {accountLoginBtn.click();return false;}"/>
								</div>
								<div class="form-group">
									<input class="form-control" type="password" value="${password}"
										id="user_password" name="user_password"
										placeholder="请输入密码"
										onkeypress="if(event.keyCode==13) {accountLoginBtn.click();return false;}"/>
								</div>
							</div>
							
							<!-- 手机验证登录方式 -->
							<div style="display:none" id="phone_input">
								<div class="form-group">
									<input id="sendPhone" name="sendPhone" type="hidden"/>
									<input class="form-control" type="tel" id="user_phone" name="user_phone" placeholder="请输入手机号" 
										onkeypress="if(event.keyCode==13) {phoneCodeBtn.click();return false;}"/>
								</div>
								<div class="input-group" style="margin-bottom:15px">
									<input id="phonecode" name="phonecode" type="hidden"/>
									<input type="text" class="form-control" id="phone_code" name="phone_code" disabled="disabled" style="margin:auto"
									placeholder="填写手机短信验证码" value="" autocomplete="off"
									onkeypress="if(event.keyCode==13) {phoneLoginBtn.click();return false;}"/>
									<span class="input-group-btn">
										<button style="margin:auto" type="button" class="btn btn-primary" id="phoneCodeBtn">免费获取验证码</button>
									</span>
								</div>
							</div>
							<!-- 记住密码 -->
							<div class="form-group clearfix">
								<label class="fancy-checkbox element-left"> 
									<input type="checkbox" id="rememberPsw" name="rememberPsw"> <span>下次自动登录</span>
								</label>
							</div>
							<!-- 登录按钮 -->
							<div>
								<button type="button" class="btn btn-primary btn-lg btn-block" id="accountLoginBtn">登录</button>
								<button type="button" class="btn btn-primary btn-lg btn-block" id="phoneLoginBtn" style="display:none">登录</button>							
							</div>
							<!-- 忘记密码 -->
							<div class="bottom">
								<span class="helper-text"><i class="fa fa-lock"></i> <a href="lost_password">忘记密码？</a></span>
							</div>
						</form>
					</div>
				</div>
				<div class="right">
					<div class="overlay"></div>
					<div class="content text">
						<h1 class="heading">员工管理系统</h1>
						<p>By 神葳总局</p>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/foot.jsp" %>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/javaScript.jsp" %>
<!-- End Javascript -->
<script src="/STSM/public/js/common/jQuery.md5.js"></script>
<!-- 自定义脚本 -->
<script src="/STSM/public/js/user/user_login.js"></script>
<!-- Cookie中有用户信息直接验证通过 -->
<%if(request.getAttribute("password")!=null){%>
	<script>
		$('#rememberPsw').attr("checked",true);
		$('#accountLoginBtn').click();
	</script>
<%}%>
<!-- 用户已登录（session中有用户信息）直接验证通过 -->
<%if(session.getAttribute("user")!=null){%>
	<script>
		$('#user_account').val('${user.getAccount()}');
		$('#user_password').val('${user.getPassword()}');
		$('#accountLoginBtn').click();
	</script>
<%}%> 
</body>
</html>
