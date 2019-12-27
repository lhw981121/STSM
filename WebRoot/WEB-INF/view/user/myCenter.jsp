<%@ page language="java" import="java.util.*,com.stsm.util.COMUtil" pageEncoding="UTF-8"%>
<!-- 页面初始化 -->
<%@include file="/WEB-INF/view/common/init.jsp"%>

<!DOCTYPE html>
<html lang="${language}">
<head>
<!-- 公共头部 -->
<%@include file="/WEB-INF/view/common/head.jsp"%>
<style>
table td:nth-child(1) {text-align: left;}
table td:nth-child(2) {text-align: left;}
table td:nth-child(3) {text-align: left;}
table td:nth-child(4) {text-align: center;}
table td p:nth-child(1) {margin: 5px 0;}
table td p:nth-child(1) {margin: 10px 0;}
.title {font-size: 18px}
.text {color: grey;font-size: 14px}
</style>
</head>
<body>
<div id="wrapper">
<!-- WRAPPER -->
<!-- 导航栏 -->
<%@include file="/WEB-INF/view/common/navbar.jsp"%>
<!-- 左侧边栏 -->
<%@include file="/WEB-INF/view/common/userLeftSidebar.jsp"%>

<!-- 内容区域 -->
<div class="main">
<!-- MAIN CONTENT -->
<div class="main-content">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="panel panel-default" style="text-align:center">
					<div class="panel-heading">
						<p style="margin:3px 0;color:; font-size:28px">
							<strong style="color:;"><%=user.getName()%></strong>&ensp;的个人中心
						</p>
					</div>
					<div class="panel-body">
						<!-- 头像开始 -->
						<div class="avatar">
							<a href="javascript:void(0);" onclick="$('#changeAvatarModal').modal('show')" title="更换头像">
								<img src="/STSM/public/images/user/avatar/${user.getID()}.jpg?t=${Math.random()}"
								onerror="this.src='/STSM/public/images/user/avatar/0.jpg';this.onerror=null"
								width="140" height="140" style="border-radius:50%" alt="Avatar" id="user_avatar_mycenter"/>
							</a>
						</div>
						<!-- 头像结束 -->
						<!-- 用户名 -->
						<p style="margin:5px 0;color:rgb(0, 170, 255);font-size:22px">${user.getName()}</p>
						<!-- 用户类型 -->
						<p class="title">
							<span>用户类型: </span><span>${user.getTypeStr()}</span>
						</p>
						<br>
						<table class="table table-hover" style="text-align:center;">
							<colgroup>
								<col style="width:8%">
								<col style="width:55%">
								<col style="width:20%">
								<col style="width:17%">
							</colgroup>
							<tbody>
								<!-- 用户信息区域 -->
								<c:if test="${user.getType() == 1 }">
								<!-- 员工 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/STSM/public/images/user/icon_information.png" height="45"></td>
									<td>
										<p class="title">今日考勤</p>
										<p class="text">请在上班前30分钟和下班后30分进行考勤打卡</p>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${atten.state==0}"><!-- 未到考勤时间段 -->
											<i class="lnr lnr-cross-circle"></i><span>非考勤时间段</span>
										</c:when>
										<c:otherwise>
											<i class="lnr lnr-checkmark-circle"></i><span>正在考勤</span>
										</c:otherwise>
										</c:choose>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${atten.state==0}"><!-- 未到考勤时间段 -->
											<a href="/STSM/staff/attendance_record" class="btn btn-primary">查看考勤记录</a>
										</c:when>
										<c:otherwise>
											<c:if test="${staff.isClockIn&&atten.state==1 }"><!-- 上班已打卡 -->
												<a href="/STSM/staff/attendance_record" class="btn btn-success">上班已打卡</a>
											</c:if>
											<c:if test="${!staff.isClockIn&&atten.state==1 }"><!-- 上班未打卡 -->
												<a href="/STSM/staff/attendance_today" class="btn btn-primary">上班打卡</a>
											</c:if>
											<c:if test="${staff.isClockIn&&atten.state==2 }"><!-- 下班已打卡 -->
												<a href="/STSM/staff/attendance_record" class="btn btn-success">下班已打卡</a>
											</c:if>
											<c:if test="${!staff.isClockIn&&atten.state==2 }"><!-- 下班未打卡 -->
												<a href="/STSM/staff/attendance_today" class="btn btn-primary">下班打卡</a>
											</c:if>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								</c:if>
								<!-- 用户信息区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/STSM/public/images/user/icon_userdata.png" height="40" style="margin-left: 6px;"></td>
									<td>
										<p class="title">用户信息</p>
										<p class="text">帐号归属者的基本资料</p>
									</td>
									<td style="vertical-align:middle;">
										<i	class="lnr lnr-checkmark-circle"></i><span>使用中</span>
									</td>
									<td style="vertical-align:middle;">
										<a href="javascript:void(0);" class="btn btn-info" onclick="$('#userInfoModal').modal('show')">查看</a>
									</td>
								</tr>
								<!-- 账号密码区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/STSM/public/images/user/icon_password.png" height="45"></td>
									<td>
										<p class="title">账号密码</p>
										<p class="text">为了保护帐号安全，登录时使用</p>
									</td>
									<td style="vertical-align:middle;">
										<i class="lnr lnr-checkmark-circle"></i><span>已设置</span>
									</td>
									<td style="vertical-align:middle;">
										<a href="javascript:void(0);" class="btn btn-info" onclick="$('#changePasswordModal').modal('show')">修改</a>
									</td>
								</tr>
								<!-- 手机区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/STSM/public/images/user/icon_bindmobile.png" height="45"></td>
									<td>
										<p class="title">联系手机<span style="color: grey;">&emsp;${COMUtil.HidePhone(user.getPhone())}</span></p>
										<p class="text">用于找回密码、安全验证、作为登录账号</p>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getPhone()}">
											<i class="lnr lnr-cross-circle"></i><span>未绑定</span>
										</c:when>
										<c:otherwise>
											<i class="lnr lnr-checkmark-circle"></i><span>已绑定</span>
										</c:otherwise>
										</c:choose>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getPhone()}">
											<a href="javascript:void(0);" class="btn btn-info" onclick="$('#bindPhoneModal').modal('show')">绑定</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" class="btn btn-danger" onclick="$('#unBindPhoneModal').modal('show')">解绑</a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<!-- 邮箱区域 -->
								<tr>
									<td style="vertical-align:middle;"><img src="/STSM/public/images/user/icon_bindemial.png" height="45"></td>
									<td>
										<p class="title">联系邮箱<span style="color: grey;">&emsp;${COMUtil.HideEmail(user.getEmail())}</span></p>
										<p class="text">用于找回密码、安全验证、作为登录账号</p>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getEmail()}">
											<i class="lnr lnr-cross-circle"></i><span>未绑定</span>
										</c:when>
										<c:otherwise>
											<i class="lnr lnr-checkmark-circle"></i><span>已绑定</span>
										</c:otherwise>
										</c:choose>
									</td>
									<td style="vertical-align:middle;">
										<c:choose>
										<c:when test="${empty user.getEmail()}">
											<a href="javascript:void(0);" class="btn btn-info" onclick="$('#bindEmailModal').modal('show')">绑定</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" class="btn btn-danger" onclick="$('#unBindEmailModal').modal('show')">解绑</a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</tbody>
						</table>
	
					</div>
				</div>
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
	
<!-- 修改头像模态框 -->
<%@include file="/WEB-INF/view/user/changeAvatarModal.jsp" %>
<!-- 个人中心页面模态框 -->
<%@include file="/WEB-INF/view/user/myCenterModal.jsp" %>

<script>
//通过审核
function passJob(){
	swal({
		title : language=='zh_CN'?"确定通过该职位发布？":"Are you sure to post through the position?",
		text : '',
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : false
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/AdminPassJob",
			datatype: "json", 
			async:false,
			data:{
				"job_id":$('#passJob_id').val(),
				"passOpinion":$('#passOpinion').val()
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					swal({
						title: language=='zh_CN'?"操作成功":"Operate Successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						$("#viewJobPanel").hide();
						$("#passOpinionPanel").hide();
						$("#waitAuditJobPanel").show();
						SelectPage(page);
						$("#passOpinion").val("");
					});
					setTimeout(function () {
						$("#viewJobPanel").hide();
						$("#passOpinionPanel").hide();
						$("#waitAuditJobPanel").show();
						SelectPage(page);
						$("#passOpinion").val("");
					}, 2000);
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: r.errorMes,
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	});
}
</script>
</body>
</html>