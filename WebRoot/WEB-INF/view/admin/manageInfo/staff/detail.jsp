<%@ page language="java" import="java.util.*,com.stsm.util.COMUtil" pageEncoding="utf-8"%>
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
	<div class="panel">
        <div class="panel-heading">
            <h3 class="panel-title">员工信息详情</h3>
        </div>
        <div class="panel-body">
            <table class="table table-bordered table-striped table-hover ">
					<!--调整格式 -->
	            <colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>
					<!--调整格式 -->
                <tbody>
						<!--工号 -->
                <tr>
                    <td>工号</td>
                    <td>${staff.number}</td>
                </tr>
						<!-- 姓名 -->
                <tr>
                    <td>姓名</td>
                    <td>${staff.name}</td>
                </tr>
						<!--性别 -->
                <tr>
                    <td>性别</td>
                    <c:if test="${staff.sex==1}">
                   		<td>男</td>
                    </c:if>
                     <c:if test="${staff.sex==2}">
                   		<td>女</td>
                    </c:if>
                     <c:if test="${staff.sex==0}">
                    		<td>未知</td>
                    </c:if>
                </tr>
						<!--年龄 -->
                <tr>
                    <td>年龄</td>
                    <c:choose>
							<c:when test="${staff.age>0}">
								<td>${staff.age}</td>
							</c:when>
							<c:otherwise>
								<td>未知</td>
							</c:otherwise>
						</c:choose>
                </tr>
					<!--职位 -->
                <tr>
                    <td>职位</td>
                    <c:choose>
							<c:when test="${staff.position==0}">
								<td>普通职员</td>
							</c:when>
							<c:when test="${staff.position==1}">
								<td>组长</td>
							</c:when>
							<c:when test="${staff.position==2}">
								<td>主管</td>
							</c:when>
							<c:when test="${staff.position==3}">
								<td>部门经理</td>
							</c:when>
							<c:when test="${staff.position==4}">
								<td>总监</td>
							</c:when>
							<c:when test="${staff.position==5}">
								<td>副总经理</td>
							</c:when>
							<c:when test="${staff.position==6}">
								<td>总经理</td>
							</c:when>
							<c:when test="${staff.position==7}">
								<td>执行董事</td>
							</c:when>
							<c:when test="${staff.position==8}">
								<td>副董事长</td>
							</c:when>
							<c:when test="${staff.position==9}">
								<td>董事长</td>
							</c:when>
							<c:otherwise>
								<td>未知</td>
							</c:otherwise>
						</c:choose>
                </tr>
					<!--业绩 -->
                <tr>
                    <td>业绩</td>
                    <c:choose>
                    	<c:when test="${staff.performance>0 }">
                    		<td>${staff.performance }</td>
                    	</c:when>
                    	<c:otherwise>
                    		<td>暂无业绩</td>
                    	</c:otherwise>
                    </c:choose>
                </tr>
						<!--奖金 -->
                <tr>
                    <td>奖金</td>
                    <c:choose>
                    	<c:when test="${staff.bonus>0 }">
                    		<td>${staff.bonus}</td>
                    	</c:when>
                    	<c:otherwise>
                    		<td>暂无奖金</td>
                    	</c:otherwise>
                    </c:choose>
                </tr>
						<!--家庭住址 -->
                 <tr>
                    <td>家庭住址</td>
                    <c:choose>
                    	<c:when test="${empty staff.house} ">
                    		<td>未知</td>
                    	</c:when>
                    	<c:otherwise>
                    		<td>${staff.house}</td>
                    	</c:otherwise>
                    </c:choose>
                </tr>
					<!--  最近考勤时间（上班） -->
                <tr>
                    <td>最近一次考勤时间(上班)</td>
                    <c:choose>
                    	<c:when test="${empty staff.lastIn} ">
                    		<td>未知</td>
                    	</c:when>
                    	<c:otherwise>
                     	<td>${staff.lastIn}</td>
                    	</c:otherwise>
                    </c:choose>
                </tr>  
                <!--  近一次考勤时间(下班) --> 
                 <tr>
                    <td>最近一次考勤时间(下班)</td>
                    <c:choose>
                    	<c:when test="${empty staff.lastOut} ">
                    		<td>未知</td>
                    	</c:when>
                    	<c:otherwise>
                    		<td>${staff.lastOut}</td>
                    	</c:otherwise>
                    </c:choose>
                </tr>  
                 <tr>
                    <td>添加时间</td>
                    <td>${staff.getCreated()}</td>
                </tr>    
                </tbody>
            </table>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-6">
                    <a  class="btn btn-primary" href="update?staff_id=${staff.ID}">修改</a>
                    <a class="btn btn-primary" href="#" onClick="history.back(-1)">返回</a>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- END 内容区域 -->

<!-- 页尾 -->
<%@include file="/WEB-INF/view/common/foot.jsp"%>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<%@include file="/WEB-INF/view/common/javaScript.jsp"%>
<!-- 自定义脚本 -->
</body>

</html>