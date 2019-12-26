package com.stsm.controller.staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class UpdateStaff
 */
@WebServlet("/admin/manage_info/staff/update")
public class UpdateStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStaff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StaffDao dao = new StaffDao();
		//获取员工id
		int StaffID = Integer.valueOf(request.getParameter("StaffID"));
		Staff staff =  dao.queryStaffByID(StaffID);
		request.setAttribute("staff", staff);
		request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/update.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
