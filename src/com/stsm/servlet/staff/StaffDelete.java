package com.stsm.servlet.staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class StaffDelete
 */
@WebServlet("/StaffDelete")
public class StaffDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StaffDao dao = new StaffDao();
		int StaffID = Integer.valueOf(request.getParameter("StaffID"));
		if(dao.deleteStaff(StaffID))
		{
			response.sendRedirect("/STSM/admin/manage_info/staff/info");
		}
		else 
		{
			System.out.println("出现未知错误");
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

}
