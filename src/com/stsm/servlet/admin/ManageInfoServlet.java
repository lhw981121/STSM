package com.stsm.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;
import com.stsm.util.COMUtil;

/**
 * Servlet implementation class ManageInfoServlet
 */
@WebServlet(urlPatterns = {
		"/StaffCreate",
		"/StaffUpdate",
		"/StaffDelete",
		
		})
public class ManageInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
		//员工新增操作
		case "/StaffCreate":{
			request.setCharacterEncoding("UTF-8");
			String staff_name = request.getParameter("staff_name");
			String staff_number = request.getParameter("staff_number");
			int staff_sex = Integer.valueOf(request.getParameter("staff_sex"));
			int staff_age = request.getParameter("staff_age")==null?0:Integer.valueOf(request.getParameter("staff_age"));
			int staff_position = request.getParameter("staff_position")==null?0:Integer.valueOf(request.getParameter("staff_position"));
			double staff_performance = COMUtil.isNull(request.getParameter("staff_performance"))?0:Double.valueOf(request.getParameter("staff_performance"));
			double staff_bonus = COMUtil.isNull(request.getParameter("staff_bonus"))?0:Double.valueOf(request.getParameter("staff_bonus"));
			String staff_house = request.getParameter("staff_house");
			
			Staff staff  = new Staff();
			staff.setName(staff_name);
			staff.setNumber( staff_number);
			staff.setSex(staff_sex);
			staff.setAge(staff_age);
			staff.setPosition(staff_position);
			staff.setPerformance(staff_performance);
			staff.setBonus(staff_bonus);
			staff.setHouse(staff_house);
			
			StaffDao dao = new StaffDao();
			//获取添加用户的Staff_id
			@SuppressWarnings("unused")
			int StaffID =  dao.createStaff(staff);
			
			//重定向到主页
			response.sendRedirect("/STSM/admin/manage_info/staff/info");
			break;
		}
		//员工修改操作
		case "/StaffUpdate":{
			request.setCharacterEncoding("UTF-8");
			int id = Integer.valueOf(request.getParameter("staff_id"));
			String staff_name = request.getParameter("staff_name");
			String staff_number = request.getParameter("staff_number");
			int staff_sex = Integer.valueOf(request.getParameter("staff_sex"));
			int staff_age = Integer.valueOf(request.getParameter("staff_age"));
			int staff_position = Integer.valueOf(request.getParameter("staff_position"));
			double staff_performance = Double.valueOf(request.getParameter("staff_performance"));
			double staff_bonus = Double.valueOf(request.getParameter("staff_bonus"));
			String staff_house = request.getParameter("staff_house");	
			
			Staff staff = new StaffDao().queryStaffByID(id);
			staff.setName(staff_name);
			staff.setNumber(staff_number);
			staff.setSex(staff_sex);
			staff.setAge(staff_age);
			staff.setPosition(staff_position);
			staff.setPerformance(staff_performance);
			staff.setBonus(staff_bonus);
			staff.setHouse(staff_house);
			
			StaffDao dao = new StaffDao();
			if(dao.updataStaff(staff))
			{
				response.sendRedirect("/STSM/admin/manage_info/staff/info");
				
			}else {
				System.out.println("修改信息失败");
				response.sendRedirect("/STSM/admin/manage_info/staff/info");
			}
			break;
		}
		//员工删除操作
		case "/StaffDelete":{
			StaffDao dao = new StaffDao();
			int staff_id = Integer.valueOf(request.getParameter("staff_id"));
			if(dao.deleteStaff(staff_id))
			{
				response.sendRedirect("/STSM/admin/manage_info/staff/info");
			}
			else 
			{
				System.out.println("出现未知错误");
				response.sendRedirect("/STSM/admin/manage_info/staff/info");
			}
			break;
		}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
