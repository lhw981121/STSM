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
<!-- MAIN CONTENT -->
<div class="panel">
        <div class="panel-heading">
            <h3 class="panel-title">新增员工信息填写</h3>
        </div>
        <div class="panel-body">
            <form name="studentForm" class="form-horizontal" role="form" method="post" 
            action="/STSM/StaffCreate" onsubmit="return checkAll()" >
                
                <div class="form-group" id="student_number_class">
                    <label for="student_number" class="col-sm-2 control-label"><a class="text-danger">*</a>员工工号</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="staff_number" name="staff_number" 
                        placeholder="请输入员工工号（全为数字且长度为11）" value="" onchange="checkStaffnumber()"
                        oninput="Inputing(document.getElementById('staff_number_span'),document.getElementById('staff_number_class'))">
                    </div>
                </div>

                <div class="form-group" id="student_name_class">
                    <label for="student_name" class="col-sm-2 control-label"><a class="text-danger">*</a>姓名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="staff_name" name="staff_name" 
                        placeholder="请输入员工姓名" value="" onchange="checkStaff_name()"
                        oninput="Inputing(document.getElementById('staff_name_span'),document.getElementById('staff_name_class'))">
                    </div>
                </div>
                
                <div class="form-group" id="student_sex_class">
                    <label for="student_sex" class="col-sm-2 control-label"><a class="text-danger">*</a>性别</label>
                    <div class="col-sm-8">
                    	&emsp;<label><input id="staff_sex" type="radio" name="staff_sex" value="1" onchange="checkStaff_sex()">男</label>
		    			&emsp;<label><input id="staff_sex" type="radio" name="staff_sex" value="2" onchange="checkStaff_sex()">女</label>
						&emsp;<label><input id="staff_sex" type="radio" name="staff_sex" value="0" onchange="checkStaff_sex()">未知</label>
						<label class="control-label text-danger" for="staff_sex">&emsp;</label><br>
                    </div>
                </div>
                
                <div class="form-group" id="student_age_class">
                    <label for="student_age" class="col-sm-2 control-label"><a class="text-danger"></a>年龄</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="staff_age" name="staff_age" 
                        placeholder="请输入员工年龄" value="" onchange="checkStaff_age()"
                        oninput="Inputing(document.getElementById('staff_age_span'),document.getElementById('staff_age_class'))">
                    </div>
                </div>
                
                <div class="form-group" id="student_age_class">
                    <label for="student_age" class="col-sm-2 control-label"><a class="text-danger"></a>职位</label>
                    <div class="col-sm-8">
			                	&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="0" onchange="checkStaff_position()">普通职员</label>
					    			&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="1" onchange="checkStaff_position()">组长</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="2" onchange="checkStaff_position()">主管</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="3" onchange="checkStaff_position()">部门经理</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="4" onchange="checkStaff_position()">总监</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="5" onchange="checkStaff_position()">副总经理</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="6" onchange="checkStaff_position()">总经理</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="7" onchange="checkStaff_position()">执行董事</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="8" onchange="checkStaff_position()">副董事长</label>
									&emsp;<label><input id="staff_position" type="radio" name="staff_position" value="9" onchange="checkStaff_position()">董事长</label>
									<label class="control-label text-danger" for="staff_sex">&emsp;</label><br>
                    </div>
                </div>
                
                 <div class="form-group" id="student_age_class">
                    <label for="student_age" class="col-sm-2 control-label"><a class="text-danger"></a>业绩</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="staff_performance" name="staff_performance" 
                        placeholder="请输员工业绩" value="" onchange="checkStaff_performance()"
                        oninput="Inputing(document.getElementById('staff_performance_span'),document.getElementById('staff_performance_class'))">
                    </div>
                </div>
                
                 <div class="form-group" id="student_age_class">
                    <label for="student_age" class="col-sm-2 control-label"><a class="text-danger"></a>奖金</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="staff_bonus" name="staff_bonus" 
                        placeholder="请输员工奖金" value="" onchange="checkStaff_bonus()"
                        oninput="Inputing(document.getElementById('staff_bonus_span'),document.getElementById('staff_bonus_class'))">
                    </div>
                </div>
					
					<div class="form-group" id="student_age_class">
                    <label for="student_age" class="col-sm-2 control-label"><a class="text-danger"></a>家庭住址</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="staff_house" name="staff_house" 
                        placeholder="请输员工家庭住址" value="" onchange="checkStaff_house()"
                        oninput="Inputing(document.getElementById('staff_house_span'),document.getElementById('staff_house_class'))">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-6">
                        <button type="submit" class="btn btn-primary" id="addBtn">提交</button>
                        <a class="btn btn-primary" href="#" onClick="history.back(-1)">返回</a>
                    </div>
                </div>

            </form>
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
<!-- 自定义脚本 -->


</body>
</html>