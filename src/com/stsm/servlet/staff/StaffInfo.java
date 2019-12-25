package com.stsm.servlet.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class StaffInfo
 */
@WebServlet("/StaffInfo")
public class StaffInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Staff> staffinfo  = new ArrayList<Staff>();
		StaffDao dao = new StaffDao();
		staffinfo = dao.getStaff();
		request.setAttribute("staffinfo", staffinfo);
		System.out.println("StaffInfo");
		request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/info.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
