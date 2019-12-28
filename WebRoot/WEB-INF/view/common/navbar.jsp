<%@ page language="java" import="java.util.*,java.net.URLDecoder,com.stsm.util.COMUtil" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- NAVBAR -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="brand">
  		<a href="/STSM/index"><img src="/STSM/public/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
   </div>
   <div class="container-fluid">
		<div class="navbar-btn">
			<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
		</div>
      <c:if test="${currentURL.indexOf(\"/message/\")!=-1 }">
		<form class="navbar-form navbar-left">
			<div class="button-group" style="margin-left:2cm">
				<a class="btn btn-primary" href="javascript:(void);" onclick="changeLanguage('en_US');">English</a>&emsp;
				<a class="btn btn-primary" href="javascript:(void);" onclick="changeLanguage('zh_CN');">简体中文</a>
			</div>
		</form>
		</c:if>
			<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-right">
				<!-- 当前时间 -->
				<li style="user-select:none"><a><i class="fa fa-clock-o"></i><span id="systemCurrentTime">${COMUtil.systemCurrentTime() }</span></a></li>
				<!-- 消息 -->
				<li class="dropdown">
					<a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
					<i class="lnr lnr-alarm"></i><span class="badge bg-danger" id="message_count_1"></span></a>
					<ul class="dropdown-menu notifications" id="message_menu"></ul>
				</li>
				<li class="dropdown">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="/STSM/public/images/user/avatar/${user.getID() }.jpg?t=<%=Math.random()%>" id="user_avatar_navbar" 
						onerror="this.src='/STSM/public/images/user/avatar/0.jpg';this.onerror=null" class="img-circle" alt="Avatar"/>
						<span>${user.getName() }</span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
	            </a>
	            <ul class="dropdown-menu">
	            
					<c:if test="${user.getType()==8 }">
						<li><a href="/STSM/admin" target="_self"><span><i class="fa fa-credit-card-alt"></i> 管理员</span></a></li>
					</c:if>

					<li><a href="/STSM/user/mycenter" target="_self"><i class="lnr lnr-user"></i> <span>个人中心</span></a></li>
					
					<c:choose>
					<c:when test="${currentURL.indexOf(\"/message/\")!=-1 }">
						<li><a href="/STSM/message" target="_self"><i class="lnr lnr-bubble"></i> <span>Message </span><span class="badge" id="message_count_2"></span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/STSM/message" target="_blank"><i class="lnr lnr-bubble"></i> <span>Message </span><span class="badge" id="message_count_2"></span></a></li>
					</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${currentURL.indexOf(\"/setting/\")!=-1 }">
						<li><a href="/STSM/setting" target="_self"><i class="lnr lnr-cog"></i> <span>设置</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:;"><i class="lnr lnr-cog"></i> <span>设置</span></a></li>
					</c:otherwise>
					</c:choose>
					
					<li><a href="/STSM/UserLogout"><i class="lnr lnr-exit"></i> <span>注销</span></a></li>
					
	      		</ul>
				</li>
			</ul>
    	</div>
	</div>
</nav>
<!-- END NAVBAR -->
