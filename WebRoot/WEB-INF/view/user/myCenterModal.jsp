<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 用户用户名验证JS -->
<script src="/STSM/public/js/user/user_name.js"></script>
<!-- 用户邮箱验证JS -->
<script src="/STSM/public/js/user/user_email.js"></script>
<!-- 用户手机号验证JS -->
<script src="/STSM/public/js/user/user_phone.js"></script>
<!-- 用户密码验证JS -->
<script src="/STSM/public/js/user/user_password.js"></script>
<script src="/STSM/public/js/common/jQuery.md5.js"></script>
<!-- 用户密码验证JS -->
<script src="/STSM/public/js/company/company_info.js"></script>

<!-- 用户信息模态框 -->
<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:10%;">
	<div class="modal-dialog" style="width:320px;">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-id-card-o"></i>&nbsp;用户信息</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="margin-top:-25px">×</button>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<table class="table table-bordered table-striped table-hover">
						<tbody>
							<tr>
								<td>用户名</td>
								<td>${user.getName()}</td>
							</tr>
							<tr>
								<td>用户类型</td>
								<td>${user.getTypeStr()}</td>
							</tr>
							<tr>
								<td>手机号</td>
								<td>${(user.getPhone()==null||user.getPhone().length()==0)?"未绑定":user.getPhone()}</td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td>${(user.getEmail()==null||user.getEmail().length()==0)?"未绑定":user.getEmail()}</td>
							</tr>
						</tbody>
					</table>
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
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="margin-top:-25px">×</button>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<!-- 旧密码输入框 -->
							<div id="oldPassword_input">
								<input id="user_password_old" type="hidden" value="${user.getPassword()}"/>
								<input id="user_account" type="hidden" value="${user.getAccount()}"/>
								<h4>验证旧密码</h4><hr>
								<input class="form-control" type="password"
								id="user_old_password" name="user_old_password"
								placeholder="请输入旧密码"
								onkeypress="if(event.keyCode==13) {verifyOldPasswordBtn.click();return false;}"/>
							</div>
							<!-- 新密码输入框 -->
							<div style="display:none" id="newPassword_input">
								<h4>输入新密码</h4><hr>
								<!-- 新密码输入框1 -->
								<div class="form-group">
									<div class="col-md-11">
										<input class="form-control" type="password"
										id="user_password" name="user_password"
										placeholder="6~18位字符,必须包含数字、字母或特殊字符其中两项及以上" 
										onkeypress="if(event.keyCode==13) {changePasswordBtn.click();return false;}"/>
									</div>
									<i class="fa fa-eye-slash fa-2x" id="eye" onclick="hideShowNewPsw()"></i>
								</div>
								<!-- 新密码输入框2 -->
								<div class="form-group">
									<div class="col-md-12">
										<input class="form-control" type="password"
										id="user_passwords" name="user_passwords"
										placeholder="请再次输入密码" 
										onkeypress="if(event.keyCode==13) {changePasswordBtn.click();return false;}"/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="verifyOldPasswordBtn" onclick="verifyOldPassword();">验证</button>
				<button style="display:none" class="btn btn-primary" id="changePasswordBtn" onchange="checkUser_password()" onclick="changePassword();">修改密码</button>
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
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="margin-top:-25px">×</button>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<h4>绑定手机号</h4><hr>
							<div class="form-group">
								<input id="sendPhone" name="sendPhone" type="hidden"/>
								<input class="form-control" type="tel"
									id="user_phone" name="user_phone" onchange="IsPhoneExist(1)"
									placeholder="请输入手机号" 
									onkeypress="if(event.keyCode==13) {bindPhoneCodeBtn.click();return false;}"/>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input id="phonecode" name="phonecode" type="hidden"/>
									<input class="form-control" type="text" autocomplete="off" 
									id="phone_code" name="phone_code" disabled="disabled"
									placeholder="请输入验证码"
									onkeypress="if(event.keyCode==13) {bindPhoneBtn.click();return false;}"/>
									<span class="input-group-btn"><button type="button" class="btn btn-primary" id="bindPhoneCodeBtn"
									onclick="if(checkUser_phone()&&IsPhoneExist()){countdown=60;sendPhoneCode($('#bindPhoneCodeBtn'),$('#phone_code'),$('#phonecode'),$('#user_phone'));}">
									免费获取验证码</button></span>
								</div>
							</div>
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
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="margin-top:-25px">×</button>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<input id="user_phone_old" name="user_phone_old" type="hidden" value="${user.getPhone()}"/>
							<h4>解绑手机号&ensp;${COMUtil.HidePhone(user.getPhone())}</h4><hr>
							<div class="form-group">
								<div class="input-group">
									<input id="unphonecode" name="unphonecode" type="hidden"/>
									<input class="form-control" type="text" autocomplete="off" 
									id="unphone_code" name="unphone_code" disabled="disabled"
									placeholder="请输入验证码"
									onkeypress="if(event.keyCode==13) {unBindPhoneBtn.click();return false;}"/>
									<span class="input-group-btn"><button type="button" class="btn btn-primary" id="unBindPhoneCodeBtn"
									onclick="countdown=60;sendPhoneCode($('#unBindPhoneCodeBtn'),$('#unphone_code'),$('#unphonecode'),$('#user_phone_old'));">
									免费获取验证码</button></span>
								</div>
							</div>
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
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="margin-top:-25px">×</button>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<h4>绑定邮箱</h4><hr>
							<div class="form-group">
								<input id="sendEmail" name="sendEmail" type="hidden"/>
								<input class="form-control" type="tel"
									id="user_email" name="user_email" onchange="IsEmailExist(1)"
									placeholder="请输入邮箱" 
									onkeypress="if(event.keyCode==13) {bindEmailCodeBtn.click();return false;}"/>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input id="emailcode" name="emailcode" type="hidden"/>
									<input class="form-control" type="text" autocomplete="off" 
									id="email_code" name="email_code" disabled="disabled"
									placeholder="请输入验证码"
									onkeypress="if(event.keyCode==13) {bindEmailBtn.click();return false;}"/>
									<span class="input-group-btn"><button type="button" class="btn btn-primary" id="bindEmailCodeBtn"
									onclick="if(checkUser_email()&&IsEmailExist()){countdown=60;sendEmailCode($('#bindEmailCodeBtn'),$('#email_code'),$('#emailcode'),$('#user_email'));}">
									免费获取验证码</button></span>
								</div>
							</div>
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
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="margin-top:-25px">×</button>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<input id="user_email_old" name="user_email_old" type="hidden" value="${user.getEmail()}"/>
							<h4>解绑邮箱&ensp;${COMUtil.HideEmail(user.getEmail())}</h4><hr>
							<div class="form-group">
								<div class="input-group">
									<input id="unemailcode" name="unemailcode" type="hidden"/>
									<input class="form-control" type="text" autocomplete="off" 
									id="unemail_code" name="unemail_code" disabled="disabled"
									placeholder="请输入验证码"
									onkeypress="if(event.keyCode==13) {unBindEmailBtn.click();return false;}"/>
									<span class="input-group-btn"><button type="button" class="btn btn-primary" id="unBindEmailCodeBtn"
									onclick="countdown=60;sendEmailCode($('#unBindEmailCodeBtn'),$('#unemail_code'),$('#unemailcode'),$('#user_email_old'));">
									免费获取验证码</button></span>
								</div>
							</div>
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

