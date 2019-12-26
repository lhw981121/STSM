package com.stsm.servlet.staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class StaffUpdate
 */
@WebServlet("/StaffUpdate")
public class StaffUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
//		Date staff_last_in = Date.valueOf(request.getParameter("staff_last_in"));
//		Date staff_last_out = Date.valueOf(request.getParameter("staff_last_out"));
		
		//封装接收信息
		Staff staff = Packaging(id,staff_name,staff_number,staff_sex,staff_age,staff_position,staff_performance,staff_bonus,staff_house);
		
		StaffDao dao = new StaffDao();
		if(dao.updataStaff(staff))
		{
			response.sendRedirect("/STSM/admin/manage_info/staff/info");
			
		}else {
			System.out.println("修改信息失败");
			response.sendRedirect("/STSM/admin/manage_info/staff/info");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public Staff Packaging(int id,String staff_name,String staff_number,int staff_sex,int staff_age,int staff_position,
			double staff_performance,double staff_bonus,String staff_house)
	{
		Staff staff  = new Staff();
		staff.setID(id);
		staff.setName(staff_name);
		staff.setNumber( staff_number);
		staff.setSex(staff_sex);
		staff.setAge(staff_age);
		staff.setPosition(staff_position);
		staff.setPerformance(staff_performance);
		staff.setBonus(staff_bonus);
		staff.setHouse(staff_house);
		return staff;
	}
}
