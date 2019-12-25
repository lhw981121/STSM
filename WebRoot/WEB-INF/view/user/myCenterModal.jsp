<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 用户用户名验证JS -->
<script src="/STSM/public/js/user/user_name.js"></script>
<!-- 用户邮箱验证JS -->
<script src="/STSM/public/js/user/user_email.js"></script>
<!-- 用户手机号验证JS -->
<script src="/STSM/public/js/user/user_phone.js"></script>
<!-- 用户密码验证JS -->
<script src="/STSM/public/js/user/user_password.js"></script>
<script src="/STSM/public/js/jQuery.md5.js"></script>
<!-- 用户密码验证JS -->
<script src="/STSM/public/js/company/company_info.js"></script>

<!-- 用户信息模态框 -->
<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%;">
	<div class="modal-dialog" style="width:320px;">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-id-card-o"></i>&nbsp;用户信息</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							用户名: ${user.getName()}
						</div>
						<div class="col-md-12">
							用户类型: ${user.getTypeStr()}
						</div>
							<%-- <div class="sidebar-widget mb-4">
								<div class="right-sidebar">
									<div class="sidebar-details">
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-user"></i>
											</div>
											<div class="meta-overview">
												<p>
													
												</p>
											</div>
										</div>
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-street-view"></i>
											</div>
											<div class="meta-overview">
												<p>
													用户类型
													: <span>${user.getTypeStr()}</span>
												</p>
											</div>
										</div>
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-phone"></i>
											</div>
											<div class="meta-overview">
												<p>
													手机号
													: <span>${(user.getPhone()==null||user.getPhone().length()==0)?"未绑定":user.getPhone()}</span>
												</p>
											</div>
										</div>
										<div class="single-overview  d-flex">
											<div class="icon">
												<i class="fa fa-envelope-o"></i>
											</div>
											<div class="meta-overview">
												<p>
													邮箱
													: <span>${(user.getEmail()==null||user.getEmail().length()==0)?"未绑定":user.getEmail()}</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div> --%>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!-- 更改密码模态框 -->
<div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-key"></i>&nbsp;修改密码</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<!-- 旧密码输入框 -->
							<div id="oldPassword_input">
								<input id="user_password_old" name="user_password_old" type="hidden" value="${user.getPassword()}"/>
								<h5>验证旧密码</h5><hr>
								<input class="form-control" type="password"
								id="user_old_password" name="user_old_password"
								placeholder="<fmt:message key="OldPasswordLimit"/>"
								onkeypress="if(event.keyCode==13) {verifyOldPasswordBtn.click();return false;}"/>
							</div>
							<!-- 新密码输入框 -->
							<div style="display:none" id="newPassword_input">
								<h5>输入新密码</h5><hr>
								<!-- 新密码输入框1 -->
								<div class="custom-control custom-control-inline" style="margin-left:-28px;width:93%">
									<input class="form-control" type="password"
										id="user_password" name="user_password"
										placeholder="<fmt:message key="PasswordLimit"/>" 
										onkeypress="if(event.keyCode==13) {changePasswordBtn.click();return false;}"/>
								</div><i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowNewPsw()"></i>
								<!-- 新密码输入框2 -->
								<p style="margin-top:16px">
									<input class="form-control" type="password"
										id="user_passwords" name="user_passwords"
										placeholder="<fmt:message key="PasswordAgain"/>" 
										onkeypress="if(event.keyCode==13) {changePasswordBtn.click();return false;}"/>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="verifyOldPasswordBtn" onclick="verifyOldPassword();">验证</button>
				<button style="display:none" class="btn btn-primary" id="changePasswordBtn" onchange="checkUser_password()" onclick="changePassword();">修改</button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 绑定手机号模态框 -->
<div class="modal fade" id="bindPhoneModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-mobile"></i>&nbsp;绑定手机号</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>
								<input id="sendPhone" name="sendPhone" type="hidden"/>
								<input class="form-control" type="tel"
									id="user_phone" name="user_phone" onchange="IsPhoneExist(1)"
									placeholder="<fmt:message key="PhoneTip"/>" 
									onkeypress="if(event.keyCode==13) {bindPhoneCodeBtn.click();return false;}"/>
							</p>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="phonecode" name="phonecode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="phone_code" name="phone_code" disabled="disabled"
								placeholder="<fmt:message key="PhoneCodeTip"/>"
								onkeypress="if(event.keyCode==13) {bindPhoneBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="bindPhoneCodeBtn" 
							onclick="if(checkUser_phone()&&IsPhoneExist()){countdown=60;sendPhoneCode($('#bindPhoneCodeBtn'),$('#phone_code'),$('#phonecode'),$('#user_phone'));}">
							免费获取验证码</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="bindPhoneBtn" onclick="bindPhone();">绑定</button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 解绑手机号模态框 -->
<div class="modal fade" id="unBindPhoneModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-mobile"></i>&nbsp;解绑手机号</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<input id="user_phone_old" name="user_phone_old" type="hidden" value="${user.getPhone()}"/>
							<h5>解绑手机号&ensp;${COMUtil.HidePhone(user.getPhone())}</h5><hr>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="unphonecode" name="unphonecode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="unphone_code" name="unphone_code" disabled="disabled"
								placeholder="<fmt:message key="PhoneCodeTip"/>"
								onkeypress="if(event.keyCode==13) {unBindPhoneBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="unBindPhoneCodeBtn" 
							onclick="countdown=60;sendPhoneCode($('#unBindPhoneCodeBtn'),$('#unphone_code'),$('#unphonecode'),$('#user_phone_old'));">
							免费获取验证码</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="unBindPhoneBtn" onclick="unBindPhone();">解绑</button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 绑定邮箱模态框 -->
<div class="modal fade" id="bindEmailModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-envelope-o"></i>&nbsp;绑定邮箱</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>
								<input id="sendEmail" name="sendEmail" type="hidden"/>
								<input class="form-control" type="tel"
									id="user_email" name="user_email" onchange="IsEmailExist(1)"
									placeholder="<fmt:message key="EmailTip"/>" 
									onkeypress="if(event.keyCode==13) {bindEmailCodeBtn.click();return false;}"/>
							</p>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="emailcode" name="emailcode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="email_code" name="email_code" disabled="disabled"
								placeholder="<fmt:message key="EmailCodeTip"/>"
								onkeypress="if(event.keyCode==13) {bindEmailBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="bindEmailCodeBtn" 
							onclick="if(checkUser_email()&&IsEmailExist()){countdown=60;sendEmailCode($('#bindEmailCodeBtn'),$('#email_code'),$('#emailcode'),$('#user_email'));}">
							免费获取验证码</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="bindEmailBtn" onclick="bindEmail();">绑定</button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 解绑邮箱模态框 -->
<div class="modal fade" id="unBindEmailModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-envelope-o"></i>&nbsp;解绑邮箱</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<input id="user_email_old" name="user_email_old" type="hidden" value="${user.getEmail()}"/>
							<h5>解绑邮箱&ensp;${COMUtil.HideEmail(user.getEmail())}</h5><hr>
							<div class="custom-control custom-control-inline" style="margin-left:-24px;width:70%">
								<input id="unemailcode" name="unemailcode" type="hidden"/>
								<input class="form-control" type="text" autocomplete="off"
								id="unemail_code" name="unemail_code" disabled="disabled"
								placeholder="<fmt:message key="EmailCodeTip"/>"
								onkeypress="if(event.keyCode==13) {unBindEmailBtn.click();return false;}"/>
							</div>
							<button class="btn btn-info" type="button" style="margin:-3px 0px 0px -20px;width:35%;font-size:15px" id="unBindEmailCodeBtn" 
							onclick="countdown=60;sendEmailCode($('#unBindEmailCodeBtn'),$('#unemail_code'),$('#unemailcode'),$('#user_email_old'));">
							免费获取验证码</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="unBindEmailBtn" onclick="unBindEmail();">解绑</button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 自定义脚本 -->
<script src="/STSM/public/js/user/user_myCenterModal.js"></script>

